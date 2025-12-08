package Recursion.towerOfHanoi;

public class TowerOfHanoiSolution {

    public static void main(String args[]){
        int n =3;
        System.out.println("Steps for 3 disc to move from A to C are : ");
        printSteps(n, 'A', 'B', 'C');
    }

    public static void printSteps(int n, char a, char b, char c){

        if(n==1){
            System.out.println("Move disc 1 from "+ a +" to "+ c);
            return;
        }

        printSteps(n-1, a, c, b);
        System.out.println("Move disc "+n+" from "+ a +" to "+ c);
        printSteps(n-1, b, a, c);
    }
    
}
