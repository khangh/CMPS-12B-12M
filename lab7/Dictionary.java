/**
 * File: Dictionary.java
 * Name: Sophia Tacderas
 * Due: 12/2/16, 10 pm
 * Class: CMPS 12M
 * Assignment: lab 7
 * Purpose: Implements the Dictionary interface with a binary search tree.
 * Borrows/modifies code from: Dictionary.c, pa3
 */

public class Dictionary implements DictionaryInterface {

    // private types and functions ------------------------------------------------

    // private inner Node class
    private class Node {
        // key is distinct/unique
        String key;
        // value may repeat
        String value;
        // pointer to left and right Nodes
        Node leftNode;
        Node rightNode;

        // constructor for private Node type
        Node(String x, String y) {
            key = x;
            value = y;
            leftNode = null;
            rightNode = null;
        }
    }

    // Private fields for the Dictionary class
    private Node root; // reference to root Node in Dictionary BST
    private int numPairs; // number of pairs in this Dictionary

    // findKey()
    // returns the Node containing key k in the subtree rooted at R, or returns
    // null if no such Node exists
    private Node findKey(Node R, String k) {
        if (R == null || k.compareTo(R.key) == 0) {
            return R;
        }
        if (k.compareTo(R.key) < 0) {
            return findKey(R.leftNode, k);
        } else { // k.compareTo(R.key) > 0
            return findKey(R.rightNode, k);
        }
    }

    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns null
    // if N is equal to R. (pre: R != null)
    private Node findParent(Node N, Node R) {
        Node P = null;
        if (N != R) {
            P = R;
            while ((P.leftNode != N) && (P.rightNode != N)) {
                if ((P.key).compareTo(R.key) < 0) {
                    P = P.leftNode;
                } else { // (P.key).compareTo(R.key) > 0
                    P = P.rightNode;
                }
            }
        }
        return P;
    }

    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or null if R is null.
    private Node findLeftmost(Node R) {
        Node L = R;
        if (L != null) {
            for (; L.leftNode != null; L = L.leftNode) ;
        }
        return L;
    }

    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R in order
    // of increasing keys to file pointed to by out.
    private void printInOrder(Node R) {
        if (R != null) {
            printInOrder(R.leftNode);
            System.out.println(R.key + " " + R.value);
            printInOrder(R.rightNode);
        }
    }

    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    private void deleteAll(Node N) {
        if (N != null) {
            deleteAll(N.leftNode);
            deleteAll(N.rightNode);
        }
    }

    // public functions -----------------------------------------------------------

    // Constructor for the Dictionary class
    public Dictionary() {
        root = null;
        numPairs = 0;
    }

    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty() {
        if (numPairs == 0) {
            return true;
        } else {
            return false;
        }
    }

    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size() {
        return numPairs;
    }

    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key) {
        Node N;
        N = findKey(root, key);
        if (N != null) {
            return N.value;
        } else {
            return null;
        }
    }

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException {
        Node N, A, B;
        if (lookup(key) != null) {
            throw new DuplicateKeyException(
                    "Dictionary Error: cannot insert() duplicate key: \"" + key + "\"");
        }
        N = new Node(key, value);
        B = null;
        A = root;
        while (A != null) {
            B = A;
            if (key.compareTo(A.key) < 0) {
                A = A.leftNode;
            } else { // key.compareTo(A.key) > 0
                A = A.rightNode;
            }
        }
        if (B == null) {
            root = N;
        } else if (key.compareTo(B.key) < 0) {
            B.leftNode = N;
        } else { // key.compareTo(B.key) > 0
            B.rightNode = N;
        }
        numPairs++;
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException {
        Node N, P, S;
        if (lookup(key) == null) {
            throw new KeyNotFoundException(
                    "Dictionary Error: cannot delete() non-existent key: \"" + key + "\"");
        } else {
            N = findKey(root, key);
            if (N.leftNode == null && N.rightNode == null) { // case 1 (no children)
                if (N == root) {
                    root = null;
                } else { // N is not the root
                    P = findParent(N, root);
                    if (P.rightNode == N) {
                        P.rightNode = null;
                    } else { // P.leftNode == N
                        P.leftNode = null;
                    }
                }
            } else if (N.rightNode == null) { // case 2 (left but no right child)
                if (N == root) {
                    root = N.leftNode;
                } else { // N is not the root
                    P = findParent(N, root);
                    if (P.rightNode == N) {
                        P.rightNode = N.leftNode;
                    } else { // P.leftNode == N
                        P.leftNode = N.leftNode;
                    }
                }
            } else if (N.leftNode == null) { // case 2 (right but no left child)
                if (N == root) {
                    root = N.rightNode;
                } else { // N is not the root
                    P = findParent(N, root);
                    if (P.rightNode == N) {
                        P.rightNode = N.rightNode;
                    } else { // P.leftNode == N
                        P.leftNode = N.rightNode;
                    }
                }
            } else { // case 3 (two children: N.leftNode!=null && N.rightNode!=null)
                S = findLeftmost(N.rightNode);
                P = findParent(S, N);
                N.key = S.key;
                N.value = S.value;
                if (P.rightNode == S) {
                    P.rightNode = S.rightNode;
                } else { // P.leftNode == S
                    P.leftNode = S.rightNode;
                }
            }
            numPairs--;
        }
    }

    // makeEmpty()
    // pre: none
    public void makeEmpty() {
        deleteAll(root);
        root = null;
        numPairs = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString() {
        printInOrder(root);
        return "";
    }

}
