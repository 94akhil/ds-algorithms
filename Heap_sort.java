/*
Implement heap sort.
1. Make an array of 15 numbers. Make sure the array is not sorted AND the numbers do not make a heap.
    You may hard code this array in your main program.
2. Convert the array into a heap, using Floyd's algorithm.  
    Print the new array  Capture screen shot of array before making a heap and after making a heap.
3. Sort the array into descending order using heap sort method. Print the array. - Capture screen shot of array before and after sorting.
Submit code and screen shots.
*/

public class Heap_sort {

    static int min(int a, int b){
        if(a < b) return a;
        else return b;
    }

    static int[] maxHeapify(int i, int[] data, int n){
        int l = 2*i,r= 2*i+1, maxIndex, temp;
        
        
        while(l<=n || r<=n){
            
            maxIndex = i;
            if(l<n && data[maxIndex] < data[l]){
                
                maxIndex=l;
            }
            if(r<n && data[maxIndex] < data[r]){

                maxIndex=r;
                
            }
            if(maxIndex !=i){
                temp = data[maxIndex];
                data[maxIndex]=data[i];
                data[i]= temp;
            }
            

            i=i+1;
            l = 2*i;
            r= 2*i+1;
            
        }
        return data;
    }

    static int[] minHeapify(int i, int[] data, int n){
        int l = 2*i,r= 2*i+1, minIndex, temp;
       
        while(l<=n || r<=n){
            
            minIndex = i;
            if(l<n && data[minIndex] > data[l]){
                
                minIndex=l;
            }
            if(r<n && data[minIndex] > data[r]){

                minIndex=r;
                
            }
            if(minIndex !=i){
                temp = data[minIndex];
                data[minIndex]=data[i];
                data[i]= temp;
            }
            

            i=i+1;
            l = 2*i;
            r= 2*i+1;
            
        }
        return data;
    }

    static int[] heapify_percolate_up(int[] data, int type){

        int first_parent= data[0]/2;
        while(first_parent >=1){
            
            if(type == 1){
                //min heap
                data = minHeapify(first_parent, data, data.length);
            }
            if(type == 2){
                //max heap
                data = maxHeapify(first_parent, data, data.length);
            }
            
            first_parent=first_parent-1;
        }  
        
        return data;
    }

    static int[] heapify_percolate_down(int[] data, int a){
        int parent = 1;
        while(parent <=data.length){
            
            data = minHeapify(parent, data, a);
            
            parent=parent+1;
        }  
        
        return data;
    }

    static int[] heap_sort(int[] data){
        int temp;
        while(data[0] >=1){
            temp = data[1];
            data[1]=data[data[0]];
            data[data[0]]= temp;
            
            data= heapify_percolate_down( data, data[0]);
            data[0]=data[0]-1;
            
        }
        return data;
    }  

    public static void main(String[] args) {
        int data[]={ 15,12,5,11,3,10,6,9,4,8,1,7,2,17,15,13};
        
        System.out.println("\n Before Heapify \n");
        for(int i=1;i<data.length;i++){
            System.out.println(data[i] + "\n");
        }
        int heapData[] = heapify_percolate_up(data,1);

        System.out.println("\n After Heapify \n");
        for(int i=1;i<heapData.length;i++){
            System.out.println(heapData[i] + "\n");
        }

        int sortedData[] = heap_sort(heapData);

        System.out.println("\n After heap sort \n");
        for(int i=1;i<sortedData.length;i++){
            System.out.println(heapData[i] + "\n");
        }
    }
    
}