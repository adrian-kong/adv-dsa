package dev.datastructure.heap;

/**
 * Heap signature
 *
 * @param <T> type
 */
public interface IHeap<T> {

    void insert(T item);

    T pop();

    T peek();

//    TODO: implement meld
//    void meld();

//    TODO: implement increase
//    void increase(T item);
}
