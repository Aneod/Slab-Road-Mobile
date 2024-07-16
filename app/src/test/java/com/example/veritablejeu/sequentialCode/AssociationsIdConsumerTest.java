package com.example.veritablejeu.sequentialCode;

import androidx.core.util.Consumer;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

import com.example.veritablejeu.BackEnd.sequentialCode.AssociationsIdConsumer;

public class AssociationsIdConsumerTest {

    private AssociationsIdConsumer associationsIdConsumer;

    @Before
    public void setUp() {
        associationsIdConsumer = new AssociationsIdConsumer();
    }

    @Test
    public void testAddMultiple_ValidInput() {
        Consumer<String> consumerA = s -> System.out.println("Consumer A: " + s);
        Consumer<String> consumerB = s -> System.out.println("Consumer B: " + s);

        associationsIdConsumer.addMultiple('A', consumerA, 'B', consumerB);

        assertNotNull(associationsIdConsumer);
    }

    @Test
    public void testAddMultiple_NullInput() {
        associationsIdConsumer.addMultiple((Object[]) null);
        assertNotNull(associationsIdConsumer);
    }

    @Test
    public void testExecute_byId_ValidConsumer() {
        AtomicBoolean executed = new AtomicBoolean(false);
        Consumer<String> consumer = s -> executed.set(true);

        associationsIdConsumer.addMultiple('A', consumer);
        associationsIdConsumer.execute_byId('A', "test");

        assertTrue(executed.get());
    }

    @Test
    public void testExecute_byId_NullCode() {
        AtomicBoolean executed = new AtomicBoolean(false);
        Consumer<String> consumer = s -> executed.set(true);

        associationsIdConsumer.addMultiple('A', consumer);
        associationsIdConsumer.execute_byId('A', null);

        assertFalse(executed.get());
    }

    @Test
    public void testExecute_byId_InvalidId() {
        AtomicBoolean executed = new AtomicBoolean(false);
        Consumer<String> consumer = s -> executed.set(true);

        associationsIdConsumer.addMultiple('A', consumer);
        associationsIdConsumer.execute_byId('B', "test");

        assertFalse(executed.get());
    }

    @Test
    public void testAddMultiple_InvalidPairs() {
        Consumer<String> consumerA = s -> System.out.println("Consumer A: " + s);

        associationsIdConsumer.addMultiple('A', consumerA, 'B');

        AtomicBoolean executedA = new AtomicBoolean(false);
        associationsIdConsumer.addMultiple('A', (Consumer<String>) s -> executedA.set(true));
        associationsIdConsumer.execute_byId('A', "test");

        assertTrue(executedA.get());
    }

    @Test
    public void testAddMultiple_DuplicateIds() {
        AtomicBoolean executedFirst = new AtomicBoolean(false);
        AtomicBoolean executedSecond = new AtomicBoolean(false);

        Consumer<String> firstConsumer = s -> executedFirst.set(true);
        Consumer<String> secondConsumer = s -> executedSecond.set(true);

        associationsIdConsumer.addMultiple('A', firstConsumer, 'A', secondConsumer);
        associationsIdConsumer.execute_byId('A', "test");

        assertFalse(executedFirst.get());
        assertTrue(executedSecond.get());
    }

    @Test
    public void testKnowThisId_KnownId() {
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        associationsIdConsumer.addMultiple('A', consumer);

        assertTrue(associationsIdConsumer.containsKey('A'));
    }

    @Test
    public void testKnowThisId_UnknownId() {
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        associationsIdConsumer.addMultiple('A', consumer);

        assertFalse(associationsIdConsumer.containsKey('B'));
    }

    @Test
    public void testKnowThisId_EmptyAssociations() {
        assertFalse(associationsIdConsumer.containsKey('A'));
    }

    @Test
    public void testKnowThisId_NullConsumer() {
        associationsIdConsumer.addMultiple('A', null);

        assertFalse(associationsIdConsumer.containsKey('A'));
    }

    @Test
    public void testKnowThisId_DuplicateIds() {
        Consumer<String> consumer1 = s -> System.out.println("Consumer1: " + s);
        Consumer<String> consumer2 = s -> System.out.println("Consumer2: " + s);

        associationsIdConsumer.addMultiple('A', consumer1, 'A', consumer2);

        assertTrue(associationsIdConsumer.containsKey('A'));
    }

    @Test
    public void testKnowThisId_LargeNumberOfAssociations() {
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            associationsIdConsumer.addMultiple(ch, consumer);
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            assertTrue(associationsIdConsumer.containsKey(ch));
        }
    }

    @Test
    public void testKnowThisId_VariousCharacters() {
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        associationsIdConsumer.addMultiple('A', consumer, 'a', consumer, '1', consumer, '*', consumer);

        assertTrue(associationsIdConsumer.containsKey('A'));
        assertTrue(associationsIdConsumer.containsKey('a'));
        assertTrue(associationsIdConsumer.containsKey('1'));
        assertTrue(associationsIdConsumer.containsKey('*'));
    }

    @Test
    public void testKnowThisId_InvalidInputs() {
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        associationsIdConsumer.addMultiple('A', consumer, 'B', consumer);

        assertFalse(associationsIdConsumer.containsKey('C'));
        assertFalse(associationsIdConsumer.containsKey('\0'));
    }

}