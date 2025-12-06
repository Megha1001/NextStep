package bitsMagic.powerOfTwo;

/*
 * Time Complexity : Theta(1)
 * Auxiliary Complexity : Theta(1)
 */
public class EfficientSolutionWithSingleLineExp {

    public static void main(String args[]){
        int n = 16;
        System.out.println("is given number is power of 2 ? "+isPowerOfTwo(n));
    }

    public static boolean isPowerOfTwo(int n){
        //base condition 
        if(n==0){
            return false;
        }
        return (n & (n-1)) == 0;
    }
    
}
