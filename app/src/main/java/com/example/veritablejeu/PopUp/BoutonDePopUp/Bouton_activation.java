package com.example.veritablejeu.PopUp.BoutonDePopUp;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

@SuppressLint("ViewConstructor")
public class Bouton_activation extends BoutonDePopUp {

    private boolean estActive;
    private final String texteActive;
    private final String texteDesactive;

    public boolean getEstActive() {
        return estActive;
    }

    /**
     * Change la couleur du bouton en blanc ou en noir en fonction
     * de si le bouton est activé.
     */
    private void updateCouleur() {
        if(estActive) prendreLaCouleurBlanche();
        else prendreLaCouleurNoire();
    }

    /**
     * Change le texte du bouton en fonction de si le bouton est activé.
     */
    private void updateTexte() {
        String texte = estActive ? texteActive : texteDesactive;
        this.setText(texte);
    }

    /**
     * Effectue les changement attendus lors d'un clic.
     */
    private void updateBouton() {
        updateTexte();
        updateCouleur();
    }

    /**
     * Lorsqu'une classe extérieure modifie estActive, on update le visuel du bouton.
     * @param estActive la nouvelle valeur de estActive.
     */
    public void setEstActive(boolean estActive) {
        this.estActive = estActive;
        updateBouton();
    }

    /**
     * Inversion simple de estActive puis modification du visuel.
     */
    public void changerEtat() {
        setEstActive(!estActive);
    }

    /**
     * Création d'un bouton de base pour la popUp.
     *
     * @param parent         la frameLayout parent.
     * @param largeurBoutons la largeur.
     * @param hauteurBoutons la hauteur.
     * @param leftMargin     la marge gauche.
     * @param topMargin      la marge supérieure.
     */
    public Bouton_activation(FrameLayout parent, int largeurBoutons, int hauteurBoutons, int leftMargin, int topMargin, boolean estActive, String texteActive, String texteDesactive) {
        super(parent, texteActive, largeurBoutons, hauteurBoutons, leftMargin, topMargin);
        this.estActive = estActive;
        this.texteActive = texteActive;
        this.texteDesactive = texteDesactive;
        updateBouton();
    }
}
