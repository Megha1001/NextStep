package Recursion.ropeCuttingProblem;

public class NaiveSolution {

    public static void main(String args[]){
        int n = 9, a = 2, b = 2, c = 2;
        System.out.println("Max cuts that we can make is : "+findMaxCut(n, a, b, c));
    }


    public static int findMaxCut(int n, int a, int b, int c){

        if(n==0){
            return 0;
        }

        if(n < 0){
            return -1;
        }

        int res =Math.max(
            Math.max(
                findMaxCut(n-a, a, b, c),
                findMaxCut(n-b, a, b, c)
            ),
            findMaxCut(n-c, a, b, c)
        );

        if(res == -1){
                return -1;
        }

        //Only add 1 to the valid cases
        return res + 1;

    }
    
}
