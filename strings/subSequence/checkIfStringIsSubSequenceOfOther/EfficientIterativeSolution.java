package strings.subSequence.checkIfStringIsSubSequenceOfOther;

/*
 * TIME COMPLEXITY : O(N+M)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientIterativeSolution {

    public static void main(String args[]){
        String s1 = "meghaverma";
        String s2 = "mavra";

        System.out.println("is s2 is subsequence of s1 ? "+ isSubSequence(s1, s2));
    }

    public static boolean isSubSequence(String s1, String s2){
        int i=0; //for s1
        int j=0; //for s2;
        int n1 = s1.length();
        int n2 = s2.length();

        while(i<n1 && j<n2){
            if(s1.charAt(i) == s2.charAt(j)){
                ++j;
            }
            ++i;
        }

        return j==n2;
    }
    
}
