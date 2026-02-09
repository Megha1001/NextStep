package dynamic_Programming.knapSack0_1;

public class RecursiveSolutionKnapSack_0_1 {

    public static void main(String args[]){
        int w[] = {5,4,6,3};
        int v[] = {10,40,30,50};
        int W = 10;

        System.out.println("maximum value that can be make from given input is : "+knapSack(w, v, W, w.length));
    }

    public static int knapSack(int[]w, int []v, int W, int n){

        //base case
        if(W == 0 || n == 0){
            return 0;
        }

        if(w[n-1] > W){
            return knapSack(w, v, W, n-1);
        }else{
            return Math.max(knapSack(w, v, W, n-1), v[n-1]+ knapSack(w, v, W - w[n-1], n-1));
        }

    }
    
}
