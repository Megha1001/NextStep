package bitsMagic.KthBitIsSetOrNot;

/*
 * Time Complexity : O(1)
 * Auxiliary Space : O(1)
 */
public class FindKthBitSetUsingLeftShiftOperator {

    public static void main(String args[]){
        int n = 7, k =2;

        System.out.println("is Kth bit set in given number n "+isKthBitSet(n, k));
    }

    public static boolean isKthBitSet(int n , int k){
        return (n & (1<<(k-1))) != 0;  // ((n>>(k-1)) & 1) !=0
    }
    
}
