package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components.ImageContainer;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components.PageNumberIndicator;
import com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.LevelsPanel;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.SimpleBackground;

@SuppressLint("ViewConstructor")
public class BottomBar extends FrameLayout implements IBottomBar {

    private static final int HEIGHT = 100;
    private static final int PAGES_SIZE = 12;

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getPagesSize() {
        return PAGES_SIZE;
    }

    private final ImageContainer leftArrow;
    private final ImageContainer rightArrow;
    private final PageNumberIndicator pageNumberIndicator;

    @NonNull
    private GradientDrawable get_background() {
        int borderWidth = LevelsPanel.getBorderWidth();
        return SimpleBackground.create(Color.WHITE, Color.BLACK, borderWidth);
    }

    @NonNull
    private FrameLayout.LayoutParams get_layoutParams(@NonNull LevelsPanel levelsPanel) {
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

        GradientDrawable background = get_background();
        setBackground(background);

        FrameLayout.LayoutParams layoutParams = get_layoutParams(levelsPanel);
        setLayoutParams(layoutParams);

        leftArrow = getLeftArrow();
        rightArrow = getRightArrow();
        pageNumberIndicator = new PageNumberIndicator(getContext());
    }

    @Override
    public void clear() {
        removeAllViews();
    }

    @Override
    public void showLoadingIcon() {

    }

    @Override
    public void setPageNumber(int pageNumber) {
        pageNumberIndicator.setPageNumber(pageNumber);
    }

    @Override
    public void setNumberOfPages(int levelsListSize) {
        int min = 1;
        int numberOfPages = (int) Math.ceil((double) levelsListSize / PAGES_SIZE);
        int reducedNumberOfPages = Math.max(min, numberOfPages);
        pageNumberIndicator.setNumberOfPages(reducedNumberOfPages);
        showAll();
        setPageNumber(1);
    }

    private void showAll() {
        safeShow(pageNumberIndicator);
        safeShow(leftArrow);
        safeShow(rightArrow);
    }

    private void safeShow(View view) {
        if(view != null && view.getParent() == null) {
            addView(view);
        }
    }

    private void getPrevious() {
        int currentPageNumber = pageNumberIndicator.getPageNumber();
        getPage(currentPageNumber - 1);
    }

    private void getNext() {
        int currentPageNumber = pageNumberIndicator.getPageNumber();
        getPage(currentPageNumber + 1);
    }

    private void getPage(int pageNumber) {
        int currentNumberOfPages = pageNumberIndicator.getNumberOfPages();
        if(pageNumber < 1 || pageNumber > currentNumberOfPages) {
            return;
        }
        pageNumberIndicator.setPageNumber(pageNumber);

        int pageNumberBy0 = pageNumber - 1;
        int firstIndex = pageNumberBy0 * PAGES_SIZE;
        int lastIndex = firstIndex + PAGES_SIZE;
        //Controller controller = Controller.getInstance(getContext());
        //controller.getLevels(firstIndex, lastIndex);
    }

}
