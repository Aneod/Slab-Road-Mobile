package com.example.veritablejeu.Navigation.Association_Symbole_Fonction;

import androidx.annotation.Nullable;

public class Association_Symbole_Fonction implements IAssociation_Symbole_Fonction {

    private final Integer symbole;
    private final Runnable runnable;

    public Association_Symbole_Fonction(@Nullable Integer symbole, @Nullable Runnable runnable) {
        this.symbole = symbole;
        this.runnable = runnable;
    }

    @Override
    public @org.jetbrains.annotations.Nullable Integer getSymbole() {
        return symbole;
    }

    @Override
    public @org.jetbrains.annotations.Nullable Runnable getRunnable() {
        return runnable;
    }
}
