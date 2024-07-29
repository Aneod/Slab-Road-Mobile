package com.slabroad.veritablejeu.PopUp.ComposedComponents;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.DoubleButtons;
import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.slabroad.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class Question {

    private final SimpleText simpleText;
    private final DoubleButtons buttons;

    public Question(@NonNull PopUp popUp, String text, String textA, Runnable runA, String textB, Runnable runB) {
        simpleText = new SimpleText(popUp, text, Gravity.CENTER);
        buttons = new DoubleButtons(popUp, textA, runA, textB, runB);
    }

    public SimpleText getSimpleText() {
        return simpleText;
    }

    public void setText(String text) {
        simpleText.setText(text);
    }

    public DoubleButtons getButtons() {
        return buttons;
    }

    public void setRunnableA(Runnable runnable) {
        buttons.setRunnableA(runnable);
    }

    public void setRunnableB(Runnable runnable) {
        buttons.setRunnableB(runnable);
    }
}
