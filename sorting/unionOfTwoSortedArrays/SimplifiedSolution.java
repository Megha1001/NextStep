package sorting.unionOfTwoSortedArrays;

/*
 * TIME COMPLEXITY : Theta(N1+N2) 
 */

public class SimplifiedSolution {

    public static void main(String args[]){
        // int a1 [] = {3,5,8};
        // int a2[] = {2,8,9,10,15};
        int a1 [] = {3,3,3};
        int a2[] = {3,3,4,4,4};

        System.out.println("Union of given arrays is : ");
        printUnion(a1, a2, a1.length, a2.length);
    }

    public static void printUnion(int a1[], int a2[], int n1, int n2){

        int i = 0, j = 0;
    
       while(i<n1 && j<n2){

        if(i>0 && a1[i] == a1[i-1]){
            ++i;
            continue;
        }

        if(j>0 && a2[j] == a2[j-1]){
            ++j;
            continue;
        }


        if (a1[i] < a2[j]){
            System.out.print(a1[i] + " ");
            ++i;
        }
        
        else if(a1[i] > a2[j]){
            System.out.print(a2[j] + " ");
            ++j;
        }

        else { //when equal
            System.out.print(a1[i] + " ");
            ++i;
            ++j;
        }

       }

       while(i<n1){
            if(i==0 || (i>0 && a1[i] != a1[i-1])){
                System.out.print(a1[i] + " ");
            }
            ++i;
       }

       while(j<n1){
        if(j==0 || (j>0 && a2[j] != a2[j-1])){
            System.out.print(a2[j] + " ");
        }
        ++j;
        }

    }
    
    
}
