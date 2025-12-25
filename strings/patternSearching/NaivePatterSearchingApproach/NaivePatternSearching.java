package strings.patternSearching.NaivePatterSearchingApproach;

/*
 * TIME COMPLEXITY : O(m * (n-m+1))
 */

public class NaivePatternSearching {
    
    public static void main(String arg[]){
        String txt = "ABCABCD";
        String pattern = "ABCD";

        System.out.println("Given pattern is present at index in given txt :- ");
        findPattern(txt, pattern);
    }

    public static void findPattern(String txt, String pattern){

        int m = pattern.length();
        int n = txt.length();

        for(int i=0; i<=n-m ; i++){
            int j=0;
            for(; j<m; j++){
                if(txt.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }
            if(j==m){
                System.out.print(i+" ");
            }
        }

    }

}
