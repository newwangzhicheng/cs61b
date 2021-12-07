import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    private int[] parents;
    private int size;

    /**
     * 创建一个长度为n的DisjointSets
     * 
     * @param n
     */
    public UnionFind(int n) {
        size = n;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    /**
     * 判断两个元素是否连接
     * 
     * @param v1
     * @param v2
     * @return
     */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int rootOfV1 = find(v1);
        int rootOfV2 = find(v2);
        return rootOfV1 == rootOfV2;
    }

    /**
     * 连接两个元素
     */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int rootOfV1 = find(v1);
        int rootOfV2 = find(v2);
        if (rootOfV1 == rootOfV2) {
            return;
        }
        int sizeOfV1 = sizeOf(v1);
        int sizeOfV2 = sizeOf(v2);
        if (sizeOfV1 < sizeOfV2) {
            // v1的根节点设为v2
            parents[rootOfV1] = rootOfV2;
            // 更新v2的节点内容
            parents[rootOfV2] = -(sizeOfV1 + sizeOfV2);
        } else {
            // v2的根节点设为v1
            parents[rootOfV2] = rootOfV1;
            // 更新v1的节点内容
            parents[rootOfV1] = -(sizeOfV1 + sizeOfV2);
        }
    }

    /**
     * 找到v1的根节点
     * 
     * @param v1
     * @return
     */
    public int find(int v1) {
        validate(v1);
        int root = v1;

        List<Integer> items = new ArrayList<>();
        while (parent(root) >= 0) {
            items.add(root);
            root = parent(root);
        }
        for (int item : items) {
            parents[item] = root;
        }
        return root;
    }

    /**
     * 返回v1的父节点，如果v1是根节点，返回v1所在树的-size
     * 
     * @param v1
     * @return
     */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /**
     * 返回v1所在树的大小
     * 
     * @param v1
     * @return
     */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -parent(root);
    }

    /**
     * 判断下标是否合法
     * 
     * @param v1
     * @throws IllegalArgumentException
     */
    public void validate(int v1) throws IllegalArgumentException {
        if (v1 < 0 || v1 > size - 1) {
            throw new IllegalArgumentException("number must more than or equals 0 and less than size - 1");
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(9);
        uf.union(2, 3);
        uf.union(1, 6);
        uf.union(5, 7);
        uf.union(8, 4);
        uf.union(7, 2);
        uf.find(3);
        uf.union(6, 4);
        uf.union(6, 3);
        uf.find(7);
        uf.find(8);
    }

}