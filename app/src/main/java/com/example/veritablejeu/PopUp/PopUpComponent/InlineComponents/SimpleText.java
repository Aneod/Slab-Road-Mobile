package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.PopUpText;
import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class SimpleText extends PopUpComponent {

    private final PopUpText textView;

    public SimpleText(@NonNull PopUp popUp, String text) {
        super(popUp);
        setHeight(Integer.MAX_VALUE);
        textView = new PopUpText(this, text, getLayoutParams().width, 0, Gravity.CENTER);
        addView(textView);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
