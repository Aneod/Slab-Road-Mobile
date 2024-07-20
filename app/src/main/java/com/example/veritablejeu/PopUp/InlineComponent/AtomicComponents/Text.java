package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class Text extends AppCompatTextView {

    private static final int TEXT_COLOR = Color.BLACK;

    public Text(@NonNull Context context) {
        super(context);
    }

    /**
     * The texts in {@link InlineComponent} take place at the left.
     */
    public Text(@NonNull InlineComponent inlineComponent, String text, int width, int minHeight, int gravity) {
        super(inlineComponent.getContext());
        setText(text);
        setTextSize(InlineComponent.getTextSize());
        setTextColor(TEXT_COLOR);
        setGravity(gravity);

        inlineComponent.setHeight(Integer.MAX_VALUE);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
        setLayoutParams(layoutParams);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = getMeasuredHeight();
                int newHeight = Math.max(minHeight, height);
                inlineComponent.setHeight(newHeight);
                layoutParams.height = newHeight;
                setLayoutParams(layoutParams);
            }
        });
    }
}
