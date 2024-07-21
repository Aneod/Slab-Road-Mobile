package com.example.veritablejeu.Navigation.Preset.NavigationEditeur;

import android.graphics.Color;
import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Input_NomDuNiveau.Input_NomDuNiveau;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.BackgroundColors;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.MusicSettings;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.CursorComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.OnOffComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.SimpleImage;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Navigation.Navigation;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationEditeur extends Navigation implements INavigationEditeur {

    private final Editeur editeur;
    private final Input_NomDuNiveau inputNomDuNiveau;

    private void propositionQuitter() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = () -> {
            editeur.retourAuMenu();
            popUp.hide();
        };
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("RECOMMENCER", "Retourner à la page principale ?", "OUI", runnableA, "NON", runnableB);
    }

    private void openVisualEffectsTools() {
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
        LittleWindow littleWindow = editeur.getLittleWindow();
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Background colors", () -> BackgroundColors.showPanel(editeur), true));
        propositions.add(new WindowProposal("Background bubbles", this::test, true));
        propositions.add(new WindowProposal("Manual", this::showManual, true));
        propositions.add(new WindowProposal("Manual2", this::showManual2, true));
        propositions.add(new WindowProposal("Music", () -> MusicSettings.showMusicSettings(editeur), true));
        littleWindow.setPosition(new Point(leftMargin, topMargin));
        littleWindow.set(propositions);
        PopUp popUp = PopUp.getInstance(editeur);
        popUp.hide();
    }

    private void test() {
        PopUp popUp = PopUp.getInstance(editeur);
        CursorComponent cursorComponent = new CursorComponent(popUp, "Ceci un un curseur qui fait rien.", .5f, null);
        OnOffComponent onOffComponent = new OnOffComponent(popUp, "TITRE", false, "YES", null, "NO", null);
        SimpleImage simpleImage = new SimpleImage(popUp, R.drawable.img6);
        SimpleImage simpleImage2 = new SimpleImage(popUp, R.drawable.pixel_art_menu2);
        popUp.setContent("TEST", cursorComponent, onOffComponent, simpleImage, simpleImage2);
    }

    private void showManual() {
        PopUp popUp = PopUp.getInstance(editeur);
        popUp.showManual();
    }

    private void showManual2() {
        PopUp popUp = PopUp.getInstance(editeur);
        popUp.showManual2();
    }

    private void activerDesactiverGrille(){
        editeur.showHideFences();
    }

    private void propositionSauvegarde() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = popUp::hide; // Il manque sauvegarderLeLevelFile.
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("SAUVEGARDE", "Sauvegarder votre niveau ?", "OUI", runnableA, "NON", runnableB);
    }

    private void propositionEssaiRapide() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = popUp::hide; // Il manque sauvegarderPuisLancer.
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("ESSAI RAPIDE", "Sauvegarder et commencer l'essai ?", "OUI", runnableA, "NON", runnableB);
    }

    @NonNull
    private List<Association_Symbole_Fonction> getAssociations() {
        List<Association_Symbole_Fonction> associations = new ArrayList<>();

        associations.add(new Association_Symbole_Fonction(
                R.drawable.symbole_retour, this::propositionQuitter)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.effets_scintillant, this::openVisualEffectsTools)
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
    }

    @Override
    public void show() {
        super.show();
        inputNomDuNiveau.show();
    }

    @Override
    public void hide() {
        super.hide();
        inputNomDuNiveau.hide();
    }
}
