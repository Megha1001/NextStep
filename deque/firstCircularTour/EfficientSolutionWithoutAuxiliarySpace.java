package deque.firstCircularTour;


/*
 * Helpful Link : https://youtu.be/xmJZSYSvgfE?t=538
 * 
 * IDEA : 1. If curr_petrol becomes -ve at Pi then none of the petrol pumps from p0 to pi can be the solution
 * 
 * Pi indicates amount of petrol need to reach Pi+1 from p0.
 * Pn-1 indicates amount of petrol need to reach p0
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(1)
 */

public class EfficientSolutionWithoutAuxiliarySpace {

    public static void main(String args[]){
        int petrol[] = {4,8,7,4};
        int dist[] = {6,5,3,5};
        // int petrol[] = {4,8};
        // int dist[] = {5,6};

        // int petrol[] = {8,9,4};
        // int dist[] = {5,10,12};

        System.out.println("The possible petrol pump from where we start is : "+findPetrolPump(petrol, dist, petrol.length));
    }


    public static int findPetrolPump(int petrol[], int dist[], int n){
        int start = 0;
        int curr_petrol=0;
        int prev_petrol=0;

        for(int i=0; i<n; i++){
            curr_petrol += (petrol[i] - dist[i]);

            if(curr_petrol<0){
                start = i+1;
                prev_petrol += curr_petrol;
                curr_petrol = 0;
            }

        }

        return (curr_petrol + prev_petrol) >=0 ? start+1 : -1;
    }
    
}
