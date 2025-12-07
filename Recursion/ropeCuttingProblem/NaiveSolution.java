package Recursion.ropeCuttingProblem;

public class NaiveSolution {

    public static void main(String args[]){
        int n = 5, a = 2, b = 5, c = 1;
        System.out.println("Max cuts that we can make is : "+findMaxCut(n, a, b, c, 0));
    }


    public static int findMaxCut(int n, int a, int b, int c, int l){

        if(n==0){
            return l;
        }

        if(n < 0){
            return -1;
        }

        return Math.max(
            Math.max(
                findMaxCut(n-a, a, b, c, l+1),
                findMaxCut(n-b, a, b, c, l+1)
            ),
            findMaxCut(n-c, a, b, c, l+1)
        );

    }
    
}
