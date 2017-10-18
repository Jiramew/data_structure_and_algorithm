package sort;

/**
 * @author jiramew
 */
public class Shell extends SortTemplate {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        int separateNum = 3;
        while (h < n / separateNum) {
            h = h * separateNum + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    }
                }
            }
            h = h / separateNum;
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
