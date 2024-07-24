package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar;

public interface IBottomBar {

    void showPageNumberIndicator();

    void hidePageNumberIndicator();

    /**
     * Set the indicated number page.
     * @param pageNumber the new indicated number page.
     */
    void setPageNumber(int pageNumber);

    /**
     * Set the number of pages.
     * @param numberOfPages the number of pages.
     */
    void setNumberOfPages(int numberOfPages);



    void showLeftArrow();

    void hideLeftArrow();

    void enableLeftArrowLoadingAnimation();

    void disableLeftArrowLoadingAnimation();



    void showRightArrow();

    void hideRightArrow();

    void enableRightArrowLoadingAnimation();

    void disableRightArrowLoadingAnimation();


    void setArrowsClickable(boolean enable);



    void justShowTheRightLoadingWheel();

}
