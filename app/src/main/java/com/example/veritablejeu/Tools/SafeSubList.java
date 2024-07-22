package com.example.veritablejeu.Tools;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;

public class SafeSubList {

    /**
     * Returns a sublist of a list, but with solutions instead of exceptions.
     */
    @NonNull
    public static <T> List<T> get(List<T> list, int from, int to) {
        if (list == null || from > to) {
            return Collections.emptyList();
        }
        int validFrom = Math.min(Math.max(0, from), list.size());
        int validTo = Math.min(Math.max(0, to), list.size());
        return list.subList(validFrom, validTo);
    }

}
