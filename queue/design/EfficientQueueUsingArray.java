package queue.design;

public class EfficientQueueUsingArray {

    static class Queue{
        int arr[];
        int cap;
        int front;
        int size;


        Queue(int cap){
            this.cap = cap;
            this.arr = new int[cap];
            this.size = 0;
            this.front = 0;
        }


        boolean isEmpty(){
            return size==0;
        }

        boolean isFull(){
            return size == cap;
        }

        int getRear(){
            if(isEmpty()){
                return -1;
            }

            return (front+size-1)%cap;
        }

        int getFront(){
            if(isEmpty()){
                return -1;
            }
            
            return front;
        }

        void enqueue(int x){
            if(isFull()){
                return;
            }
            int rear = getRear();
            rear = (rear+1)%cap;
            arr[rear] = x;
            ++size;
        }

        int dequeue(){
            if(isEmpty()){
                return Integer.MAX_VALUE;
            }

            int e = arr[front];
            front = (front+1)%cap;
            --size;
            return e;
        }

        int front(){
            if(isEmpty()){
                return -1;
            }

            return arr[front];
        }

        int rear(){
            if(isEmpty()){
                return -1;
            }

            return arr[(front+size-1)%cap];
        }

    }

    public static void main(String args[]){
        Queue queue = new Queue(1000); 

		queue.enqueue(10); 
		queue.enqueue(20); 
		queue.enqueue(30); 
		queue.enqueue(40); 

		System.out.println(queue.dequeue() 
						+ " dequeued from queue\n"); 

		System.out.println("Front item is "
						+ queue.front()); 

		System.out.println("Rear item is "
						+queue.rear());

    }
    
}
