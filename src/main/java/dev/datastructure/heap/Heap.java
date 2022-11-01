package dev.datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

public abstract class Heap<T> implements IHeap<T> {

    protected final Comparator<T> comparator;
    protected final T[] array;

    protected int i = 0;

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
