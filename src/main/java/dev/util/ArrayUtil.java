package dev.util;

public class ArrayUtil {

    public static <T> void swapElem(T[] arr, int a, int b) {
        T tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] genericFixedArray(int cap) {
        return (T[]) new Object[cap];
    }

}
