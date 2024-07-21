package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class SimpleText extends InlineComponent {

    private final Text textView;

    public SimpleText(@NonNull PopUp popUp, String text) {
        super(popUp);
        textView = new Text(this, text, getLayoutParams().width, 0, Gravity.NO_GRAVITY);
        addView(textView);
    }

    public SimpleText(@NonNull PopUp popUp, String text, int gravity) {
        super(popUp);
        textView = new Text(this, text, getLayoutParams().width, 0, gravity);
        addView(textView);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
