
import java.util.Scanner;
class Gardens
{
    private static int x, y;
 
   // method to find a random pivot and partitions around pivot using the leftptr and rightptr
    private static int pivotselection(int arr[], int leftptr, int rightptr)
    {
        int k = rightptr-leftptr+1;
        int pivot = (int) (Math.random() % k);
        arr = swap(arr, leftptr + pivot, rightptr);
        return Partition(arr, leftptr, rightptr);
    }  


   // Partitioning method to give the position of the pivot
    private static int Partition(int arr[], int leftptr, int rightptr)
    {
        
          int pivPosition = leftptr;
          int leftTemp = leftptr;
        while (leftTemp < rightptr)
        {
            if (arr[rightptr] > arr[leftTemp])
            {
                arr = swap(arr, pivPosition, leftTemp);
                pivPosition++;
            }
            leftTemp++;
        }
        arr = swap(arr, pivPosition, rightptr);
        return pivPosition;
    }

   // method to swap elements
 private static int[] swap(int[] arr, int a, int b)
    {
        int temp_element = arr[a];
        arr[a] = arr[b];
        arr[b] = temp_element;
        return arr;
    }
 
   
 
   
 
    // Function to find if partition is median, on the left, or the right side of the middle position
    private static int findMedian(int arr[], int leftptr, int rightptr, int middlePos)
    {
       int minVal = Integer.MIN_VALUE;
        
        if (leftptr <= rightptr)
        {
 
            // Find the index of where the pivot is
            int p_index = pivotselection(arr, leftptr, rightptr);

            // If the pivot's spot is one behind the middle spot in the array then use x and y as the middle element (for even length array)
            if (p_index == middlePos - 1)
            {
                x = arr[p_index];
                if (y > 0)
                    return minVal;
            }
 
            // If pivots spot is the middle spot, then we have found the median (if its an odd length array)
            else if (p_index == middlePos)
            {
                y = arr[p_index];
                if (x > 0)
                    return minVal;
            }


            // If the pivots is to the left of the middle index, find median from the right side of partition
            if(p_index<=middlePos)
                return findMedian(arr, p_index + 1, rightptr, middlePos);
          
            // If pivots spot is to the right of the middle index, find median from left side of partition
            else
                return findMedian(arr, leftptr, p_index - 1, middlePos);
 
          
        }
 
        return minVal;
      
    }
 
    // Function to get Median
    private static int getMedian(int arr[], int arraylength)
    {
        int median;
        x = 0;
        y = 0;
 
        // If the length of the array is even take average of 2 middle values
        if (arraylength % 2 == 0)
        {
            findMedian(arr, 0, arraylength - 1, arraylength / 2);
            median = (x + y)/2;
        }
 
        // If the length of the array is odd simply take the middle value
        else
        {
            findMedian(arr, 0, arraylength - 1, arraylength / 2);
            median = y;
        }
 
        // return the median
        return median;
    } 
 
    // Main method to take user input and output optimal irrigation line (median)
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int numGardens = in.nextInt();
        int[] gardenArr = new int[numGardens*2];
        int sizeCount=0;
    for (int i = 0;i < numGardens*2; i++) {
          
              
            
            gardenArr[i]=in.nextInt();
            
      

          if(i%2==1){

            sizeCount++;
          }

            
        
         
           
        
          } 

      int[] sizeArr = new int[sizeCount];
     int mysizeIndex =0;
      
      for (int i=0;i<numGardens*2;i++){
          if(i%2==1){
              sizeArr[mysizeIndex]=gardenArr[i];
              mysizeIndex++;
            
           
          }
        
      }

      int sizeSum=0;

    for (int i=0; i<sizeCount;i++){ // loop to find the sum of all the size values
      sizeSum+=sizeArr[i];
    } 

    int[] coordinateArr=new int[sizeSum]; // set the new coordinate array to size of the sum of sizes

    int index = 0;

      for(int i=0;i<numGardens*2;i++){
        if(i%2==0){ // y-coordinates are at every even index of array
     // place y-coordinate into array
            
          for(int j=0;j<gardenArr[i+1];j++){ // loop until size value 
            coordinateArr[index] = gardenArr[i]; // repeat the y-coordinate value
            index++;
          }
        }
      }
        


      System.out.println(getMedian(coordinateArr, sizeSum)); // print the median
    }
      
       
    }


