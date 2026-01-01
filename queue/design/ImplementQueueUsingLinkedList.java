package queue.design;

public class ImplementQueueUsingLinkedList {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }

    static class Queue{
        Node front;
        Node rear;
        int size;

        Queue(){
            front = rear = null;
            size = 0;
        }

        boolean isEmpty(){
            if(size==0){
                return true;
            }

            return false;
        }


        void enqueue(int x){
            Node newNode = new Node(x);
            if(isEmpty()){
                ++size;
                front = rear = newNode;
                return;
            }

            ++size;
            rear.next = newNode;
            rear = newNode;
        }

        int dequeue(){
            if(isEmpty()){
                return -1;
            }

            --size;
            int val = front.data;
            front = front.next;

            if(front==null){
                rear = null;
            }

            return val;
        }

        int getFront(){
            if(isEmpty()){
                return -1;
            }

            return front.data;
        }

        int getRear(){
            if(isEmpty()){
                return -1;
            }

            return rear.data;
        }

    }

    public static void main(String args[]){
        Queue queue = new Queue(); 

		queue.enqueue(10); 
		queue.enqueue(20); 
		queue.enqueue(30); 
		queue.enqueue(40); 

		System.out.println(queue.dequeue() 
						+ " dequeued from queue\n"); 

		System.out.println("Front item is "
						+ queue.getFront()); 

		System.out.println("Rear item is "
						+queue.getRear());

    }
    
}
