package com.slabroad.veritablejeu.LevelsPanelMVC.LevelsPanel.BottomBar.Components;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.R;
import com.slabroad.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import org.jetbrains.annotations.Contract;

public class ImageContainer extends FrameLayout {

    private static final int MARGIN = 20;
    private static final int LOADING_RES = R.drawable.loading_wheel;

    private final ImageView imageView;
    private final int res;

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

    public ImageContainer(Context context) {
        super(context);
        this.res = LOADING_RES;
        this.imageView = getImageView(res);
    }

    public ImageContainer(@NonNull FrameLayout parent, int height, int leftMargin, int topMargin, int res) {
        super(parent.getContext());
        this.res = res;

        FrameLayout.LayoutParams layoutParamsContainer =
                getLayoutParamsContainer(height, leftMargin, topMargin);
        setLayoutParams(layoutParamsContainer);

        imageView = getImageView(res);
        addView(imageView);
    }

    public void enableLoadingAnimation() {
        imageView.setImageResource(LOADING_RES);
        enableLoopAnimation();
    }

    private void enableLoopAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, .5f,
                Animation.RELATIVE_TO_SELF, .5f
        );
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        imageView.setAnimation(rotateAnimation);
    }

    public void disableLoadingAnimation() {
        imageView.setImageResource(res);
        imageView.setAnimation(null);
    }
}
