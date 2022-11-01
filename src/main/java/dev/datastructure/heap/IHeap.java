package dev.datastructure.heap;

import dev.IValidator;

/**
 * Heap signature
 *
 * @param <T> type
 */
public interface IHeap<T> extends IValidator {

    void insert(T item);

    T pop();

    T peek();

//    TODO: implement meld
//    void meld();

//    TODO: implement increase
//    void increase(T item);
}
