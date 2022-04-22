package Assignment.Assignment_1;

/*
We covered binary search algorithm for an array.  
Write program similar to binary search, but now divide the list into 3 parts each time.  
So this would be tertiary search algorithm. 
Your program must be recursive,  
Submit the code and screen shot of executions.
run it 2 times .. once try to search a number in the list.
Second time search a number not in the list.
*/

public class AssignmentTwo {    
        
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