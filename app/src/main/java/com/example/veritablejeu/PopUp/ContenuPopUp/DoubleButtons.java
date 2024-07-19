package com.example.veritablejeu.PopUp.ContenuPopUp;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.PopUpButton.PopUpButton;
import com.example.veritablejeu.Tools.LayoutParams.ConstraintParams;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

@SuppressLint("ViewConstructor")
public class DoubleButtons extends PopUpComponent {

    private static final float TOTAL_BUTTONS_WIDTH_OCCUPATION_PERCENTAGE = .7f;
    private static final int BUTTONS_HEIGHT = 75;

    private final PopUpButton boutonA;
    private final PopUpButton boutonB;

    public DoubleButtons(@NonNull PopUp popUp, String textA, Runnable runA, String textB, Runnable runB) {
        super(popUp);
        setHeight(BUTTONS_HEIGHT);

        int disponibleWidth = getLayoutParams().width;
        float buttonsWidth = disponibleWidth * TOTAL_BUTTONS_WIDTH_OCCUPATION_PERCENTAGE;
        float buttonWidth = buttonsWidth / 2;
        float side_margins = (disponibleWidth - buttonsWidth) / 4;
        float buttonsGap = 2 * side_margins;

        ConstraintParams constraintParams1 = new ConstraintParams((int) buttonWidth, BUTTONS_HEIGHT, (int) side_margins + popUp.getBORDER_WIDTH(), 0);
        boutonA = new PopUpButton(popUp, constraintParams1);
        boutonA.takeWhiteAppearance(textA);
        boutonA.setRunnable(runA);
        this.addView(boutonA);

        float leftMargin = side_margins + buttonWidth + buttonsGap;
        ConstraintParams constraintParams2 = new ConstraintParams((int) buttonWidth, BUTTONS_HEIGHT, (int) leftMargin + popUp.getBORDER_WIDTH(), 0);
        boutonB = new PopUpButton(popUp, constraintParams2);
        boutonB.takeBlackAppearance(textB);
        boutonB.setRunnable(runB);
        this.addView(boutonB);
    }

    public void setRunnableA(Runnable runnable) {
        boutonA.setRunnable(runnable);
    }

    public void setRunnableB(Runnable runnable) {
        boutonB.setRunnable(runnable);
    }
}
