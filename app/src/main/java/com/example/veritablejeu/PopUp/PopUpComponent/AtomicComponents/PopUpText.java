package com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class PopUpText extends AppCompatTextView {

    private static final int TEXT_COLOR = Color.BLACK;

    public PopUpText(@NonNull Context context) {
        super(context);
    }

    public PopUpText(@NonNull PopUpComponent popUpComponent, String text, int width, int minHeight, int gravity) {
        super(popUpComponent.getContext());
        setText(text);
        setTextSize(PopUpComponent.getTextSize());
        setTextColor(TEXT_COLOR);
        setGravity(gravity);

        popUpComponent.setHeight(Integer.MAX_VALUE);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
        setLayoutParams(layoutParams);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = getMeasuredHeight();
                int newHeight = Math.max(minHeight, height);
                popUpComponent.setHeight(newHeight);
                layoutParams.height = newHeight;
                setLayoutParams(layoutParams);
            }
        });
    }
}
