.data

One: .asciiz "$"
Zero: .asciiz "."
Endl: .asciiz "\n"
PromptForRule: .asciiz "Enter Rule (0-255): "
PromptForNumGen: .asciiz "Eneter Num Generations: "

.text

#Disgusting, vile, rotten code
#It works though

la $a0, PromptForRule
li $v0, 4
syscall

li $v0, 5
syscall               #Read input and move to reg $t0
move $s0, $v0

la $a0, PromptForNumGen
li $v0, 4
syscall

li $v0, 5
syscall               #Read input and move to reg $t0
move $s2, $v0

#li $s0, 30	# Rule
li $s1, 65536	# cur = 1<<16
#li $s2, 20	#numGens = 20

li $s3, 0
jal PrintRow
ForNumGen:
beq $s3, $s2, End
jal CalcNextRow
move $s1, $v0
jal PrintRow
addi $s3, $s3, 1
j ForNumGen

End:
li $v0, 10
syscall

CalcNextRow:

li $t2, 0		#cur
move $t5, $s1		#prev

andi $t4, $t5, -1073741824 	#prev&(3<<30)
srl $t4, $t4, 30		#(prev&(3<<30))>>>30
andi $t0, $t5, 1		#prev&1
sll $t0, $t0, 2			#prev&1 << 2

add $t4, $t4, $t0		#(prev&(3<<30))>>>30) + ((prev&1) << 2)
and $t4, $t4, $s0
beq $t4, $0, NoWrap
addi $t2, $t2, 1
NoWrap:

li $t0, 0
li $t1, 31
calcForEveryBit:
beq $t0, $t1, EndCalcForEveryBit

sll $t2, $t2, 1

li $t4, 1
andi $t3, $t5, -536870912	#prev&(7<<29) EX: [XXX]0101101
srl $t3, $t3, 29		#((prev&(7<<29))>>29)
sllv $t3, $t4, $t3	#1<<((prev&(7<<29))>>>29)
and $t3, $t3, $s0	#rule&(1<<((prev&(7<<29))>>>29))


beq $t3, 0, NotInRule	
addi $t2, $t2, 1
NotInRule:
andi $t4, $t5, -2147483648
srl $t4, $t4, 31
#sll $t2, $t2, 1
sll $t5, $t5, 1
add $t5, $t5, $t4

addi $t0, $t0, 1
j calcForEveryBit
EndCalcForEveryBit:

move $v0, $t2

jr $ra

PrintRow:

li $t0, 31
li $t1, 0
move $t3, $s1

PrintForEveryBit:

blt $t0, $t1, EndPrintForEveryBit
addi $t0, $t0, -1

li $t2, 1
sllv $t2, $t2, $t0 
and $t4, $t3, $t2

bne $t4, $t2, Zero1
li $v0, 4
la $a0, One
syscall
j PrintForEveryBit
Zero1:
li $v0, 4
la $a0, Zero
syscall
j PrintForEveryBit
EndPrintForEveryBit:

li $v0, 4
la $a0, Endl
syscall

jr $ra
