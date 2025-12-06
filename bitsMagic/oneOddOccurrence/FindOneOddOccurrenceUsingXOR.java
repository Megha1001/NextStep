package bitsMagic.oneOddOccurrence;

public class FindOneOddOccurrenceUsingXOR {

    public static void main(String args[]){
        int n[] = {4,3,4,4,4,5,5};
        System.out.println("The number that occurred odd number of times is : "+findOddOccurrence(n));
    }


    public static int findOddOccurrence(int n[]){
        int res=0;
        for(int i=0; i<n.length; i++){
            res = res ^ n[i];
        }

        return res;
    }

    
}
