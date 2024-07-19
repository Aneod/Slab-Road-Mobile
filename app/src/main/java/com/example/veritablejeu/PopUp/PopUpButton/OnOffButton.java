package com.example.veritablejeu.PopUp.PopUpButton;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.ConstraintParams;

@SuppressLint("ViewConstructor")
public class OnOffButton extends PopUpButton {

    private final String activeText;
    private final Runnable activation;
    private final String disactiveText;
    private final Runnable disactivation;

    public OnOffButton(PopUp popUp, ConstraintParams constraintParams,
                       boolean active,
                       String activeText, Runnable activation,
                       String disactiveText, Runnable disactivation) {
        super(popUp, constraintParams);
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
        activation.run();
        takeWhiteAppearance();
    }

    public void disactivation() {
        disactivation.run();
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
