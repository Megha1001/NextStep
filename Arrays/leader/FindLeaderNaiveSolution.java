package Arrays.leader;

/*
 * Time COmplexity : Theta(N*N)
 * Auxiliary Space : Theta(1)
 */

public class FindLeaderNaiveSolution {

    public static void main(String args[]){
        int arr[] = {7,10,4,3,6,5,2};
        System.out.println("Leaders : ");
        findLeaders(arr, arr.length);
    }
    

    public static void findLeaders(int arr[], int n){
        //last element will always be a leader

        for(int i=0; i<n-1; i++){
            boolean isLeader = true;
            for(int j=i+1; j<n; j++){
                if(arr[i] < arr[j]){
                    isLeader = false;
                }
            }
            if(isLeader){
                System.out.print(" "+arr[i]+" ");

            }
        }

        System.out.print(" "+arr[n-1]+" ");

    }

}
