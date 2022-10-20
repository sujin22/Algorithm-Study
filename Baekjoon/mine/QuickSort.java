package mine;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int array[] = {5, 7, 9, 0, 3, 1, 6 , 2, 4, 8};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
    private static void quickSort(int[] arr, int start, int end){
        //원소 하나면 return
        if(start>=end){
            return;
        }
        int pivot = start;
        int left = start+1;
        int right = end;

        while(left <= right){
            while(left<=end && arr[left] < arr[pivot]){
                left++;
            }
            while(right > pivot && arr[right] > arr[pivot]){
                right--;
            }
            if(left>right){
                //pivot이랑 right swap
                swap(arr, pivot, right);
                pivot = right;
                quickSort(arr, 0, pivot-1);
                quickSort(arr, pivot+1, end);
            }else{
                swap(arr, left, right);
            }
        }
    }
    private static void swap(int[] arr, int i1, int i2){
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
