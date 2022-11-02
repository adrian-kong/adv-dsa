package dev.datastructure.heap;

import dev.datastructure.heap.impl.BinomialHeap;

import java.util.Arrays;

public class BinomialHeapTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public static void main(String[] args) {
        var heap = new BinomialHeap<>(5, Integer::compare);

        var arr = new int[]{3, 4, 2, 5, 1, 2, 3};

        for (int i : arr) {
            heap.insert(i);
            System.out.println("inserted " + i);
            Arrays.stream(heap.getRoots()).forEach(System.out::println);
        }
    }

}
