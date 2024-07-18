package com.example.veritablejeu.Game.Board.BoardElement.Square;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.TransparencySettings.TransparencySettings;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.Board.BoardsMovements.OnTouchForElement;
import com.example.veritablejeu.Game.Board.BoardElement.BoardElement;
import com.example.veritablejeu.Game.Board.BoardElement.Square.Versions.Ghost;
import com.example.veritablejeu.Game.Board.BoardElement.Square.Versions.NormalSquare;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterConverter;
import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.AddExternalWallsOfSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinatesManager;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public abstract class ModularSquare extends BoardElement {

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
                    'b', (Consumer<String>) t -> createBlob(),
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

    public void createBlob() {
        ModularBlob modularBlob = new ModularBlob(this);
        board.addBlob(modularBlob);
    }

    /**
     * Some blobs can be on the Square at the same time. This method returns the first of them.
     * @return a {@link ModularBlob} on the Square.
     */
    public ModularBlob getBlobOnSquare() {
        return board.getBlobAtSquare(this);
    }

    /**
     * Create a slab without check if it's possible.
     * @param code the code of the slab.
     */
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
            boolean unaccessible = !isShowAsAccessible();
            if(unaccessible) {
                master.showImpossibleTravelMessage();
            } else {
                master.moveTo(cord);
            }
        }
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
    public List<WindowProposal> getEditPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Add Door", this::openDoorPropositions));
        propositions.add(new WindowProposal("Add Wall", this::openWallPropositions));
        propositions.add(new WindowProposal("Add slab", this::openSlabPropositions));
        if(this instanceof NormalSquare) {
            propositions.add(new WindowProposal("Add blob", this::createSecureBlob, true));
        }
        propositions.add(new WindowProposal("Transparency", this::openTransparencySettings, true));
        propositions.add(new WindowProposal("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    private void openTransparencySettings() {
        TransparencySettings.showGameSettingsPopUp(this);
    }

    private void openSlabPropositions() {
        board.getGame().getLittleWindow().set(getSlabPropositions());
    }

    @NonNull
    private List<WindowProposal> getSlabPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Light blue", () -> createSecureSlab("01"), CouleurDuJeu.BleuClair.Int(), true));
        propositions.add(new WindowProposal("Dark blue", () -> createSecureSlab("11"), CouleurDuJeu.BleuFonce.Int(), true));
        propositions.add(new WindowProposal("Red", () -> createSecureSlab("21"), CouleurDuJeu.Rouge.Int(), true));
        propositions.add(new WindowProposal("Green", () -> createSecureSlab("31"), CouleurDuJeu.Vert.Int(), true));
        propositions.add(new WindowProposal("Yellow", this::createSecureYellowSlab, CouleurDuJeu.Jaune.Int(), true));
        propositions.add(new WindowProposal("Orange", () -> createSecureSlab("51"), CouleurDuJeu.Orange.Int(), true));
        propositions.add(new WindowProposal("Purple", () -> createSecureSlab("61"), CouleurDuJeu.Violet.Int(), true));
        return propositions;
    }

    private void openWallPropositions() {
        board.getGame().getLittleWindow().set(getWallPropositions("0"));
    }

    private void openDoorPropositions() {
        board.getGame().getLittleWindow().set(getDoorPropositions());
    }

    @NonNull
    private List<WindowProposal> getDoorPropositions() {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal(
                "Light blue", () -> board.getGame().getLittleWindow().set(getWallPropositions("a")), CouleurDuJeu.BleuClair.Int()));
        propositions.add(new WindowProposal(
                "Dark blue", () -> board.getGame().getLittleWindow().set(getWallPropositions("b")), CouleurDuJeu.BleuFonce.Int()));
        propositions.add(new WindowProposal(
                "Red", () -> board.getGame().getLittleWindow().set(getWallPropositions("c")), CouleurDuJeu.Rouge.Int()));
        return propositions;
    }

    @NonNull
    private List<WindowProposal> getWallPropositions(String code) {
        List<WindowProposal> propositions = new ArrayList<>();
        propositions.add(new WindowProposal("Top", () -> createSecureWall(WallsOfSquare.Direction.Top, code), true));
        propositions.add(new WindowProposal("Left", () -> createSecureWall(WallsOfSquare.Direction.Left, code), true));
        propositions.add(new WindowProposal("Bottom", () -> createSecureWall(WallsOfSquare.Direction.Bottom, code), true));
        propositions.add(new WindowProposal("Right", () -> createSecureWall(WallsOfSquare.Direction.Right, code), true));
        return propositions;
    }

    /**
     * Create a wall <u>only if</u> it's possible.
     */
    private void createSecureWall(WallsOfSquare.Direction direction, String code) {
        if(thereIsAlreadyAWallAt(direction)) {
            game.getPopUp().showMessage("WARNING", "There is already a wall on this place.", 1500);
            return;
        }
        walls.setByCode(direction, code);
    }

    private boolean thereIsAlreadyAWallAt(WallsOfSquare.Direction direction) {
        ModularWall wall = walls.get(direction);
        return wall != null;
    }

    /**
     * Create a slab <u>only if</u> it's possible.
     * @param code the code of the slab.
     */
    private void createSecureSlab(String code) {
        if(thereIsAlreadyASlab()) {
            game.getPopUp().showMessage("WARNING", "There is already a slab on this place.", 1500);
            return;
        }
        if(thereIsABlob()) {
            game.getPopUp().showMessage("WARNING", "There is already a blob on this place.", 1500);
            return;
        }
        createSlab(code);
    }

    /**
     * Create a yellow slab <u>only if</u> it's possible.
     */
    private void createSecureYellowSlab() {
        int casesEnLargeur = 2;
        for(int x = 0; x < casesEnLargeur; x++) {
            for(int y = 0; y < casesEnLargeur; y++) {
                ZdecimalCoordinates cord = this.cord;
                for(int xIndex = 0; xIndex < x; xIndex++) {
                    cord = ZdecimalCoordinatesManager.getRightOf(cord);
                }
                for(int yIndex = 0; yIndex < y; yIndex++) {
                    cord = ZdecimalCoordinatesManager.getBottomOf(cord);
                }
                ModularSquare square = board.getSquareAt(cord);
                boolean freeSquare = checkIfFreePlace(square);
                if(!freeSquare) {
                    return;
                }
            }
        }
        createSlab("41");
    }

    private boolean checkIfFreePlace(ModularSquare square) {
        if(square == null) {
            game.getPopUp().showMessage("WARNING", "There isn't enough space.", 1500);
            return false;
        }
        if(square.thereIsAlreadyASlab()) {
            game.getPopUp().showMessage("WARNING", "There is already a slab on this place.", 1500);
            return false;
        }
        if(square.thereIsABlob()) {
            game.getPopUp().showMessage("WARNING", "There is already a blob on this place.", 1500);
            return false;
        }
        return true;
    }

    /**
     * Create a blob <u>only if</u> it's possible.
     */
    private void createSecureBlob() {
        if(thereIsABlob()) {
            game.getPopUp().showMessage("WARNING", "There is already a blob on this place.", 1500);
            return;
        }
        createBlob();
    }

    public boolean thereIsAlreadyASlab() {
        ModularSlab slab = board.getSlabAt(cord);
        return slab != null;
    }

    public boolean thereIsABlob() {
        ModularBlob blob = board.getBlobAtSquare(this);
        return blob != null;
    }

    @Override
    public void remove() {
        super.remove();
        removeSlabOn();
        removeBlobOn();
    }

    private void removeSlabOn() {
        ModularSlab slab = board.getSlabAt(cord);
        if(slab != null) {
            slab.remove();
        }
    }

    private void removeBlobOn() {
        ModularBlob blob = board.getBlobAtSquare(this);
        if(blob != null) {
            blob.remove();
        }
    }
}
