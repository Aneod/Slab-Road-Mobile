package com.example.veritablejeu.Tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SafeSubListTest {

    private List<Integer> list;

    @Before
    public void setUp() {
        list = Arrays.asList(2, 3, 6, 10, 23, 8, 1, 5, 4);
    }

    @Test
    public void get_list_null() {
        List<Integer> subList = SafeSubList.get(null, 5, 6);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, subList);
    }

    @Test
    public void get_fromTop_outOfLowerBound() {
        List<Integer> subList = SafeSubList.get(list, -10, -5);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, subList);
    }

    @Test
    public void get_fromTop_outOfHigherBound() {
        List<Integer> subList = SafeSubList.get(list, 20, 25);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, subList);
    }

    @Test
    public void get_from_outOfLowerBound() {
        List<Integer> subList = SafeSubList.get(list, -10, 5);
        List<Integer> expected = Arrays.asList(2, 3, 6, 10, 23);
        assertEquals(expected, subList);
    }

    @Test
    public void get_normal() {
        List<Integer> subList = SafeSubList.get(list, 1, 5);
        List<Integer> expected = Arrays.asList(3, 6, 10, 23);
        assertEquals(expected, subList);
        List<Integer> noBullshit = Arrays.asList(3, 6, 10);
        assertNotEquals(noBullshit, subList);
    }

    @Test
    public void get_FromHigherTo_to() {
        List<Integer> subList = SafeSubList.get(list, 7, 5);
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, subList);
    }

    @Test
    public void get_ToOutOfHigherBound() {
        List<Integer> subList = SafeSubList.get(list, 5, 10);
        List<Integer> expected = Arrays.asList(8, 1, 5, 4);
        assertEquals(expected, subList);
    }
}