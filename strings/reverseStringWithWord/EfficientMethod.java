package strings.reverseStringWithWord;

/*
 * IDEA : 
 * 1. Reverse the words first
 * 2. reverse the whole string
 * 
 * For Example : "Welcome to This code"
 * Output : "code this to welcome"
 */

public class EfficientMethod {
    
    public static void main(String args[]){
        String s = "Welcome to This code";

        //convert to character array
        char[] c = s.toCharArray();

        //reverse string
        reverseString(c, c.length);

        //print string
        System.out.println("The reversed string is : "+ new String(c));

    }

    public static void reverseString(char[] c, int n){

        int start = 0;

        for(int end=0; end<n; end++){
            if (c[end] == ' '){ //indicating previous word is ended
                reverse(c, start, end-1);
                start = end+1;
                //we are not changing ' ' (space) so it will remain as is
            }
        }

        //for last word
        reverse(c, start, n-1);
        
        //reverse whole string
        reverse(c, 0, n-1);

    }

    public static void reverse(char[] c, int low, int high){

        while(low < high){
            char temp = c[low];
            c[low] = c[high];
            c[high] = temp;

            ++low;
            --high;
        }

    }

}
