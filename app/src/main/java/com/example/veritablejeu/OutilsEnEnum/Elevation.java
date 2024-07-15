package com.example.veritablejeu.OutilsEnEnum;

public enum Elevation {
    PopUp, Fenetre, Blob, Cable, PointIntersection, Porte, Mur, Dalle, Square, SpecSquare, Bulle;

    public int getElevation() {
        switch (this) {
            case PopUp: return 10;
            case Fenetre: return 9;
            case PointIntersection:
            case Blob: return 7;
            case Porte: return 6;
            case Dalle: return 5;
            case Cable: return 4;
            case Mur: return 1;
            case Square: return 0;
            case SpecSquare: return -1;
            case Bulle: return -2;
        }
        return 0;
    }
}
