package queue.GenerateNumbersWithGivenDigits;

/*
 * Question : Given a number n, print first n numbers(in increasing order) such that all the numbers have digits in set {5,6}
 * For example : if n=10;
 *  5,6,55,56,65,66,555,556,565,566 etc
 * 
 * IDEA : Use Queue and store numbers in form of string
 *  1. store 5, 6 
 *  2. then poll 5 and print and appent 5,6 to it then again push to queue
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(N)
 *
 */

import java.util.Queue;
import java.util.ArrayDeque;

public class EfficientSolutionUsingQueue {

    public static void main(String args[]){
        int n = 10;
        // int n = 4;
        System.out.println("The numbers are  ");
        printNumbers(n);
    }

    public static void printNumbers(int n){
        Queue<String> q = new ArrayDeque<>(); //string is intentional

        q.offer("5");
        q.offer("6");

        for(int i=0; i<n; i++){
            String curr = q.poll();

            System.out.print(curr +" ");
            q.offer(curr + "5");
            q.offer(curr + "6");
        }

    }
    
}
