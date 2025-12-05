package mathematics.lcm;

public class NaiveSolution2 {

    public static void main(String args[]){
        int a = 3, b = 7;
        System.out.println("LCM of given number is : "+findLCM(a, b));
    }


    public static int findLCM(int a, int b){
        int res = Math.max(a,b);

        while(true){
            if(res%a == 0 && res%b == 0){
                return res;
            }
            ++res;
        }
    }
    
}
