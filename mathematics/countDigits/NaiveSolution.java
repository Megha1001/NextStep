package mathematics.countDigits;

public class NaiveSolution {

    public static void main(String args[]){

        int input = 9235;
        System.out.println("Num of digits in input is : "+countDigits(input));
    }


    public static int countDigits(int n){
        int res = 0;

        while(n!=0){
            ++res;
            n = n/10;
        }

        return res;
    }
    
}
