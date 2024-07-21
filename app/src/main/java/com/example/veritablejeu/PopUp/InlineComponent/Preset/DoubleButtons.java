package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;

@SuppressLint("ViewConstructor")
public class DoubleButtons extends InlineComponent {

    private static final float TOTAL_BUTTONS_WIDTH_OCCUPATION_PERCENTAGE = .7f;

    public static float getTotalButtonsWidthOccupationPercentage() {
        return TOTAL_BUTTONS_WIDTH_OCCUPATION_PERCENTAGE;
    }

    private final Button boutonA;
    private final Button boutonB;

    public DoubleButtons(@NonNull PopUp popUp, String textA, Runnable runA, String textB, Runnable runB) {
        super(popUp);
        setHeight(Button.getHEIGHT());

        int disponibleWidth = getLayoutParams().width;
        float buttonsWidth = disponibleWidth * TOTAL_BUTTONS_WIDTH_OCCUPATION_PERCENTAGE;
        float buttonWidth = buttonsWidth / 2;
        float side_margins = (disponibleWidth - buttonsWidth) / 4;
        float buttonsGap = 2 * side_margins;

        int leftMarginFirst = (int) side_margins + popUp.getBORDER_WIDTH();
        boutonA = new Button(this, (int) buttonWidth, leftMarginFirst);
        boutonA.takeWhiteAppearance(textA);
        boutonA.setRunnable(runA);
        this.addView(boutonA);

        int leftMarginSecond = (int) (side_margins + buttonWidth + buttonsGap + popUp.getBORDER_WIDTH());
        boutonB = new Button(this, (int) buttonWidth, leftMarginSecond);
        boutonB.takeBlackAppearance(textB);
        boutonB.setRunnable(runB);
        this.addView(boutonB);
    }

    public Button getBoutonA() {
        return boutonA;
    }

    public Button getBoutonB() {
        return boutonB;
    }

    public void setRunnableA(Runnable runnable) {
        boutonA.setRunnable(runnable);
    }

    public void setRunnableB(Runnable runnable) {
        boutonB.setRunnable(runnable);
    }
}
