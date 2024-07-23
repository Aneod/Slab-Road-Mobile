package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

public class PageNumberIndicator extends androidx.appcompat.widget.AppCompatTextView {

    private static final int COLOR = Color.BLACK;
    private static final int SIZE = 16;
    private static final int GRAVITY = Gravity.CENTER;

    private int pageNumber = 1;
    private int numberOfPages = 1;

    public PageNumberIndicator(Context context) {
        super(context);
        setTextColor(COLOR);
        setTextSize(SIZE);
        setGravity(GRAVITY);
    }

    private void refresh() {
        String text = pageNumber + " / " + numberOfPages;
        setText(text);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        refresh();
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        refresh();
    }
}
