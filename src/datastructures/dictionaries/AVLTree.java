package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;

/**
 * TODO: Replace this comment with your own as appropriate.
 *
 * AVLTree must be a subclass of BinarySearchTree<E> and must use
 * inheritance and calls to superclass methods to avoid unnecessary
 * duplication or copying of functionality.
 *
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override the insert method such that it creates AVLNode instances
 *    instead of BSTNode instances.
 * 3. Do NOT "replace" the children array in BSTNode with a new
 *    children array or left and right fields in AVLNode.  This will 
 *    instead mask the super-class fields (i.e., the resulting node 
 *    would actually have multiple copies of the node fields, with 
 *    code accessing one pair or the other depending on the type of 
 *    the references used to access the instance).  Such masking will 
 *    lead to highly perplexing and erroneous behavior. Instead, 
 *    continue using the existing BSTNode children array.
 * 4. If this class has redundant methods, your score will be heavily
 *    penalized.
 * 5. Cast children array to AVLNode whenever necessary in your
 *    AVLTree. This will result a lot of casts, so we recommend you make
 *    private methods that encapsulate those casts.
 * 6. Do NOT override the toString method. It is used for grading.
 */

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V>  {
    // TODO: Implement me!
    
    public class AVLNode extends BSTNode {
        private int height;
        
        public int getHeight() {
            return this.height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public AVLNode(K key, V value, int height) {
            super(key, value);
            this.height = height;
        }
        
        public AVLNode getAVLChildren(int child) {
            return (AVLNode) children[child];
        }
    }
    
    public AVLTree() {
        super();
    }
    @Override
    public V insert(K key, V value) {
        insert((AVLNode)root, key, value);
        
        
    }
    
    private void insert(AVLNode current, K key, V value) {
        if (current != null) {
            int direction = Integer.signum(key.compareTo(current.key));
            if (direction == 0) {
                current.value = value;
            } else {
                int child = Integer.signum(direction + 1);
                if (current.getAVLChildren(child) != null) {
                    insert(current.getAVLChildren(child), key, value);
                    
                } else {
                    current.children[child] = new AVLNode(key, value, 0);
                    current.setHeight(Math.max(current.getAVLChildren(0).getHeight(), 
                          current.getAVLChildren(1).getHeight()) + 1);
                }
            }
        } else {
            current = new AVLNode(key, value, 0);
        }
    }
    
    @SuppressWarnings("unchecked")

    private rotate() {
        
    }
}
