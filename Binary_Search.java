/* 
Tertiary search algorithm. 
A program similar to binary search alogrithm, but dividing the list into 3 parts.  
*/

public class Tertiarysearch {    
        
    static int tertiarySearch(int[] arrVal, int low, int high, int val){
        int midVal= (high-low)/3;
        int mid1 = low + midVal;
        int mid2 = mid1 + midVal;

        if(low > high) return -1;
        if(val == arrVal[high] ) return high;
        if(val == arrVal[low] ) return low;
        if(val == arrVal[mid1] ) return mid1;
        if(val == arrVal[mid2]) return mid2;   
        if(val < arrVal[mid1]) high= mid1-1;
        if(val > arrVal[mid2]) low= mid2+1;
        if(val > arrVal[mid1] && val < arrVal[mid2]){
            low = mid1;
            high= mid2;
        }
        
        return tertiarySearch(arrVal, low, high, val);
        
    }
    public static void main(String[] args) {
        int array_val[]={2,3,5,6,7,8,9,10,12,23,33,34,40,45,56};
        int num=88;
        int index=tertiarySearch(array_val, 0, array_val.length-1, num);
        if(index == -1){
            System.out.println("\n Number "+num+" is not present in the array\n");
        }else{
            System.out.println("\n Position of "+num+" is : " +index+"\n");
        }
        
    }
}
