package dev.datastructure.heap.impl;

import dev.datastructure.heap.Heap;
import dev.datastructure.heap.impl.binomial.BinomialHeapNode;
import lombok.Getter;
import lombok.NonNull;

import java.util.Comparator;
import java.util.StringJoiner;

public class BinomialHeap<T> extends Heap<T> {

    @Getter private BinomialHeapNode<T> ptr;

    @Getter private BinomialHeapNode<T> peek;

    @Getter private int size;

    /**
     * Heap constructor
     *
     * @param comparator to give ordered-ness
     */
    public BinomialHeap(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void insert(T item) { // this is amortized O(1), speed up of merge
        size += 1;
        var newLeft = new BinomialHeapNode<>(item);
        if (ptr == null) {
            ptr = newLeft;
            peek = ptr;
            return;
        }
        if (ptr.getDepth() != newLeft.getDepth()) { // update ptr to 0 depth node
            newLeft.setRight(ptr);
            ptr = newLeft;
            if (precedenceOver(item, peek.getItem())) peek = ptr;
            return;
        }
        assert ptr.getChild() == null; // 0 depth should not have children
        BinomialHeapNode sum = mergeNodes(ptr, newLeft);
        while (sum.getRight() != null) {
            var next = sum.getRight();
            if (sum.getDepth() != next.getDepth()) break;
            sum = mergeNodes(next, sum);
        }
        ptr = sum;
    }

    @Override
    public T pop() {
        assert peek != null; // trying to pop empty heap
        size -= 1;

        var pre = findPrePeek();
        var peek = this.peek;

        this.peek = null;

        if (pre == null) ptr = ptr.getRight();
        else pre.setRight(peek.getRight());
        if (peek.getChild() != null) {
            var child = peek.getChild();

            // reverse child, O(logn) can be O(1) if we cache but since
            // wikipedia says reordering we shall do as specified, reversing doesn't
            // matter since merge is also O(logn)
            merge(child.reverse());

        }
        if (ptr != null) {
            this.peek = ptr;
            BinomialHeapNode<T> tmp = ptr;
            while ((tmp = tmp.getRight()) != null) {
                if (precedenceOver(tmp.getItem(), this.peek.getItem())) this.peek = tmp;
            }
        }
        return peek.getItem();
    }

    @Override
    public T peek() {
        return peek == null ? null : peek.getItem();
    }

    public void merge(@NonNull BinomialHeapNode<T> root) {
        if (ptr == null) {
            ptr = root;
            return;
        }

        var curr = ptr;
        var other = root;

        if (other.getDepth() < curr.getDepth()) { // prevents needing to replace ptr after, ensure lowest depth is ptr
            ptr = other;
            other = curr;
            curr = ptr;
        }
        System.out.println(ptr);
        System.out.println(other);

        BinomialHeapNode<T> prev = null; // cache prev ptr to set right, ensures mutation

        while (curr != null && other != null) {
            int depthA = curr.getDepth();
            int depthB = other.getDepth();
            System.out.println(depthA + " " + depthB);

            if (depthA == depthB) {
                // cut the right (prevents mutation / no need to clone)
                var otherTmp = other;
                other = other.getRight();
                otherTmp.setRight(null);

                curr = mergeNodes(curr, otherTmp);
                if (prev == null) {
                    ptr = curr;
                } else {
                    prev.setRight(curr);
                }
            } else if (curr.getDepth() < other.getDepth()) {
                prev = curr;
                curr = curr.getRight();
            } else {
                var otherRight = other.getRight();
                other.setRight(curr);
                if (prev == null) {
                    ptr = curr;
                } else {
                    prev.setRight(other);
                }
                prev = curr;
                curr = other;
                other = otherRight;
            }
        }

        if (other != null) prev.setRight(other);
        System.out.println(ptr);

    }

    /**
     * This is to traverse and update peek value when extracting
     *
     * @return node before peek
     */
    private BinomialHeapNode<T> findPrePeek() {
        if (peek == ptr) return null;
        var pre = ptr;
        while (pre.getRight() != null) {
            if (pre.getRight() == peek) return pre;
            pre = ptr.getRight();
        }
        return null;
    }

    private BinomialHeapNode<T> mergeNodes(BinomialHeapNode<T> o1, BinomialHeapNode<T> o2) {
        var top = o1;
        var bot = o2;
        if (!precedenceOver(o1.getItem(), o2.getItem())) {
            top = o2;
            bot = o1;
            top.setRight(o1.getRight()); // persist the connection to right
            if (peek != null && precedenceOver(peek.getItem(), top.getItem())) peek = top;
        }
        top.linkChild(bot);
        return top;
    }

    /**
     * Binary representation for debugging depth
     *
     * @return pointer array corresponding to their depth
     */
    @SuppressWarnings("unchecked")
    public BinomialHeapNode<T>[] binaryRepr() {
        BinomialHeapNode<T> curr = ptr;
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }
        int cap = curr.getDepth();
        BinomialHeapNode<T>[] nodes = new BinomialHeapNode[cap + 1];
        curr = ptr;
        do {
            nodes[curr.getDepth()] = curr;
        } while ((curr = curr.getRight()) != null);
        return nodes;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BinomialHeap.class.getSimpleName() + "[", "]")
                .add("ptr=" + ptr)
                .add("size=" + size)
                .add("peek=" + (peek == null ? null : peek.getItem()))
                .toString();
    }
}
