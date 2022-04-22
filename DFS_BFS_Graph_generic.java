import java.util.Arrays;

/*
    The program includes graph creation, DFS and BFS Traversal
    NOTE: Code is written for an undirected graph.
*/


public class topological {

    static Node[] adjacenyList;
    static char[] adjacentVQ;
    static char[] BFSVisited;
    static char[] DFSLabeled;
    static int adjIndex=0;

    static class Node {
        char val;
        Node next;
    }

    static Node createNode(char val) {
        Node new_node = new Node();
        new_node.val = val;
        new_node.next = null;
        return new_node;
    }

    static Node addNode(Node head, char val) {
        if (head == null) {
            return createNode(val);
        } else {
            head.next = addNode(head.next, val);
        }
        return head;
    }

    static Node traverseNode(Node head, char val) {
        if (head == null)
            return null;
        System.out.println("( " + val + " , " + head.val + " )");
        return traverseNode(head.next, val);
    }

    static boolean checkNodePresent(Node head, char val) {

        if (head == null)
            return false;
        if (head.val == val)
            return true;
        return checkNodePresent(head.next, val);

    }

    static void updateAdjacenyList(char u, char v) {

        if (u != v) {
            Node head = fetchNode(u);

            if (!checkNodePresent(head, v)) {
                
                if (head == null)
                    head = createNode(u);
                int i = fetchIndexAdjList(u);
                System.out.println("fetchIndexAdjList: "+i+" "+u+" "+v);
                if(i >=0){
                    adjacenyList[i] = addNode(head, v);
                }else{
                    System.out.println("not in fetchIndexAdjList: "+adjIndex+" "+u+" "+v);
                    adjacenyList[adjIndex]=addNode(head, v);
                    adjIndex+=1;
                }


                Node temp = fetchNode(v);
                if (temp == null)
                    temp = createNode(v);
                int j = fetchIndexAdjList(v);
                if(j <0){
                    System.out.println("not in AdjList: "+j+" "+adjIndex+" "+u+" "+v+"\n");
                    adjacenyList[adjIndex] = temp;
                    adjIndex+=1;
                }
                

            }
        }
    }

    static Node fetchNode(char val) {
        Node adjacentNode = fetchItemAdjList(val);
        if (adjacenyList.length < 1 || adjacentNode == null)
            return null;
        return adjacentNode;
    }

    static Node fetchItemAdjList(char val){
        for(int i=0; i<adjacenyList.length;i++){
            if(adjacenyList[i] != null  && adjacenyList[i].val == val) return adjacenyList[i];
        }
        return null;
    }

    static int fetchIndexAdjList(char val){
        for(int i=0; i< adjacenyList.length;i++){
            if(adjacenyList[i] != null  && adjacenyList[i].val == val) return i;
        }
        return -1;
    }

    static void DFS(Node head, boolean[] DFSVisited) {
        if (DFSVisited[fetchIndexAdjList(head.val)] == true)
            return;
        
        DFSVisited[fetchIndexAdjList(head.val)] = true;
        System.out.println(head.val);
        while (head.next != null) {
            if(fetchNode(head.next.val) != null){
                DFS(fetchNode(head.next.val), DFSVisited);
            }else{
                DFS(createNode(head.next.val), DFSVisited);
            }
            head = head.next;
        }
        
        
    }

    static void BFS(Node head) {
        int l = 0;
        BFSVisited[0] = head.val;
        l++;
        fetchAdjV(head);

        while (l < BFSVisited.length) {
            char next = dequeue();
            BFSVisited[l] = next;
            l++;
            if (next != -1)
                fetchAdjV(fetchNode(next));

        }

        for (int i = 0; i < BFSVisited.length; i++) {
            System.out.println(BFSVisited[i]);
        }

    }

    static void fetchAdjV(Node head) {
        while (head != null && head.next != null) {
            if (!isPresentInQ(head.next.val) && !isPresentInList(head.next.val)) {
                enqueue(head.next.val);
            }
            head = head.next;
        }
        return;
    }

    static boolean isPresentInQ(char val) {
        if (adjacentVQ == null)
            return false;
        for (int i = 0; i < adjacentVQ.length; i++) {
            if (adjacentVQ[i] == val)
                return true;
        }
        return false;
    }

    static boolean isPresentInList(char val) {
        if (BFSVisited == null)
            return false;
        for (int i = 0; i < BFSVisited.length; i++) {
            if (BFSVisited[i] == val)
                return true;
        }
        return false;
    }

    static void enqueue(char val) {
        for (int i = 0; i < adjacentVQ.length; i++) {
            if (adjacentVQ[i] == ' ') {
                adjacentVQ[i] = val;
                return;
            }
        }

        for (int i = 0; i < adjacentVQ.length; i++) {
            System.out.println(adjacentVQ[i]);
        }
    }

    static char dequeue() {
        int len = adjacentVQ.length;
        char nextVal = adjacentVQ[0];

        for (int i = 0; i < len - 1; i++) {
            adjacentVQ[i] = adjacentVQ[i + 1];
            adjacentVQ[i + 1] = ' ';
        }

        return nextVal;
    }

    public static void main(String[] args) {

        adjacenyList = new Node[10];
        adjacentVQ = new char[10];
        Arrays.fill(adjacentVQ, ' ');

        updateAdjacenyList('0', '1');
        updateAdjacenyList('0', '3');
        updateAdjacenyList('0', '4');
        updateAdjacenyList('1', '2');
        updateAdjacenyList('2', '3');
        updateAdjacenyList('4', '5');
        updateAdjacenyList('5', '6');
        updateAdjacenyList('5', '8');
        updateAdjacenyList('8', '7');
        updateAdjacenyList('8', '9');
        updateAdjacenyList('7', '6');

        boolean DFSVisited[] = new boolean[adjacenyList.length];
        //DFSLabeled = new char[adjacenyList.length];
        BFSVisited = new char[adjacenyList.length];

        System.out.println("\n");
        System.out.println("Nodes and Edges of undirected graph:");
        for (int i = 0; i < adjacenyList.length; i++) {
            System.out.println(" ");
            System.out.println("Vertex: " + adjacenyList[i].val);
            System.out.println("-----------");
           
            if(adjacenyList[i] != null){
                traverseNode(adjacenyList[i].next, adjacenyList[i].val);
            
            }
            
        }

        System.out.println("\n");
        System.out.println("DFS Traversal:");
        System.out.println(" ");
        DFS(fetchNode('0'), DFSVisited);

        System.out.println("\n");
        System.out.println("BFS Traversal:");
        System.out.println(" ");
        BFS(fetchNode('0'));
        System.out.println("\n");
    }

}
