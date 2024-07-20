package com.example.veritablejeu.PopUp.InlineComponent.InlineComponents;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.InlineComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class SimpleText extends PopUpComponent {

    private final Text textView;

    public SimpleText(@NonNull PopUp popUp, String text) {
        super(popUp);
        textView = new Text(this, text, getLayoutParams().width, 0, Gravity.CENTER);
        addView(textView);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
