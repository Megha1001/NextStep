package mathematics.gcdOrHcf;

public class EuclideanAlgo {

    public static void main(String args[]){
        int n1 = 4, n2 = 6;
        System.out.println("GCD of given  numbers are : "+findGCD(n1, n2));
    }

    public static int findGCD(int n1, int n2){

        while(n1 != n2){
            if(n1 > n2){
                n1 = n1 - n2;
            }else {
                n2 = n2 - n1;
            }
        }

        return n1;
    }
    
}
