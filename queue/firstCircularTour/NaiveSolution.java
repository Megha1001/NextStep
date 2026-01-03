package queue.firstCircularTour;

/*
 * Consider every start point as viable answer
 */

public class NaiveSolution {

    public static void main(String args[]){
        // int petrol[] = {4,8,7,4};
        // int dist[] = {6,5,3,5};
        // int petrol[] = {4,8};
        // int dist[] = {5,6};

        int petrol[] = {8,9,4};
        int dist[] = {5,10,12};

        System.out.println("The possible petrol pump from where we start is : "+findPetrolPump(petrol, dist, petrol.length));
    }

    public static int findPetrolPump(int petrol[], int dist[], int n){

        for(int i=0; i<n; i++){
            int j=i, x = 0;

            do{
                x += (petrol[j%n] - dist[j%n]);
                ++j;
            }while(x>=0 && i!=(j%n));

            if(x>=0 && (i==j%n)){
                return i+1;
            }

        }

        return -1;
    }
    
}
