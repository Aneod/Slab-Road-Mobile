package com.slabroad.veritablejeu.Navigation.Preset.NavigationEditeur.Input_NomDuNiveau;

public interface IInput_NomDuNiveau {

    /**
     * S'ajoute au contexte parent et effectue une animation de transparence de 0.0f à 1.0f.
     * @param startOffSet la durée en ms avant l'ajout au contexte et le lancement de l'animation.
     */
    void apparition(long startOffSet);

    /**
     * Effectue une animation de transparence de 1.0f à 0.0f puis supprime la classe
     * du contexte parent.
     * @param startOffSet la durée en ms avant le lancement de l'animation.
     */
    void disparition(long startOffSet);

    /**
     * Show immediately the input.
     */
    void show();

    /**
     * Hide immediately the input.
     */
    void hide();
}
