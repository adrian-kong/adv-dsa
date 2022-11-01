package dev.datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Abstract implementation of Heap,
 * Assumes all heap data structures follow this abstraction
 * <p>
 * Internal implementation requires static sized array,
 * any dynamically sized arrays require resizing
 * which is not currently supported
 *
 * @param <T> generic type to be used in heap
 */
public abstract class Heap<T> implements IHeap<T> {

    /**
     * To compare between two elements,
     * This removes inheritance requirement
     * from otherwise type <T extends Comparable>
     * which allows more flexibility
     */
    protected final Comparator<T> comparator;

    /**
     * Heap constructor
     *
     * @param comparator to give ordered-ness
     */
    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override public abstract void insert(T item);

    @Override public abstract T pop();

    @Override public abstract T peek();

    @Override public boolean validate() {
        return false;
    }

}
