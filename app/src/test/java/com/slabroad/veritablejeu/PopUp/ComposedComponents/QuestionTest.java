package com.slabroad.veritablejeu.PopUp.ComposedComponents;

import static org.junit.Assert.*;

import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.DoubleButtons;
import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class QuestionTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final Question question = new Question(popUp, "This is it !", "A", null, "B", null);
    private int WITNESS = 10;

    @Test
    public void getSimpleText_notNull() {
        SimpleText simpleText = question.getSimpleText();
        assertNotNull(simpleText);
        TextView textView = simpleText.getTextView();
        assertNotNull(textView);
    }

    @Test
    public void getSimpleText_goodContent() {
        SimpleText simpleText = question.getSimpleText();
        TextView textView = simpleText.getTextView();
        assertEquals("This is it !", textView.getText());
    }

    @Test
    public void setText() {
        SimpleText simpleText = question.getSimpleText();
        TextView textView = simpleText.getTextView();
        assertEquals("This is it !", textView.getText());
        question.setText("NEW");
        assertEquals("NEW", textView.getText());
    }

    @Test
    public void getButtons_notNull() {
        DoubleButtons buttons = question.getButtons();
        assertNotNull(buttons);
    }

    @Test
    public void getButtons_goodContent() {
        DoubleButtons buttons = question.getButtons();
        CharSequence textA = buttons.getBoutonA().getText();
        assertEquals("A", textA);
        CharSequence textB = buttons.getBoutonB().getText();
        assertEquals("B", textB);
    }

    @Test
    public void setRunnableA() {
        question.setRunnableA(() -> WITNESS = 90);
        DoubleButtons buttons = question.getButtons();
        buttons.getBoutonA().performClick();
        assertEquals(90, WITNESS);
    }

    @Test
    public void setRunnableB() {
        question.setRunnableB(() -> WITNESS = 110);
        DoubleButtons buttons = question.getButtons();
        buttons.getBoutonA().performClick();
        assertEquals(10, WITNESS);
        buttons.getBoutonB().performClick();
        assertEquals(110, WITNESS);
    }
}