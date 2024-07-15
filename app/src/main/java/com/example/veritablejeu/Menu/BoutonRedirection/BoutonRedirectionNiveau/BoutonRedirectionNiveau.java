package com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirectionNiveau;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.DataBases.Local.PersonalBests.PersonalBests;
import com.example.veritablejeu.DataBases.Local.UserData;
import com.example.veritablejeu.Menu.BoutonRedirection.BoutonRedirection;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.Menu.PageDeSelection.ListeDefilanteDeNiveaux;
import com.example.veritablejeu.Menu.PageDeSelection.PanneauDeNiveauxParticulier.PanneauDeNiveauxNormaux.EtoilesNiveauxNormaux;
import com.example.veritablejeu.OutilsEnEnum.LongToReadableTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("ViewConstructor")
public class BoutonRedirectionNiveau extends BoutonRedirection implements IBoutonRedirectionNiveau {

    protected final ListeDefilanteDeNiveaux parent;
    protected LevelFile levelFileAOuvrir;
    private AppCompatTextView textePrimaire;
    private AppCompatTextView texteSecondaire;
    private AppCompatTextView texteTertiaire;

    @NonNull
    private SilhouettePlateau getSilhouettePlateau(int width, int height, LevelFile levelFile) {
        int plusPetitCote = Math.min(width, height);
        int leftMarginLogo = width - plusPetitCote;
        int topMarginLogo = height - plusPetitCote;

        int reductionSurLesBordures = 10;
        leftMarginLogo += reductionSurLesBordures;
        topMarginLogo += reductionSurLesBordures;
        plusPetitCote -= 2 * reductionSurLesBordures;

        return new SilhouettePlateau(getContext(), levelFile, plusPetitCote, plusPetitCote, leftMarginLogo, topMarginLogo);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            private boolean moveEffectue = false;
            private float lastTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchY = event.getRawY();
                        moveEffectue = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dy = event.getRawY() - lastTouchY;

                        if (!moveEffectue && Math.abs(dy) > 10) {
                            moveEffectue = true;
                            dy = 0;
                        }
                        if(moveEffectue) {
                            parent.setTranslationYListe(dy);
                            lastTouchY = event.getRawY();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(!moveEffectue) goInGame();
                }
                return true;
            }
        });
    }

    private void goInGame() {
        if(levelFileAOuvrir == null) return;
        Context context = getContext();
        if(context instanceof MainActivity) {
            ((MainActivity) context).goInGame(levelFileAOuvrir);
        }
    }

    /**
     * Création d'un bouton de redirection pour choisir un niveau.
     * @param parent la frameLayout parent.
     * @param width la largeur du bouton.
     * @param height la hauteur du bouton.
     * @param leftMargin la marge gauche.
     * @param topMargin la marge supérieure.
     * @param levelFile l'id du niveeau vers lequel ce bouton redirige.
     */
    public BoutonRedirectionNiveau(@NonNull Context context, ListeDefilanteDeNiveaux parent, int width, int height, int leftMargin, int topMargin, LevelFile levelFile) {
        super(context, levelFile.name, width, height, leftMargin, topMargin);
        this.parent = parent;
        creeLesTroisLignes();
        setOnTouchListener();
        setBackgroundColor(Color.WHITE);
    }

    public void creeSilouhettePlateau() {
        SilhouettePlateau silhouette = getSilhouettePlateau(width, height, levelFileAOuvrir);
        addView(silhouette);
    }

    public void afficherLaDate() {
        String auteur = levelFileAOuvrir.autor;
        Date date = levelFileAOuvrir.date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        String texte = auteur + " " + dateFormat.format(date);
        textePrimaire.setText(texte);
    }

    public void afficherRecordPerso_sansEtoiles() {
        String username = UserData.getUsername(getContext().getApplicationContext());
        PersonalBests repository = PersonalBests.getInstance(getContext().getApplicationContext());
        String levelStringId = String.valueOf(levelFileAOuvrir.id);
        repository.get(levelStringId, recordsPerso -> {
            String recordPerso;
            if (recordsPerso != null) {

                int numberOfMoves = recordsPerso.numberOfMoves;
                String leMotCoupSingulierOuPluriel2 = numberOfMoves > 1 ? "coups" : "coup";
                String printMoves2 = numberOfMoves + " " + leMotCoupSingulierOuPluriel2;

                long time = recordsPerso.time;
                String readableTime2 = LongToReadableTime.getElapsedTimeFormatted(time);

                recordPerso = username + " : " + readableTime2 + " en " + printMoves2;
            } else {
                recordPerso = username + " : ---";
            }
            texteSecondaire.setText(recordPerso);
        });
    }

    public void afficherRecordPerso_avecEtoiles() {
        String username = UserData.getUsername(getContext().getApplicationContext());
        PersonalBests repository = PersonalBests.getInstance(getContext().getApplicationContext());
        String levelStringId = String.valueOf(levelFileAOuvrir.id);
        repository.get(levelStringId, recordsPerso -> {
            String recordPerso;
            int nbEtoileActives = 0;
            if (recordsPerso != null) {
                nbEtoileActives++;

                int numberOfMoves = recordsPerso.numberOfMoves;
                boolean meilleurNbDeCoups = numberOfMoves <= levelFileAOuvrir.movesNumber;
                if(meilleurNbDeCoups) nbEtoileActives++;
                String leMotCoupSingulierOuPluriel2 = numberOfMoves > 1 ? "coups" : "coup";
                String printMoves2 = numberOfMoves + " " + leMotCoupSingulierOuPluriel2;

                long time = recordsPerso.time;
                boolean meilleurTemps = time <= levelFileAOuvrir.time;
                if(meilleurTemps) nbEtoileActives++;

                String readableTime2 = LongToReadableTime.getElapsedTimeFormatted(time);

                recordPerso = username + " : " + readableTime2 + " en " + printMoves2;
            } else {
                recordPerso = username + " : ---";
            }
            texteSecondaire.setText(recordPerso);

            int finalNbEtoileActives = nbEtoileActives;
            ((MainActivity) getContext()).runOnUiThread(() -> {
                FrameLayout etoiles = new EtoilesNiveauxNormaux(getContext(), finalNbEtoileActives, width / 4, 18, 20, 66);
                addView(etoiles);
            });
        });
    }

    public void afficherObjectif_ou_recordMondial() {
        long objectif_recordMondial_TEMPS = levelFileAOuvrir.time;
        String readableTime = LongToReadableTime.getElapsedTimeFormatted(objectif_recordMondial_TEMPS);

        int objectif_recordMondial_COUPS = levelFileAOuvrir.movesNumber;
        String leMotCoupSingulierOuPluriel = objectif_recordMondial_COUPS > 1 ? "coups" : "coup";
        String printMoves = objectif_recordMondial_COUPS + " " + leMotCoupSingulierOuPluriel;

        String motObjectif_ou_pseudoDuMeilleur = levelFileAOuvrir.bestPlayer;
        String texteTertiaire = motObjectif_ou_pseudoDuMeilleur + " : " + readableTime + " en " + printMoves;
        this.texteTertiaire.setText(texteTertiaire);
    }

    private AppCompatTextView creeLigne(int topMargin) {
        AppCompatTextView texteView = new AppCompatTextView(getContext());
        texteView.setTextColor(Color.BLACK);
        texteView.setTextSize(12);
        ConstraintLayout.LayoutParams layoutParamsTitre = getLayoutParamsTitre();
        layoutParamsTitre.topMargin += topMargin;
        texteView.setLayoutParams(layoutParamsTitre);
        return texteView;
    }

    private void creeLesTroisLignes() {
        textePrimaire = creeLigne(60);
        texteSecondaire = creeLigne(85);
        texteTertiaire = creeLigne(110);
        addView(textePrimaire);
        addView(texteSecondaire);
        addView(texteTertiaire);
    }
}
