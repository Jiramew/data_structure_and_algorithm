package sort;

/**
 * @author jiramew
 */
public class Quick extends SortTemplate {
    public static void sort(Comparable[] a) {
        int n = a.length;
        sort(a, 0, n - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int partIndex = partition(a, lo, hi);
        sort(a, lo, partIndex - 1);
        sort(a, partIndex + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        a[0] = 3;
        a[1] = 9;
        a[2] = 15;
        a[3] = 3;
        a[4] = 25;
        a[5] = 2;
        a[6] = 0;
        a[7] = 7;
        a[8] = 8;
        a[9] = 4;
        sort(a);
        show(a);
    }
}
