import java.util.Arrays;

public class SelectionSort2 {

    static void sort1(int[] arr)
    {
        int temp;
        int min_index;

        //one by one move the boundary of the unsorted
        for(int i=0; i<arr.length-1;i++)
        {
            min_index = i;
            for(int j=i+1; j<arr.length; j++)
            {
                if(arr[min_index]> arr[j])
                {
                    min_index = j;
                }
                //swap the minimum element with 1st
                temp=arr[i];
                arr[i]=arr[min_index];
                //TODO
            }
        }
    }

    public static void main(String[] args)
    {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        System.out.println(Arrays.toString(array));

        sort1(array);
        System.out.println(Arrays.toString(array));

    }
}
