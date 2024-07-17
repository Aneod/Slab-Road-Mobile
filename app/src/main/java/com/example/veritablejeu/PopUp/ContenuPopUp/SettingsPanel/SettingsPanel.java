package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.BoutonDePopUp.BoutonOnOff;
import com.example.veritablejeu.PopUp.BoutonDePopUp.Curseur;
import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.List;

public class SettingsPanel extends ContenuPopUp {

    private static final String POPUP_TITLE = "SETTINGS";
    private static final int LEFT_RIGHT_MARGINS = 25;
    private static final int COMPONENTS_HEIGHT = 100;
    private static final int HEIGHT_BETWEEN_COMPONENTS = 15;

    public SettingsPanel(@NonNull Context context) {
        super(context, POPUP_TITLE);
    }

    public SettingsPanel(@NonNull PopUp popUp, @NonNull List<Title_Effect_Association> components) {
        super(popUp.getContext(), POPUP_TITLE);

        int width = popUp.getLargeur() - 2 * LEFT_RIGHT_MARGINS;
        hauteurTotale = components.size() * (COMPONENTS_HEIGHT + HEIGHT_BETWEEN_COMPONENTS);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, hauteurTotale, LEFT_RIGHT_MARGINS, popUp.getHauteurInitiale()
        );
        this.setLayoutParams(layoutParams);

        int topMargin = 0;
        for(Title_Effect_Association component : components) {
            String title = component.title;
            if(component instanceof Title_Consumer_Association) {
                float startValue = ((Title_Consumer_Association) component).startValue;
                Consumer<Float> consumer = ((Title_Consumer_Association) component).consumer;
                int color = ((Title_Consumer_Association) component).color;
                Curseur cursor = new Curseur(
                        getContext(), title,
                        width, COMPONENTS_HEIGHT, 0, topMargin,
                        startValue, consumer, color
                );
                addView(cursor);
            }

            else if(component instanceof Title_Runnables_Association) {
                boolean startState = ((Title_Runnables_Association) component).startState;
                Runnable runnableA = ((Title_Runnables_Association) component).runnableA;
                Runnable runnableB = ((Title_Runnables_Association) component).runnableB;
                BoutonOnOff boutonOnOff = new BoutonOnOff(
                        getContext(), title,
                        width, COMPONENTS_HEIGHT, 0, topMargin,
                        startState,
                        "ON", runnableA,
                        "OFF", runnableB
                );
                addView(boutonOnOff);
            }

            topMargin += COMPONENTS_HEIGHT + HEIGHT_BETWEEN_COMPONENTS;
        }
    }

    /**
     * La classe de base pour les composants de paramètres.
     * Cette classe a pour objectif d'être étendues par chaque type de composants.
     * Néanmoins elle contient d'office l'unique information communes aux composants : un titre.
     */
    public static class Title_Effect_Association {

        private final String title;

        public Title_Effect_Association(String title) {
            this.title = title;
        }
    }

    /**
     * Crée un ensemble de paramètres pour conçevoir un curseur. Autrement dit :
     * Un titre, un consumer, etc...
     */
    public static class Title_Consumer_Association extends Title_Effect_Association {

        private final float startValue;
        private final Consumer<Float> consumer;
        private final int color;

        public Title_Consumer_Association(String title, Consumer<Float> consumer, float startValue) {
            this(title, consumer, startValue, Color.BLACK);
        }

        public Title_Consumer_Association(String title, Consumer<Float> consumer, float startValue, int color) {
            super(title);
            this.startValue = startValue;
            this.consumer = consumer;
            this.color = color;
        }
    }

    /**
     * Crée un ensemble de paramètres pour conçevoir un bouton on/off. Autrement dit :
     * Un titre, deux runnables, etc...
     */
    public static class Title_Runnables_Association extends Title_Effect_Association {

        private final boolean startState;
        private final Runnable runnableA;
        private final Runnable runnableB;

        public Title_Runnables_Association(String title, Runnable runnableA, Runnable runnableB, boolean startState) {
            super(title);
            this.startState = startState;
            this.runnableA = runnableA;
            this.runnableB = runnableB;
        }
    }
}
