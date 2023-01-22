// Created by: Thomas Conner

/* 
Elementary Cellular Automaton created without using arrays

This program is incomplete as I will stil need to implement "wrapping" using arithmetic operations

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
        
        //Probably not the best way to perform rudimentary input validation, but input validation
        //was sort of an after-thought with this program
        
        
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
    
    //Calculates the binary string representing the next row in the cellular automaton
    
    public static int calcNextRow(int prev, int rule){
        int cur = 0;
        
        for(int i = 0; i < 31; i++){
            cur=cur<<1;
            if((rule&(1<<((prev&(7<<29))>>>29)))!=0)
                cur++;
            int temp = prev&(3<<30)>>>30;

            prev=prev<<1 + temp;
        }
        return cur;
    }
    
    //Draws the given bit string in ascii characters
    
    public static void drawRow(int row){
        for(int i = 31; i >=0; i--){
            if((row&(1<<i)) == 1<<i){
                System.out.print("$");
            }
            else{
                System.out.print(".");
            }
        }
        System.out.println();
    }
}
