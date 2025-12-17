package searching.squareRoot;

/*
 * TIME COMPLEXITY : O(logN)
 * Auxiliary Space : O(1)
 */

public class EfficientSolutionUsingBinarySearch {

    public static void main(String args[]){
        int x = 26;
        System.out.println("The square root of given number is : "+findSqrRoot(x));
    }

    public static int findSqrRoot(int x){
        int l = 1, h = x, ans = -1;

        while(l<=h){
            int m = l +(h-l)/2;
            int mSq = m*m;
            
            if(mSq == x){
                return m;
            }
            else if (mSq > x){
                h = m-1;
            }
            else {
                l = m+1;
                ans = m;
            }
        }

        return ans;
    }
    
}
