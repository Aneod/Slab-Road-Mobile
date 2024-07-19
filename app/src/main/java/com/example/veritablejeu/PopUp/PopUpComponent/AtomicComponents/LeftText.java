package com.example.veritablejeu.PopUp.PopUpComponent.AtomicComponents;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;

public class LeftText extends AppCompatTextView {

    public LeftText(@NonNull Context context) {
        super(context);
    }

    public LeftText(@NonNull PopUpComponent popUpComponent, String text, int width) {
        super(popUpComponent.getContext());
        setText(text);
        setTextSize(PopUpComponent.getTextSize());
        setGravity(Gravity.CENTER_VERTICAL);

        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                width, ConstraintLayout.LayoutParams.MATCH_PARENT, 0, 0);
        setLayoutParams(layoutParams);
    }
}
