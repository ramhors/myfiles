package com.harcker.practice;

import java.util.List;

public class Main {
	public static void main(String[] args) {
	 int N = 9;
	 
	 for(int i =1; i <+N; i++){
         if(i%3 ==0 && i%5 ==0){
             System.out.println("FizzBuzz");
         }
         else if (i%3==0) {
        	 System.out.println("Fizz"); 
         }else if(i%5 ==0) {
        	 System.out.println("Buzz");
         }else {
        	 System.out.println(i);
         }
	 }
	}	
}
