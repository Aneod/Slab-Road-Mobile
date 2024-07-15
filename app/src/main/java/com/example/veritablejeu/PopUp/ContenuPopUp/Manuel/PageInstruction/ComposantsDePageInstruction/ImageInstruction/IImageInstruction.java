package com.example.veritablejeu.PopUp.ContenuPopUp.Manuel.PageInstruction.ComposantsDePageInstruction.ImageInstruction;

public interface IImageInstruction {

    /**
     * Renvoie la marge supérieure actuelle de la classe.
     * @return un int.
     */
    int getTopMargin();

    /**
     * Modifie la marge supérieure de la classe.
     * @param topMargin la nouvelle marge supérieure.
     */
    void setTopMargin(int topMargin);

    /**
     * Changer l'image de la classe.
     * @param image la nouvelle image.
     */
    void changerImage(int image);

}
