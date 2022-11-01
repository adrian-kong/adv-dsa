package dev.datastructure.heap.impl;

import dev.datastructure.heap.Heap;
import dev.util.ArrayUtil;

import java.util.Comparator;

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

    public BinaryHeap(int capacity, Comparator<T> comparator) {
        super(capacity, comparator);
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
        while (comparator.compare(array[parent], elem) > 0) { // bubble up, amortized O(1), worst case O(log n)
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
     * @return
     */
    @Override
    public T pop() {
        if (i == 0) return null;
        T peek = array[0];
        array[0] = array[i - 1];
        array[i - 1] = null;
        i -= 1;

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
            if (comparator.compare(array[j], c1) > 0) return false;

            if (this.i <= 2 * j + 2) break;
            T c2 = array[2 * j + 2];
            if (comparator.compare(array[j], c2) > 0) return false;
        }
        return true;
    }
}
