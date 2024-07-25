package com.example.veritablejeu.Navigation.Preset.NavigationEditeur;

import android.graphics.Point;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.BainDeSavon.BubblesSettings;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.LevelsPanelMVC.LevelsReader.PersonalLevelsReader;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.example.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.example.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Input_NomDuNiveau.Input_NomDuNiveau;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.BackgroundColors;
import com.example.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.MusicSettings;
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
        propositions.add(new WindowProposal("Background bubbles", this::openBubblesSettings, true));
        propositions.add(new WindowProposal("Music", () -> MusicSettings.showMusicSettings(editeur), true));
        littleWindow.setPosition(new Point(leftMargin, topMargin));
        littleWindow.set(propositions);
        PopUp popUp = PopUp.getInstance(editeur);
        popUp.hide();
    }

    private void openBubblesSettings() {
        BubblesSettings.showPanel(editeur);
    }

    private void activerDesactiverGrille(){
        editeur.swapFences();
    }

    private void deletionProposal() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = popUp::hide; // Supprimer des fichiers locaux.
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("DELETE", "Delete this level from your personal files ? As long as you are in the editor you can save it again.", "DELETE", runnableA, "NO", runnableB);
    }

    private void saveProposal() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = () -> {
            saveAndLaunch();
            popUp.hide();
        };
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("SAVE & PLAY", "Save this version of this level and try to solve it ?", "YES", runnableA, "NO", runnableB);
    }

    private void saveAndLaunch() {
        saveNew();
        Log.e("", editeur.buildCode());
        PersonalLevelsReader.getInstance(editeur).refreshLevelList(editeur);
    }

    private void saveNew() {
        String userName = UserData.getUsername(editeur);
        String code = editeur.buildCode();
        LevelFile levelFile = new LevelFile("TestName", userName, 0L, 0, code);
        PersonalFiles personalFiles = PersonalFiles.getInstance(editeur);
        personalFiles.set(levelFile);
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
                R.drawable.poubelle, this::deletionProposal)
        );
        associations.add(new Association_Symbole_Fonction(
                R.drawable.disquette, this::saveProposal)
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
