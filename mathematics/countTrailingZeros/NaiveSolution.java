package mathematics.countTrailingZeros;

public class NaiveSolution {

    public static void main(String args[]){
        int n = 1002000;
        System.out.println("Number of trailing zeros are : "+findNumOfTrailingZeros(n));
    }

    public static int findNumOfTrailingZeros(int n){
        int res = 0;
        while(n > 0){
            if(n%10 == 0){
                ++res;
                n = n/10;
            }else{
                break;
            }
        }

        return res;
    }
    
}
