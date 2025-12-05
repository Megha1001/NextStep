package mathematics.lcm;

/*
 * Time complexity : O(a*b)
 * Auxiliary Space : Theta(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int a = 3, b = 7;
        System.out.println("LCM of given number is : "+findLCM(a, b));
    }

    public static int findLCM(int a, int b){
        int res = a*b;

        //base cases
        if(a%b==0){
            return a;
        }

        if(b%a == 0){
            return b;
        }


        for(int i = 2; i < a*b; i++){
            if(i%a == 0 && i%b == 0 ){
                return i;
            }
        }

        return res;
    }
    
}
