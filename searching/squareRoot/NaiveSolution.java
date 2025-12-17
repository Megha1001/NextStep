package searching.squareRoot;
/*
 * TIME COMPLEXITY : O(Number)
 * 
 * NOTE : THIS CAN CAUSE OVERFLOW
 */

public class NaiveSolution {

    public static void main(String args[]){
        int x = 2;
        System.out.println("The square root of given number is : "+findSqrRoot(x));
        System.out.println("The square root of given number is : "+findSqrRoot2(x));
    }

    public static int findSqrRoot(int x){

        //corner cases
        if(x==0){
            return 0;
        }

        //corner cases
        if( x==1 || x==2 || x==3){
            return 1;
        }

        for(int i=1; i<x-1; i++){
            if(i*i == x || (i+1)* (i+1) > x){ // we can remove (i*i)
                return i;
            }
        }

        return -1;
    }


    public static int findSqrRoot2(int x){
        int i=1;
        while(i*i <= x){
            ++i;
        }
        return (i-1);
    
    }
    
}
