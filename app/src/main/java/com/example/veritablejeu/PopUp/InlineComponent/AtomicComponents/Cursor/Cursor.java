package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Cursor;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import kotlin.annotation.MustBeDocumented;

@SuppressLint("ViewConstructor")
public class Cursor extends FrameLayout implements ICursor {

    private static final int HEIGHT = 100;
    private static final int CURSOR_LINE_HEIGHT = 6;
    private static final int CURSOR_LINE_COLOR = Color.LTGRAY;
    private static final int CURSOR_DIAMETER = 25;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getCursorLineHeight() {
        return CURSOR_LINE_HEIGHT;
    }

    public static int getCursorDiameter() {
        return CURSOR_DIAMETER;
    }

    private float value;
    private final InlineComponent inlineComponent;
    private final View cursor;
    private FrameLayout.LayoutParams layoutParamsLine;
    private FrameLayout.LayoutParams layoutParamsCursor;

    /**
     * A cursor take place at the right of the {@link InlineComponent}.
     */
    public Cursor(@NonNull InlineComponent inlineComponent, int width,
                  float startValue, Consumer<Float> consumer, int color) {
        super(inlineComponent.getContext());
        this.inlineComponent = inlineComponent;
        this.value = getCorrectValue(startValue);
        int halfDiameter = CURSOR_DIAMETER / 2;
        int cursorLineWidth = width - CURSOR_DIAMETER;
        int leftMargin = inlineComponent.getLayoutParams().width - width;
        int cursorLineLeftMargin = leftMargin + halfDiameter;
        int cursorLineTopMargin = (inlineComponent.getLayoutParams().height - CURSOR_LINE_HEIGHT) / 2;

        View cursorLine = createCursorLine(cursorLineWidth, cursorLineLeftMargin, cursorLineTopMargin);
        addView(cursorLine);

        cursor = createCursor(cursorLineWidth, cursorLineLeftMargin, cursorLineTopMargin, consumer, color);
        addView(cursor);
    }

    @FloatRange(from = 0.0f, to = 1.0f)
    public float getCorrectValue(float startValue) {
        return (float) Math.min(Math.max(0.0, startValue), 1.0);
    }

    @NonNull
    private View createCursorLine(int cursorLineWidth, int cursorLineLeftMargin, int cursorLingTopMargin) {
        View cursorLine = new View(getContext());
        cursorLine.setBackgroundColor(CURSOR_LINE_COLOR);

        layoutParamsLine = new LayoutParamsDeBase_pourFrameLayout(
                cursorLineWidth, CURSOR_LINE_HEIGHT, cursorLineLeftMargin, cursorLingTopMargin);
        cursorLine.setLayoutParams(layoutParamsLine);

        return cursorLine;
    }

    @NonNull
    private View createCursor(int cursorLineWidth, int cursorLineLeftMargin, int cursorLineTopMargin, Consumer<Float> consumer, int color) {
        View cursor = new View(getContext());

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color);
        cursor.setBackground(drawable);

        int halfDiameter = CURSOR_DIAMETER / 2;
        int cursor_min_leftMargin = cursorLineLeftMargin - halfDiameter;
        int cursor_max_leftMargin = cursorLineLeftMargin + cursorLineWidth - halfDiameter;

        int yCursorLineCenter = cursorLineTopMargin + CURSOR_LINE_HEIGHT / 2;
        int topMargin = yCursorLineCenter - halfDiameter;
        layoutParamsCursor = new LayoutParamsDeBase_pourFrameLayout(
                CURSOR_DIAMETER, CURSOR_DIAMETER, 0, topMargin);
        cursor.setLayoutParams(layoutParamsCursor);
        int startXpos = (int) (cursorLineLeftMargin + cursorLineWidth * value - halfDiameter);
        cursor.setTranslationX(startXpos);

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
        return cursor;
    }

    public float getValue() {
        return value;
    }

    public View getCursor() {
        return cursor;
    }

    public FrameLayout.LayoutParams getLayoutParamsLine() {
        return layoutParamsLine;
    }

    public FrameLayout.LayoutParams getLayoutParamsCursor() {
        return layoutParamsCursor;
    }

    public InlineComponent getInlineComponent() {
        return inlineComponent;
    }

    @Override
    public void refreshHeight() {
        int cursorLineTopMargin = (inlineComponent.getLayoutParams().height - CURSOR_LINE_HEIGHT) / 2;

        if(layoutParamsLine != null) {
            layoutParamsLine.topMargin = cursorLineTopMargin;
        }

        int halfDiameter = CURSOR_DIAMETER / 2;
        int yCursorLineCenter = cursorLineTopMargin + CURSOR_LINE_HEIGHT / 2;
        int cursorTopMargin = yCursorLineCenter - halfDiameter;
        if(layoutParamsCursor != null) {
            layoutParamsCursor.topMargin = cursorTopMargin;
        }
    }
}
