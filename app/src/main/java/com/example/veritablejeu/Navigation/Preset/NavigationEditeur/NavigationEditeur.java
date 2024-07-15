package com.example.veritablejeu.Navigation.Preset.NavigationEditeur;

import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.Navigation.Input_NomDuNiveau.Input_NomDuNiveau;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Navigation.Navigation;
import com.example.veritablejeu.PopUp.ContenuPopUp.QuestionFermee.Question;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationEditeur extends Navigation implements INavigationEditeur {

    private final Editeur editeur;
    private final Input_NomDuNiveau inputNomDuNiveau;
    public final LittleWindow petiteFenetreFlottante;

    private void propositionQuitter() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = () -> {
            editeur.retourAuMenu();
            popUp.cacher();
        };
        Runnable runnableB = popUp::cacher;
        Question contenuPopUp = new Question(
                popUp, "RECOMMENCER", "Retourner à la page principale ?", "OUI", runnableA, "NON", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private void ouvrirPetiteFenetrePourEffetsVisuels() {
        int leftMargin;
        int topMargin;
        leftMargin = getMargesHautGaucheDroite();
        BoutonNavigation boutonPrincipal = getBoutonPrincipal();
        if(boutonPrincipal != null) {
            int topMarginBouton = boutonPrincipal.getTopMargin();
            int diametreBouton = boutonPrincipal.getDiametre();
            int ecartEntreFenetreEtBouton = 15;
            topMargin = topMarginBouton + diametreBouton + ecartEntreFenetreEtBouton;
        } else {
            topMargin = 0;
        }

        PopUp popUp = editeur.getPopUp();
        LittleWindow petiteFenetreFlottante2 = editeur.getPetiteFenetreFlottante();
        List<LittleWindow.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(
                new LittleWindow.StringRunnablePair("Couleurs de fond", popUp::afficherManuel));
        propositions.add(
                new LittleWindow.StringRunnablePair("Particules de fond", () -> {
                    //popUp.afficherParametres(editeur.getPlateauModifiable());
                }));
        petiteFenetreFlottante2.set(new Point(leftMargin, topMargin), propositions);
        petiteFenetreFlottante2.setObjectInMemory(null);
    }

    private void activerDesactiverGrille(){
        editeur.showHideFences();
    }

    private void propositionSauvegarde() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = popUp::cacher; // Il manque sauvegarderLeLevelFile.
        Runnable runnableB = popUp::cacher;
        Question contenuPopUp = new Question(
                popUp, "SAUVEGARDE", "Sauvegarder votre niveau ?", "OUI", runnableA, "NON", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private void propositionEssaiRapide() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = popUp::cacher; // Il manque sauvegarderPuisLancer.
        Runnable runnableB = popUp::cacher;
        Question contenuPopUp = new Question(
                popUp, "ESSAI RAPIDE", "Sauvegarder et commencer l'essai ?", "OUI", runnableA, "NON", runnableB
        );
        popUp.setContenu(contenuPopUp);
    }

    private List<Association_Symbole_Fonction> getAssociations() {
        List<Association_Symbole_Fonction> associations = new ArrayList<>();

        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_retour, this::propositionQuitter)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.effets_scintillant, this::ouvrirPetiteFenetrePourEffetsVisuels)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.grille, this::activerDesactiverGrille)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.disquette, this::propositionSauvegarde)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.double_fleche_droite, this::propositionEssaiRapide)
        );

        return associations;
    }

    public NavigationEditeur(@NonNull Editeur editeur) {
        super(editeur);
        this.editeur = editeur;
        List<Association_Symbole_Fonction> associations = getAssociations();
        setContenu(associations);
        inputNomDuNiveau = new Input_NomDuNiveau(editeur, this);
        this.petiteFenetreFlottante = new LittleWindow(editeur);
    }

    @Override
    public Input_NomDuNiveau getInputNomDuNiveau() {
        return inputNomDuNiveau;
    }
}
