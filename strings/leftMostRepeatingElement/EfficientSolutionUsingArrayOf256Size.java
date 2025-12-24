package strings.leftMostRepeatingElement;

public class EfficientSolutionUsingArrayOf256Size {
    
    public static int CHAR = 256;

    public static void main(String arr[]){
        String s = "abbcc";
        System.out.println("The leftmost repeating character element in given string is present at idx : "+findLeftMostRepeatingCharacter(s, s.length()));
    }

    public static int findLeftMostRepeatingCharacter(String s, int n){

        int arr[] = new int[CHAR];

        for(int i=0; i<n; i++){
            arr[s.charAt(i)]++;
        }

        for(int i=0; i<n; i++){
            if(arr[s.charAt(i)] > 1){
                return i;
            }
        }

        return -1;
    }
    
}
