import java.util.Scanner;
import java.lang.System;

class IfElse{
    public static void main(String[] args){
        // System.out.println("He");
        boolean raining = false;
        if (raining){
            System.out.println("Hey Buddy! It's raining.");
        } else{
            System.out.println("What an amazing climate !");
        }

        Scanner scan = new Scanner(System.in);
        int val = scan.nextInt();
        System.out.println(val>5);
        if (val < 5){
            System.out.println("Was Lesser than 5");
        } else if (val == 5){
            System.out.println("Was 5");
        } else{
            System.out.println("Was Greater than 5");
        }

        if (val % 3 == 0 && val % 5 == 0){
            System.out.println("FizzBuzz");
        } else if (val % 3 == 0){
            System.out.println("Fizz");
        } else if (val % 5 == 0){
            System.out.println("Buzz");
        } else{
            System.out.println((char[])null);
        }
    }

}