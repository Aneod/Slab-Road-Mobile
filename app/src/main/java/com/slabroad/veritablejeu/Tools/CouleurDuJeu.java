package com.slabroad.veritablejeu.Tools;

import android.graphics.Color;

public enum CouleurDuJeu {
    BleuClair, BleuFonce, Rouge, Jaune, Orange, Vert, Violet, Mauve;

    private static final int DEFAULT_COLOR = Color.BLACK;

    public int Int() {
        switch (this) {
            case BleuClair: return 0xFF96DBF2;
            case BleuFonce: return 0xFF86ADDC;
            case Rouge: return 0xFFFF9D93;
            case Jaune: return 0xFFFFBD81;
            case Orange: return 0xFFFFC59D;
            case Vert: return 0xFFB0E4C8;
            case Violet: return 0xFFF0BDFE;
            case Mauve: return 0xFFFFACC2;
            default: return DEFAULT_COLOR;
        }
    }
}
