package com.example.veritablejeu.PopUp.InlineComponent;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public class PopUpComponent extends FrameLayout {

    private static final int SIDE_MARGINS = 30;
    protected static final int TEXT_SIZE = 18;

    public static int getTextSize() {
        return TEXT_SIZE;
    }

    protected final PopUp popUp;
    protected final FrameLayout.LayoutParams layoutParams;

    public PopUpComponent(@NonNull PopUp popUp) {
        super(popUp.getContext());
        this.popUp = popUp;

        int popUpWidth = popUp.getLayoutParams().width - 2 * popUp.getBORDER_WIDTH();
        int width = popUpWidth - 2 * SIDE_MARGINS;
        layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, 0, SIDE_MARGINS, 0
        );
        setLayoutParams(layoutParams);
    }

    public void setHeight(int height) {
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        popUp.refreshHeight();
    }

    public void setTopMargin(int topMargin) {
        layoutParams.topMargin = topMargin;
    }

}
