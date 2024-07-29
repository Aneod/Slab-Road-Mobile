package com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class TextTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final InlineComponent inlineComponent = new InlineComponent(popUp);
    private final Text text = new Text(inlineComponent, "This is a test text.", 150, 3000, Gravity.CENTER);

    @Test
    public void testDefaultLayoutConstructor_layoutParams() {
        Text text = new Text(ApplicationProvider.getApplicationContext());
        assertEquals(0, text.getLayoutParams().width);
        assertEquals(0, text.getLayoutParams().height);
        assertEquals(0, text.getLayoutParams().leftMargin);
        assertEquals(0, text.getLayoutParams().topMargin);
    }

    @Test
    public void testWidth() {
        int actual = text.getLayoutParams().width;
        assertEquals(150, actual);
    }

    @Test
    public void testHeight_wrapContent() {
        int actual = text.getLayoutParams().height;
        assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, actual);
    }

    @Test public void testInlineComponentHeightMaximize() {
        assertEquals(Integer.MAX_VALUE, inlineComponent.getLayoutParams().height);
    }

    @Test
    public void testLeftMargin() {
        int actual = text.getLayoutParams().leftMargin;
        assertEquals(0, actual);
    }

    @Test
    public void testTopMargin() {
        int actual = text.getLayoutParams().topMargin;
        assertEquals(0, actual);
    }

    @Test
    public void testTextContent() {
        CharSequence actual = text.getText();
        assertEquals("This is a test text.", actual);
    }

    @Test
    public void testGravity() {
        int actual = text.getGravity();
        assertEquals(Gravity.CENTER, actual);
        assertNotEquals(Gravity.CENTER_HORIZONTAL, actual);
    }

    @Test
    public void testTextColor() {
        int actual = text.getCurrentTextColor();
        int expected = Text.getTextColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testTextSize() {
        int actual = (int) text.getTextSize();
        int expected = InlineComponent.getTextSize();
        assertEquals(expected, actual);
    }

    @Test
    public void testOnGlobalLayoutListener_minHeightWork() {
        text.measure(0, 0);
        text.layout(0, 0, 100, 50);

        ViewTreeObserver observer = text.getViewTreeObserver();
        if (observer.isAlive()) {
            observer.dispatchOnGlobalLayout();
        }

        int expectedHeight = Math.max(3000, text.getMeasuredHeight());

        assertEquals(expectedHeight, text.getLayoutParams().height);
        assertEquals(expectedHeight, inlineComponent.getLayoutParams().height);
    }

    @Test
    public void testOnGlobalLayoutListener_textHeightisTaken_whenItHighest() {
        Text text = new Text(inlineComponent, "This is a test text.", 150, 0, Gravity.CENTER);

        text.measure(0, 0);
        text.layout(0, 0, 100, 50);

        ViewTreeObserver observer = text.getViewTreeObserver();
        if (observer.isAlive()) {
            observer.dispatchOnGlobalLayout();
        }

        int expectedHeight = Math.max(0, text.getMeasuredHeight());

        assertEquals(expectedHeight, text.getLayoutParams().height);
        assertEquals(expectedHeight, inlineComponent.getLayoutParams().height);
        assertNotEquals(0, text.getLayoutParams().height);
        assertNotEquals(0, inlineComponent.getLayoutParams().height);
    }
}