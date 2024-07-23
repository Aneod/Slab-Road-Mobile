package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar;

public interface IBottomBar {

    /**
     * Hide all elements in the bar.
     */
    void clear();

    /**
     * Hide the buttons and the number page, and show a loading icon.
     */
    void showLoadingIcon();

    /**
     * Set the indicated number page.
     * @param pageNumber the new indicated number page.
     */
    void setPageNumber(int pageNumber);

    /**
     * Hide the loading icon, and show the buttons and the number page (1 by default).
     * @param numberOfPages the number of pages.
     */
    void setNumberOfPages(int numberOfPages);

}
