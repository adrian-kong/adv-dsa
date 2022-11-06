package dev.datastructure.heap.impl.binomial;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BinomialHeapNode<T> implements Cloneable {

    @NonNull @Getter @Setter private T item;

    public BinomialHeapNode(@NonNull T item) {
        this.item = item;
    }

    @Getter @Setter private int depth;

    @Getter @Setter private BinomialHeapNode<T> right;

    @Getter @Setter private BinomialHeapNode<T> child;

    public void linkChild(BinomialHeapNode<T> node) {
        if (child != null) node.setRight(child);
        child = node;
        depth = Math.max(depth, node.getDepth() + 1);
    }

    public BinomialHeapNode<T> reverse() {
        BinomialHeapNode<T> reversed = null;
        BinomialHeapNode<T> curr = this;
        while (curr != null) {
            var next = curr.getRight();
            curr.setRight(reversed);
            reversed = curr;
            curr = next;
        }
        return reversed;
    }

    @Override public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("x=" + item)
                .add("d=" + depth)
                .add("↓=" + child)
                .add("→=" + right)
                .toString();
    }
}
