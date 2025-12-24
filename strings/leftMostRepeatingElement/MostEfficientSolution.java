package strings.leftMostRepeatingElement;

/*
 * IDEA : Traverse from right to left and maintain visited boolean array if found a repeating element then update the result
 * Trick : here the leftmost repeating element will update at the end so no need to maintain anything
 */

public class MostEfficientSolution {

    public static int CHAR = 256;

    public static void main(String args[]){
        String s = "abbcc";
        System.out.println("The leftmost repeating character element in given string is present at idx : "+findLeftMostRepeatingCharacter(s, s.length()));
    }
    

    public static int findLeftMostRepeatingCharacter(String s, int n){
        boolean visited[] = new boolean[CHAR];

        int res = -1;

        for(int i = n-1; i>=0; i--){
            if(visited[s.charAt(i)]){
                res = i;
            }else {
                visited[s.charAt(i)] = true;
            }
        }


        return res;
    }
}
