package bitsMagic.oneOddOccurrence;

/*
 * Time complexity : O(n*n)
 * Auxiliary space : O(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int n[] = {4,3,4,4,4,5,5};
        System.out.println("The number that occurred odd number of times is : "+findOddOccurrence(n));
    }

    public static int findOddOccurrence(int n[]){
        int res = -1;
        for(int i = 0; i<n.length; i++){
            int temp = 0;
            for(int j=0; j<n.length; j++){
                if(n[i] == n[j]){
                    ++temp;
                }
            }
            if(temp%2==1){
                return n[i];
            }
        }

        return res;
    }
    
}
