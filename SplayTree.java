public class SplayTree {
    BTNode root;

    private BTNode rightRotate(BTNode x) {
        BTNode y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        return y;
    }

    private BTNode leftRotate(BTNode x) {
        BTNode y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        return y;
    }

    private BTNode splay(BTNode node, int value) {
        if (node == null || node.getValue() == value) {
            return node;
        }
        if (node.getValue() > value) {
            if (node.getLeft() == null) {
                return node;
            }
            if (node.getLeft().getValue() > value) {
                node.getLeft().setLeft(splay(node.getLeft().getLeft(), value));
                node = rightRotate(node);
            } else if (node.getLeft().getValue() < value) {
                node.getLeft().setRight(splay(node.getLeft().getRight(), value));
                if (node.getLeft().getRight() != null) {
                    node.setLeft(leftRotate(node.getLeft()));
                }
            }
            return (node.getLeft() == null) ? node : rightRotate(node);
        } else {
            if (node.getRight() == null) {
                return node;
            }
            if (node.getRight().getValue() > value) {
                node.getRight().setLeft(splay(node.getRight().getLeft(), value));
                if (node.getRight().getLeft() != null) {
                    node.setRight(rightRotate(node.getRight()));
                }
            } else if (node.getRight().getValue() < value) {
                node.getRight().setRight(splay(node.getRight().getRight(), value));
                node = leftRotate(node);
            }
            return (node.getRight() == null) ? node : leftRotate(node);
        }
    }

    private BTNode insert(BTNode node, int value) {
        if (node == null) {
            return new BTNode(value);
        }
        node = splay(node, value);

        if (node.getValue() == value) {
            return node;
        }

        BTNode nBtNode = new BTNode(value);
        if (node.getValue() > value) {
            nBtNode.setRight(node);
            nBtNode.setLeft(node.getLeft());
            node.setLeft(null);
        } else {
            nBtNode.setLeft(node);
            nBtNode.setRight(node.getRight()); // Fix the typo here (remove setRight(null))
            node.setRight(null); // This line is not necessary and can be removed
        }
        return nBtNode; // Return the new node instead of the original 'node'
    }

    public void add(int value) {
        root = insert(root, value);
    }

    private void preOrder(BTNode node) {
        if (node != null) {
            System.out.print(node.getValue() + ", ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    private BTNode delete(BTNode node, int value) {
        if (node == null) {
            return null;
        }

        // Splay the tree to bring the node to the root
        node = splay(node, value);

        // If the value is not found, no need to delete
        if (value != node.getValue()) {
            return node;
        }

        // If the node has a left child
        if (node.getLeft() != null) {
            BTNode temp = node;
            node = splay(node.getLeft(), value);
            node.setRight(temp.getRight());
        } else {
            // If the node has no left child, simply make the right child the new root
            node = node.getRight();
        }

        return node;
    }

    public void remove(int value) {
        root = delete(root, value);
    }

    public void printTree() {
        preOrder(root);
    }
}
