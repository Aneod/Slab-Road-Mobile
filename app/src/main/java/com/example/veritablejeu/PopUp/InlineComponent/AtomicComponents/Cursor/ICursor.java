package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Cursor;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;

public interface ICursor {

    /**
     * Update the height of the cursor and the line cursor. Useful when a text on the
     * same {@link InlineComponent} change the global height.
     */
    void refreshHeight();
}
