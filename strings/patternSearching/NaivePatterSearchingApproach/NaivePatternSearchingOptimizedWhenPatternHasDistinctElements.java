package strings.patternSearching.NaivePatterSearchingApproach;

/*
 * TIME COMPLEXITY : Theta(N)
 */

public class NaivePatternSearchingOptimizedWhenPatternHasDistinctElements {

    public static void main(String arg[]){
        String txt = "ABCABCD";
        String pattern = "ABCD";

        System.out.println("Given pattern is present at index in given txt :- ");
        findPattern(txt, pattern);
    }

    public static void findPattern(String txt, String pat){
        int n = txt.length();
        int m = pat.length();

        for(int i=0; i<= n-m; ){
            int j=0;
            for(; j<m; j++){
                if(txt.charAt(i+j) != pat.charAt(j)){
                    break;
                }
            }

            if(j==m){
                System.out.print(i+ " ");
            }

            //OPTIMIZATION
            if(j==0){
                ++i;
            }
            else {
                i = i+j;
            }

        }
    }
    
}
