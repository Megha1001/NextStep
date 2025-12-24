package strings.subSequence.checkIfStringIsSubSequenceOfOther;

/*
 * TIME COMPLEXITY : O(N+M)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientRecursiveSolution {

    public static void main(String args[]){
        String s1 = "meghaverma";
        String s2 = "mavra";

        System.out.println("is s2 is subsequence of s1 ? "+ isSubSequence(s1, s2, s1.length(), s2.length()));
    }

    public static boolean isSubSequence(String s1, String s2, int n, int m){
        
        if(m==0){
            return true;
        }

        if(n==0){
            return false;
        }

        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return isSubSequence(s1, s2, n-1, m-1);
        }else{
            return isSubSequence(s1, s2, n-1, m);
        }

    }
    
}
