package strings.anagram;

public class EfficientSolutionUsingArrayOf26Size {

    public static void main(String args[]){
        // String s1 = "listen";
        // String s2 = "slient";
        String s1 = "abdfd";
        String s2 = "adfda";
        int n1 = s1.length();
        int n2 = s2.length();

        System.out.println("are the two strings anagram ? "+isAnagram(s1.toLowerCase(), s2.toLowerCase(), n1, n2));
    }

    public static boolean isAnagram(String s1, String s2, int n1, int n2){

        if(n1!=n2){
            return false;
        }

        int arr[] = new int[26];

        for(int i=0; i<n1; i++){
            arr[s1.charAt(i) - 'a']++;
        }

        for(int i=0; i<n2; i++){
            arr[s2.charAt(i) -'a']--;
        }

        for(int i=0; i<26; i++){
            if(arr[i]!=0){
                return false;
            }
        }

        return true;

    }
    
}
