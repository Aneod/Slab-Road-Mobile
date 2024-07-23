package com.example.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import org.jetbrains.annotations.Contract;

public class ImageContainer extends FrameLayout {

    private static final int MARGIN = 20;

    @NonNull
    private FrameLayout.LayoutParams getLayoutParamsImage() {
        int height = getLayoutParams().height - 2 * MARGIN;
        return new LayoutParamsDeBase_pourFrameLayout(height, height, MARGIN, MARGIN);
    }

    @NonNull
    private ImageView getImageView(int image) {
        ImageView fleche = new ImageView(getContext());
        fleche.setLayoutParams(getLayoutParamsImage());
        fleche.setImageResource(image);
        return fleche;
    }

    @NonNull
    @Contract("_, _, _ -> new")
    private FrameLayout.LayoutParams getLayoutParamsContainer(int height, int leftMargin, int topMargin) {
        return new LayoutParamsDeBase_pourFrameLayout(height, height, leftMargin, topMargin);
    }

    public ImageContainer(@NonNull FrameLayout parent, int height, int leftMargin, int topMargin, int res) {
        super(parent.getContext());

        FrameLayout.LayoutParams layoutParamsContainer =
                getLayoutParamsContainer(height, leftMargin, topMargin);
        setLayoutParams(layoutParamsContainer);

        ImageView image = getImageView(res);
        addView(image);
    }

    public ImageContainer(Context context) {
        super(context);
    }
}
