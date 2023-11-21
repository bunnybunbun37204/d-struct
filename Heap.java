public class Heap {
    private int[] heapArray;
    private int size;

    public Heap(int capacity) {
        heapArray = new int[capacity];
        size = 0;
    }

    public void buildHeap(int[] arr, int numNode) {
        if (numNode > heapArray.length) {
            throw new IllegalArgumentException("Input array is too large");
        }

        size = numNode;
        System.arraycopy(arr, 0, heapArray, 0, size);

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + ",");
        }
        System.out.println();
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && heapArray[leftChild] < heapArray[smallest]) {
            smallest = leftChild;
        }

        if (rightChild < size && heapArray[rightChild] < heapArray[smallest]) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public static void main(String[] args) {
        // Create an instance of MinHeap with a specified capacity
        Heap h = new Heap(10);

        int[] arr1 = {2, 7, 8, 10, 11, 3};

        // Use the buildHeap method to build the MinHeap
        h.buildHeap(arr1, arr1.length);

        // Use the printHeap method to print the MinHeap
        h.printHeap();
    }
}
