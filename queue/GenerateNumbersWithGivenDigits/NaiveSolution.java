package queue.GenerateNumbersWithGivenDigits;

/*
 * Question : Given a number n, print first n numbers(in increasing order) such that all the numbers have digits in set {5,6}
 * For example : if n=10;
 *  5,6,55,56,65,66,555,556,565,566 etc
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int n = 10;
        int n = 4;
        System.out.println("The numbers are  ");
        printNumbers(n);
    }

    public static void printNumbers(int n){
        int count =1;
        System.out.print(5+ " ");
        int temp = 5;


        while(count!=n){
            ++temp;
            int temp2 = temp;

            while(temp2!=0){
                if(temp2%10 !=5 && temp2%10!=6){
                    break;
                }
                temp2 = temp2/10;
            }

            if(temp2==0){
                System.out.print(temp+ " ");
                ++count;
            }

        }


    }
    
}
