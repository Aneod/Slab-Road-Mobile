package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;

public interface IButton {

    /**
     * Change the button appearence for a black version. And also the text.
     * @param text the new text in the button.
     */
    void takeBlackAppearance(String text);

    /**
     * Change the button appearence for a white version. And also the text.
     * @param text the new text in the button.
     */
    void takeWhiteAppearance(String text);

    /**
     * Modify what append when a user clic on the button.
     * @param runnable the new clic effect.
     */
    void setRunnable(Runnable runnable);

    /**
     * Update the button layoutParams. Useful when a text on the
     * same {@link InlineComponent} change the global height.
     */
    void refreshLayoutParams();
}
