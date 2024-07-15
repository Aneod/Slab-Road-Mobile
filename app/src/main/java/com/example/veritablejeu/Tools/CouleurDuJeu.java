package com.example.veritablejeu.Tools;

public enum CouleurDuJeu {
    GrisClair, BleuClair, BleuFonce, Rouge, Jaune, Orange, Vert, Violet, Mauve;

    public int Int() {
        switch (this) {
            case GrisClair: return 0xFFCCCCCC;
            case BleuClair: return 0xFF96DBF2;
            case BleuFonce: return 0xFF86ADDC;
            case Rouge: return 0xFFFF9D93;
            case Jaune: return 0xFFFFBD81;
            case Orange: return 0xFFFFC59D;
            case Vert: return 0xFFB0E4C8;
            case Violet: return 0xFFF0BDFE;
            case Mauve: return 0xFFFFACC2;
            default: return 0x00000000;
        }
    }

    public static CouleurDuJeu getCouleurCaseSelonCode(int code) {
        switch (code) {
            case 2 : return CouleurDuJeu.BleuClair;
            case 3 : return CouleurDuJeu.BleuFonce;
            case 4 : return CouleurDuJeu.Rouge;
            case 5 : return CouleurDuJeu.Vert;
            case 6 : return CouleurDuJeu.Jaune;
            case 8 : return CouleurDuJeu.Orange;
            default: return CouleurDuJeu.Violet;
        }
    }
}
