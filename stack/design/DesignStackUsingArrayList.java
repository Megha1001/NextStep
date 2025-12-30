package stack.design;

import java.util.ArrayList;

class MyStackUsingArrayList{

    ArrayList<Integer> list;

    MyStackUsingArrayList(){
        this.list = new ArrayList<>();
    }

    public void push(int x){
        list.add(x);
    }

    public int pop(){
        int res = list.get(list.size()-1);
        list.remove(list.size()-1);
        return res;
    }

    public int peek(){
        return list.get(list.size()-1);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }


}

public class DesignStackUsingArrayList {
    public static void main(String args[]){
        MyStackUsingArrayList s = new MyStackUsingArrayList();
        s.push(5);
        s.push(15);
        s.push(25);
        System.out.println(s.peek());
        System.out.println(s.pop());
        s.push(35);
        System.out.println(s.size());
        System.out.println(s.isEmpty());
    }
}
