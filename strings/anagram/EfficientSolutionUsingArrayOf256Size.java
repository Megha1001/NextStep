package strings.anagram;

/*
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolutionUsingArrayOf256Size {

    public static void main(String args[]){
        String s1 = "listen";
        String s2 = "slient";
        // String s1 = "abdfd";
        // String s2 = "adfda";
        int n1 = s1.length();
        int n2 = s2.length();

        System.out.println("are the two strings anagram ? "+isAnagram(s1, s2, n1, n2));
    }

    public static boolean isAnagram(String s1, String s2, int n1, int n2){

        if(n1!=n2){
            return false;
        }

        int arr[] = new int[256];

        for(int i=0; i<n1; i++){
            arr[s1.charAt(i)]++;
            arr[s2.charAt(i)]--;
        }

        for(int i=0; i<256; i++){
            if(arr[i]!=0){
                return false;
            }
        }

        return true;

    }
    
}
