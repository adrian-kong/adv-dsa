package dev.datastructure.heap;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
public class BinomialHeapNode<T> {

    @NonNull @Getter private T item;

    @Getter private final List<BinomialHeapNode<T>> subTrees = new ArrayList<>();

    public BinomialHeapNode<T> addSubTree(BinomialHeapNode<T> subTree) {
        subTrees.add(subTree);
        return this;
    }

    @Override public String toString() {
        return new StringJoiner(", ", BinomialHeapNode.class.getSimpleName() + "[", "]")
                .add("item=" + item)
                .add("subTrees=" + subTrees)
                .toString();
    }
}
