package mathematics.gcdOrHcf;

/*
 * Time Complexity : O(log(min(a,b)))
 * Auxiliary Space : O(log(min(a,b)))
 */
public class OptimizedEuclideanAlgorithm {

    public static void main(String args[]){
        int a = 4, b = 6;
        System.out.println("GCD of two given numbers is : "+findGCD(a,b));
    }

    public static int findGCD(int a, int b){

        if(b==0){
            return a;
        }

        return findGCD(b, a%b);
    }
    
}
