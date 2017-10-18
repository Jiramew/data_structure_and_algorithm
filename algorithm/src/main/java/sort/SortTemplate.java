package sort;

/**
 * @author jiramew
 */
public class SortTemplate {
    public static void sort(Comparable[] a) {
        return;
    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        a[0] = 1;
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
