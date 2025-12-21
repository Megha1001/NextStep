package sorting.mergeOverlappingIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EfficientSolution {

    static class Interval{
        int start;
        int end;

        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    static class MyComparator implements Comparator<Interval>{

        public int compare(Interval i1, Interval i2){
            return i1.start - i2.start;
        }

    }

    public static void main(String args[]){
        ArrayList<Interval> input = new ArrayList<>();
        input.add(new Interval(5,10));
        input.add(new Interval(2,3));
        input.add(new Interval(6,8));
        input.add(new Interval(1,7));

        System.out.println("The merged intervals are : ");
        findAndPrintMergedIntervals(input, input.size());
    }


    public static void findAndPrintMergedIntervals(ArrayList<Interval> input, int inputSize){
        ArrayList<Interval> merged = new ArrayList<>();

        //sort
        Collections.sort(input, new MyComparator());
        merged.add(input.get(0));

        //print
        // input.forEach(x -> System.out.println(x.start + " " + x.end));
        // merged.forEach(x -> System.out.println(x.start + " " + x.end));


        for(int i=1; i<inputSize; i++){
            if(((merged.get(merged.size()-1).start <= input.get(i).start) && (input.get(i).start<= merged.get(merged.size()-1).end))){
                //merged
                System.out.println("HERE : "+(merged.size()-1));
                merged.set(merged.size()-1, new Interval((Math.min(merged.get(merged.size()-1).start,input.get(i).start)), Math.max(merged.get(merged.size()-1).end,input.get(i).end)));
                // merged.forEach(x -> System.out.println(x.start + " " + x.end));
            }
        }

        merged.forEach(x-> System.out.println(x.start+" "+x.end));

    }
    
}
