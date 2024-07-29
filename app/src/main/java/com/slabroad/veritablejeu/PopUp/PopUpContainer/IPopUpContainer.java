package com.slabroad.veritablejeu.PopUp.PopUpContainer;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;

public interface IPopUpContainer {

    /**
     * Modify the title and the popUp content.
     * @param contents all {@link InlineComponent} who will compose the popUp. From top to bottom.
     */
    void setContent(@NonNull InlineComponent... contents);

    /**
     * Add a single {@link InlineComponent} at the bottom of the popUp.
     * @param popUpContent the content to add.
     */
    void addContent(InlineComponent popUpContent);

    /**
     * Remove all {@link InlineComponent} in the popUp, and reset it height.
     */
    void clearContents();

    /**
     * Reverify all components height. Useful if a component changes is height.
     */
    void refreshHeight();

}
