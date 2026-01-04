package tree.convertBTToDLL;


/*
 * IDEA : Maintain a previous reference
 *  -> prev.right = curr
 *  -> curr.left = prev
 *  -> prev = curr
 * 
 * TIME COMPLEXITY : O(N)
 * AUXILIARY SPACE : O(H), where H is height of binary 
 */

public class ConvertBTToDLL {

    public static Node prev = null;

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static void main(String args[]){
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.right.left = new Node(30);
        root.right.right = new Node(35);

        System.out.println("Doubly LL for given tree is : ");
        Node head = BTToDLL(root);
        traverse(head);
    }

    public static Node BTToDLL(Node root){
        if(root == null){
            return root;
        }

        //left
        Node head = BTToDLL(root.left);

        //node
        if(prev == null){
            head = root;
        }
        else{
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        //right
        BTToDLL(root.right);

        return head;

    }

    public static void traverse(Node head){
        if(head == null){
            return;
        }

        Node curr = head;
        while(curr!= null){
            System.out.print(curr.data + " ");
            curr = curr.right;
        }

    }
    
}
