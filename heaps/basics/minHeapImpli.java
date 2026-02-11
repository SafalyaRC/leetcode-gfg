import java.util.*;
public class minHeapImpli {
    ArrayList<Integer> heap;

    minHeapImpli() {
        heap = new ArrayList<>();
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int leftChild(int i) {
        return 2 * i + 1;
    }

    public int rightChild(int i) {
        return 2 * i + 2;
    }

    public void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public void insert(int val) {
        heap.add(val);
        int i = heap.size() - 1;

        // heap-up
        while (i > 0 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void printHeap() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }

    public int deleteMin() {
        if (heap.isEmpty())
            return -1;
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public void heapifyDown(int i) {
        int smallest = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < heap.size() && heap.get(l) < heap.get(smallest))
            smallest = l;
        if (r < heap.size() && heap.get(r) < heap.get(smallest))
            smallest = r;

        if (i != smallest) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    public static void main(String[] args) {
        minHeapImpli heap = new minHeapImpli();
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);
        heap.insert(1);
        heap.printHeap();
        System.out.println(heap.deleteMin());
        heap.printHeap();
    }
}
