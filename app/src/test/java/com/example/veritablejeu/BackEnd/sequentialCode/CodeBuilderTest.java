package com.example.veritablejeu.BackEnd.sequentialCode;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodeBuilderTest {

    @Test
    public void buildKeyValue_normal() {
        char key = 'a';
        String codeValue = "38";
        String actual = CodeBuilder.buildKeyValue(key, codeValue);
        String expected = "a1238";
        assertEquals(expected, actual);
    }

    @Test
    public void buildKeyValue_casual() {
        char key = '1';
        String codeValue = "xxxxxxyyyyyyzzzzzz";
        String actual = CodeBuilder.buildKeyValue(key, codeValue);
        String expected = "11ixxxxxxyyyyyyzzzzzz";
        assertEquals(expected, actual);
    }

    @Test
    public void buildKeyValue_empty_by0() {
        char key = 'b';
        String codeValue = "";
        String actual = CodeBuilder.buildKeyValue(key, codeValue);
        String expected = "b0";
        assertEquals(expected, actual);
    }

    @Test
    public void buildKeyValue_null_by0() {
        char key = 'b';
        String codeValue = null;
        String actual = CodeBuilder.buildKeyValue(key, codeValue);
        String expected = "b0";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_normal() {
        int number = 10;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "1a";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_just0() {
        int number = 0;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_negative_to0() {
        int number = -10;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_higherThan36() {
        int number = 38;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "212";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_maxInteger() {
        int number = Integer.MAX_VALUE;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "6zik0zj";
        assertEquals(expected, actual);
    }

    @Test
    public void quantifyToDoubleZdecimals_maxLong() {
        long number = Long.MAX_VALUE;
        String actual = CodeBuilder.quantifyToDoubleZdecimals(number);
        String expected = "d1y2p0ij32e8e7";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_normal() {
        int number = 10;
        String actual = CodeBuilder.toBase36(number);
        String expected = "a";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_0() {
        int number = 0;
        String actual = CodeBuilder.toBase36(number);
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_negative_to0() {
        int number = -10;
        String actual = CodeBuilder.toBase36(number);
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_higherThan36() {
        int number = 38;
        String actual = CodeBuilder.toBase36(number);
        String expected = "12";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_maxInteger() {
        int number = Integer.MAX_VALUE;
        String actual = CodeBuilder.toBase36(number);
        String expected = "zik0zj";
        assertEquals(expected, actual);
    }

    @Test
    public void toBase36_maxLong() {
        long number = Long.MAX_VALUE;
        String actual = CodeBuilder.toBase36(number);
        String expected = "1y2p0ij32e8e7";
        assertEquals(expected, actual);
    }

}