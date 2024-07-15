package com.example.veritablejeu.Game.PlateauModulaire.Square;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.PlateauModulaire.Deprime.OnTouchForElement;
import com.example.veritablejeu.Game.PlateauModulaire.ModularObject;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;
import com.example.veritablejeu.sequentialCode.Code;
import com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.AddExternalWallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinatesManager;
import com.example.veritablejeu.PopUp.ContenuPopUp.Message.Message;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public abstract class ModularSquare extends ModularObject {

    private final Board board;
    private final FrameLayout.LayoutParams layoutParams;
    private final ZdecimalCoordinates cord;
    private final WallsOfSquare walls;
    private boolean showAsAccessible = true;

    /**
     * Create and apply a basic square layoutParams, with the good positions and size.
     * @param zdecimalCoordinates the coordinates of the Square.
     * @return the custom layoutParams.
     */
    @NonNull
    @Contract("null -> new")
    private FrameLayout.LayoutParams setLayoutParams_byCords(ZdecimalCoordinates zdecimalCoordinates) {
        if(zdecimalCoordinates == null) {
            return new LayoutParamsDeBase_pourFrameLayout(0, 0, 0, 0);
        }
        int xPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(zdecimalCoordinates.getX());
        int yPos = ZdecimalCharacterConverter.zdecimalCharacter_to_intDecimal(zdecimalCoordinates.getY());

        int leftMargin = xPos * Board.SQUARE_SIZE + Board.BORDER_WIDTH;
        int topMargin = yPos * Board.SQUARE_SIZE + Board.BORDER_WIDTH;

        return new LayoutParamsDeBase_pourFrameLayout(
                Board.SQUARE_SIZE, Board.SQUARE_SIZE, leftMargin, topMargin
        );
    }

    @Nullable
    public static ModularSquare create(Board board, String code) {
        if(board == null || code == null || code.isEmpty()) {
            return null;
        }
        char squareType = code.charAt(0);
        switch (squareType) {
            case '0': return new NormalSquare(board, code);
            case '1': return new Ghost(board, code);
        }
        return null;
    }

    /**
     * @param code the Squares code have a typeSquare, and ZdecimalCoordinates.
     *             <p>
     *                 Like : Txy...
     *                 <br>
     *                 T : Square type
     *                 <br>
     *                 x : ZdecimalCoordinates x
     *                 <br>
     *                 y : ZdecimalCoordinates y
     *             </p>
     */
    public ModularSquare(@NonNull Board board, @NonNull String code) {
        super(board);
        this.board = board;
        cord = new ZdecimalCoordinates(code.charAt(1), code.charAt(2));
        walls = new WallsOfSquare(this);

        layoutParams = setLayoutParams_byCords(cord);
        setLayoutParams(layoutParams);
        board.addView(this);

        setShowAsAccessible(isAccessible());
        setElevation(Elevation.Square.getElevation());

        String subString = code.substring(3);
        if(!subString.isEmpty()) {
            Code.apply(subString,
                    'w', (Consumer<String>) this::addWalls,
                    'b', (Consumer<String>) this::createBlob,
                    's', (Consumer<String>) this::createSlab
            );
        }
    }

    /**
     * During the board creation, some Squares must be sourrounded by traversable walls.
     * <br>
     * Just for aesthetic. Nothing of functionnal.
     * @return true if the Square accept the outline walls.
     */
    public abstract boolean acceptAutomaticWalls();

    public abstract GradientDrawable getAccessibleBackground();
    public abstract GradientDrawable getInaccessibleBackground();


    public Board getBoard() {
        return board;
    }

    @Override
    public FrameLayout.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public ZdecimalCoordinates getCord() {
        return cord;
    }

    public void createBlob(String code) {
        ModularBlob modularBlob = new ModularBlob(this, code);
        board.addBlob(modularBlob);
    }

    /**
     * Some blobs can be on the Square at the same time. This method returns the first of them.
     * @return a {@link ModularBlob} on the Square.
     */
    public ModularBlob getBlobOnSquare() {
        return board.getBlobAtSquare(this);
    }

    public void createSlab(String code) {
        ModularSlab modularSlab = ModularSlab.create(this, code);
        board.addSlab(modularSlab);
    }

    @Nullable
    public ModularSlab getModularSlab() {
        return board.getSlabAt(cord);
    }

    /**
     * Add a wall around the square.
     * @param code the entire code of the wall. This code decides by which side this wall will be placed on.
     */
    public void addWalls(String code) {
        Code.apply(code,
                't', (Consumer<String>) codex -> walls.setByCode(WallsOfSquare.Direction.Top, codex),
                'l', (Consumer<String>) codex -> walls.setByCode(WallsOfSquare.Direction.Left, codex),
                'b', (Consumer<String>) codex -> walls.setByCode(WallsOfSquare.Direction.Bottom, codex),
                'r', (Consumer<String>) codex -> walls.setByCode(WallsOfSquare.Direction.Right, codex)
        );
    }

    public void addExternalWalls() {
        AddExternalWallsOfSquare.add(this);
    }

    public WallsOfSquare getWalls() {
        return walls;
    }

    public boolean isOnTopOfBoard() {
        return ZdecimalCharacterSequencer.isEqualTo(cord.getY(), board.getTopLimit());
    }

    public boolean isOnLeftOfBoard() {
        return ZdecimalCharacterSequencer.isEqualTo(cord.getX(), board.getLeftLimit());
    }

    public boolean isOnBottomOfBoard() {
        return ZdecimalCharacterSequencer.isEqualTo(cord.getY(), board.getBottomLimit());
    }

    public boolean isOnRightOfBoard() {
        return ZdecimalCharacterSequencer.isEqualTo(cord.getX(), board.getRightLimit());
    }

    @Nullable
    public ModularSquare getSquareOfTop() {
        return board.getSquareAt(ZdecimalCoordinatesManager.getTopOf(cord));
    }

    @Nullable
    public ModularSquare getSquareOfLeft() {
        return board.getSquareAt(ZdecimalCoordinatesManager.getLeftOf(cord));
    }

    @Nullable
    public ModularSquare getSquareOfBottom() {
        return board.getSquareAt(ZdecimalCoordinatesManager.getBottomOf(cord));
    }

    @Nullable
    public ModularSquare getSquareOfRight() {
        return board.getSquareAt(ZdecimalCoordinatesManager.getRightOf(cord));
    }

    public abstract boolean isAccessible();

    public boolean isShowAsAccessible() {
        return showAsAccessible;
    }

    public void setShowAsAccessible(boolean showAsAccessible) {
        this.showAsAccessible = showAsAccessible;
        if(showAsAccessible) {
            showAsAccessible();
        } else {
            showAsInaccessible();
        }
    }

    public void showAsAccessible() {
        setBackground(getAccessibleBackground());
    }

    public void showAsInaccessible() {
        setBackground(getInaccessibleBackground());
    }

    private void setPlateauADeplacer(@Nullable Board board) {
        game.setPlateauADeplacer(board);
    }

    private void inGameListeners() {
        ModularBlob master = board.getMaster();
        ModularBlob blobOnSquare = getBlobOnSquare();

        boolean newSelectedMaster = blobOnSquare != null;
        boolean masterToMove = master != null;

        if(newSelectedMaster) {
            board.setMaster(blobOnSquare);
        }
        else if (masterToMove) {
            if(isShowAsAccessible()) {
                master.moveTo(cord);
            } else {
                showImpossibleTravelMessage();
            }
        }
    }

    private void showImpossibleTravelMessage() {
        PopUp popUp = PopUp.getInstance(getContext());
        Message message = new Message(popUp, "SYSTEME", "Impossible d'aller ici.", 1000);
        popUp.setContenu(message);
    }

    @Override
    public void enableInGameListeners() {

        new OnTouchForElement(this) {
            @Override
            public Consumer<MotionEvent> clickEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> longPressWithoutMoveEvent() {
                return event -> {
                    setPlateauADeplacer(board);
                    board.bringToFront();
                };
            }

            @Override
            public Consumer<MotionEvent> longPressAfterMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> fastMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> moveAfterLongPressEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithoutMoveEvent() {
                return event -> inGameListeners();
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithMoveEvent() {
                return event -> setPlateauADeplacer(null);
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithoutMoveAfterLongPressEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithMoveAfterLongPressEvent() {
                return event -> setPlateauADeplacer(null);
            }
        };
    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Add blob", () -> createBlob(null), true));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }
}
