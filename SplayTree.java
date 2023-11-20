class BTNode {
    private int value;
    private BTNode left;
    private BTNode right;

    public BTNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public BTNode getLeft() {
        return left;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }
}

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

        node = insertWithoutSplay(node, value); // Add the node without splaying
        node = splay(node, value); // Splay the tree after adding the node

        return node;
    }

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

    public void remove(int value) {
        root = delete(root, value);
    }

    public void printTree() {
        preOrder(root);
    }
}
