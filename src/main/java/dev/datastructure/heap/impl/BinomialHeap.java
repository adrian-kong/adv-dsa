package dev.datastructure.heap.impl;

import dev.datastructure.heap.Heap;
import dev.datastructure.heap.impl.binomial.BinomialHeapNode;
import lombok.Getter;

import java.util.Comparator;

public class BinomialHeap<T> extends Heap<T> {

    @Getter private BinomialHeapNode<T> ptr;


    @Getter private BinomialHeapNode[]

    /**
     * Heap constructor
     *
     * @param comparator to give ordered-ness
     */
    public BinomialHeap(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void insert(T item) {

        if (ptr == null) {
            ptr = new BinomialHeapNode<>(item);
            size = 1;
            return;
        }

        BinomialHeapNode<T>[] bits = new BinomialHeapNode[size + 1];
        bits[ptr.getDepth()] = ptr;

        BinomialHeapNode next;
        while ((next = ptr.getRight()) != ptr) {
            bits[next.getDepth()] = next;


        }

        bits[ptr.getDepth()] = ptr;


    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return ptr == null ? null : ptr.getItem();
    }

    public void merge(BinomialHeap<T> otherHeap) {

    }

}
