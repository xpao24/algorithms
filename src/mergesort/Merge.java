package mergesort;

/**
 * @Author george.liu on 16/1/19
 * 合并排序
 */
public class Merge {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int h = lo; h <= hi; h++) {
            if (i > mid)
                a[h] = aux[j++];
            else if (j > hi)
                a[h] = aux[i++];
            else if (aux[i].compareTo(aux[j]) < 0) {
                a[h] = aux[j++];
            } else {
                a[h] = aux[i++];
            }
        }

    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];//辅助数组
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
        print(a);
    }

    public static void main(String[] args) {
        Integer a[] = {9, 1, 6, 5, 90, 3};
        Merge.sort(a);
    }

    private static void print(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if(i == a.length-1)
                System.out.print(a[i] + "\n");
            else {
                System.out.print(a[i] + " ");

            }
        }
    }
}
