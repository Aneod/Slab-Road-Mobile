package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
     * <p>
     *     For resize perfectly the height of the popup for contain the text, there is a specific action to do.
     *     <br>
     *     The difficulty is to find the height of the text. That's not possible to calculate.
     *     The solution is to print the text on the screen, and AFTER measure the height and take it.
     *     <br>
     *     If the text container height is lower than the text height, the measured text height will be
     *     the text container height !
     *     So, we set the popup height to Integer.MAX_VALUE for be sure to have a popup height higher than the text height.
     *     <br>
     *     Once the text is print, an event is automatically called.
     *     This event find the text height and apply it to the container and to the text.
     * </p>
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
                Log.e("", "" + height);
                int newHeight = Math.max(minHeight, height);
                inlineComponent.setHeight(newHeight);
                layoutParams.height = newHeight;
                setLayoutParams(layoutParams);
            }
        });
    }
}
