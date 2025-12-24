package strings.palindromeCheck;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolution {

    public static void main(String args[]){
        // String s = "ABCDCBA";
        // String s = "ABCCBA";
        String s = "ABCA";
        System.out.print("Is the given string palindrome ? "+isPalindrome(s));
    }


    public static boolean isPalindrome(String s){
        int i=0;
        int j=s.length()-1;

        while(i<=j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            ++i;
            --j;
        }

        return true;

    }
    
}
