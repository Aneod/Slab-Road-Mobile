package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.OnOffButton;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;

@SuppressLint("ViewConstructor")
public class OnOffButton extends Button implements IOnOffButton {

    private final String activeText;
    private final Runnable activation;
    private final String disactiveText;
    private final Runnable disactivation;

    public OnOffButton(InlineComponent inlineComponent, int width, int leftMargin,
                       boolean active,
                       String activeText, @Nullable Runnable activation,
                       String disactiveText, @Nullable Runnable disactivation) {
        super(inlineComponent, width, leftMargin);
        this.activeText = activeText;
        this.activation = activation;
        this.disactiveText = disactiveText;
        this.disactivation = disactivation;
        setAppearance(active);
    }

    @Override
    public void setAppearance(boolean active) {
        if(active) takeWhiteAppearance();
        else takeBlackAppearance();
    }

    @Override
    public void activation() {
        if(activation != null) {
            activation.run();
        }
        takeWhiteAppearance();
    }

    @Override
    public void disactivation() {
        if(disactivation != null) {
            disactivation.run();
        }
        takeBlackAppearance();
    }

    @Override
    public void takeWhiteAppearance() {
        takeWhiteAppearance(activeText);
        setRunnable(this::disactivation);
    }

    @Override
    public void takeBlackAppearance() {
        takeBlackAppearance(disactiveText);
        setRunnable(this::activation);
    }
}
