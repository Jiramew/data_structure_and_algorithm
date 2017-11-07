package find;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return this.size(this.root);
    }

    public Value get(Key key) {
        return this.get(root, key);
    }

    public void put(Key key, Value val) {
        root = this.put(root, key, val);
    }

    public Key min() {
        return this.min(root).key;
    }

    public Key max() {
        return this.max(root).key;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node delete(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, k);
        else if (cmp > 0) x.right = delete(x.right, k);
        else {
            // 如果左右子节点为null，退化为单节点形式
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            // 用要被删除节点的右子树的最小点替换将要被删除的点
            // 其左子节点连接的为要被删除节点的左子树
            // 右子节点连接的为要被删除节点的右子树的删除最小节点后的树
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        BST<String, Integer> bs = new BST<String, Integer>();
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

        bs.delete("u");

        System.out.println(bs.min());
        System.out.println(bs.max());
    }
}

//       o
//   f       u
// c   j   r   w
//b d i k q s v z
