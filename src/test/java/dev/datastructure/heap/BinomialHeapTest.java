package dev.datastructure.heap;

import dev.datastructure.heap.impl.BinomialHeap;
import dev.datastructure.heap.impl.binomial.BinomialHeapNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.ToIntFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinomialHeapTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public static void main(String[] args) {
        ToIntFunction<Object> mapper = x -> x != null ? 1 : 0;
        var heap1 = new BinomialHeap<>(Integer::compare) {{
            Arrays.stream(new int[]{3, 4, 2, 5, 1, 2}).forEach(this::insert);
        }};
        var bin1 = Arrays.stream(heap1.binaryRepr()).mapToInt(mapper).toArray();
        System.out.println(Arrays.toString(bin1));

        var heap2 = new BinomialHeap<>(Integer::compare) {{
            Arrays.stream(new int[]{3, 4, 2, 5, 1, 2, 3, 6, 0}).forEach(this::insert);
        }};

        var bin2 = Arrays.stream(heap2.binaryRepr()).mapToInt(mapper).toArray();
        System.out.println(Arrays.toString(bin2));

        heap1.merge(heap2.getPtr());
        bin1 = Arrays.stream(heap1.binaryRepr()).mapToInt(mapper).toArray();
        System.out.println(Arrays.toString(bin1));
    }

    @Test
    void testBinomialHeap() {
        var heap = new BinomialHeap<>(Integer::compare);

        var arr = new int[]{3, 4, 2, 5, 1, 2, 3, 6, 0};

        for (int i : arr) {
            heap.insert(i);
        }

        Arrays.stream(arr).sorted().forEach(x -> {
            assertEquals(x, heap.pop());
        });
    }

}
