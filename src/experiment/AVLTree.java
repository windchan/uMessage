package experiment;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;

/**
 * TODO: Replace this comment with your own as appropriate.
 *
 * AVLTree must be a subclass of BinarySearchTree<E> and must use inheritance
 * and calls to superclass methods to avoid unnecessary duplication or copying
 * of functionality.
 *
 * 1. Create a subclass of BSTNode, perhaps named AVLNode. 2. Override the
 * insert method such that it creates AVLNode instances instead of BSTNode
 * instances. 3. Do NOT "replace" the children array in BSTNode with a new
 * children array or left and right fields in AVLNode. This will instead mask
 * the super-class fields (i.e., the resulting node would actually have multiple
 * copies of the node fields, with code accessing one pair or the other
 * depending on the type of the references used to access the instance). Such
 * masking will lead to highly perplexing and erroneous behavior. Instead,
 * continue using the existing BSTNode children array. 4. If this class has
 * redundant methods, your score will be heavily penalized. 5. Cast children
 * array to AVLNode whenever necessary in your AVLTree. This will result a lot
 * of casts, so we recommend you make private methods that encapsulate those
 * casts. 6. Do NOT override the toString method. It is used for grading.
 */

public class AVLTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

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
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        V oldValue = find(key);
        this.root = insert((AVLNode) root, key, value);
        return oldValue;
    }

    // Basically equivalent to BST private find
    @SuppressWarnings("unchecked")
    private AVLNode insert(AVLNode current, K key, V value) {
        if (current != null) {
            int direction = Integer.signum(key.compareTo(current.key));
            if (direction == 0) {
                current.value = value;
                return current;
            }
            else {
                // This is the situation where we modify the structure of the
                // tree.
                int child = Integer.signum(direction + 1);
                AVLNode ret;
                if (current.getAVLChildren(child) != null) {
                    current.children[child] = insert(current.getAVLChildren(child), key, value);
                } else {
                    current.children[child] = new AVLNode(key, value, 0);
                    size++;
                }

                updateHeight(current);

                if (heightDiff(current) >= 2) {
                    if (heightDiff(current.getAVLChildren(0)) < 0) {
                        current.children[0] = rotate(current.getAVLChildren(0), 1);
                    }
                    return rotate(current, 0);
                }
                else if (heightDiff(current) <= -2) {
                    if (heightDiff(current.getAVLChildren(1)) > 0) {
                        current.children[1] = rotate(current.getAVLChildren(1), 0);
                    }
                    return rotate(current, 1);
                }
                return current;
            }
        }
        else {
            // add to root.
            root = new AVLNode(key, value, 0);
            current = (AVLNode) root;
            size++;
            return current;
        }
    }

    @SuppressWarnings("unchecked")
    private AVLNode rotate(AVLNode parent, int childIndex) {
        int oppositeIndex = 1 - childIndex;
        AVLNode temp = parent.getAVLChildren(childIndex);
        parent.children[childIndex] = parent.getAVLChildren(childIndex)
                .getAVLChildren(oppositeIndex);
        temp.children[oppositeIndex] = parent;

        updateHeight(parent);
        updateHeight(temp);

//        if (this.root == parent) {
//            this.root = temp;
//        }
        return temp;
    }

    private void updateHeight(AVLNode current) {
        int leftHeight = (current.children[0] == null) ? -1
                : current.getAVLChildren(0).getHeight();
        int rightHeight = (current.children[1] == null) ? -1
                : current.getAVLChildren(1).getHeight();
        current.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private int heightDiff(AVLNode current) {
        if (current == null) {
            return -1;
        }
        int leftHeight = (current.children[0] == null) ? -1
                : current.getAVLChildren(0).getHeight();
        int rightHeight = (current.children[1] == null) ? -1
                : current.getAVLChildren(1).getHeight();
        return leftHeight - rightHeight;
    }

}
