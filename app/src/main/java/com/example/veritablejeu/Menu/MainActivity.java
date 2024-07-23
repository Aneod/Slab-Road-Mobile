package com.example.veritablejeu.Menu;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.veritablejeu.LevelsPanelMVC.LevelFilesStorage.PersonalLevelsReader;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.InGame.InGame;
import com.example.veritablejeu.LevelsPanelMVC.Controller;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Menu.PageDeSelection.BoutonNouveauNiveau;
import com.example.veritablejeu.Menu.PagePrincipale.PanneauDeBoutonsRedirection;
import com.example.veritablejeu.Tools.BackgroundColoration;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.R;
import com.example.veritablejeu.BainDeSavon.BainDeSavon;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout container;
    private PanneauDeBoutonsRedirection panneau;
    private DemanderUnUserName demanderUnUserName;
    private BoutonExit boutonExit;
    private BoutonRefresh boutonRefresh;
    private ImageView imageTitre;
    private TexteAccomplissement texteMenuHD;
    private BoutonNouveauNiveau boutonNouveauNiveau;
    private ContenuAPropos contenuAPropos;

    public TexteAccomplissement getTexteMenuHD() {
        return texteMenuHD;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Hide status bar and navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For API level 30 and above
            getWindow().getInsetsController().hide(android.view.WindowInsets.Type.statusBars() | android.view.WindowInsets.Type.navigationBars());
        } else {
            // For API level below 30
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(this);
        mediaPlayerInstance.playMainPageMusic();

        container = this.findViewById(R.id.main);
        BackgroundColoration.colorierBackground(container, Color.WHITE, Color.BLACK);
        BainDeSavon bainDeSavon = BainDeSavon.getInstance(this);
        bainDeSavon.setContainerDeToutesLesBulles(this);
        bainDeSavon.setDesignDeBase();

        String userName = UserData.getUsername(this.getApplicationContext());
        boolean userNamePasEncoreDefini = Objects.equals(userName, "");
        if(userNamePasEncoreDefini) {
            demanderUnUserName();
            return;
        }
        creationPageAccueil();

        // On commence dès maintenant a charger les données personnelles pour les avoir a disposition
        // quand nécessaire.
        PersonalLevelsReader.getInstance(this);
    }

    private void demanderUnUserName() {
        demanderUnUserName = new DemanderUnUserName(this);
        container.addView(demanderUnUserName);
    }

    public void userNameValide() {
        container.removeView(demanderUnUserName);
        creationPageAccueil();
    }

    private void creationPageAccueil() {
        boutonExit = new BoutonExit(this);
        container.addView(boutonExit);
        boutonExit.setOnClickListener(view -> finishAffinity());
        boutonExit.setElevation(2);

        String versionTexte;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionTexte = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionTexte = "";
        }

        texteMenuHD = new TexteAccomplissement(this, versionTexte);
        container.addView(texteMenuHD);

        panneau = new PanneauDeBoutonsRedirection(this);
        container.addView(panneau);

        panneau.getBoutonJouer().setOnClickListener(view -> goSelectionNiveau());
        panneau.getBoutonMondial().setOnClickListener(view -> goMondial());
        panneau.getBoutonMesNiveaux().setOnClickListener(v -> goSelectionDeMesNiveaux());
        panneau.getBoutonAPropos().setOnClickListener(v -> goAPropos());

        imageTitre = new ImageView(this);
        imageTitre.setImageResource(R.drawable.pixel_art_menu2);
        int margesHB = 50;
        int topMargin = 130 + margesHB;
        int margesGD = 100;
        int width = ScreenUtils.getScreenWidth() - 2 * margesGD;
        int hauteurMinimale = PanneauDeBoutonsRedirection.hauteurGenerale - 2 * margesHB;
        int height = hauteurMinimale - topMargin;
        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(width, height, margesGD, topMargin);
        imageTitre.setLayoutParams(layoutParams);
        container.addView(imageTitre);
    }

    /**
     * Colorie le fond d'écran avec la couleur donnée en haut, et du noir en bas.
     * @param couleurDuHaut la couleur du haut de la page.
     */
    private void colorierBackground(int couleurDuHaut) {
        BackgroundColoration.colorierBackground(container, couleurDuHaut, Color.BLACK);
    }

    private void goMenu() {
        container.addView(imageTitre);
        container.removeView(boutonRefresh);
        Controller.getInstance(this).hide();
        container.removeView(boutonNouveauNiveau);
        container.removeView(contenuAPropos);
        texteMenuHD.afficherNumeroDeVersion();

        colorierBackground(Color.WHITE);
        boutonExit.setOnClickListener(view -> finishAffinity());

        if(panneau.getParent() == null) {
            container.addView(panneau);
        }
    }

    private void goSelectionNiveau() {
        container.removeView(imageTitre);
        container.removeView(panneau);
        colorierBackground(panneau.getBoutonJouer().getColor());
        boutonExit.setOnClickListener(view -> goMenu());
        texteMenuHD.afficherAccomplissementCampagne();
        Controller.getInstance(this).showNormalLevels(container);
    }

    private void goMondial() {
        container.removeView(imageTitre);
        container.removeView(panneau);
        colorierBackground(panneau.getBoutonMondial().getColor());
        boutonExit.setOnClickListener(view -> goMenu());
        texteMenuHD.afficherPseudoUtilisateur();
        Controller.getInstance(this).showGlobalLevels(container);

        if(boutonRefresh == null) {
            boutonRefresh = new BoutonRefresh(this);
            boutonRefresh.setElevation(1);
            // No Effect
        }
        container.addView(boutonRefresh);
    }

    private void goSelectionDeMesNiveaux() {
        container.removeView(imageTitre);
        container.removeView(panneau);
        colorierBackground(panneau.getBoutonMesNiveaux().getColor());
        boutonExit.setOnClickListener(view -> goMenu());
        texteMenuHD.afficherNombreDeFichiersPerso();
        Controller.getInstance(this).showPersonalLevels(container);

        if(boutonNouveauNiveau == null) {
            int margesH = 130;
            int margesGDB = 50;
            int width = ScreenUtils.getScreenWidth() - 2 * margesGDB;
            int heightBoutonNouveauNiveau = 100;
            boutonNouveauNiveau = new BoutonNouveauNiveau(this, width, heightBoutonNouveauNiveau, margesGDB, margesH);
        }
        container.addView(boutonNouveauNiveau);
    }

    private void goAPropos() {
        container.removeView(imageTitre);
        container.removeView(panneau);
        int couleurBouton = panneau.getBoutonAPropos().getColor();
        BackgroundColoration.colorierBackground(container, couleurBouton, couleurBouton);
        boutonExit.setOnClickListener(view -> goMenu());

        if(contenuAPropos == null) {
            contenuAPropos = new ContenuAPropos(this);
        }
        container.addView(contenuAPropos);
    }

    public void goEditeur(@Nullable LevelFile levelFile) {
        Bus.getInstance().setLevelFile(levelFile);
        Intent activity = new Intent(getApplicationContext(), Editeur.class);
        startActivity(activity);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void goInGame(@NonNull LevelFile levelFile) {
        Bus.getInstance().setLevelFile(levelFile);
        Intent activity = new Intent(getApplicationContext(), InGame.class);
        startActivity(activity);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * A singleton to pass through the activities a {@link LevelFile}.
     */
    public static class Bus {
        private static Bus instance;
        private LevelFile levelFile;

        private Bus(){}

        public static Bus getInstance() {
            if(instance == null) {
                instance = new Bus();
            }
            return instance;
        }

        public LevelFile getLevelFile() {
            return levelFile;
        }

        public void setLevelFile(LevelFile levelFile) {
            this.levelFile = levelFile;
        }
    }

}