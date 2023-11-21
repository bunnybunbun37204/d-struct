public class AVLTree {

    BTNode root;

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int height(BTNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private BTNode rightRotation(BTNode y) {
        if (y == null) {
            return null; // or throw an exception, depending on your error handling strategy
        }

        BTNode x = y.getLeft();

        if (x == null) {
            return y; // no left child to rotate with
        }

        BTNode t = x.getRight();

        // Rotate
        x.setRight(y);
        y.setLeft(t);

        // Update height
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private BTNode leftRotation(BTNode x) {
        if (x == null) {
            return null; // or throw an exception, depending on your error handling strategy
        }

        BTNode y = x.getRight();

        if (y == null) {
            return x; // no right child to rotate with
        }

        BTNode t = y.getLeft();

        // Rotate
        y.setLeft(x);
        x.setRight(t);

        // Update height
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    private int getBalance(BTNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private BTNode add(BTNode node, int value) {
        if (node == null) {
            return new BTNode(value);
        }

        if (value < node.getValue()) {
            node.setLeft(add(node.getLeft(), value)); 
        } else if (value > node.getValue()) {
            node.setRight(add(node.getRight(), value));
        } else {
            return node;
        }

        // Update height after add
        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));

        return balanceNode(node, value);
    }

    private BTNode balanceNode(BTNode node, int value) {
        // get Node balance
        int balance = getBalance(node);

        // if Unbalance

        // Case 1 : Left > Right and value < Node.left
        if (balance > 1 && value < node.getLeft().getValue()) {
            return rightRotation(node);
        }
        // Case 2 : Left < Right and value > Node.right
        if (balance < -1 && value > node.getRight().getValue()) {
            return leftRotation(node);
        }

        // Case 3 : Left > Right and value > Node.left Left Right case
        if (balance > 1 && value > node.getLeft().getValue()) {
            node.setLeft(leftRotation(node.getLeft()));
            return rightRotation(node);
        }

        // Case 3 : Left < Right and value < Node.right Right Left case
        if (balance < -1 && value < node.getRight().getValue()) {
            node.setRight(rightRotation(node.getRight()));
            return leftRotation(node);
        } else {
            return node;
        }
    }

    public BTNode minValue(BTNode node) {
        BTNode current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public BTNode deleteNode(BTNode node, int value) {
        if (node == null) {
            return node;
        }

        if (value < node.getValue()) {
            node.setLeft(deleteNode(node.getLeft(), 
            value));
        } else if (value > node.getValue()) {
            node.setRight(deleteNode(node.getRight(), value));
        }

        else {
            // if left or right is null
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                BTNode temp = null;
                if (temp == node.getLeft()) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }

                // no child
                if (temp == null) {
                    temp = node;
                    node = null;
                }
                // one child
                else {
                    node = temp;
                }
            } else {
                // node with two child
                BTNode temp = minValue(node.getRight());

                // copy to root
                node.setValue(temp.getValue());

                // delete
                node.setRight(deleteNode(node.getRight(), temp.getValue()));
            }
        }
        if (node == null) {
            return node;
        }

        // Update Height
        node.setHeight(max(height(node.getLeft()), height(node.getRight())));
        System.out.println();
        printTree();
        return balanceNode(node, value);
    }

    public void add(int value) {
        root = add(root, value);
    }

    public void remove(int value) {
        root = deleteNode(root, value);
    }

    private void preOrder(BTNode node) {
        if (node != null) {
            System.out.print(node.getValue() + ", ");
            preOrder(node.getLeft());
            System.out.print("sp");
            preOrder(node.getRight());
        }
    }

    public void printTree() {
        preOrder(root);
    }

}
