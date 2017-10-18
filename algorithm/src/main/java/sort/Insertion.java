package sort;

/**
 * @author jiramew
 */
public class Insertion extends SortTemplate {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
            }
        }
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
