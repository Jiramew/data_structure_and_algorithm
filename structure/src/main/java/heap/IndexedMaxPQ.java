package heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 描述：索引数组pq保存的是数据集keys中的下标，遍历索引数组可以刻画出二叉堆<br/>
 * 其中keys指的是IndexedMaxPQ.java里的一个成员变量<br/>
 * keys={it,was,the,best,of,times,it,was,the,worst,null,}<br/>
 * 则索引数组pq为{0,9,1,5,8,7,2,6,3,4,0,}<br/>
 * 那么遍历pq,则pq[1]=9,keys[9]=worst;pq[2]=1,keys[1]=was;pq[3]=5,keys[5]= times
 *
 * @author jiramew
 * @since 1.0
 */
public class IndexedMaxPQ<Key extends Comparable<Key>> implements
        Iterable<Integer> {
    private int n; // number of elements on PQ
    private int[] pq; // binary heap using 1-based indexing
    private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys; // keys[i] = priority of i

    public IndexedMaxPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1]; // make this of length maxN??
        pq = new int[maxN + 1];
        qp = new int[maxN + 1]; // make this of length maxN??
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, Key key) {
        if (contains(i)) {
            throw new IllegalArgumentException(
                    "index is already in the priority queue");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int maxIndex() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    public Key maxKey() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    public int delMax() {
        if (n == 0) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int min = pq[1];
        exch(1, n--);
        sink(1);

        assert pq[n + 1] == min;
        qp[min] = -1; // delete
        keys[min] = null; // to help with garbage collection
        pq[n + 1] = -1; // not needed
        return min;
    }

    public Key keyOf(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException(
                    "index is not in the priority queue");
        } else {
            return keys[i];
        }
    }

    public void changeKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException(
                    "index is not in the priority queue");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    @Deprecated
    public void change(int i, Key key) {
        changeKey(i, key);
    }

    public void increaseKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException(
                    "index is not in the priority queue");
        }
        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException(
                    "Calling increaseKey() with given argument would not strictly increase the key");
        }

        keys[i] = key;
        swim(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException(
                    "index is not in the priority queue");
        }
        if (keys[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException(
                    "Calling decreaseKey() with given argument would not strictly decrease the key");
        }

        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException(
                    "index is not in the priority queue");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexedMaxPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexedMaxPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }
    }

    /**
     * Unit tests the {@code IndexMaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it",
                "was", "the", "worst"};

        IndexedMaxPQ<String> pq = new IndexedMaxPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }

        System.out.println();

        while (!pq.isEmpty()) {
            String key = pq.maxKey();
            int i = pq.delMax();
            System.out.println(i + " " + key);
        }
        System.out.println();

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        int[] perm = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            perm[i] = i;
        }
        for (int i = 0; i < perm.length; i++) {
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            System.out.println(perm[i] + " " + key);
        }
    }
}
