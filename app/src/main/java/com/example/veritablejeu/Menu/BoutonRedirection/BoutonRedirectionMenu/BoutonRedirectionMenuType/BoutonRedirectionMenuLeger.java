package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenuType;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenu;

public class BoutonRedirectionMenuLeger extends BoutonRedirectionMenu {

    public BoutonRedirectionMenuLeger(@NonNull Context context) {
        super(context);
    }

    public BoutonRedirectionMenuLeger(@NonNull Context context, String titre, int width, int height, int leftMargin, int topMargin, int color) {
        super(context, titre, width, height, leftMargin, topMargin, color);
        textViewTitre.setGravity(Gravity.CENTER_VERTICAL);
    }
}
