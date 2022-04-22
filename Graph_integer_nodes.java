import java.util.Arrays;

// Write a program:
// 1. Create a graph of at least 10 nodes and 20 edges. You may use any representation we have discussed
//        Print the nodes and the edges.
// 2. Do a DFS traversal of the graph.  Print the nodes in the order DFSVisited.
// 3. Do a BFS traversal.  Use the same node to start as in 2 above.
// Submit program files.
// submit screen shot of execution

// NOTE: Code is written for an undirected graph

public class graph {

    static Node[] adjacenyList;
    static int[] adjacentVQ;
    static int[] BFSVisited;

    static class Node {
        int val;
        Node next;
    }

    static Node createNode(int val) {
        Node new_node = new Node();
        new_node.val = val;
        new_node.next = null;
        return new_node;
    }

    static Node addNode(Node head, int val) {
        if (head == null) {
            return createNode(val);
        } else {
            head.next = addNode(head.next, val);
        }
        return head;
    }

    static Node traverseNode(Node head, int val) {
        if (head == null)
            return null;
        System.out.println("( " + val + " , " + head.val + " )");
        return traverseNode(head.next, val);
    }

    static boolean checkNodePresent(Node head, int val) {

        if (head == null)
            return false;
        if (head.val == val)
            return true;
        return checkNodePresent(head.next, val);

    }

    static void updateAdjacenyList(int u, int v) {

        if (u != v) {
            Node head = fetchNode(u);

            if (!checkNodePresent(head, v)) {
                if (head == null)
                    head = createNode(u);
                adjacenyList[u] = addNode(head, v);

                Node temp = fetchNode(v);
                if (temp == null)
                    temp = createNode(v);
                adjacenyList[v] = addNode(temp, u);
            }
        }
    }

    static Node fetchNode(int val) {
        if (adjacenyList.length < 1 || adjacenyList[val] == null)
            return null;
        return adjacenyList[val];
    }

    static void DFS(Node head, boolean[] DFSVisited) {
        if (DFSVisited[head.val] == true)
            return;
        DFSVisited[head.val] = true;
        System.out.println(head.val);
        while (head.next != null) {
            DFS(fetchNode(head.next.val), DFSVisited);
            head = head.next;
        }
    }

    static void BFS(Node head) {
        int l = 0;
        BFSVisited[0] = head.val;
        l++;
        fetchAdjV(head);

        while (l < BFSVisited.length) {
            int next = dequeue();
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

    static boolean isPresentInQ(int val) {
        if (adjacentVQ == null)
            return false;
        for (int i = 0; i < adjacentVQ.length; i++) {
            if (adjacentVQ[i] == val)
                return true;
        }
        return false;
    }

    static boolean isPresentInList(int val) {
        if (BFSVisited == null)
            return false;
        for (int i = 0; i < BFSVisited.length; i++) {
            if (BFSVisited[i] == val)
                return true;
        }
        return false;
    }

    static void enqueue(int val) {
        for (int i = 0; i < adjacentVQ.length; i++) {
            if (adjacentVQ[i] == -1) {
                adjacentVQ[i] = val;
                return;
            }
        }

        for (int i = 0; i < adjacentVQ.length; i++) {
            System.out.println(adjacentVQ[i]);
        }
    }

    static int dequeue() {
        int len = adjacentVQ.length;
        int nextVal = adjacentVQ[0];

        for (int i = 0; i < len - 1; i++) {
            adjacentVQ[i] = adjacentVQ[i + 1];
            adjacentVQ[i + 1] = -1;
        }

        return nextVal;
    }

    public static void main(String[] args) {

        adjacenyList = new Node[10];
        adjacentVQ = new int[10];
        int num = -1;
        Arrays.fill(adjacentVQ, num);

        updateAdjacenyList(0, 1);
        updateAdjacenyList(0, 3);
        updateAdjacenyList(0, 4);
        updateAdjacenyList(1, 2);
        updateAdjacenyList(2, 3);
        updateAdjacenyList(4, 5);
        updateAdjacenyList(5, 6);
        updateAdjacenyList(5, 8);
        updateAdjacenyList(8, 7);
        updateAdjacenyList(8, 9);
        updateAdjacenyList(7, 6);

        boolean DFSVisited[] = new boolean[adjacenyList.length];
        BFSVisited = new int[adjacenyList.length];

        System.out.println("\n");
        System.out.println("Nodes and Edges of undirected graph:");
        for (int i = 0; i < adjacenyList.length; i++) {
            System.out.println(" ");
            System.out.println("Vertex: " + i);
            System.out.println("-----------");
            traverseNode(fetchNode(i).next, i);
        }

        System.out.println("\n");
        System.out.println("DFS Traversal:");
        System.out.println(" ");
        DFS(fetchNode(0), DFSVisited);

        System.out.println("\n");
        System.out.println("BFS Traversal:");
        System.out.println(" ");
        BFS(fetchNode(0));
        System.out.println("\n");
    }

}