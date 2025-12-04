/*
 * Question : Find whether the number is palindrome or not ?
 * 
 * Time Complexity : Theta(d), where d is length of digit
 * Auxiliary Space : Theta(1)
 */

package mathematics.palindrome;

public class NaiveSolution {

    public static void main(String args[]){
        int n = 1234321;
        System.out.println("is the given number palindrome  : ? "+isPalindrome(n));
    }
    

    public static boolean isPalindrome(int n){
        int rev = 0;
        int temp = n;
        while(n!=0){
            rev = rev*10 + (n%10);
            n = n/10;
        }

        return temp == rev;
    }

}
