package com.example.veritablejeu.Navigation.Association_Symbole_Fonction;

import org.jetbrains.annotations.Nullable;

public interface IAssociation_Symbole_Fonction {

    /**
     * Renvoie l'objet Integer donné lors de l'instanciation.
     * @return un Integer. Qui peut être null.
     */
    @Nullable
    Integer getSymbole();

    /**
     * Renvoie l'objet Runnable donné lors de l'instanciation.
     * @return un Runnable. Une fonction sans paramètre ni valeur de retour.
     */
    @Nullable
    Runnable getRunnable();

}
