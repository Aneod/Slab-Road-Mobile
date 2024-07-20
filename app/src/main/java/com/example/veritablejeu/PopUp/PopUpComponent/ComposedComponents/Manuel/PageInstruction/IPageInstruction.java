package com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.Manuel.PageInstruction;

public interface IPageInstruction {

    /**
     * Modifie toutes les variables pour redesigner la page.
     *
     * @param texteDuParagraphe1 le texte du premier paragraphe.
     * @param hauteurParagraphe1 et sa hauteur.
     * @param nouvelleImg1 l'image qui suit.
     * <p>
     * @param texteDuParagraphe2 le texte du second paragraphe.
     * @param hauteurParagraphe2 sa hauteur.
     * @param nouvelleImg2 l'image qui suis.
     */
    void setTouteLaPage(String texteDuParagraphe1, int hauteurParagraphe1, int nouvelleImg1,
                        String texteDuParagraphe2, int hauteurParagraphe2, int nouvelleImg2);

    /**
     * Renvoie le nombre total de pages dans le manuel.
     * @return un int.
     */
    int getNbDePages();

    /**
     * Changer le contenu de la page pour afficher celle du numéro donné.
     * @param numeroDePage le numéro de la page recherchée.
     */
    void setALaPage(int numeroDePage);
}
