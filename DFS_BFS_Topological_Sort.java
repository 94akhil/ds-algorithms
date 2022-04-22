import java.util.Arrays;

/*
    a.  Write a program to do DFS topological sort . your program must be able to catch the loop.
        Run the program on the attached graphs.
    b.  Write a program to do BFS topological sort . your program must be able to catch the loop.
        Run the program on the attached graphs.
        submit screen shots of execution results.
        submit the code
*/

public class topological {

    static Node[] adjacenyList;
    static char[] adjacentVQ;
    static char[] BFSVisited;
    static char[] DFSLabeled;
    static int[] predecessorCount;
    static int adjIndex = 0;
    static int DFSLabeledIndex = 0;
    static boolean cyclic = false;

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
                if (i >= 0) {
                    adjacenyList[i] = addNode(head, v);
                } else {
                    adjacenyList[adjIndex] = addNode(head, v);
                    adjIndex += 1;
                }

                Node temp = fetchNode(v);
                if (temp == null)
                    temp = createNode(v);
                int j = fetchIndexAdjList(v);
                if (j < 0) {
                    adjacenyList[adjIndex] = temp;
                    adjIndex += 1;
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

    static Node fetchItemAdjList(char val) {
        for (int i = 0; i < adjacenyList.length; i++) {
            if (adjacenyList[i] != null && adjacenyList[i].val == val)
                return adjacenyList[i];
        }
        return null;
    }

    static int fetchIndexAdjList(char val) {
        for (int i = 0; i < adjacenyList.length; i++) {
            if (adjacenyList[i] != null && adjacenyList[i].val == val)
                return i;
        }
        return -1;
    }

    static boolean checkDFSLabeled(char val) {
        for (int i = 0; i < DFSLabeled.length; i++) {
            if (DFSLabeled[i] == val)
                return true;
        }
        return false;
    }

    static void DFS(Node head, boolean[] DFSVisited) {

        if (DFSVisited[fetchIndexAdjList(head.val)] == true && checkDFSLabeled(head.val) == true) {
            return;
        }

        if (DFSVisited[fetchIndexAdjList(head.val)] == true && checkDFSLabeled(head.val) != true) {
            System.out.println("Graph has a loop at node: " + head.val);
            cyclic = true;
            return;
        }

        DFSVisited[fetchIndexAdjList(head.val)] = true;

        Node temp = head;
        while (head != null) {

            if (head.next != null) {
                DFS(fetchNode(head.next.val), DFSVisited);
            }

            if (cyclic)
                return;
            head = head.next;

        }
        DFSLabeled[DFSLabeledIndex] = temp.val;
        DFSLabeledIndex += 1;

        return;
    }

    static void BFS() {
        int l = 0;

        for (int i = 0; i < predecessorCount.length; i++) {
            if (predecessorCount[i] == 0) {
                enqueue(adjacenyList[i].val);
            }
        }

        while (l < BFSVisited.length) {
            char next = dequeue();
            BFSVisited[l] = next;

            if (fetchNode(next) != null) {
                Node head = fetchNode(next).next;

                while (head != null) {
                    if (!isPresentInQ(head.val)) {
                        int index = fetchIndexAdjList(head.val);
                        predecessorCount[index] -= 1;
                    }
                    head = head.next;
                }

                for (int i = 0; i < predecessorCount.length; i++) {
                    if (predecessorCount[i] == 0 && !isPresentInQ(adjacenyList[i].val)
                            && !isPresentInList(adjacenyList[i].val)) {
                        enqueue(adjacenyList[i].val);
                    }
                }
            }

            l++;

        }

        int count=0;
        for (int i = 0; i < BFSVisited.length; i++) {
            if (BFSVisited[i] != ' '){
                count+=1;
                System.out.println(BFSVisited[i]);
            }
                
        }
        
        if(count < adjacenyList.length) System.out.println("Graph has a loop");

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

    static void countNdPredecessor() {

        for (int i = 0; i < adjacenyList.length; i++) {
            char val = adjacenyList[i].val;
            int count = 0;
            for (int j = 0; j < adjacenyList.length; j++) {
                if (i != j) {
                    Node head = adjacenyList[j];
                    while (head != null) {
                        if (head.val == val)
                            count += 1;
                        head = head.next;
                    }
                }
            }
            predecessorCount[i] = count;
        }
    }

    public static void main(String[] args) {

        int nodeCount = 8;
        char DFSStartNode = '1';

        adjacenyList = new Node[nodeCount];
        adjacentVQ = new char[nodeCount];
        predecessorCount = new int[nodeCount];
        Arrays.fill(adjacentVQ, ' ');

        // // Example 1
        updateAdjacenyList('1', '2');
        updateAdjacenyList('1', '5');
        updateAdjacenyList('1', '6');
        updateAdjacenyList('2', '3');
        updateAdjacenyList('2', '5');
        updateAdjacenyList('2', '7');
        updateAdjacenyList('3', '4');
        updateAdjacenyList('4', '5');
        updateAdjacenyList('5', '7');
        updateAdjacenyList('5', '8');
        updateAdjacenyList('6', '5');
        updateAdjacenyList('6', '8');
        updateAdjacenyList('7', '4');
        updateAdjacenyList('7', '8');

        // Example 2
        // updateAdjacenyList('m', 'q');
        // updateAdjacenyList('m', 'r');
        // updateAdjacenyList('m', 'x');
        // updateAdjacenyList('n', 'o');
        // updateAdjacenyList('n', 'q');
        // updateAdjacenyList('n', 'u');
        // updateAdjacenyList('o', 'r');
        // updateAdjacenyList('o', 'v');
        // updateAdjacenyList('o', 's');
        // updateAdjacenyList('p', 'o');
        // updateAdjacenyList('p', 's');
        // updateAdjacenyList('p', 'z');
        // updateAdjacenyList('q', 't');
        // updateAdjacenyList('r', 'u');
        // updateAdjacenyList('r', 'y');
        // updateAdjacenyList('s', 'r');
        // updateAdjacenyList('u', 't');
        // updateAdjacenyList('v', 'w');
        // updateAdjacenyList('v', 'x');
        // updateAdjacenyList('w', 'z');
        // updateAdjacenyList('y', 'v');

        boolean DFSVisited[] = new boolean[nodeCount];
        DFSLabeled = new char[nodeCount];
        BFSVisited = new char[nodeCount];
        Arrays.fill(BFSVisited, ' ');

        System.out.println("\n");
        System.out.println("Nodes and Edges of undirected graph:");
        for (int i = 0; i < adjacenyList.length; i++) {
            System.out.println(" ");
            System.out.println("Vertex: " + adjacenyList[i].val);
            System.out.println("-----------");

            if (adjacenyList[i] != null) {
                traverseNode(adjacenyList[i].next, adjacenyList[i].val);

            }

        }

        countNdPredecessor();

        System.out.println("\n");
        System.out.println("DFS Traversal from node "+DFSStartNode+ " :");
        System.out.println(" ");
        DFS(fetchNode(DFSStartNode), DFSVisited);

        if (!cyclic) {
            for (int i = DFSLabeled.length - 1; i >= 0; i--) {
                if (DFSLabeled[i] != '\0') {
                    System.out.println(DFSLabeled[i]);
                }

            }
        }

        System.out.println("\n");
        System.out.println("BFS Traversal:");
        System.out.println(" ");
        BFS();
        System.out.println("\n");
    }

}