package com.example.veritablejeu.LevelsPanel;

import com.example.veritablejeu.LevelsPanel.BottomBar.BottomBar;
import com.example.veritablejeu.LevelsPanel.Scroller.Scroller;

public interface ILevelsPanel {

    /**
     * Show the panel of the screen.
     */
    void show();

    /**
     * Hide the panel of the screen.
     */
    void hide();

    /**
     * @return the scroller of the panel.
     */
    Scroller getScroller();

    /**
     * @return the bottom bar of the panel.
     */
    BottomBar getBottomBar();

}
