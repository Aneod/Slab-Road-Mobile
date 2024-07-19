package com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.ContenuPopUp.DoubleButtons;
import com.example.veritablejeu.PopUp.ContenuPopUp.SimpleText;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class Question extends FrameLayout {

    private final SimpleText simpleText;
    private final DoubleButtons buttons;

    public Question(@NonNull PopUp popUp, String text, String textA, Runnable runA, String textB, Runnable runB) {
        super(popUp.getContext());
        simpleText = new SimpleText(popUp, text);
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
