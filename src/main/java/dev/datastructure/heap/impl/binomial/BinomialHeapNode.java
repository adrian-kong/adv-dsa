package dev.datastructure.heap.impl.binomial;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BinomialHeapNode<T> {

    @NonNull @Getter private T item;

    public BinomialHeapNode(@NonNull T item) {
        this.item = item;
        this.left = this;
        this.right = this;
    }

    @Getter @Setter private int depth;

    @Getter @Setter private BinomialHeapNode<T> left;

    @Getter @Setter private BinomialHeapNode<T> right;

    @Getter @Setter private BinomialHeapNode<T> child;

}
