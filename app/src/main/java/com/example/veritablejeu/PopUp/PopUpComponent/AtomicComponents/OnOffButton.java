package com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.Tools.LayoutParams.ConstraintParams;

@SuppressLint("ViewConstructor")
public class OnOffButton extends PopUpButton {

    private final String activeText;
    private final Runnable activation;
    private final String disactiveText;
    private final Runnable disactivation;

    public OnOffButton(PopUpComponent popUpComponent, int width, int leftMargin,
                       boolean active,
                       String activeText, @Nullable Runnable activation,
                       String disactiveText, @Nullable Runnable disactivation) {
        super(popUpComponent, width, leftMargin);
        this.activeText = activeText;
        this.activation = activation;
        this.disactiveText = disactiveText;
        this.disactivation = disactivation;
        setAppearance(active);
    }

    public void setAppearance(boolean active) {
        if(active) takeWhiteAppearance();
        else takeBlackAppearance();
    }

    public void activation() {
        if(activation != null) {
            activation.run();
        }
        takeWhiteAppearance();
    }

    public void disactivation() {
        if(disactivation != null) {
            disactivation.run();
        }
        takeBlackAppearance();
    }

    public void takeWhiteAppearance() {
        takeWhiteAppearance(activeText);
        setRunnable(this::disactivation);
    }

    public void takeBlackAppearance() {
        takeBlackAppearance(disactiveText);
        setRunnable(this::activation);
    }
}
