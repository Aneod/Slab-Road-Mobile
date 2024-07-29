package com.slabroad.veritablejeu.Navigation.Preset.NavigationEditeur;

import android.content.Intent;
import android.graphics.Point;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.slabroad.veritablejeu.BackEnd.DataBases.Local.LevelFiles.PersonalFiles;
import com.slabroad.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.slabroad.veritablejeu.BainDeSavon.BubblesSettings;
import com.slabroad.veritablejeu.Game.Board.Board;
import com.slabroad.veritablejeu.Game.Editeur.Editeur;
import com.slabroad.veritablejeu.Game.InGame.InGame;
import com.slabroad.veritablejeu.LevelsPanelMVC.LevelsReader.PersonalLevelsReader;
import com.slabroad.veritablejeu.LittleWindow.LittleWindow;
import com.slabroad.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.slabroad.veritablejeu.Menu.MainActivity;
import com.slabroad.veritablejeu.Navigation.Association_Symbole_Fonction.Association_Symbole_Fonction;
import com.slabroad.veritablejeu.Navigation.BoutonNavigation.BoutonNavigation;
import com.slabroad.veritablejeu.Navigation.Preset.NavigationEditeur.Input_NomDuNiveau.Input_NomDuNiveau;
import com.slabroad.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.BackgroundColors;
import com.slabroad.veritablejeu.Navigation.Preset.NavigationEditeur.Settings.MusicSettings;
import com.slabroad.veritablejeu.PopUp.PopUp;
import com.slabroad.veritablejeu.Navigation.Navigation;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationEditeur extends Navigation implements INavigationEditeur {

    private final Editeur editeur;
    private final Input_NomDuNiveau inputNomDuNiveau;

    private void propositionQuitter() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = () -> {
            editeur.goMenu();
            popUp.hide();
        };
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("EXIT", "Return to main page ?", "YES", runnableA, "NO", runnableB);
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
        Runnable runnableA = () -> {
            delete();
            popUp.hide();
        };
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("DELETE",
                "Delete this level from your personal files ? " +
                        "As long as you are in the editor you can save it again.",
                "DELETE", runnableA, "NO", runnableB);
    }

    private void delete() {
        PersonalFiles personalFiles = PersonalFiles.getInstance(editeur);
        LevelFile levelFile = editeur.getLevelFiles();
        personalFiles.remove(levelFile, new PersonalFiles.BooleanCallback() {
            @Override
            public void onSuccess() {
                PersonalLevelsReader.getInstance(editeur).refreshLevelList(editeur);
                showToast("File deleted successfully.");
            }

            @Override
            public void onFailure() {
                showToast("Unable to delete level.");
            }
        });
    }

    private void saveProposal() {
        PopUp popUp = editeur.getPopUp();
        Runnable runnableA = this::saveIfThereIsAOrangeSlab;
        Runnable runnableB = popUp::hide;
        popUp.showQuestion("SAVE & PLAY",
                "Save this version of this level and try to solve it ?",
                "YES", runnableA, "NO", runnableB
        );
    }

    private void saveIfThereIsAOrangeSlab() {
        if(isThereAtLeastOneOrangeSlab()) {
            saveAndLaunch();
            PopUp popUp = editeur.getPopUp();
            popUp.hide();
        } else {
            showNotOrangeSlabPopUp();
        }
    }

    private void showNotOrangeSlabPopUp() {
        PopUp popUp = editeur.getPopUp();
        popUp.showMessage("ORANGE MISSING", "Each level must have at least one orange slab. " +
                "Please add an orange slab to continue.");
    }

    private boolean isThereAtLeastOneOrangeSlab() {
        for(Board board : editeur.getPlateauModulaireSet()) {
            if(board.isThereAtLeastOneOrangeSlab()) {
                return true;
            }
        }
        return false;
    }

    private void saveAndLaunch() {
        LevelFile savedFile = save();
        launch(savedFile);
    }

    @NonNull
    private LevelFile save() {
        LevelFile originalLevelFile = editeur.getLevelFiles();
        String id = originalLevelFile.id;
        String userName = originalLevelFile.autor;
        String code = editeur.buildCode();
        LevelFile levelFile = new LevelFile(
                id, getLevelName(), userName, 0L, 0, code);
        PersonalFiles personalFiles = PersonalFiles.getInstance(editeur);
        personalFiles.set(levelFile, new PersonalFiles.BooleanCallback() {
            @Override
            public void onSuccess() {
                PersonalLevelsReader.getInstance(editeur).refreshLevelList(editeur);
                showToast("File saved successfully.");
            }

            @Override
            public void onFailure() {

            }
        });
        return levelFile;
    }

    @NonNull
    private String getLevelName() {
        Editable editable = inputNomDuNiveau.getText();
        if(editable == null || editable.length() == 0) {
            return  "No name";
        } else {
            return editable.toString();
        }
    }

    private void launch(LevelFile levelFile) {
        MainActivity.Bus.getInstance().setLevelFile(levelFile);
        Intent mainActivity = new Intent(editeur.getApplicationContext(), InGame.class);
        editeur.startActivity(mainActivity);
        editeur.finish();
        editeur.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void showToast(String text) {
        editeur.runOnUiThread(() -> Toast.makeText(editeur, text, Toast.LENGTH_SHORT).show());
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
