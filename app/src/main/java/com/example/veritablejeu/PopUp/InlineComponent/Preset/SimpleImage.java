package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Image;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;

@SuppressLint("ViewConstructor")
public class SimpleImage extends InlineComponent {

    public SimpleImage(@NonNull PopUp popUp, int res) {
        super(popUp);
        Image image = new Image(this, res);
        addView(image);
    }
}
