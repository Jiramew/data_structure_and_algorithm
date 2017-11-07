package find;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    // 1、所有红链接均为左连接
    // 2、所有节点不能有两个红链接
    // 3、所有空子节点（叶子）到根节点的层数为途经黑色链接的个数，且一致
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        boolean color;

        private Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    //////

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        // 结点有右红节点，并且其左节点为黑，需将右红变为左红
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        // 节点有左红节点，并且左红节点仍有左红节点，需将两红节点转变
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        // 节点有左右红节点，需反色
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> bs = new RedBlackBST<String, Integer>();
        bs.put("o", 1);
        bs.put("f", 2);
        bs.put("u", 3);
        bs.put("c", 3);
        bs.put("j", 1);
        bs.put("r", 2);
        bs.put("w", 3);
        bs.put("b", 3);
        bs.put("d", 1);
        bs.put("i", 2);
        bs.put("k", 3);
        bs.put("q", 3);
        bs.put("s", 3);
        bs.put("v", 3);
        bs.put("z", 3);
    }
}
