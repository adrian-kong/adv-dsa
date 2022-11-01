package dev.datastructure.heap;

import dev.IValidator;

/**
 * Heap signature
 *
 * @param <T> type
 */
public interface IHeap<T> extends IValidator {

    /**
     * Inserts item into array
     *
     * @param item to be added into array
     */
    void insert(T item);

    /**
     * Remove first element
     *
     * @return highest priority element
     */
    T pop();

    /**
     * Lookup first element
     *
     * @return highest priority element
     */
    T peek();

//    TODO: implement meld
//    void meld();

//    TODO: implement increase
//    void increase(T item);
}
