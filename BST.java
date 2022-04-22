package Assignment.Assignment_2;

/*
We have discussed Binary Search Trees.(BST)
Write a program to implement a delete operation from BST.
You will have to write the program to insert nodes in the BST also (we already did the algorithm in detail in the class for insert).
DO NOT USE ANY LIBRARIES
Insert the following nodes in the order mentioned here.
40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46
Do an inorder traversal.  
make screen shot
Now delete 40 (you decide predecessor or successor).
Do inorder traversal again.
Make screen shot
Now delete 20
Do inroder traversal
make screen shot.
Submit the code.
Submit the screen shots.
*/

public class BST {

    static class Node{    
        int val;    
        Node parent; 
        Node lchild;
        Node rchild;       
    }

    static Node createNode(int val) {
        Node new_node=new Node();    
        new_node.val = val;    
        new_node.parent = null; 
        new_node.lchild = null; 
        new_node.rchild = null;  
        return new_node;  
    }   

    static Node insertNode(Node head,int val) {  

        if(head == null){
            head = createNode(val);
            return head;
        }
           
        if(head.val < val) {  
            if(head.rchild == null){
                head.rchild =createNode(val);
            }
            else{
                return insertNode(head.rchild, val);
            } 
            
        }
        if(head.val > val) {  
            if(head.lchild == null){
                head.lchild =createNode(val);
            }
            else{
                return insertNode(head.lchild, val);
            } 
            
        }
        return head;
    }

    static void inorderTraverse(Node head) { 
        if(head == null) return;
        inorderTraverse(head.lchild);
        System.out.println(head.val);
        inorderTraverse(head.rchild);
    }

    static Node deleteNode(Node head, int val){
        if(head == null) return head;

        if(head.val > val){
            head.lchild = deleteNode(head.lchild, val);
        }
        else if(head.val < val){
            head.rchild = deleteNode(head.rchild, val);
        }
        else{
            if(head.lchild == null) return head.rchild;
            else if(head.rchild == null) return head.lchild;
            
            head.val=getPredecessor(head.lchild).val;
            head.lchild = deleteNode(head.lchild, head.val);

            // head.val=getSuccessor(head.rchild).val;
            // head.rchild = deleteNode(head.rchild, head.val);
            
        }

        return head;
    }

    /*
    static int getPredecessor(Node head){
        int predecessor = head.val;
        while(head.rchild != null){
            predecessor = head.rchild.val;
            head = head.rchild;
        }
        return predecessor;
    }
    */

    static Node getPredecessor(Node head){
        if(head.rchild == null) return head;
        return getPredecessor(head.rchild);
    }

    static Node getSuccessor(Node head){
        if(head.lchild == null) return head;
        return getSuccessor(head.lchild);
    }

    public static void main(String[] args) {
        int node_val[]={40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46};
        Node head = null;
        
        head = insertNode(head, node_val[0]);
        
        for(int i=0;i<node_val.length;i++){
            insertNode(head,node_val[i]);
        }
        
        System.out.println("\n1. Traversing the list:\n" );
        inorderTraverse(head);

        deleteNode(head, 40);
        System.out.println("\n2. Traversing the list after deleting node 40:\n" );
        inorderTraverse(head);

        deleteNode(head, 20);
        System.out.println("\n3. Traversing the list after deleting node 20:\n" );
        inorderTraverse(head);
    }
}