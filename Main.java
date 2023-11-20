/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        tree.add(50);
        tree.add(20);
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.printTree();
        System.out.println();
        tree.add(3);
        tree.printTree();
        System.out.println();
        tree.remove(15);
        tree.printTree();
        System.out.println();
        SplayTree tree2 = new SplayTree();
        tree2.add(8);
        tree2.add(4);
        tree2.add(7);
        tree2.add(40);
        tree2.add(15);
        tree2.add(13);
        tree2.add(16);
        tree2.add(1);
        tree2.printTree();

    }
}