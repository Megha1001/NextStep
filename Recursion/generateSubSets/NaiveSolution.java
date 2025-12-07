package Recursion.generateSubSets;

public class NaiveSolution {

    public static void main(String args[]){
        String s = "ABC";
        System.out.println("Subsets/Subsequences of given string are :- ");
        printSubSets(s, 0,"");
    }

    public static void printSubSets(String s, int idx, String toPrint){

        if(idx == s.length()-1){
            System.out.println(toPrint);
            return;
        }

        printSubSets(s, idx+1, toPrint + s.charAt(idx));
        printSubSets(s, idx+1, toPrint);

    }
    
}
