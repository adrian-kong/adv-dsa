package dev.datastructure.heap;

import dev.datastructure.heap.impl.BinaryHeap;

public class BinaryHeapTest {

    public static void main(String[] args) {
        var a = new BinaryHeap<>(4, Integer::compare);
        a.insert(10);
        System.out.println(a);
        a.insert(3);
        System.out.println(a);
        a.insert(4);
        System.out.println(a);
        a.insert(5);
        System.out.println(a);
        System.out.println(a.pop());
        System.out.println(a);
        System.out.println(a.pop());
        System.out.println(a);
        System.out.println(a.pop());
        System.out.println(a);
        System.out.println(a.pop());
        System.out.println(a);
    }

}
