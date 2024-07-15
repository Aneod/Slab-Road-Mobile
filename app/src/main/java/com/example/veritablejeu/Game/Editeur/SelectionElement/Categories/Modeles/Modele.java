package com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.Modeles;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Editeur.SelectionElement.Categories.TypeCategorie;
import com.example.veritablejeu.Tools.CouleurDuJeu;

public enum Modele {
    Square, Fantome, Gomme,
    PorteBleuClair, PorteBleuFoncee, PorteRouge, Mur,
    DalleBleuClair, DalleBleuFoncee, DalleRouge, DalleOrange, DalleJaune, DalleVerte, DalleViolette,
    Blob,
    Cable;

    public int getCouleurDuModele() {
        switch (this) {
            case PorteBleuClair:
            case DalleBleuClair:
                return CouleurDuJeu.BleuClair.Int();
            case PorteBleuFoncee:
            case DalleBleuFoncee:
                return CouleurDuJeu.BleuFonce.Int();
            case PorteRouge:
            case DalleRouge:
                return CouleurDuJeu.Rouge.Int();
            case DalleOrange: return CouleurDuJeu.Orange.Int();
            case DalleJaune: return CouleurDuJeu.Jaune.Int();
            case DalleVerte: return CouleurDuJeu.Vert.Int();
            case DalleViolette: return CouleurDuJeu.Violet.Int();
            default: return Color.BLACK;
        }
    }

    public int getNumeroDeCouleurDeCode() {
        switch (this) {
            case DalleBleuClair: return 2;
            case DalleBleuFoncee: return 3;
            case DalleRouge: return 4;
            case DalleVerte: return 5;
            case DalleJaune: return 6;
            case DalleOrange: return 8;
            case DalleViolette: return 9;
            default: return 0;
        }
    }

    public static Modele getDalleDuNumero(int numero) {
        switch (numero) {
            case 2: return DalleBleuClair;
            case 3: return DalleBleuFoncee;
            case 4: return DalleRouge;
            case 5: return DalleVerte;
            case 6: return DalleJaune;
            case 8: return DalleOrange;
            default: return DalleViolette;
        }
    }

    public static Modele getPorteDuNumero(int numero) {
        switch (numero) {
            case 2: return PorteBleuClair;
            case 3: return PorteBleuFoncee;
            default: return PorteRouge;
        }
    }

    public boolean estBleu() {
        return getCouleurDuModele() == CouleurDuJeu.BleuClair.Int() ||
                getCouleurDuModele() == CouleurDuJeu.BleuFonce.Int();
    }

    public boolean peutYAjouterUnCable() {
        return this == Modele.DalleBleuClair || this == Modele.DalleBleuFoncee ||this == Modele.DalleRouge;
    }

    public boolean estUnePorte() {
        return this.equals(PorteBleuClair) || this.equals(PorteBleuFoncee) || this.equals(PorteRouge);
    }

    @NonNull
    public TypeCategorie getTypeDeCategorie() {
        switch (this) {
            case Square:
            case Fantome:
            case Gomme:
                return TypeCategorie.Square;
            case PorteBleuClair:
            case PorteBleuFoncee:
            case PorteRouge:
            case Mur:
                return TypeCategorie.Porte;
            case DalleBleuClair:
            case DalleBleuFoncee:
            case DalleRouge:
            case DalleOrange:
            case DalleJaune:
            case DalleVerte:
            case DalleViolette:
                return TypeCategorie.Dalle;
            case Blob: return TypeCategorie.Blob;
            default: return TypeCategorie.Cable;
        }
    }
}
