import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {

    /*
     *      Killian Callaghan
     *      18332783
     *      Practical 4/5/6
     */

    static void selectionSort(int[] arr) {
        int temp;
        int min_index;

        for (int i = 0; i < arr.length - 1; i++)
        {
            min_index = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[min_index] > arr[j]) {
                    min_index = j;
                }
                //swap arr[i] & arr[min_index]
                temp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = temp;

            }
        }
    }


    static void insertSort (int[] arr){

        for (int i = 1; i < arr.length; ++i) {
           int key = arr[i];
           int j = i -1;

            while(j >= 0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
                arr[j+1] = key;
            }
        }
    }

    /**
     * StalinSort removes elements that are not in correct order (smallest to biggest)
     * If an element is smaller than the previous, it is removed
     * @param arr The array
     * @return The sorted array
     */
    static int[] StalinSort(int[] arr)
    {
        //Current smallest element
        int key = arr[0];
        //Counts number of elements to be removed
        int executions=0;

        for(int i=0;i<arr.length;i++)
        {
            if(key>arr[i])
            {
                arr[i] = 0;
                executions++;
            }
            else{
                key = arr[i];
            }
        }

        //Copy of array arr which will be returned
        int[] newArray = new int[arr.length - executions];

        // j keep tracks of how many elements currently added to newArray
        int j=0;
        for(int i=0; i<arr.length;i++)
        {
            if(arr[i] != 0)
            {
                newArray[j] = arr[i];
                j++;
            }
        }
        return newArray;
    }

    static void mergeSort (int[] a, int n) {

        //int n = a.length;

//base case
        if (n == 1) {
            return;
        }

//create left and right sub-arrays

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }

        for (int i = mid; i < n; i++)
        {
            right[i-mid] = a[i];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(a, left, right, mid, n - mid);

    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while (i < left)
            a[k++] = l[i++];
        while (j < right)
            a[k++] = r[j++];
    }

    static void MergeSortEnhanced(int[] a, int n) {

        int CUTOFF = 10;
        //int n = a.length;
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        if(sorted(a))
        {
            return;
        }


        //if array is under a certain size, call insertion sort to sort it instead
        if(a.length < CUTOFF)
        {
            insertSort(a);
            return;
        }

    //create left and right sub-arrays
        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }

        for (int i = mid; i < n; i++)
        {
            right[i-mid] = a[i];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(a, left, right, mid, n - mid);
    }

    public static boolean sorted(int[] array)
    {
        for(int i=0;i<array.length-1;i++)
        {
            if (array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }


    /* Partition for QuickSort:
       This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static void QuickSort(int[] arr, int low, int high)
    {

        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            QuickSort(arr, low, pi-1);
            QuickSort(arr, pi+1, high);
        }
    }

    public static void QuickSortEnhanced(int[] arr, int low, int high)
    {
        if(arr.length <= 10)
        {
            insertSort(arr);
            return;
        }

        shuffle(arr);

        if (low < high)
        {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partitionEnhanced(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            QuickSort(arr, low, pi-1);
            QuickSort(arr, pi+1, high);
        }

    }

    public static int partitionEnhanced(int[] arr, int low, int high)
    {
        int pivot = median(low, high, arr[high/2]);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public static int median(int low, int high, int middle)
    {

        //low is median
        if(low < high && low > middle)
        {
            return low;
        }
        if(low < middle && low > high)
        {
            return low;
        }

        //high is median
        if(high < low && high > middle)
        {
            return high;
        }
        if(high < middle && high > low)
        {
            return high;
        }

        //middle is median
        if(middle < high && middle > low)
        {
            return middle;
        }
        else
            return middle;

    }

    public static void shuffle(int[] array)
    {
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
            System.out.println(Arrays.toString(array));
    }


    public static void  main(String[] args)
    {
        int arraySize = 100000;

        int[] arraySelectionSort = new int[arraySize];
        int[] arrayInsertionSort = new int[arraySize];
        int[] arrayStalinSort = new int[arraySize];
        int[] arrayMergeSort = new int[arraySize];
        int[] arrayMergeSortEnhanced = new int[arraySize];
        int[] arrayQuickSort = new int[arraySize];
        int[] arrayQuickSortEnhanced = new int[arraySize];

        Random rand = new Random();


        //SelectionSort
        for(int i=0;i<arraySize;i++)
        {
            arraySelectionSort[i] = rand.nextInt(100);
        }

        //InsertionSort
        for(int i=0;i<arraySize;i++)
        {
            arrayInsertionSort[i] = rand.nextInt(100);
        }

        //StalinSort
        for(int i=0;i<arraySize;i++)
        {
            arrayStalinSort[i] = rand.nextInt(100);
        }




        //AssertionSort
        for(int i=0;i<arraySize;i++)
        {
            arrayInsertionSort[i] = rand.nextInt(100);
        }

        //MergeSort
        for(int i=0; i<arraySize; i++)
        {
            arrayMergeSort[i] = rand.nextInt(100);
        }
        int mergeSize = arrayMergeSort.length;

        //MergeSortEnhanced
        for(int i=0; i<arraySize; i++)
        {
            arrayMergeSortEnhanced[i] = rand.nextInt(100);
        }
        int mergeEnhancedSize = arrayMergeSortEnhanced.length;

        //QuickSort
        for(int i=0; i<arraySize; i++)
        {
            arrayQuickSort[i] = rand.nextInt(100);
        }
        int QuickSortSize = arrayQuickSort.length;

        //QuickSortEnhanced
        for(int i=0; i<arraySize; i++)
        {
            arrayQuickSortEnhanced[i] = rand.nextInt(100);
        }
        int QuickSortEnhancedSize = arrayQuickSortEnhanced.length;



        //SELECTION SORT
        System.out.println("Selection Sort");
        System.out.println(Arrays.toString(arraySelectionSort));
        final long startTimeSelection = System.nanoTime();
        selectionSort(arraySelectionSort);
        final long elapsedTimeSelection = System.nanoTime() - startTimeSelection;
        System.out.println(Arrays.toString(arraySelectionSort));
        System.out.println("the time taken Iterative: " + elapsedTimeSelection +"\n");

        //INSERTION SORT
        System.out.println("Insertion Sort");
        System.out.println(Arrays.toString(arrayInsertionSort));
        final long startTimeInsert = System.nanoTime();
        insertSort(arrayInsertionSort);
        final long elapsedTimeInsert = System.nanoTime() - startTimeInsert;
        System.out.println(Arrays.toString(arrayInsertionSort));
        System.out.println("the time taken Iterative: " + elapsedTimeInsert+"\n");


        //STALIN SORT
        System.out.println("Stalin Sort:");
        System.out.println(Arrays.toString(arrayStalinSort));
        final long startTimeStalin = System.nanoTime();
        System.out.println(Arrays.toString(StalinSort(arrayStalinSort)));
        final long elapsedTimeStalin = System.nanoTime() - startTimeStalin;
        System.out.println("the time taken Iterative: " + elapsedTimeStalin+"\n");




        //MERGE SORT
        System.out.println("Merge Sort");
        System.out.println(Arrays.toString(arrayMergeSort));
        long mergeSpeed = System.nanoTime();
        mergeSort(arrayMergeSort, mergeSize);
        long elapsedMerge = System.nanoTime() - mergeSpeed;
        System.out.println(Arrays.toString(arrayMergeSort));
        System.out.println("the time taken in nano seconds: " + elapsedMerge+"\n");


        //MERGE SORT Enhanced
        System.out.println("Merge Sort Enhanced");
        System.out.println(Arrays.toString(arrayMergeSortEnhanced));
        long mergeEnhancedSpeed = System.nanoTime();
        MergeSortEnhanced(arrayMergeSortEnhanced, mergeEnhancedSize);
        long elapsedEnhancedMerge = System.nanoTime() - mergeEnhancedSpeed;
        System.out.println(Arrays.toString(arrayMergeSortEnhanced));
        System.out.println("the time taken in nano seconds: " + elapsedEnhancedMerge+"\n");



         //QuickSort
        System.out.println("QuickSort:");
        System.out.println(Arrays.toString(arrayQuickSort));
        long QuickSortSpeed = System.nanoTime();
        QuickSort(arrayQuickSort,0, QuickSortSize-1);
        long elapsedQuickSort = System.nanoTime() - QuickSortSpeed;
        System.out.println(Arrays.toString(arrayQuickSort));
        System.out.println("The time taken in nano seconds: " + elapsedQuickSort);



        //QuickSortEnhanced
        System.out.println("QuickSortEnhanced:");
        System.out.println(Arrays.toString(arrayQuickSortEnhanced));
        long QuickSortEnhancedSpeed = System.nanoTime();
        QuickSortEnhanced(arrayQuickSortEnhanced,0, QuickSortSize-1);
        long elapsedQuickSortEnhanced = System.nanoTime() - QuickSortEnhancedSpeed;
        System.out.println(Arrays.toString(arrayQuickSortEnhanced));
        System.out.println("The time taken in nano seconds: " + elapsedQuickSortEnhanced);





    }

}
