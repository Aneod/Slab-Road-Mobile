package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.Menu.PageDeSelection.ContainerDeFleche;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.SimpleBackground;

@SuppressLint("ViewConstructor")
public class BottomBar extends FrameLayout implements IBottomBar {

    private static final int PAGES_SIZE = 12;

    public static int getPagesSize() {
        return PAGES_SIZE;
    }

    private final ContainerDeFleche flecheGauche;
    private final ContainerDeFleche flecheDroite;
    private final PageNumberIndicator pageNumberIndicator;

    public BottomBar(@NonNull FrameLayout parent, int width, int height, int topMargin, int largeurBordure) {
        super(parent.getContext());

        GradientDrawable background = SimpleBackground.create(
                Color.WHITE, Color.BLACK, largeurBordure);
        setBackground(background);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(width, height, 0, topMargin);
        setLayoutParams(layoutParams);

        flecheGauche = new ContainerDeFleche(
                this, height, 0, 0, R.drawable.fleche_gauche);
        flecheGauche.setOnClickListener(v -> getPrevious());
        addView(flecheGauche);

        int leftMarginBoutonDroit = width - height;
        flecheDroite = new ContainerDeFleche(
                this, height, leftMarginBoutonDroit, 0, R.drawable.fleche_droite);
        flecheDroite.setOnClickListener(v -> getNext());
        addView(flecheDroite);

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
    }

    private void showAll() {
        safeShow(pageNumberIndicator);
        safeShow(flecheGauche);
        safeShow(flecheDroite);
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
        Controller controller = Controller.getInstance(getContext());
        controller.getLevels(firstIndex, lastIndex);
    }

}
