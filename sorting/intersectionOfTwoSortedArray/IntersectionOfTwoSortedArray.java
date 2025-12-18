package sorting.intersectionOfTwoSortedArray;

public class IntersectionOfTwoSortedArray {

    public static void main(String args[]){
        int a1[] = {3,5,10,10,10,15,15,20};
        int a2[] = {5,10,10,15,30};

        // int a1[] = {1,1,3,3,3};
        // int a2[] = {1,1,1,1,3,5,7};

        System.out.println("Intersection of two sorted array is/are : ");
        printDistinctCommonElements(a1, a2, a1.length, a2.length);
    }

    public static void printDistinctCommonElements(int a1[], int a2[], int n1, int n2){

        int i=0, j=0;

        while(i<n1 && j<n2){

            if(a1[i]==a2[j] && (i==0 || i>0 && a1[i]!=a1[i-1])){
                System.out.print(a1[i] + " ");
                ++i;
                ++j;
            }

            else if (a1[i] > a2[j]){
                ++j;
            }else {
                ++i;
            }

        }

    }
    
}
