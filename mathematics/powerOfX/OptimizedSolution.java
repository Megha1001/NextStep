package mathematics.powerOfX;

public class OptimizedSolution {

    public static void main(String args[]){
        int x = 2, n = 10;
        System.out.println("x^n : "+findPower(x,n));
    }

    public static int findPower(int x, int n){

        if(n==0){
            return 1;
        }

        int temp = findPower(x, n/2);
        temp = temp * temp;

        if(n%2 == 0){
            return temp;
        }else {
            return temp * x;
        }

    }
    
}
