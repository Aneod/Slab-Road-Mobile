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

    public static float getWidthTitledDistribution() {
        return WIDTH_TITLED_DISTRIBUTION;
    }

    private final Cursor cursor;

    public CursorComponent(@NonNull PopUp popUp, String title, float startValue,
                           @Nullable Consumer<Float> consumer, int color) {
        super(popUp);
        int width = getLayoutParams().width;
        int height = Cursor.getHEIGHT();
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        Text text = new Text(this, title, titledWidth, height, Gravity.CENTER_VERTICAL);
        addView(text);

        int cursorWidth = width - titledWidth;
        cursor = new Cursor(this, cursorWidth, startValue, consumer, color);
        addView(cursor);
    }

    public Cursor getCursor() {
        return cursor;
    }

    @Override
    public void setHeight(int height) {
        layoutParams.height = height;
        popUp.refreshHeight();
        if(cursor != null) {
            cursor.refreshHeight();
        }
    }
}
