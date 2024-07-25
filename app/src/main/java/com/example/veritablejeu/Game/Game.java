package com.example.veritablejeu.Game;

import android.content.Intent;
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

import com.example.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.example.veritablejeu.BainDeSavon.BainDeSavon;
import com.example.veritablejeu.BackEnd.LevelFile.LevelFile;
import com.example.veritablejeu.Game.InGame.Chronometre.Chronometre;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardsMovements.BoardsMovements;
import com.example.veritablejeu.MediaPlayerInstance.MediaPlayerInstance;
import com.example.veritablejeu.Menu.MainActivity;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.BackgroundColoration;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.Objects;

public class Game extends AppCompatActivity implements IGame {

    protected ConstraintLayout container;
    protected LevelFile levelFile;
    protected PopUp popUp;
    protected LittleWindow littleWindow;
    public final Chronometre chronometre = new Chronometre();
    public int nombreDeCoups = 0;
    protected BoardsMovements onToucheListenerPlateauModulaire;
    protected final ArrayList<Board> plateauModulaireSet = new ArrayList<>();
    private Board plateauADeplacer;
    private GameBackgroundColors backgroundColors;
    private boolean cableOutline = false;


    public Game() {}

    private void ajouterPopUp() {
        popUp = PopUp.getInstance(this);
        popUp.setConstraintLayout(this);
        popUp.setVisibility(View.GONE);
    }

    private void ajouterFenetre() {
        littleWindow = new LittleWindow(this);
        littleWindow.attachToActivity(this);
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
        backgroundColors = new GameBackgroundColors(this);

        ajouterPopUp();
        ajouterFenetre();

        BainDeSavon bainDeSavon = BainDeSavon.getInstance(this);
        bainDeSavon.setContainerDeToutesLesBulles(this);

        FirstCodeReader.read(this, levelFile.sequentialCode);
    }

    @Override
    public ConstraintLayout getContainer() {
        return container;
    }

    public PopUp getPopUp() {
        return popUp;
    }

    public LittleWindow getLittleWindow() {
        return littleWindow;
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
    public void colorFlash(int color) {
        int[] colors = backgroundColors.getColors();
        BackgroundColoration.colorFlash(container, color, colors);
    }

    public GameBackgroundColors getBackgroundColors() {
        return backgroundColors;
    }

    public BoardsMovements getOnToucheListenerPlateauModulaire() {
        return onToucheListenerPlateauModulaire;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        littleWindow.hide();
        popUp.hide();
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

    public String getCableOutlineCode() {
        String code = isCableOutline() ? "t" : "f";
        char key = FirstCodeReader.getKeyCableOutline();
        return CodeBuilder.buildKeyValue(key, code);
    }

    /**
     * This method rebuild the entire code of the level.
     * @return ththe entire code of the level.
     */
    public String buildCode() {
        return buildAestheticCode() + buildMusicCode() + buildGameBoardCode();
    }

    @NonNull
    private String buildAestheticCode() {
        char key = FirstCodeReader.getKeyAesthetic();
        String backgroundColoration = backgroundColors.getBackgroundColorationCode();
        String bubbles = BainDeSavon.getInstance(this).getCode();
        String cableOutline = getCableOutlineCode();
        String code = backgroundColoration + bubbles + cableOutline;
        return CodeBuilder.buildKeyValue(key, code);
    }

    @NonNull
    private String buildMusicCode() {
        char key = FirstCodeReader.getKeyMusic();
        MediaPlayerInstance mediaPlayerInstance = MediaPlayerInstance.getInstance(this);
        String code = "" + mediaPlayerInstance.getCurrentTrackNumber();
        return CodeBuilder.buildKeyValue(key, code);
    }

    @NonNull
    private String buildGameBoardCode() {
        String onReturn = "";
        char key = FirstCodeReader.getKeyGameboard();
        for(Board board : plateauModulaireSet) {
            String code = board.getCode();
            String boardCode = CodeBuilder.buildKeyValue(key, code);
            onReturn = onReturn.concat(boardCode);
        }
        return onReturn;
    }

}