package com.example.veritablejeu.PopUp.PopUpContainer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.PopUp.PopUpContainer.ScrollBar.ScrollBar;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.Tools.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("ViewConstructor")
public class PopUpContainer extends FrameLayout implements IPopUpContainer {

    private static final int HEIGHT_BETWEEN_COMPONENTS = 30;
    private static final int MAX_VISUAL_HEIGHT = ScreenUtils.getScreenHeight() - PopUp.getInitialHeight()
            - 2 * (PopUp.getBORDER_WIDTH() + PopUp.getVerticalMargins());

    public static int getMaxVisualHeight() {
        return MAX_VISUAL_HEIGHT;
    }

    private final PopUp popUp;
    private final FrameLayout.LayoutParams layoutParams;
    private int currentHeight = 0;
    private final List<InlineComponent> contents = new ArrayList<>();
    private float translationY = 0.0f;
    private final ScrollBar scrollBar;

    @NonNull
    private FrameLayout.LayoutParams createLayoutParams() {
        int width = popUp.getLayoutParams().width - 2 * PopUp.getBORDER_WIDTH();
        int height = 0;
        int leftMargin = PopUp.getBORDER_WIDTH();
        int topMargin = PopUp.getBORDER_WIDTH() + PopUp.getInitialHeight();
        return new LayoutParamsDeBase_pourFrameLayout(width, height, leftMargin, topMargin);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void enableVerticalScroll() {
        this.setOnTouchListener(new View.OnTouchListener() {
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dy = event.getRawY() - startY;

                        float minTransaltionY = - (currentHeight - MAX_VISUAL_HEIGHT);
                        float maxTransaltionY = 0.0f;

                        boolean tooLow = translationY + dy < minTransaltionY && dy < 0;
                        boolean tooHigh = translationY + dy > maxTransaltionY && dy > 0;
                        boolean canMove = !tooLow && !tooHigh;
                        if(canMove) {
                            setTranslation((int) (translationY + dy));
                        }

                        startY = event.getRawY();
                        break;
                }
                return true;
            }
        });
    }

    public PopUpContainer(@NonNull PopUp popUp) {
        super(popUp.getContext());
        this.popUp = popUp;
        this.layoutParams = createLayoutParams();
        setLayoutParams(layoutParams);
        enableVerticalScroll();
        scrollBar = new ScrollBar(this);
        addView(scrollBar);
    }

    @Override
    public LayoutParams getLayoutParams() {
        return layoutParams;
    }

    /// CONTENT MANAGER
    @Override
    public void setContent(@NonNull InlineComponent... contents) {
        clearContents();
        Arrays.stream(contents)
                .sequential()
                .forEach(this::addContent);
        resetTranslation();
    }

    @Override
    public void addContent(InlineComponent component) {
        if(component == null || component.getParent() != null) return;
        LayoutParams layoutParamsComponent = component.getLayoutParams();
        if(layoutParamsComponent != null) {
            layoutParamsComponent.topMargin = currentHeight;
            component.setLayoutParams(layoutParamsComponent);
            addView(component);
            contents.add(component);
            addHeight(layoutParamsComponent.height);
        }
    }

    @Override
    public void clearContents() {
        contents.forEach(this::removeView);
        contents.clear();
        resetHeight();
    }


    /// HEIGHT MANAGER
    public int getCurrentHeight() {
        return currentHeight;
    }

    @Override
    public void refreshHeight() {
        resetHeight();
        resetTranslation();
        for(InlineComponent inlineComponent : contents) {
            LayoutParams layoutParamsComponent = inlineComponent.getLayoutParams();
            if(layoutParamsComponent != null) {
                layoutParamsComponent.topMargin = currentHeight;
                addHeight(layoutParamsComponent.height);
            }
        }
    }

    private void addHeight(int height) {
        int newHeight = currentHeight + height + HEIGHT_BETWEEN_COMPONENTS;
        setHeight(newHeight);
    }

    private void resetHeight() {
        setHeight(0);
    }

    @Override
    public float getTranslationY() {
        return translationY;
    }

    private void setTranslation(int translation) {
        translationY = translation;
        contents.forEach(contents -> contents.setTranslationY(translationY));
        scrollBar.refreshTopMargin();
    }

    private void resetTranslation() {
        setTranslation(0);
    }

    private void setHeight(int height) {
        currentHeight = Math.max(0, height);
        layoutParams.height = Math.min(currentHeight, MAX_VISUAL_HEIGHT);
        popUp.getLayoutParams().height =
                layoutParams.height + PopUp.getInitialHeight() + 2 * PopUp.getBORDER_WIDTH();
        scrollBar.refreshHeight();
    }
}
