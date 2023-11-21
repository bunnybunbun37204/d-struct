/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.add(8);
        tree.add(6);
        tree.add(7);
        tree.add(2);
        tree.add(5);
        tree.remove(7);
        tree.printTree();
        System.out.println();
        AVLTree tree2 = new AVLTree();
        tree2.add(3);
        tree2.add(2);
        tree2.add(1);
        tree2.add(4);
        // tree2.remove(4);
        tree2.printTree();

    }
}