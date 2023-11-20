public class BTNode {
    private int value, height = 0;
    private BTNode left, right;

    public BTNode(int value) {
        this.value = value;
        this.height = 1;
    }

    public BTNode(Object value, BTNode left, BTNode right) {
        this.value = (Integer) value;
        this.left = left;
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public BTNode getLeft() {
        return left;
    }

    public BTNode getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }
    
    public void setRight(BTNode right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
