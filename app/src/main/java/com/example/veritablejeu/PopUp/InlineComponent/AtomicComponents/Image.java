package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

public class Image extends AppCompatImageView {

    private static final float WIDTH_PORCENTAGE = .8f;

    public Image(@NonNull Context context) {
        super(context);
    }

    /**
     * The images in {@link InlineComponent} take place at the horizontal center.
     * <br>
     * Their width is already the same by {@link #WIDTH_PORCENTAGE}.
     * <br>
     * Their height is calculated for keep the original proportion with their width.
     * @param res the image to print.
     */
    public Image(@NonNull InlineComponent inlineComponent, int res) {
        super(inlineComponent.getContext());
        setLayoutParams(inlineComponent, res);
        setImageResource(res);
        int height = getLayoutParams().height;
        inlineComponent.setHeight(height);
    }

    private void setLayoutParams(InlineComponent inlineComponent, int res) {
        int width = getWidth(inlineComponent);
        int height = get_Height(width, res);
        int leftMargin = getLeftMargin(inlineComponent, width);
        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, leftMargin, 0
        );
        setLayoutParams(layoutParams);
    }

    private int getWidth(InlineComponent inlineComponent) {
        if(inlineComponent == null) return 0;
        int inlineComponentWidth = getInlineComponentWidth(inlineComponent);
        return (int) (inlineComponentWidth * WIDTH_PORCENTAGE);
    }

    private int getLeftMargin(InlineComponent inlineComponent, int width) {
        int inlineComponentWidth = getInlineComponentWidth(inlineComponent);
        return (inlineComponentWidth - width) / 2;
    }

    private int getInlineComponentWidth(InlineComponent inlineComponent) {
        if(inlineComponent == null)
            return 0;
        return inlineComponent.getLayoutParams().width;
    }

    private int get_Height(int width, int res) {
        float ratio = get_Height_Width_ImageRatio(res);
        return (int) (width * ratio);
    }

    private float get_Height_Width_ImageRatio(int res) {
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable = getContext().getResources().getDrawable(res, null);

        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            // Si ce n'est pas un BitmapDrawable, essayer de charger l'image comme Bitmap
            bitmap = BitmapFactory.decodeResource(getContext().getResources(), res);
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return (float) height / (float) width;
    }

    private float getResWidth(Bitmap res) {
        if(res == null)
            return 0;
        return res.getWidth();
    }

    private int getResHeight(Bitmap res) {
        if(res == null)
            return 0;
        return res.getHeight();
    }
}
