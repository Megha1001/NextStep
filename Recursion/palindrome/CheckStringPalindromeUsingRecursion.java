package Recursion.palindrome;

public class CheckStringPalindromeUsingRecursion {

    public static void main(String args[]){
        String s = "abbcbba";
        System.out.println("is the input string is palindrome : ? "+isPalindrome(s,0,s.length()-1));
    }


    public static boolean isPalindrome(String s, int start, int end){

        if(start >= end){
            return true;
        }

        return s.charAt(start) == s.charAt(end) && isPalindrome(s, start+1, end-1);

    }
    
}
