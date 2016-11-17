package quicksort;

/**
 * @Author george.liu on 9/23/16
 */
public class Solution {
    public static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int key = a[start];
        while (left < right) {
            for (; right > left && a[right] >= key; right--) {

            }
            for (; right > left && a[left] <= key; left++){

            }
            if(left < right) {
                int temp = a[right];
                a[right] = a[left];
                a[left] = temp;
            }
        }
        a[start] = a[left];
        a[left] = key;
        quickSort(a,start,left-1);
        quickSort(a,left+1,end);
    }

    public static void main(String[] args) {
        int[] a = {3,5,8,3,5,7,9,10};
        print(a);
        quickSort(a,0,a.length-1);
        print(a);
    }
    public static void print(int [] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            System.out.print(" ");
            if(i==a.length-1){
                System.out.println("====================");
            }
        }
    }
}
