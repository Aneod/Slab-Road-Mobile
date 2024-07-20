package com.example.veritablejeu.PopUp.ComposedComponents.Manuel.PageInstruction.ModificateurDePage;

public interface IModificateurDePage {

    /**
     * Renvoie le nombre total de pages dans le manuel.
     * @return un int.
     */
    int getNbDePages();

    /**
     * Change le contenu de la page de pageInstruction pour celle correspondant au numéro donné.
     * @param numeroDePage le numéro de la page recherchée.
     */
    void setALaPage(int numeroDePage);

}
