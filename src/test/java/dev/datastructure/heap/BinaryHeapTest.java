package dev.datastructure.heap;

import dev.datastructure.heap.impl.BinaryHeap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryHeapTest {

    public static void main(String[] args) {
        var heap = new BinaryHeap<>(4, Integer::compare);
        int[] arr = new int[]{10, 3, 4, 5};

        for (int i : arr) {
            heap.insert(i);
            System.out.println(heap + " " + heap.validate());
        }

        for (int i : arr) {
            System.out.println(heap.pop() + " " + heap.validate());
        }
    }

    @Test
    public void testIntegerBinaryHeap() {
        var heap = new BinaryHeap<>(20, Integer::compare);

        int[] arr = new int[]{5, 3, 2, 5, 7, 2, 3, 4, 1, 2};

        for (int i : arr) {
            heap.insert(i);
            assertTrue(heap.validate());
        }

        int[] sorted = Arrays.stream(arr).sorted().toArray();

        for (int i = 0; i < arr.length; i++) {
            assertEquals(sorted[i], heap.pop());
            assertTrue(heap.validate());
        }

        assertEquals(0, heap.i);
    }

    @Test
    public void testSortOrder() {
        Comparator<Integer> cmp = ((Comparator<Integer>) (Integer::compare)).reversed();
        var heap = new BinaryHeap<>(20, cmp);

        Integer[] arr = new Integer[]{5, 3, 2, 5, 7, 2, 3, 4, 1, 2};

        for (int i : arr) {
            heap.insert(i);
            assertTrue(heap.validate());
        }

        var sorted = Arrays.stream(arr).sorted(cmp).toList();

        for (int i = 0; i < arr.length; i++) {
            assertEquals(sorted.get(i), heap.pop());
            assertTrue(heap.validate());
        }

        assertEquals(0, heap.i);
    }
}