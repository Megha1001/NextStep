package bitsMagic.twoOddOccurrence;


/*
 * Idea : Find XOR of all the elements : 
 *  x = 5^6^10^6^10^6^3^3
 *    = 5^6
 *    = 3
 * How do we find the number 5 and 6 from value 3
 *  A set bit in 3 means bit is having different values in 5 and 6
 * 
 * Find the number for which LSB that is set in the array - 5 ^ 3 ^3 = this will give us one of the odd occurrence number
 * 
 * Use x & (~(x-1))
 * It finds a number which has only 1 bit set , and the set bit corresponds to the last set bit of x.
 */

public class EfficientSolution {

    public static void main(String args[]){
        int n[] = {1,6,5,6,6,1};
        System.out.println("Numbers that occurred odd number of times are :- ");
        oddOccurrence(n);
    }

    public static void oddOccurrence(int n[]){

        //find the XOR of all the numbers
        int x = 0;
        for(int i = 0; i < n.length; i++){
            x ^= n[i];
        }

        int k = x &(~(x-1));
        int res1 = 0, res2 = 0;
        for(int i = 0; i < n.length; i++){
            if((n[i] & k) != 0){
                res1 = res1 ^ n[i];
            }else {
                res2 = res2 ^ n[i];
            }
        }

        System.out.println(res1 +" "+res2);

    }
    
}
