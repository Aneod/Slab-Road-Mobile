package com.example.veritablejeu.LevelsPanel.BottomBar;

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

    /**
     * Modify the runnable of the previous page button.
     * @param runnable the new runnable.
     */
    void setPreviousPageRunnable(Runnable runnable);

    /**
     * Modify the runnable of the next page button.
     * @param runnable the new runnable.
     */
    void setNextPageRunnable(Runnable runnable);

}
