package org.xi.quick.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    public static <T> List<T> toList(T[]... array) {

        List<T> list = new ArrayList<>();
        for (T[] subArr : array) {
            for (T t : subArr) {
                list.add(t);
            }
        }
        return list;
    }

    public static <T> void combineArray(T[] resultArray, T[]... array) {
        int i = 0;
        for (T[] subArr : array) {
            for (T t : subArr) {
                resultArray[i++] = t;
            }
        }
    }
}
