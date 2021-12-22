package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root; /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        }
        return p.value;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        Node parent = p;
        while (true) {
            if (key.compareTo(parent.key) < 0) {
                if (parent.left == null) {
                    parent.left = new Node(key, value);
                    break;
                } else {
                    parent = parent.left;
                }
            } else if (key.compareTo(parent.key) > 0) {
                if (parent.right == null) {
                    parent.right = new Node(key, value);
                    break;
                } else {
                    parent = parent.right;
                }
            }
        }
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (get(key) == null) {
            root = putHelper(key, value, root);
            size++;
        } else {
            Node node = getNodeHelper(key, root);
            node.value = value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    /**
     * iterate BST with recursion
     * 
     * @param p
     * @return
     */
    private void keySetHelper(Node p, Set<K> set) {
        if (p != null) {
            set.add(p.key);
            keySetHelper(p.left, set);
            keySetHelper(p.right, set);
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySetHelper(root, set);
        return set;
    }

    /**
     * get the parent node of p
     * 
     * @param key
     * @param p
     * @return
     */
    private Node getParentNodeHelper(K key, Node p) {
        if ((p.left != null && p.left.key.compareTo(key) == 0) ||
                (p.right != null && p.right.key.compareTo(key) == 0)) {
            return p;
        }
        return null;
    }

    /**
     * get the node of p
     * 
     * @param key
     * @param p
     * @return
     */
    private Node getNodeHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return getNodeHelper(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return getNodeHelper(key, p.right);
        }
        return p;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        /** key does not present */
        if (get(key) == null) {
            return null;
        }

        size--;
        /** node to delete is root */
        if (key.compareTo(root.key) == 0) {
            V value = root.value;
            root = null;
            return value;
        }

        /** node to delete is not leaf */
        Node replacedNode = getNodeHelper(key, root);
        Node parentDeletedNode = replacedNode;
        Node deletedNode = null;
        if (replacedNode.left != null || replacedNode.right != null) {
            if (replacedNode.left != null) {
                /** find biggest node to replace deleted node in the left tree */
                deletedNode = parentDeletedNode.left;
                if (deletedNode.right == null) {
                    parentDeletedNode.left = deletedNode.left;
                } else {
                    while (deletedNode.right != null) {
                        parentDeletedNode = deletedNode;
                        deletedNode = deletedNode.right;
                    }
                    parentDeletedNode.right = deletedNode.left;
                }
            } else {
                /** find smallest node to replace deleted node in the left tree */
                deletedNode = parentDeletedNode.right;
                if (deletedNode.left == null) {
                    parentDeletedNode.right = deletedNode.right;
                } else {
                    while (deletedNode.left != null) {
                        parentDeletedNode = deletedNode;
                        deletedNode = deletedNode.left;
                    }
                    parentDeletedNode.left = deletedNode.right;
                }
            }
            V value = replacedNode.value;
            replacedNode.key = deletedNode.key;
            replacedNode.value = deletedNode.value;
            return value;
        }

        /** node to delete is leaf */
        parentDeletedNode = getParentNodeHelper(key, root);
        V value = parentDeletedNode.value;
        if (parentDeletedNode.left != null && parentDeletedNode.left.key.compareTo(key) == 0) {
            parentDeletedNode.left = null;
        } else {
            parentDeletedNode.right = null;
        }
        return value;

    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value. Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) != null && get(key).equals(value)) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
