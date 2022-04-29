
/*
    Implement Hash table.
    Inserting words of letters a-zA-Z into a hash table. 
    The collision resolution scheme should be open addressing - quadratic.
    Initially the table size is 31.  The program increases the table size and rehash at load factor of .5
    So program automatically doubles the table size and re-inserts (automatically) the old words and then continue the insert of additional words.
*/

public class hash_table {
    static String[] hashTable;
    static int tableSize;
    static int entries = 0;
    static int collision=0;
    static int prevCollision=0;

    static void insertHashTable(String word) {

        System.out.println("word: " + word);
        int hashVal = Math.abs(word.hashCode());

        entries += 1;
        float loadFactor = (float) entries / (float) tableSize;
        int hashIndx = hashVal % tableSize;

        if (hashIndx >= tableSize)
            hashIndx = hashIndx - tableSize;

        System.out.println("Hash Index: " + hashIndx);

        if (loadFactor <= 0.5) {
            if (hashTable[hashIndx] != null) {
                collision+=1;
                int i = 1;
                while (true) {
                    int indx = hashIndx + (i * i);

                    if (indx >= tableSize)
                        indx = hashIndx - tableSize;
                    if (hashTable[indx] == null) {
                        hashIndx = indx;
                        break;
                    }
                    i += 1;
                }
            }
            hashTable[hashIndx] = word;
        } else {
            System.out.println("\nResizing the Hash Table at entry: " + entries+"\n");
            prevCollision=collision;
            
            int oldSize = tableSize;
            String[] bufferTable = new String[tableSize];
            tableSize = nearestPrime(2 * tableSize);
            bufferTable = hashTable;
            hashTable = new String[tableSize];

            for (int i = 0; i < oldSize; i++) {
                if (bufferTable[i] != null) {
                    int newHashIndx = Math.abs(bufferTable[i].hashCode()) % tableSize;
                    if (hashTable[newHashIndx] != null) {
                        collision+=1;
                        int j = 1;

                        while (true) {

                            int indx = newHashIndx + (j * j);
                            if (indx >= tableSize)
                                indx = hashIndx - tableSize;

                            if (hashTable[indx] == null) {
                                newHashIndx = indx;
                                break;
                            }
                            j += 1;
                        }
                    }
                    hashTable[newHashIndx] = bufferTable[i];
                }
            }

            entries -= 1;
            insertHashTable(word);
        }

        System.out.println("\n");
    }

    static int nearestPrime(int num) {

        if (isPrime(num))
            return num;
        if (num % 2 == 0)
            return nearestPrime(num + 1);
        else
            return nearestPrime(num + 2);

    }

    static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        tableSize = 31;
        hashTable = new String[tableSize];

        System.out.println("\n");

        insertHashTable("Demo");
        insertHashTable("Cartilage");
        insertHashTable("Apple");
        insertHashTable("Ink");
        insertHashTable("Trial");
        insertHashTable("Homogenous");
        insertHashTable("Flamingo");
        insertHashTable("Pilot");
        insertHashTable("Wax");
        insertHashTable("Quit");
        insertHashTable("Banana");
        insertHashTable("Rain");
        insertHashTable("Olive");
        insertHashTable("Kite");
        insertHashTable("Sunglasses");
        insertHashTable("Jacket");
        insertHashTable("Vehicle");
        insertHashTable("It");
        insertHashTable("Mango");
        insertHashTable("Fan");

        for (int i = 0; i < tableSize; i++) {
            if (hashTable[i] != null) {
                System.out.println("------------------");
                System.out.println(i + "   " + hashTable[i]);
                if (i > 0 && (hashTable[i - 1] != null || (hashTable[i - 1] == null && hashTable[i + 1] == null)))
                    System.out.println("------------------");
            } else
                System.out.println(i + " ");

        }

        System.out.println("\nNumber of collisions before table resize: " + prevCollision);
        System.out.println("\nTotal Number of collisions (after resize): " + collision);

        System.out.println("\n");

    }

}