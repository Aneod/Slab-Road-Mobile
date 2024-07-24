package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components.ImageContainer;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components.PageNumberIndicator;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.SimpleBackground;

@SuppressLint("ViewConstructor")
public class BottomBar extends FrameLayout implements IBottomBar {

    private static final int HEIGHT = 100;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    private final LevelsPanel levelsPanel;
    private final ImageContainer leftArrow;
    private final ImageContainer rightArrow;
    private final PageNumberIndicator pageNumberIndicator;

    @NonNull
    private GradientDrawable get_background() {
        int borderWidth = LevelsPanel.getBorderWidth();
        return SimpleBackground.create(Color.WHITE, Color.BLACK, borderWidth);
    }

    @NonNull
    private FrameLayout.LayoutParams get_layoutParams() {
        int width = levelsPanel.getLayoutParams().width;
        int topMargin = levelsPanel.getLayoutParams().height - HEIGHT;
        return new LayoutParamsDeBase_pourFrameLayout(width, HEIGHT, 0, topMargin);
    }

    @NonNull
    private ImageContainer getLeftArrow() {
        ImageContainer leftArrow = new ImageContainer(
                this, getLayoutParams().height, 0, 0, R.drawable.fleche_gauche);
        leftArrow.setOnClickListener(v -> getPrevious());
        return leftArrow;
    }

    @NonNull
    private ImageContainer getRightArrow() {
        int leftMargin = getLayoutParams().width - getLayoutParams().height;
        ImageContainer rightArrow = new ImageContainer(
                this, getLayoutParams().height, leftMargin, 0, R.drawable.fleche_droite);
        rightArrow.setOnClickListener(v -> getNext());
        return rightArrow;
    }

    public BottomBar(@NonNull LevelsPanel levelsPanel) {
        super(levelsPanel.getContext());
        this.levelsPanel = levelsPanel;

        GradientDrawable background = get_background();
        setBackground(background);

        refreshLayoutParams();

        leftArrow = getLeftArrow();
        rightArrow = getRightArrow();
        pageNumberIndicator = new PageNumberIndicator(getContext());
    }

    public void refreshLayoutParams() {
        FrameLayout.LayoutParams layoutParams = get_layoutParams();
        setLayoutParams(layoutParams);
    }

    @Override
    public void showPageNumberIndicator() {
        safeShow(pageNumberIndicator);
    }

    @Override
    public void hidePageNumberIndicator() {
        removeView(pageNumberIndicator);
    }

    @Override
    public void setPageNumber(int pageNumber) {
        pageNumberIndicator.setPageNumber(pageNumber);
    }

    @Override
    public void setNumberOfPages(int numberOfPages) {
        pageNumberIndicator.setNumberOfPages(numberOfPages);
    }

    @Override
    public void showLeftArrow() {
        safeShow(leftArrow);
    }

    @Override
    public void hideLeftArrow() {
        removeView(leftArrow);
    }

    @Override
    public void enableLeftArrowLoadingAnimation() {
        leftArrow.enableLoadingAnimation();
        setArrowsClickable(false);
    }

    @Override
    public void disableLeftArrowLoadingAnimation() {
        leftArrow.disableLoadingAnimation();
        setArrowsClickable(true);
    }

    @Override
    public void showRightArrow() {
        safeShow(rightArrow);
    }

    @Override
    public void hideRightArrow() {
        removeView(rightArrow);
    }

    @Override
    public void enableRightArrowLoadingAnimation() {
        rightArrow.enableLoadingAnimation();
        setArrowsClickable(false);
    }

    @Override
    public void disableRightArrowLoadingAnimation() {
        rightArrow.disableLoadingAnimation();
        setArrowsClickable(true);
    }

    @Override
    public void setArrowsClickable(boolean enable) {
        leftArrow.setClickable(enable);
        rightArrow.setClickable(enable);
    }

    @Override
    public void justShowTheRightLoadingWheel() {
        levelsPanel.getBottomBar().hidePageNumberIndicator();
        levelsPanel.getBottomBar().hideLeftArrow();
        levelsPanel.getBottomBar().disableLeftArrowLoadingAnimation();
        levelsPanel.getBottomBar().showRightArrow();
        levelsPanel.getBottomBar().enableRightArrowLoadingAnimation();
    }

    private void safeShow(View view) {
        if(view != null && view.getParent() == null) {
            addView(view);
        }
    }

    private void getPrevious() {
        if(!firstPageReached()) {
            enableLeftArrowLoadingAnimation();
            int currentPageNumber = pageNumberIndicator.getPageNumber();
            getPage(currentPageNumber - 1);
        }
    }

    private void getNext() {
        if(!lastPageReached()) {
            enableRightArrowLoadingAnimation();
            int currentPageNumber = pageNumberIndicator.getPageNumber();
            getPage(currentPageNumber + 1);
        }
    }

    private boolean firstPageReached() {
        int min = 1;
        int currentPageNumber = pageNumberIndicator.getPageNumber();
        return currentPageNumber <= min;
    }

    private boolean lastPageReached() {
        int max = pageNumberIndicator.getNumberOfPages();
        int currentPageNumber = pageNumberIndicator.getPageNumber();
        return currentPageNumber >= max;
    }

    private void getPage(int pageNumber) {
        int pageNumberBy0 = pageNumber - 1;
        int firstIndex = pageNumberBy0 * LevelsPanel.getPagesSize();
        int lastIndex = firstIndex + LevelsPanel.getPagesSize();
        levelsPanel.get(firstIndex, lastIndex, pageNumber);
    }

}
