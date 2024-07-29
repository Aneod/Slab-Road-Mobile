package com.slabroad.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.PopUp.InlineComponent.AtomicComponents.Image;
import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.slabroad.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class SimpleImage extends InlineComponent {

    public SimpleImage(@NonNull PopUp popUp, int res) {
        super(popUp);
        Image image = new Image(this, res);
        addView(image);
    }
}
