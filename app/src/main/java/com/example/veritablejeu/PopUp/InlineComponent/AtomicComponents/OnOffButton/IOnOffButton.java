package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.OnOffButton;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;

public interface IOnOffButton {

    /**
     * Change the button appearance. If the given boolean is true (active), the button become white,
     * false otherwise.
     * @param active true for take the white appearence, false for the black one.
     */
    void setAppearance(boolean active);

    /**
     * The button can be activated when it's black, and then become white and use his activation effect.
     * <br>
     * So the button become white and run his activation effect.
     */
    void activation();

    /**
     * The button can be disactivated when it's white, and then become black and use his disactivation effect.
     * <br>
     * So the button become black and run his disactivation effect.
     */
    void disactivation();

    /**
     * Change the appearence for the white one.
     * @see Button#getWhiteAppearence()
     */
    void takeWhiteAppearance();

    /**
     * Change the appearence for the black one.
     * @see Button#getBlackAppearence()
     */
    void takeBlackAppearance();
}
