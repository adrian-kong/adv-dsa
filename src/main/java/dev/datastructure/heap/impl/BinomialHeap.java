package dev.datastructure.heap.impl;

import dev.datastructure.heap.Heap;
import dev.datastructure.heap.BinomialHeapNode;
import dev.util.ArrayUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

public class BinomialHeap<T> extends Heap<T> {

    @Getter private final BinomialHeapNode<T>[] roots;

    private int peakIndex = 0;

    /**
     * Heap constructor
     *
     * @param capacity   of root, would mean 2^n max nodes in entire heap
     * @param comparator to give ordered-ness
     */
    @SuppressWarnings("unchecked")
    public BinomialHeap(int capacity, Comparator<T> comparator) {
        super(comparator);
        this.roots = new BinomialHeapNode[capacity];
    }

    @Override
    public void insert(T item) {
        var sum = new BinomialHeapNode<>(item);
        for (int i = 0; i < roots.length; i++) {
            if (roots[i] == null) {
                roots[i] = sum;
                if (comparator.compare(roots[peakIndex].getItem(), sum.getItem()) > 0)
                    peakIndex = i;
                break;
            }
            sum = mergeNodes(sum, roots[i]);
            assert sum.getSubTrees().size() == i + 1;
            roots[i] = null;
        }
    }

    @Override
    public T pop() {

        var peakNode = roots[peakIndex];

        for (int i = peakNode.getSubTrees().size() - 1; i >= 0; i--) {
            int order = peakIndex - i;
            var node = peakNode.getSubTrees().get(i);




        }

        return null;
    }

    @Override
    public T peek() {
        return roots[peakIndex] == null ? null : roots[peakIndex].getItem();
    }


    /**
     * Helper method to merge two binomial heap nodes,
     * <p>
     * This method justified not a method inside BinomialHeapNode as T isn't comparable,
     * and thus comparison caller should be propagated upwards via taking two objects
     *
     * @param a binomial heap root node
     * @param b binomial heap root node
     * @return new binomial heap root node
     */
    private BinomialHeapNode<T> mergeNodes(BinomialHeapNode<T> a, BinomialHeapNode<T> b) {
        int cmp = comparator.compare(a.getItem(), b.getItem());

        return cmp < 0 ? a.addSubTree(b) : b.addSubTree(a);
    }
}
