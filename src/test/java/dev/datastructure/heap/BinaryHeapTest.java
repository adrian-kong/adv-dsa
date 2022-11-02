package dev.datastructure.heap;

import dev.datastructure.heap.impl.BinaryHeap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryHeapTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

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

        // [1, 2, 2, 2, 3, 3, 4, 5, 5, 7] ascending order, min heap
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

        assertEquals(0, heap.getI());
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

        assertEquals(0, heap.getI());
    }
}
