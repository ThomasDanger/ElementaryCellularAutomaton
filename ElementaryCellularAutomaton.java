// Created by: Thomas Conner

/* 
Elementary Cellular Automaton created without using arrays

This program is incomplete as I will stil need to implement "wrapping" using arithmetic operations

At first I believed I had done this without any arithmetic operations, but obviously the for-loops
have arithmetic operations, so I was wrong.

FOR STUDENTS:
If you are a student, do NOT copy and paste this code on a programming assignment. Your
teacher/professor will not expect you to use bitwise functions like this.

YOU WILL GET CAUGHT CHEATING

*/

package com.mycompany.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int rule = -1;
        
        while(rule < 0 || rule > 255){
            System.out.print("Enter rule (0-255): ");
            rule = scan.nextInt();
        }
        
        int numGens = -1;
        
        while(numGens<=0){
            System.out.print("Enter num generations: ");
            numGens = scan.nextInt();
        }
        
        int cur = 1<<16; //This makes it easier to change starting pos intead of putting an int literal
        
        for(int i = 0; i <= numGens; i++){
            drawRow(cur);
            cur = calcNextRow(cur, rule);
        }
        scan.close();
    }
    
    public static int calcNextRow(int prev, int rule){
        int cur = 0;
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 8; j++){
                if((j^(prev&7))==0){
                    if((1&(rule>>j))!=0)
                        cur++;
                    break;
                }
            }
            cur=cur<<1;
            prev=prev>>1;
        }
        return cur>>1;
    }
    
    public static void drawRow(int row){
        for(int i = 0; i < 32; i++){
            if((row&1) == 1){
                System.out.print("$");
            }
            else{
                System.out.print("`");
            }
            row=row>>1;
        }
        System.out.println();
    }
}
