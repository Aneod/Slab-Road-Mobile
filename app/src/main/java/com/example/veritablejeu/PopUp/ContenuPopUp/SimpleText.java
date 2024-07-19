package com.example.veritablejeu.PopUp.ContenuPopUp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;

@SuppressLint("ViewConstructor")
public class SimpleText extends PopUpComponent {

    private static final int TEXT_SIZE = 18;
    private static final int TEXT_COLOR = Color.BLACK;
    private static final int TEXT_GRAVITY = Gravity.CENTER;

    private final AppCompatTextView textView;

    public SimpleText(@NonNull PopUp popUp, String text) {
        super(popUp);
        textView = getTextView(text);
        setHeight(Integer.MAX_VALUE);
        addView(textView);
    }

    @NonNull
    private AppCompatTextView getTextView(String text) {
        AppCompatTextView textView = new AppCompatTextView(getContext());
        textView.setText(text);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(TEXT_COLOR);
        textView.setGravity(TEXT_GRAVITY);
        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0
        );
        textView.setLayoutParams(layoutParams);

        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = textView.getMeasuredHeight();
                setHeight(height);
            }
        });
        return textView;
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
