package sort;

/**
 * @author jiramew
 */
public class Merge extends SortTemplate {
    private static Comparable[] aux;

    public static void sortBU(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int i = 1; i < n; i += i) {
            for (int lo = 0; lo < n - i; lo += 2 * i) {
                merge(a, lo, lo + i - 1, Math.min(lo + 2 * i - 1, n - 1));
            }
        }
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, n - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (hi - lo) / 2 + lo;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
        show(a);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int loIndex = lo;
        int midIndex = mid + 1;

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            if (loIndex > mid) {
                // [lo, mid]取完, 添加[mid+1, hi]
                a[i] = aux[midIndex++];
            } else if (midIndex > hi) {
                // [mid + 1, hi]取完, 添加[lo, mid]
                a[i] = aux[loIndex++];
            } else if (less(aux[midIndex], aux[loIndex])) {
                // [lo, mid]，[mid+1, hi]中去小的部分拼接
                a[i] = aux[midIndex++];
            } else {
                a[i] = aux[loIndex++];
            }
        }
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        a[0] = 30;
        a[1] = 9;
        a[2] = 15;
        a[3] = 3;
        a[4] = 25;
        a[5] = 2;
        a[6] = 0;
        a[7] = 7;
        a[8] = 8;
        a[9] = 4;
        //sort(a)
        sortBU(a);
        show(a);
    }
}
