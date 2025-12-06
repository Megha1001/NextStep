package bitsMagic.twoOddOccurrence;

/*
 * Time Complexity : O(N*N)
 * Auxiliary Space : Theta(1)
 */

public class NaiveSolution {

    public static void main(String args[]){
        int n[] = {5,6,10,6,10,6,3,3};
        System.out.println("Numbers that occurred odd number of times are :- ");
        findOccurrence(n);
    }

    public static void findOccurrence(int n[]){

        boolean [] visited = new boolean[n.length];

        for(int i=0; i<n.length; i++){
            if(visited[i]) continue;
            int temp=0;
            for(int j=0; j<n.length; j++){
                if(n[i] == n[j]){
                    ++temp;
                    visited[j] = true;
                }
            }

            if(temp%2 != 0){
                System.out.print(n[i]+" ");
            }
        }

    }
    
}
