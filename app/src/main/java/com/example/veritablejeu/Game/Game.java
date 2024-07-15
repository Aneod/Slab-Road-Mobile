package com.example.veritablejeu.Game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.MorceauStorage.Generator.CablePart.CablePart;
import com.example.veritablejeu.LevelFile.LevelFile;
import com.example.veritablejeu.Game.InGame.Chronometre.Chronometre;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardsMovements.BoardsMovements;
import com.example.veritablejeu.Tools.StringColorConverter;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.ColorierBackground;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.Objects;

public class Game extends AppCompatActivity implements IGame {

    protected ConstraintLayout container;

    protected LevelFile levelFile;

    protected PopUp popUp;
    protected LittleWindow petiteFenetreFlottante;
    public final Chronometre chronometre = new Chronometre();
    public int nombreDeCoups = 0;
    protected BoardsMovements onToucheListenerPlateauModulaire;
    protected final ArrayList<Board> plateauModulaireSet = new ArrayList<>();
    private Board plateauADeplacer;
    private int[] backgroundColors = new int[]{Color.WHITE, Color.WHITE};
    private boolean cableOutline = false;


    public Game() {}

    private void ajouterPopUp() {
        popUp = PopUp.getInstance(this);
        popUp.setConstraintLayout(this);
        popUp.setVisibility(View.GONE);
    }

    private void ajouterFenetre() {
        petiteFenetreFlottante = new LittleWindow(this);
        petiteFenetreFlottante.attachToActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_in_game);
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

        container = this.findViewById(R.id.main);
        levelFile = MainActivity.Bus.getInstance().getLevelFile();
        onToucheListenerPlateauModulaire = new BoardsMovements(this);

        ajouterPopUp();
        ajouterFenetre();

        BainDeSavon bainDeSavon = BainDeSavon.getInstance(this);
        bainDeSavon.setContainerDeToutesLesBulles(this);

        FirstCodeReader.read(this, levelFile.sequentialCode);
        verifyCompletion();
    }

    @Override
    public ConstraintLayout getContainer() {
        return container;
    }

    public PopUp getPopUp() {
        return popUp;
    }

    public LittleWindow getPetiteFenetreFlottante() {
        return petiteFenetreFlottante;
    }

    @NonNull
    @Override
    public LevelFile getLevelFiles() {
        return levelFile;
    }

    @Override
    public void retourAuMenu() {
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
        this.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void flashDeCouleur(int couleur) {

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{couleur, couleur});

        if(backgroundColors.length < 2) return;
        GradientDrawable initial = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, backgroundColors);

        Drawable[] layers = {gradientDrawable, initial};
        TransitionDrawable transitionDrawable = new TransitionDrawable(layers);
        container.setBackground(transitionDrawable);

        transitionDrawable.startTransition(2000);
    }

    public BoardsMovements getOnToucheListenerPlateauModulaire() {
        return onToucheListenerPlateauModulaire;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        petiteFenetreFlottante.hide();
        popUp.cacher();
        onToucheListenerPlateauModulaire.onTouchEvent(event, plateauADeplacer);
        return true;
    }

    public void verifyCompletion() {
        if(isComplete()) {
            retourAuMenu();
        }
    }

    public boolean isComplete() {
        return plateauModulaireSet.stream().allMatch(Board::isComplete);
    }

    public ArrayList<Board> getPlateauModulaireSet() {
        return plateauModulaireSet;
    }

    public void addBoard(Board board) {
        plateauModulaireSet.add(board);
    }

    public void setPlateauADeplacer(Board plateauADeplacer) {
        this.plateauADeplacer = plateauADeplacer;
    }

    /**
     * Manages the background colors.
     * @param code who containes some colors like : xxxxxxyyyyyyzzzzzz...
     */
    public void backgroundColoration(String code) {
        backgroundColors = StringColorConverter.turnIntoColors(code);
        ColorierBackground.colorierBackground(this, backgroundColors);
    }

    /**
     * Enable or not the outline of cable with a code.
     * <br>
     * false by default.
     * @param code just 't' for true or 'f' for false.
     */
    public void setCableOutline(String code) {
        boolean enable = Objects.equals(code, "t");
        setCableOutline(enable);
    }

    /**
     * Enable or not the outline of cable.
     * <br>
     * false by default.
     * @param enable true for enable cables outlines. False otherwise.
     */
    public void setCableOutline(boolean enable) {
        this.cableOutline = enable;
        for(Board board : plateauModulaireSet) {
            if(cableOutline) {
                board.printCableOutlines();
            } else {
                board.hideCableOutlines();
            }
        }
    }

    public void swapCableOutline() {
        setCableOutline(!cableOutline);
    }

    public boolean isCableOutline() {
        return cableOutline;
    }

    /**
     * Activate the cable editor.
     * <p>
     *     When a cable is editing, others board elements can't be edited.
     *     <br>
     *     The ATH disappear. And a cross appear for close the cable editing.
     * </p>
     * <p>
     *     How cables are modify ?
     *     <br>
     *     There are only <u>three actions</u> :
     *     <br>
     *     Click on a <b>square with intersection</b> : Remove the both {@link CablePart} who compose the intersection.
     *     <br>
     *     Click on a <b>square without intersection</b> : Create an intersection on this square.
     *     <br>
     *     Click on a <b>door</b> : Connect the cable to the clicked door.
     *     <br>
     *     For each actions the graphic cable will be refreshed.
     * </p>
     * @param cable the cable to modify.
     */
    public void enableCableEditing(Cable cable) {
        // TODO
    }
}