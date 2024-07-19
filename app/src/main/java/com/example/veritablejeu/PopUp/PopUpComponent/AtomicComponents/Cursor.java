package com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.CursorComponent.CursorComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class Cursor extends FrameLayout {

    private static final int CURSOR_LINE_HEIGHT = 6;
    private static final int CURSOR_LINE_COLOR = Color.LTGRAY;
    private static final int CURSOR_DIAMETER = 25;

    private float value;

    public Cursor(@NonNull CursorComponent cursorComponent, float startValue, Consumer<Float> consumer, int color) {
        super(cursorComponent.getContext());
        this.value = startValue;
        int halfDiameter = CURSOR_DIAMETER / 2;
        int width = cursorComponent.getLayoutParams().width;
        int titledWidth = (int) (width * CursorComponent.getWidthTitledDistribution());
        int cursorLineWidth = width - titledWidth - CURSOR_DIAMETER;
        int cursorLineLeftMargin = titledWidth + halfDiameter;
        int cursorLineTopMargin = (cursorComponent.getLayoutParams().height - CURSOR_LINE_HEIGHT) / 2;
        addCursorLine(cursorLineWidth, cursorLineLeftMargin, cursorLineTopMargin);
        addCursor(cursorLineWidth, cursorLineLeftMargin, cursorLineTopMargin, consumer, color);
    }

    private void addCursorLine(int cursorLineWidth, int cursorLineLeftMargin, int cursorLingTopMargin) {
        View cursorLine = new View(getContext());
        cursorLine.setBackgroundColor(CURSOR_LINE_COLOR);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                cursorLineWidth, CURSOR_LINE_HEIGHT, cursorLineLeftMargin, cursorLingTopMargin);
        cursorLine.setLayoutParams(layoutParams);

        addView(cursorLine);
    }

    private void addCursor(int cursorLineWidth, int cursorLineLeftMargin, int cursorLineTopMargin, Consumer<Float> consumer, int color) {
        View cursor = new View(getContext());

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color);
        cursor.setBackground(drawable);

        int halfDiameter = CURSOR_DIAMETER / 2;
        int startXpos = (int) (cursorLineLeftMargin + cursorLineWidth * value - halfDiameter);
        int cursor_min_leftMargin = cursorLineLeftMargin - halfDiameter;
        int cursor_max_leftMargin = cursorLineLeftMargin + cursorLineWidth - halfDiameter;

        int yCursorLineCenter = cursorLineTopMargin + CURSOR_LINE_HEIGHT / 2;
        int topMargin = yCursorLineCenter - halfDiameter;
        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                CURSOR_DIAMETER, CURSOR_DIAMETER, 0, topMargin);
        cursor.setLayoutParams(layoutParams);
        cursor.setTranslationX(startXpos);

        addView(cursor);

        setOnTouchListener(new OnTouchListener() {
            private float xPos;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xPos = event.getRawX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getRawX() - xPos;

                        boolean minimumReached = cursor.getTranslationX() + dx < cursor_min_leftMargin && dx < 0;
                        boolean maximumReached = cursor.getTranslationX() + dx > cursor_max_leftMargin && dx > 0;
                        if(minimumReached || maximumReached) dx = 0;

                        cursor.setTranslationX(cursor.getTranslationX() + dx);
                        value = (cursor.getTranslationX() + halfDiameter - cursorLineLeftMargin) / cursorLineWidth;

                        if(consumer != null) {
                            consumer.accept(value);
                        }

                        xPos = event.getRawX();
                        break;
                }
                return true;
            }
        });
    }
}
