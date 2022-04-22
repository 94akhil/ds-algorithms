/*
    Program creates a single linked list of integers.
    Program includes Traversing the list and selection sort.
*/

public class SingleLinkedList {

    static class Node{    
        int val;    
        Node next;        
    }

    static Node createNode(int val) {
        Node new_node=new Node();    
        new_node.val = val;    
        new_node.next = null;  
        return new_node;  
    }   

    static Node addNode(Node head,int val) {        
        if(head == null) {   
            return createNode(val);
        }else{
            head.next= addNode(head.next,val);           
        }
        return head;
    }

    static Node traverseNode(Node head) { 
        if(head == null) return null;
        System.out.println(head.val);
        return traverseNode(head.next);
    }

    static void selectionSort(Node head){
        Node current1 = head;
        Node current2;
        Node minNode;

        while(current1 != null){
            minNode = current1;
            current2=current1.next;
            while(current2!=null){
                if(current2.val<minNode.val){
                    minNode=current2;
                }
                current2=current2.next;
            }
           
            if(minNode.val != current1.val){
                swapNodes(head,current1,minNode);
            }
            
            current1=minNode.next;
        }
        return;
    }

    static void swapNodes(Node head,Node node1,Node node2){
        Node prevNode1=null;
        Node prevNode2=null;
            
        while(head!=null){
                if(head.next.val == node1.val ){
                    prevNode1=head;
                }
                if(head.next.val == node2.val ){
                    prevNode2=head;
                }
                if(prevNode1!=null && prevNode2!=null){ 
                    prevNode1.next= node2;
                    prevNode2.next= node1;
                    Node temp = node1.next;
                    node1.next=node2.next;
                    node2.next=temp;
                    return;
                }
                
                head= head.next;
            }
    }

    static void deleteNode(Node head, int val){
        if(head.next == null) return;
        if(head.val == val) {
            head= head.next;
            return;
        }
        if(head.next.val == val){
            head.next = head.next.next;
            return;
        }
        deleteNode(head.next, val);
    }

    static void insertNode(Node head,int val, int refVal) {        
        if(head == null) {   
            head = createNode(val);
            return;
        }else if(head.val == refVal){
            Node nd= createNode(val);
            nd.next = head.next;
            head.next= nd;
            return;           
        }
        insertNode(head.next, val,refVal);
    }

    public static void main(String[] args) {
        int node_val[]={2,9,3,10,6,8,23,12,45,5,7,56,34,40,33};
        Node head =null;   
        
        for(int i=0;i<node_val.length;i++){
            head=addNode(head,node_val[i]);
        }
        System.out.println("\n1. Traversing the list:\n" );
        traverseNode(head);

        System.out.println("\n3. Delete node:\n");
        deleteNode(head, 10);
        traverseNode(head);

        System.out.println("\n4. Insert node:\n");
        insertNode(head, 10,23);
        traverseNode(head);

        selectionSort(head);
        System.out.println("\n2. Traversing the list after sorting:\n");
        traverseNode(head);

        
    }
}
