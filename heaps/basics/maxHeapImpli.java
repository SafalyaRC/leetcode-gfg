import java.util.*;

public class maxHeapImpli {
    List<Integer> heap;

    public maxHeapImpli() {
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

        while (i > 0 && heap.get(parent(i)) < heap.get(i)) {
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

    public int deleteMax() {
        if (heap.isEmpty())
            return -1;
        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return max;
    }

    public void heapifyDown(int i) {
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < heap.size() && heap.get(l) > heap.get(largest))
            largest = l;
        if (r < heap.size() && heap.get(r) > heap.get(largest))
            largest = r;

        if (i != largest) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    public static void main(String[] args) {
        maxHeapImpli heap = new maxHeapImpli();
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);
        heap.insert(1);
        heap.printHeap();
        System.out.println(heap.deleteMax());
        heap.printHeap();
    }
}
