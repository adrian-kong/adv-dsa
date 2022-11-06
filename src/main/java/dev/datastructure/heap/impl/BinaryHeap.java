package dev.datastructure.heap.impl;

import dev.datastructure.heap.Heap;
import dev.util.ArrayUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

/**
 * An implementation of the Binary Heap data structure
 * <p>
 * Binary Heap can be compactly represented as an array.
 * Tree representation consists of all depth filled left to right.
 * <p>
 * This would imply the property of parent k -> left 2k+1, right 2k+2
 * <p>
 * ------ k ------
 * --- /     \ ---
 * - 2k+1   2k+2 -
 *
 * @author Adrian Kong
 * @see <a href="https://en.wikipedia.org/wiki/Binary_heap#Heap_operations">Binary Heap</a>
 */
public class BinaryHeap<T> extends Heap<T> {

    /**
     * Generic sized array
     */
    @Getter private final T[] array;

    /**
     * Current elements in array
     */
    @Getter private int i = 0;

    /**
     * Instantiates generic array and requires
     * comparator to be provided for order
     *
     * @param capacity   to statically size array
     * @param comparator to give ordered-ness
     */
    public BinaryHeap(int capacity, Comparator<T> comparator) {
        super(comparator);
        array = ArrayUtil.genericFixedArray(capacity);
    }

    /**
     * Insert element into heap
     * <p>
     * Parent at p implies children @ 2p+1, 2p+2
     * Each depth would be log_2(i + 1) for any index i
     * <p>
     * Time complexity O(log n)
     * Amortized O(1) due to percolation / restructuring
     *
     * @param elem to append to array
     */
    @Override
    public void insert(T elem) {
        assert i < array.length;
        array[i] = elem;

        int tmp_i = i, parent = (tmp_i - 1) / 2; // odd numbers are floored
        while (precedenceOver(elem, array[parent])) { // bubble up, amortized O(1), worst case O(log n)
            ArrayUtil.swapElem(array, tmp_i, parent);
            tmp_i = parent;
            parent = (tmp_i - 1) / 2;
        }
        i += 1;
    }

    /**
     * Swap last element with peek and percolate downwards.
     * Time complexity O(log n) as element may require swapping down in one branch
     *
     * @return highest priority element
     */
    @Override
    public T pop() {
        if (i == 0) return null;
        T peek = array[0];
        array[0] = array[--i];
        array[i] = null;

        int tmp_i = 0, left, right;
        boolean checkChild;
        do {
            checkChild = false;
            left = 2 * tmp_i + 1;
            right = left + 1;
            if (left >= i && right >= i) break;
            int cmp = left < i && right < i ? comparator.compare(array[right], array[left]) : 0;
            int lcmp = left < i ? comparator.compare(array[left], array[tmp_i]) : 0;
            int rcmp = right < i ? comparator.compare(array[right], array[tmp_i]) : 0;
            if (cmp >= 0 && lcmp < 0) {
                ArrayUtil.swapElem(array, tmp_i, left);
                tmp_i = left;
                checkChild = true;
            }
            if (!checkChild && cmp <= 0 && rcmp < 0) {
                ArrayUtil.swapElem(array, tmp_i, right);
                tmp_i = right;
                checkChild = true;
            }
        } while (checkChild);
        return peek;
    }

    @Override
    public T peek() {
        return i == 0 ? null : array[0];
    }

    @Override
    public boolean validate() {
        for (int j = 0; j < this.i; j++) {
            if (this.i <= 2 * j + 1) break;
            T c1 = array[2 * j + 1];
            if (precedenceOver(c1, array[j])) return false;

            if (this.i <= 2 * j + 2) break;
            T c2 = array[2 * j + 2];
            if (precedenceOver(c2, array[j])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BinaryHeap.class.getSimpleName() + "[", "]")
                .add("array=" + Arrays.toString(array))
                .add("i=" + i)
                .toString();
    }
}
