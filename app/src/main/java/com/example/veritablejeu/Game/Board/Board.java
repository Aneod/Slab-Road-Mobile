package com.example.veritablejeu.Game.Board;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;

import com.example.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.example.veritablejeu.Game.Board.BoardElement.BoardElement;
import com.example.veritablejeu.Game.Board.BoardElement.Fence.SpecSquare.SpecSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Editeur.Editeur;
import com.example.veritablejeu.Game.Board.BoardElement.Fence.Fence;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.GroupOfBlobsOfBoard;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularBlob.ModularBlob;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.DoorIdentity.DoorIdentity;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.CabledSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.OrangeSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.Board.BoardsMovements.BoardFraming;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacterSequencer;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;
import com.example.veritablejeu.Game.Game;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCharacter.ZdecimalCharacter;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.StringColorConverter;

import org.jetbrains.annotations.Contract;

import java.util.HashSet;
import java.util.Set;

@SuppressLint("ViewConstructor")
public class Board extends FrameLayout {

    private static final char KEY_PRESET = 'v';
    private static final char KEY_BLOBS_COLOR = 'b';
    private static final char KEY_SQUARE_CODE = 's';

    public static final int SQUARE_SIZE = 240;
    public static final int BORDER_WIDTH = 50;

    private final Game game;
    private ConstraintLayout.LayoutParams layoutParams;
    private final Set<BoardElement> boardElementSet = new HashSet<>();
    public final float normalScale;
    private final int width;
    private final int height;
    private ZdecimalCharacter topLimit;
    private ZdecimalCharacter leftLimit;
    private ZdecimalCharacter bottomLimit;
    private ZdecimalCharacter rightLimit;
    private BoardFraming.Preset preset;
    private final Set<ModularSquare> modularSquareSet = new HashSet<>();
    private final GroupOfBlobsOfBoard groupOfBlobsOfBoard = new GroupOfBlobsOfBoard(this);
    private final Set<ModularSlab> groupOfSlabs = new HashSet<>();
    private Fence fence;
    private final BoardTransparency boardTransparency = new BoardTransparency();

    @Override
    public ConstraintLayout.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    /**
     * Calculation how many pixels in width must be show for see all elements on board.
     * @return a number of pixels.
     */
    public int getWidthToShow() {
        return width;
    }

    /**
     * Calculation how many pixels in height must be show for see all elements on board.
     * @return a number of pixels.
     */
    public int getHeightToShow() {
        return height;
    }

    /**
     * Among all elements on the board, get the left margin of the closest to the left side.
     * @return the smallest left margin.
     */
    public int getNearestLeftMargin() {
        return 0;
    }

    /**
     * Among all elements on the board, get the top margin of the closest to the top side.
     * @return the smallest top margin.
     */
    public int getNearestTopMargin() {
        return 0;
    }

    /**
     * In function of elements on the board, set the left and top margins, and the normal scale.
     */
    private void setVision() {
        BoardFraming.sizeAndPositionBoard(this, preset);
    }

    /**
     * Set the basic layoutParams of the board.
     * <br>
     * ( 36 * {@link #SQUARE_SIZE} + 2 * {@link #BORDER_WIDTH} ) in length and in width. Independent of what will be on. And that can't be changed.
     * <br>
     * The margins will changes in function of what will be on the board.
     * 0 for now.
     */
    private void setLayoutParams() {
        int widthHeight = 36 * SQUARE_SIZE + 2 * BORDER_WIDTH;
        layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                widthHeight, widthHeight, 0, 0
        );
        setLayoutParams(layoutParams);
    }

    public Board(@NonNull Game game, @NonNull String code) {
        super(game);
        this.game = game;
        setLayoutParams();

        ConstraintLayout container = game.findViewById(R.id.main);
        container.addView(this);

        Code.apply(code,
                KEY_SQUARE_CODE, (Consumer<String>) this::createSquare,
                KEY_BLOBS_COLOR, (Consumer<String>) this::setBlobsColorByCode,
                KEY_PRESET, (Consumer<String>) this::setPreset
        );

        connectDoors_and_cables();

        determineBoardLimits();
        width = getNumberOf_squarePerLines() * SQUARE_SIZE + BORDER_WIDTH * 2;
        height = getNumberOf_squarePerColumns() * SQUARE_SIZE + BORDER_WIDTH * 2;

        setVision();
        normalScale = getScaleX();
    }

    private void setPreset(String code) {
        preset = BoardFraming.Preset.createPreset_byCode(this, code);
    }

    public void cropping() {
        setScaleX(normalScale);
        setScaleY(normalScale);
        setTranslationX(0.0f);
        setTranslationY(0.0f);
    }

    private void determineBoardLimits() {

        if(game instanceof Editeur) {
            topLimit = new ZdecimalCharacter(ZdecimalCharacter.getMinValidChar());
            leftLimit = new ZdecimalCharacter(ZdecimalCharacter.getMinValidChar());
            bottomLimit = new ZdecimalCharacter(ZdecimalCharacter.getMaxValidChar());
            rightLimit = new ZdecimalCharacter(ZdecimalCharacter.getMaxValidChar());
        } else {
            for (ModularSquare modularSquare : modularSquareSet) {
                ZdecimalCharacter cordXSquare = modularSquare.getCord().getX();
                ZdecimalCharacter cordYSquare = modularSquare.getCord().getY();

                if (topLimit == null || ZdecimalCharacterSequencer.isLowerTo(cordYSquare, topLimit)) {
                    topLimit = cordYSquare;
                }
                if (leftLimit == null || ZdecimalCharacterSequencer.isLowerTo(cordXSquare, leftLimit)) {
                    leftLimit = cordXSquare;
                }
                if (bottomLimit == null || ZdecimalCharacterSequencer.isHigherTo(cordYSquare, bottomLimit)) {
                    bottomLimit = cordYSquare;
                }
                if (rightLimit == null || ZdecimalCharacterSequencer.isHigherTo(cordXSquare, rightLimit)) {
                    rightLimit = cordXSquare;
                }
            }
        }
    }

    public Set<BoardElement> getBoardElementSet() {
        return boardElementSet;
    }

    public void createFences() {
        fence = new Fence(this);
    }

    public void enableEditorListeners() {
        for(BoardElement boardElement : boardElementSet) {
            if(boardElement instanceof ModularDoor) {
                ((ModularDoor) boardElement).disableCableEditorListener();
            } else if(boardElement instanceof SpecSquare) {
                ((SpecSquare) boardElement).disableCableEditorListener();
            }
            boardElement.enableEditorListeners();
        }
    }

    public void enableCableEditorListeners(Cable cable) {
        for(BoardElement boardElement : boardElementSet) {
            boardElement.disableEditorListeners();
            if(boardElement instanceof ModularDoor) {
                ((ModularDoor) boardElement).enableCableEditorListener(cable);
            } else if(boardElement instanceof SpecSquare) {
                ((SpecSquare) boardElement).enableCableEditorListener(cable);
            }
        }
    }

    @Nullable
    public ModularDoor getCorrespondingDoor_OfDoorIdentity(DoorIdentity doorIdentity) {
        ModularWall correspondingWall = getCorrespondingWall_OfDoorIdentity(doorIdentity);
        if(correspondingWall instanceof ModularDoor) {
            return (ModularDoor) correspondingWall;
        }
        return null;
    }

    @Nullable
    private ModularWall getCorrespondingWall_OfDoorIdentity(DoorIdentity doorIdentity) {
        ModularSquare correspondingSquare = getCorrespondingSquare_OfDoorIdentity(doorIdentity);
        if(correspondingSquare == null) return null;
        WallsOfSquare.Direction doorDirection = doorIdentity.getDirection();
        return correspondingSquare.getWalls().get(doorDirection);
    }

    @Nullable
    private ModularSquare getCorrespondingSquare_OfDoorIdentity(DoorIdentity doorIdentity) {
        if(doorIdentity == null) return null;
        ZdecimalCoordinates doorCords = doorIdentity.getZdecimalCoordinates();
        return getSquareAt(doorCords);
    }

    private void connectDoors_and_cables() {
        groupOfSlabs.stream()
                .filter(CabledSlab.class::isInstance)
                .map(modularSlab -> ((CabledSlab) modularSlab).getConnectedCables())
                .flatMap(Set::stream)
                .forEach(Cable::connectCorrespondingDoor);
    }

    public void showFence() {
        if(fence != null) {
            fence.showFence();
        }
    }

    public void hideFence() {
        if(fence != null) {
            fence.hideFence();
        }
    }

    public void addBlob(ModularBlob blob) {
        groupOfBlobsOfBoard.add(blob);
    }

    public void removeBlob(ModularBlob blob) {
        groupOfBlobsOfBoard.remove(blob);
    }

    public GroupOfBlobsOfBoard getBlobs() {
        return groupOfBlobsOfBoard;
    }

    public ZdecimalCharacter getTopLimit() {
        return topLimit;
    }

    public ZdecimalCharacter getLeftLimit() {
        return leftLimit;
    }

    public ZdecimalCharacter getBottomLimit() {
        return bottomLimit;
    }

    public ZdecimalCharacter getRightLimit() {
        return rightLimit;
    }

    public int getNumberOf_squarePerLines() {
        return ZdecimalCharacterSequencer.getAbsoluteGapBetween(leftLimit, rightLimit) + 1;
    }

    public int getNumberOf_squarePerColumns() {
        return ZdecimalCharacterSequencer.getAbsoluteGapBetween(topLimit, bottomLimit) + 1;
    }

    public void addExternalWalls() {
        for(ModularSquare modularSquare : modularSquareSet) {
            modularSquare.addExternalWalls();
        }
    }

    public Game getGame() {
        return game;
    }

    public int getTotalWidthPlateau() {
        return width;
    }

    public int getTotalHeightPlateau() {
        return height;
    }

    public float getNormalScale() {
        return normalScale;
    }

    public int getBlobsColor() {
        return groupOfBlobsOfBoard.getBlobsColor();
    }

    private void setBlobsColorByCode(String blobsColor) {
        int color = StringColorConverter.turnIntoColor(blobsColor);
        groupOfBlobsOfBoard.setBlobsColor(color);
    }

    public void createSquare(@NonNull String code) {
        ModularSquare modularSquare = ModularSquare.create(this, code);
        modularSquareSet.add(modularSquare);
    }

    @Nullable
    public ModularSquare getSquareAt(ZdecimalCoordinates cord) {
        for(ModularSquare modularSquare : modularSquareSet) {
            ZdecimalCoordinates squareCord = modularSquare.getCord();
            if(cord == null || squareCord == null) {
                return null;
            }
            boolean areSame = cord.equals(squareCord);
            if(areSame) {
                return modularSquare;
            }
        }
        return null;
    }

    public void addSlab(ModularSlab modularSlab) {
        groupOfSlabs.add(modularSlab);
    }
    public void removeSlab(ModularSlab modularSlab) {
        groupOfSlabs.remove(modularSlab);
    }

    @Nullable
    public ModularSlab getSlabAt(ZdecimalCoordinates coordinates) {
        for(ModularSlab modularSlab : groupOfSlabs) {
            if(modularSlab.haveNum(coordinates)) {
                return modularSlab;
            }
        }
        return null;
    }

    public boolean isComplete() {
        long howManyOrange = groupOfSlabs.stream()
                .filter(modularSlab -> modularSlab instanceof OrangeSlab)
                .count();

        long howManyActiveOrange = groupOfSlabs.stream()
                .filter(modularSlab -> modularSlab instanceof OrangeSlab)
                .filter(ModularSlab::isActive)
                .count();

        return howManyActiveOrange >= howManyOrange;
    }

    public void unsealedDoors() {
        modularSquareSet.stream()
                .flatMap(modularSquare -> modularSquare.getWalls().getAll().stream())
                .filter(modularWall -> modularWall instanceof ModularDoor && ((ModularDoor) modularWall).isSealed())
                .forEach(modularWall -> ((ModularDoor) modularWall).unsealing());
    }

    public void printCableOutlines() {
        groupOfSlabs.stream()
                .filter(modularSlab -> modularSlab instanceof CabledSlab)
                .forEach(modularSlab -> ((CabledSlab) modularSlab).printCableOutlines());
    }

    public void hideCableOutlines() {
        groupOfSlabs.stream()
                .filter(modularSlab -> modularSlab instanceof CabledSlab)
                .forEach(modularSlab -> ((CabledSlab) modularSlab).hideCableOutlines());
    }

    @Nullable
    public ModularBlob getBlobAtSquare(@NonNull ModularSquare modularSquare) {
        return groupOfBlobsOfBoard.getBlobAtSquare(modularSquare);
    }

    @Nullable
    public ModularBlob getMaster() {
        return groupOfBlobsOfBoard.getMaster();
    }

    public void setMaster(ModularBlob modularBlob) {
        groupOfBlobsOfBoard.setMaster(modularBlob);
    }

    public void setSquaresAccessibilities() {
        boolean movesHelperDisable = !AccessibleSquaresFinder.isHelperEnable();
        if(getMaster() == null || movesHelperDisable) {
            showAllSquaresAccessible();
        } else {
            Set<ZdecimalCoordinates> accessibleSquares = AccessibleSquaresFinder.getAllAccessibleSquares_byMaster(this);
            setSquaresAccessibilities_byList(accessibleSquares);
        }
    }

    private void setSquaresAccessibilities_byList(Set<ZdecimalCoordinates> accessibleSquares) {
        for(ModularSquare modularSquare : modularSquareSet) {
            ZdecimalCoordinates current = modularSquare.getCord();
            modularSquare.setShowAsAccessible(accessibleSquares.contains(current));
        }
    }

    private void showAllSquaresAccessible() {
        for(ModularSquare modularSquare : modularSquareSet) {
            modularSquare.setShowAsAccessible(modularSquare.isAccessible());
        }
    }

    public Set<ModularSquare> getModularSquareSet() {
        return modularSquareSet;
    }

    public BoardTransparency getBoardTransparency() {
        return boardTransparency;
    }

    public String getCode() {
        return getPresetCode() + getBlobsColorCode() + getSquaresCode();
    }

    @NonNull
    @Contract(pure = true)
    private String getPresetCode() {
        // Until there is only one board by which level, define a preset is useless.
        return "";
    }

    @NonNull
    private String getBlobsColorCode() {
        int blobsColor = groupOfBlobsOfBoard.getBlobsColor();
        String codeColor = StringColorConverter.turnIntoCode(blobsColor);
        return CodeBuilder.buildKeyValue(KEY_BLOBS_COLOR, codeColor);
    }

    @NonNull
    private String getSquaresCode() {
        String onReturn = "";
        for(ModularSquare square : modularSquareSet) {
            String squareCode = square.getCode();
            String code = CodeBuilder.buildKeyValue(KEY_SQUARE_CODE, squareCode);
            onReturn = onReturn.concat(code);
        }
        return onReturn;
    }
}
