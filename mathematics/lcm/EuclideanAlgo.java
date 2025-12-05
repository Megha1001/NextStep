package mathematics.lcm;

/*
 * Time Complexity : Same as euclidean algo for gcd : O(log(min(a,b)))
 * Auxilary Space : Same as euclidean algo for gcd : O(log(min(a,b)))
 */

public class EuclideanAlgo {

    public static void main(String args[]){
        int a = 7, b = 21;
        System.out.println("LCM of given number is : "+findLCM(a, b));
    }

    public static int findLCM(int a, int b){
        return (a*b)/findGCD(a,b);
    }

    public static int findGCD(int a, int b){

        if(b==0){
            return a;
        }

        return findGCD(b, a%b);

    }
    
}
