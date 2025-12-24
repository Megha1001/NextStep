package strings.leftmostNonRepeatingElement;

/*
 * TIME COMPLEXITY : O(N), where N is length of string
 */

public class EfficientSolutionUsingVisitedArray {

    public static int CHAR = 256;

    public static void main(String args[]){
        String s = "abbcc";
        System.out.println("The leftmost non repeating character element in given string is present at idx : "+findLeftMostNonRepeatingCharacter(s, s.length()));
    }
    
    public static int findLeftMostNonRepeatingCharacter(String s, int n){
        boolean visited[] = new boolean[CHAR];

        int res = -1;

        for(int i=n-1; i>=0; i--){
            if(!visited[s.charAt(i)]){
                res = i;
                visited[s.charAt(i)] = true;
            }
        }

        return res;

    }

}
