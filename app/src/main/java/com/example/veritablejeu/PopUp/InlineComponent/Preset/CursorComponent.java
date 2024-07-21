package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Cursor.Cursor;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class CursorComponent extends InlineComponent {

    private static final float WIDTH_TITLED_DISTRIBUTION = .5f;
    private static final int DEFAULT_CURSOR_COLOR = Color.BLACK;

    private final Cursor cursor;

    public CursorComponent(@NonNull PopUp popUp, String title, float startValue,
                           @Nullable Consumer<Float> consumer) {
        this(popUp, title, startValue, consumer, DEFAULT_CURSOR_COLOR);
    }

    public CursorComponent(@NonNull PopUp popUp, String title, float startValue,
                           @Nullable Consumer<Float> consumer, int color) {
        super(popUp);
        float correctValue = (float) Math.min(Math.max(0.0, startValue), 1.0);

        int width = getLayoutParams().width;
        int height = Cursor.getHEIGHT();
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        Text text = new Text(this, title, titledWidth, height, Gravity.CENTER_VERTICAL);
        addView(text);

        int cursorWidth = width - titledWidth;
        cursor = new Cursor(this, cursorWidth, correctValue, consumer, color);
        addView(cursor);
    }

    @Override
    public void setHeight(int height) {
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        popUp.refreshHeight();
        if(cursor != null) {
            cursor.refreshHeight();
        }
    }
}
