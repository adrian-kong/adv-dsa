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
     * Generic sized array
     */
    protected final T[] array;

    /**
     * Current elements in array
     */
    protected int i = 0;

    /**
     * Heap constructor
     * <p>
     * Instantiates generic array and requires
     * comparator to be provided for order
     *
     * @param capacity   to statically size array
     * @param comparator to give ordered-ness
     */
    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<T> comparator) {
        this.array = (T[]) new Object[capacity];
        this.comparator = comparator;
    }

    @Override public abstract void insert(T item);

    @Override public abstract T pop();

    @Override public abstract T peek();

    @Override public boolean validate() {
        return false;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Heap.class.getSimpleName() + "[", "]")
                .add("array=" + Arrays.toString(array))
                .add("i=" + i)
                .toString();
    }
}
