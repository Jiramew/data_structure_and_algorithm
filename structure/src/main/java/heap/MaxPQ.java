package heap;


/**
 * @author jiramew
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ(Integer[] list) {
        int n = list.length;
        pq = (Key[]) new Comparable[n + 1];

        for (int i = 0; i < n; i++) {
            insert((Key) list[i]);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void show() {
        int mod = 1;
        for (int i = 1; i <= N; i++) {
            if (i % mod == 0) {
                System.out.println();
                mod *= 2;
            }
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }


    private void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    private Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    public static void main(String[] args) {
//        MaxPQ pq = new MaxPQ(10);
//
//        for (int i = 0; i < 10; i++) {
//            pq.insert(i);
//        }
//        pq.show();
//        pq.delMax();
//        pq.show();
//        pq.delMax();
//        pq.show();
//        pq.delMax();
//        pq.show();

        Integer[] a = new Integer[10];
        a[0] = 1;
        a[1] = 9;
        a[2] = 15;
        a[3] = 11;
        a[4] = 25;
        a[5] = 2;
        a[6] = 10;
        a[7] = 7;
        a[8] = 8;
        a[9] = 4;
        MaxPQ pq = new MaxPQ(a);
        pq.show();
    }
}
