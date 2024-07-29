package com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates;

import static org.junit.Assert.*;

import com.slabroad.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;

import org.junit.Test;

import java.util.ArrayList;

public class ZdecimalCoordinatesManagerTest {

    @Test
    public void getTopOf() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', '7');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getTopOf(first);
        ZdecimalCoordinates expected = new ZdecimalCoordinates('5', '6');
        boolean result = actual.equals(expected);
        assertTrue(result);
    }

    @Test
    public void getTopOf_limit() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', '0');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getTopOf(first);
        assertNull(actual);
    }

    @Test
    public void getBottomOf() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', '7');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getBottomOf(first);
        ZdecimalCoordinates expected = new ZdecimalCoordinates('5', '8');
        boolean result = actual.equals(expected);
        assertTrue(result);
    }

    @Test
    public void getBottomOf_limit() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', 'z');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getBottomOf(first);
        assertNull(actual);
    }

    @Test
    public void getLeftOf() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', '7');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getLeftOf(first);
        ZdecimalCoordinates expected = new ZdecimalCoordinates('4', '7');
        boolean result = actual.equals(expected);
        assertTrue(result);
    }

    @Test
    public void getLeftOf_limit() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getLeftOf(first);
        assertNull(actual);
    }

    @Test
    public void getRightOf() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('5', '7');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getRightOf(first);
        ZdecimalCoordinates expected = new ZdecimalCoordinates('6', '7');
        boolean result = actual.equals(expected);
        assertTrue(result);
    }

    @Test
    public void getRightOf_limit() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('z', '0');
        ZdecimalCoordinates actual = ZdecimalCoordinatesManager.getRightOf(first);
        assertNull(actual);
    }

    @Test
    public void getMultiOf() {
        ZdecimalCoordinates first = new ZdecimalCoordinates('0', '0');
        int numberOfBottomMoves = 5;
        int numberOfRightMoves = 3;
        for(int i = 0; i < numberOfBottomMoves; i++) {
            first = ZdecimalCoordinatesManager.getBottomOf(first);
        }
        for(int i = 0; i < numberOfRightMoves; i++) {
            first = ZdecimalCoordinatesManager.getRightOf(first);
        }
        ZdecimalCoordinates expected = new ZdecimalCoordinates(
                ZdecimalCharacterConverter.intDecimal_to_charBase36(numberOfRightMoves),
                ZdecimalCharacterConverter.intDecimal_to_charBase36(numberOfBottomMoves)
        );
        boolean result = first.equals(expected);
        assertTrue(result);
    }

    @Test
    public void testGetLeftOf() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('b', '1');
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getLeftOf(coordinates);
        assertEquals('a', result.getX().getCharacter());
        assertEquals('1', result.getY().getCharacter());
    }

    @Test
    public void testGetRightOf() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('b', '1');
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getRightOf(coordinates);
        assertEquals('c', result.getX().getCharacter());
        assertEquals('1', result.getY().getCharacter());
    }

    @Test
    public void testGetTopOf() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('b', '2');
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getTopOf(coordinates);
        assertEquals('b', result.getX().getCharacter());
        assertEquals('1', result.getY().getCharacter());
    }

    @Test
    public void testGetBottomOf() {
        ZdecimalCoordinates coordinates = new ZdecimalCoordinates('b', '1');
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getBottomOf(coordinates);
        assertEquals('b', result.getX().getCharacter());
        assertEquals('2', result.getY().getCharacter());
    }

    @Test
    public void testGetLeftOfNull() {
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getLeftOf(null);
        assertNull(result);
    }

    @Test
    public void testGetRightOfNull() {
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getRightOf(null);
        assertNull(result);
    }

    @Test
    public void testGetTopOfNull() {
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getTopOf(null);
        assertNull(result);
    }

    @Test
    public void testGetBottomOfNull() {
        ZdecimalCoordinates result = ZdecimalCoordinatesManager.getBottomOf(null);
        assertNull(result);
    }

    @Test
    public void testIsOnTopOf() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '2');
        assertTrue(ZdecimalCoordinatesManager.isOnTopOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnTopOf(to, from));
    }

    @Test
    public void testIsOnLeftOf() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('b', '1');
        assertTrue(ZdecimalCoordinatesManager.isOnLeftOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnLeftOf(to, from));
    }

    @Test
    public void testIsOnBottomOf() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '2');
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertTrue(ZdecimalCoordinatesManager.isOnBottomOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnBottomOf(to, from));
    }

    @Test
    public void testIsOnRightOf() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('b', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertTrue(ZdecimalCoordinatesManager.isOnRightOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnRightOf(to, from));
    }

    @Test
    public void testIsOnTopOfWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertFalse(ZdecimalCoordinatesManager.isOnTopOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnTopOf(to, from));
    }

    @Test
    public void testIsOnLeftOfWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertFalse(ZdecimalCoordinatesManager.isOnLeftOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnLeftOf(to, from));
    }

    @Test
    public void testIsOnBottomOfWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertFalse(ZdecimalCoordinatesManager.isOnBottomOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnBottomOf(to, from));
    }

    @Test
    public void testIsOnRightOfWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertFalse(ZdecimalCoordinatesManager.isOnRightOf(from, to));
        assertFalse(ZdecimalCoordinatesManager.isOnRightOf(to, from));
    }

    @Test
    public void testGetXdistanceBetween() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('d', '1');
        assertEquals(3, ZdecimalCoordinatesManager.getXdistanceBetween(from, to));
        assertEquals(3, ZdecimalCoordinatesManager.getXdistanceBetween(to, from));
    }

    @Test
    public void testGetYdistanceBetween() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '4');
        assertEquals(3, ZdecimalCoordinatesManager.getYdistanceBetween(from, to));
        assertEquals(3, ZdecimalCoordinatesManager.getYdistanceBetween(to, from));
    }

    @Test
    public void testGetDistanceBetween() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('d', '4');
        assertEquals(4.24, ZdecimalCoordinatesManager.getDistanceBetween(from, to), 0.01);
        assertEquals(4.24, ZdecimalCoordinatesManager.getDistanceBetween(to, from), 0.01);
    }

    @Test
    public void testGetXdistanceBetweenWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertEquals(0, ZdecimalCoordinatesManager.getXdistanceBetween(from, to));
        assertEquals(0, ZdecimalCoordinatesManager.getXdistanceBetween(to, from));
    }

    @Test
    public void testGetYdistanceBetweenWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertEquals(0, ZdecimalCoordinatesManager.getYdistanceBetween(from, to));
        assertEquals(0, ZdecimalCoordinatesManager.getYdistanceBetween(to, from));
    }

    @Test
    public void testGetDistanceBetweenWithNull() {
        ZdecimalCoordinates from = null;
        ZdecimalCoordinates to = new ZdecimalCoordinates('a', '1');
        assertEquals(0, ZdecimalCoordinatesManager.getDistanceBetween(from, to), 0);
        assertEquals(0, ZdecimalCoordinatesManager.getDistanceBetween(to, from), 0);
    }

    @Test
    public void testGetMoreLogicalDirectionHorizontalPriority() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('a', '1');
        ZdecimalCoordinates to = new ZdecimalCoordinates('d', '1');
        ArrayList<ZdecimalCoordinates> directions = ZdecimalCoordinatesManager.getMoreLogicalDirection(from, to);
        ArrayList<ZdecimalCoordinates> expected = new ArrayList<>();
        expected.add(new ZdecimalCoordinates('b', '1'));
        expected.add(new ZdecimalCoordinates('a', '2'));
        expected.add(new ZdecimalCoordinates('a', '0'));
        expected.add(new ZdecimalCoordinates('9', '1'));
        for(int index = 0; index < directions.size(); index++) {
            assertEquals(expected.get(index), directions.get(index));
        }
    }

    @Test
    public void testGetMoreLogicalDirection_NullInput() {
        ArrayList<ZdecimalCoordinates> result = ZdecimalCoordinatesManager.getMoreLogicalDirection(null, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetMoreLogicalDirection_SameCoordinates() {
        ZdecimalCoordinates coord = new ZdecimalCoordinates('1', '1');
        ArrayList<ZdecimalCoordinates> result = ZdecimalCoordinatesManager.getMoreLogicalDirection(coord, coord);
        assertTrue(result.contains(ZdecimalCoordinatesManager.getTopOf(coord)));
        assertTrue(result.contains(ZdecimalCoordinatesManager.getBottomOf(coord)));
        assertTrue(result.contains(ZdecimalCoordinatesManager.getLeftOf(coord)));
        assertTrue(result.contains(ZdecimalCoordinatesManager.getRightOf(coord)));
    }

    @Test
    public void testGetMoreLogicalDirection_SameCoordinates_butTopAndLeftNull() {
        ZdecimalCoordinates coord = new ZdecimalCoordinates('0', '0');
        ArrayList<ZdecimalCoordinates> result = ZdecimalCoordinatesManager.getMoreLogicalDirection(coord, coord);
        assertTrue(result.contains(ZdecimalCoordinatesManager.getBottomOf(coord)));
        assertTrue(result.contains(ZdecimalCoordinatesManager.getRightOf(coord)));
        assertEquals(2, result.size());
    }

    @Test
    public void testGetMoreLogicalDirection_VerticalPriority() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates to = new ZdecimalCoordinates('0', '9');
        ArrayList<ZdecimalCoordinates> result = ZdecimalCoordinatesManager.getMoreLogicalDirection(from, to);
        assertEquals(result.get(0), ZdecimalCoordinatesManager.getBottomOf(from));
    }

    @Test
    public void testGetMoreLogicalDirection_HorizontalPriority() {
        ZdecimalCoordinates from = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates to = new ZdecimalCoordinates('9', '0');
        ArrayList<ZdecimalCoordinates> result = ZdecimalCoordinatesManager.getMoreLogicalDirection(from, to);
        assertEquals(result.get(0), ZdecimalCoordinatesManager.getRightOf(from));
    }

    @Test
    public void testIsAdjacentTopOf() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        ZdecimalCoordinates bottom = new ZdecimalCoordinates('0', '2');
        assertTrue(ZdecimalCoordinatesManager.isAdjacentTopOf(top, mid));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(mid, top));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentTopOf(mid, bottom));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(bottom, mid));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(top, bottom));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(bottom, top));
    }

    @Test
    public void testIsAdjacentTopOf_nullFrom() {
        ZdecimalCoordinates top = null;
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(top, mid));
    }

    @Test
    public void testIsAdjacentTopOf_nullTo() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = null;
        assertFalse(ZdecimalCoordinatesManager.isAdjacentTopOf(top, mid));
    }

    @Test
    public void testIsAdjacentBottomOf() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        ZdecimalCoordinates bottom = new ZdecimalCoordinates('0', '2');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(top, mid));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentBottomOf(mid, top));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(mid, bottom));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentBottomOf(bottom, mid));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(top, bottom));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(bottom, top));
    }

    @Test
    public void testIsAdjacentBottomOf_nullFrom() {
        ZdecimalCoordinates top = null;
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(top, mid));
    }

    @Test
    public void testIsAdjacentBottomOf_nullTo() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = null;
        assertFalse(ZdecimalCoordinatesManager.isAdjacentBottomOf(top, mid));
    }

    @Test
    public void testIsAdjacentLeftOf() {
        ZdecimalCoordinates left = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = new ZdecimalCoordinates('1', '0');
        ZdecimalCoordinates right = new ZdecimalCoordinates('2', '0');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(mid, left));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentLeftOf(left, mid));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(right, mid));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentLeftOf(mid, right));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(left, right));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(right, left));
    }

    @Test
    public void testIsAdjacentLeftOf_nullFrom() {
        ZdecimalCoordinates top = null;
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(top, mid));
    }

    @Test
    public void testIsAdjacentLeftOf_nullTo() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = null;
        assertFalse(ZdecimalCoordinatesManager.isAdjacentLeftOf(top, mid));
    }

    @Test
    public void testIsAdjacentRightOf() {
        ZdecimalCoordinates left = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = new ZdecimalCoordinates('1', '0');
        ZdecimalCoordinates right = new ZdecimalCoordinates('2', '0');
        assertTrue(ZdecimalCoordinatesManager.isAdjacentRightOf(mid, left));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(left, mid));
        assertTrue(ZdecimalCoordinatesManager.isAdjacentRightOf(right, mid));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(mid, right));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(left, right));
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(right, left));
    }

    @Test
    public void testIsAdjacentRightOf_nullFrom() {
        ZdecimalCoordinates top = null;
        ZdecimalCoordinates mid = new ZdecimalCoordinates('0', '1');
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(top, mid));
    }

    @Test
    public void testIsAdjacentRightOf_nullTo() {
        ZdecimalCoordinates top = new ZdecimalCoordinates('0', '0');
        ZdecimalCoordinates mid = null;
        assertFalse(ZdecimalCoordinatesManager.isAdjacentRightOf(top, mid));
    }
}