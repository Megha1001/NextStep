package Recursion.sumOfDigits;

public class SumOfDigitsUsingRecursion {

    public static void main(String arr[]){
        int n = 9987;
        System.out.println("SUm of digits in given number is : "+findSum(n));
    }

    public static int findSum(int n){

        if(n==0){
            return 0;
        }

        return (n%10) + findSum(n/10);
    }
    
}
