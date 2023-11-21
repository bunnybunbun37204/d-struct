// ** Represents a node in the binary tree
class BTNode {
    private int value;
    private BTNode left;
    private BTNode right;

    // ** Node constructor
    public BTNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // ** Getter method for the node value
    public int getValue() {
        return value;
    }

    // ** Getter method for the left child
    public BTNode getLeft() {
        return left;
    }

    // ** Setter method for the left child
    public void setLeft(BTNode left) {
        this.left = left;
    }

    // ** Getter method for the right child
    public BTNode getRight() {
        return right;
    }

    // ** Setter method for the right child
    public void setRight(BTNode right) {
        this.right = right;
    }
}

// ** Represents a Splay Tree
public class SplayTree {
    BTNode root;

    // ** Rotates the given node to the right
    private BTNode rightRotate(BTNode x) {
        BTNode y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        return y;
    }

    // ** Rotates the given node to the left
    private BTNode leftRotate(BTNode x) {
        BTNode y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        return y;
    }

    // ** Splays the tree to bring the node with the specified value to the root
    private BTNode splay(BTNode node, int value) {
        // If the current node is null or its value is equal to the target value,
        // we have reached the target node or an empty subtree, and we return the current node.
        if (node == null || node.getValue() == value) {
            return node;
        }

        // If the target value is smaller than the current node's value,
        // we perform splaying in the left subtree.
        if (node.getValue() > value) {
            // If the left child is null, the target value is not in the tree,
            // and we return the current node.
            if (node.getLeft() == null) {
                return node;
            }

            // Zig-Zig (Left Left case)
            if (node.getLeft().getValue() > value) {
                // Recursively splay in the left-left subtree
                node.getLeft().setLeft(splay(node.getLeft().getLeft(), value));
                // Perform a right rotation to bring the current node up
                node = rightRotate(node);
            }
            // Zig-Zag (Left Right case)
            else if (node.getLeft().getValue() < value) {
                // Recursively splay in the left-right subtree
                node.getLeft().setRight(splay(node.getLeft().getRight(), value));
                // If there is a right child in the left subtree, perform a left rotation on it
                if (node.getLeft().getRight() != null) {
                    node.setLeft(leftRotate(node.getLeft()));
                }
            }
            // Zig (Left case)
            // In this case, we have performed a single right rotation
            return (node.getLeft() == null) ? node : rightRotate(node);
        } 
        // If the target value is greater than the current node's value,
        // we perform splaying in the right subtree.
        else {
            // If the right child is null, the target value is not in the tree,
            // and we return the current node.
            if (node.getRight() == null) {
                return node;
            }

            // Zag-Zig (Right Left case)
            if (node.getRight().getValue() > value) {
                // Recursively splay in the right-left subtree
                node.getRight().setLeft(splay(node.getRight().getLeft(), value));
                // If there is a left child in the right subtree, perform a right rotation on it
                if (node.getRight().getLeft() != null) {
                    node.setRight(rightRotate(node.getRight()));
                }
            }
            // Zag-Zag (Right Right case)
            else if (node.getRight().getValue() < value) {
                // Recursively splay in the right-right subtree
                node.getRight().setRight(splay(node.getRight().getRight(), value));
                // Perform a left rotation to bring the current node up
                node = leftRotate(node);
            }
            // Zag (Right case)
            // In this case, we have performed a single left rotation
            return (node.getRight() == null) ? node : leftRotate(node);
        }
    }

    // ** Inserts a new node with the specified value into the tree
    private BTNode insert(BTNode node, int value) {
        if (node == null) {
            return new BTNode(value);
        }

        node = insertWithoutSplay(node, value); // Add the node without splaying
        node = splay(node, value); // Splay the tree after adding the node

        return node;
    }

    // ** Inserts a new node without splaying the tree
    private BTNode insertWithoutSplay(BTNode node, int value) {
        if (node == null) {
            return new BTNode(value);
        }

        if (value < node.getValue()) {
            node.setLeft(insertWithoutSplay(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertWithoutSplay(node.getRight(), value));
        }

        return node;
    }

    // ** Public method to add a new node to the tree
    public void add(int value) {
        root = insert(root, value);
    }

    // ** Traverses the tree in pre-order and prints the node values
    private void preOrder(BTNode node) {
        if (node != null) {
            System.out.print(node.getValue() + ", ");
            preOrder(node.getLeft());
            System.out.print("sp");
            preOrder(node.getRight());
        }
    }

    // ** Deletes the node with the specified value from the tree
    private BTNode delete(BTNode node, int value) {
        if (node == null) {
            return null;
        }

        node = splay(node, value);

        if (value != node.getValue()) {
            return node;
        }

        if (node.getLeft() != null) {
            BTNode temp = node;
            node = splay(node.getLeft(), value);
            node.setRight(temp.getRight());
        } else {
            node = node.getRight();
        }

        return node;
    }

    // ** Public method to remove a node with the specified value from the tree
    public void remove(int value) {
        root = delete(root, value);
    }

    // ** Public method to print the tree using pre-order traversal
    public void printTree() {
        preOrder(root);
    }
}
