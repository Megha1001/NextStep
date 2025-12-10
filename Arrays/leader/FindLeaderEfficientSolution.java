package Arrays.leader;

public class FindLeaderEfficientSolution {

    public static void main(String args[]){
        int arr[] = {7,10,4,3,6,5,2};
        System.out.println("Leaders : ");
        findLeaders(arr, arr.length);
    }
    

    public static void findLeaders(int arr[], int n){
        //last element will always be a leader
        int currLeader = arr[n-1];
        System.out.print(" "+ currLeader+" ");

        for(int i=n-1; i >0; i--){
            if(arr[i-1] > currLeader){
                currLeader = arr[i-1];
                System.out.print(" "+ currLeader+" ");
            }
        }

    }
    
}
