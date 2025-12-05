package mathematics.lcm;

/*
 * 
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
