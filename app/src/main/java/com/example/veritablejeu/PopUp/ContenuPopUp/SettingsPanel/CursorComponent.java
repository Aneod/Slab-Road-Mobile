package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

/**
 * Crée un curseur appliquant une modification personnalisée selon un 
 * paramètre appartenant à un interval continu.
 */
public class CursorComponent extends FrameLayout {

    /**
     * La largeur occupée par le titre, en pct.
     */
    private static final float WIDTH_TITLED_DISTRIBUTION = .6f;
    private static final int TEXT_SIZE = 16;
    private static final int CURSOR_LINE_HEIGHT = 6;
    private static final int CURSOR_LINE_COLOR = Color.LTGRAY;
    private static final int CURSOR_DIAMETER = 25;

    /**
     * La valeur actuelle du curseur en poucentage.
     */
    private float VALUE;

    public CursorComponent(@NonNull Context context) {
        super(context);
    }

    public CursorComponent(@NonNull Context context, String titled, int width, int height, int leftMargin, int topMargin, float startValue, Consumer<Float> consumer, int color) {
        super(context);
        VALUE = (float) Math.min(Math.max(0.0, startValue), 1.0);
        initializeView(titled, width, height, leftMargin, topMargin, consumer, color);
    }

    private void initializeView(String titled, int width, int height, int leftMargin, int topMargin, Consumer<Float> consumer, int color) {
        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(width, height, leftMargin, topMargin);
        setLayoutParams(layoutParams);
        setBackgroundColor(Color.WHITE);

        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        addTitled(titled, titledWidth);

        int halfDiameter = CURSOR_DIAMETER / 2;
        int cursorLineWidth = width - titledWidth - CURSOR_DIAMETER;
        int cursorLineLeftMargin = titledWidth + halfDiameter;
        int cursorLingTopMargin = (height - CURSOR_LINE_HEIGHT) / 2;
        addCursorLine(cursorLineWidth, cursorLineLeftMargin, cursorLingTopMargin);
        addCursor(cursorLineWidth, cursorLineLeftMargin, cursorLingTopMargin, consumer, color);
    }

    private void addTitled(String text, int width) {
        AppCompatTextView titled = new AppCompatTextView(getContext());
        titled.setText(text);
        titled.setTextSize(TEXT_SIZE);
        titled.setGravity(Gravity.CENTER_VERTICAL);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                width, ConstraintLayout.LayoutParams.MATCH_PARENT, 0, 0);
        titled.setLayoutParams(layoutParams);
        
        addView(titled);
    }

    private void addCursorLine(int width, int leftMargin, int topMargin) {
        View cursorLine = new View(getContext());
        cursorLine.setBackgroundColor(CURSOR_LINE_COLOR);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                width, CURSOR_LINE_HEIGHT, leftMargin, topMargin);
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
        int startXpos = (int) (cursorLineLeftMargin + cursorLineWidth * VALUE - halfDiameter);
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
                        VALUE = (cursor.getTranslationX() + halfDiameter - cursorLineLeftMargin) / cursorLineWidth;

                        consumer.accept(VALUE);

                        xPos = event.getRawX();
                        break;
                }
                return true;
            }
        });
    }
}
