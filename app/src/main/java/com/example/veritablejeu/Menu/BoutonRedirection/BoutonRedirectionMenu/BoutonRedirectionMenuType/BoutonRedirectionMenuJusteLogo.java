package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenuType;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionMenu.BoutonRedirectionMenu;

public class BoutonRedirectionMenuJusteLogo extends BoutonRedirectionMenu {

    public BoutonRedirectionMenuJusteLogo(@NonNull Context context) {
        super(context);
    }

    public BoutonRedirectionMenuJusteLogo(@NonNull Context context, int widthHeight, int leftMargin, int topMargin, int color) {
        super(context, null, widthHeight, widthHeight, leftMargin, topMargin, color);
    }
}
