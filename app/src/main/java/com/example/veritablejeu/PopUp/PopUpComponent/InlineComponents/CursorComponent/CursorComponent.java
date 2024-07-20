package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.CursorComponent;

import android.annotation.SuppressLint;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.Cursor;
import com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents.PopUpText;
import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUp;

/**
 * Crée un curseur appliquant une modification personnalisée selon un 
 * paramètre appartenant à un interval continu.
 */
@SuppressLint("ViewConstructor")
public class CursorComponent extends PopUpComponent {

    /**
     * La largeur occupée par le titre, en pct.
     */
    private static final float WIDTH_TITLED_DISTRIBUTION = .5f;

    public static float getWidthTitledDistribution() {
        return WIDTH_TITLED_DISTRIBUTION;
    }

    public CursorComponent(@NonNull PopUp popUp, String title, int height, float startValue, @Nullable Consumer<Float> consumer, int color) {
        super(popUp);
        float correctValue = (float) Math.min(Math.max(0.0, startValue), 1.0);
        initializeView(title, correctValue, height, consumer, color);
    }

    private void initializeView(String titled, float value, int height, Consumer<Float> consumer, int color) {
        setHeight(height);

        int width = getLayoutParams().width;
        int titledWidth = (int) (width * WIDTH_TITLED_DISTRIBUTION);
        PopUpText popUpText = new PopUpText(this, titled, titledWidth, height, Gravity.CENTER_VERTICAL);
        addView(popUpText);

        Cursor cursor = new Cursor(this, value, consumer, color);
        addView(cursor);
    }
}
