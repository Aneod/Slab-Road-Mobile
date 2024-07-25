package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.sequentialCode.CodeBuilder;
import com.example.veritablejeu.Game.Board.Board;
import com.example.veritablejeu.Game.Board.BoardElement.BoardElement;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.OutlineWall;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.ModularDoor;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions.SimpleWall;
import com.example.veritablejeu.Game.Board.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public abstract class ModularWall extends BoardElement {

    private static final char SIMPLE_WALL_TYPE = '0';
    private static final char OUTLINE_WALL_TYPE = '1';
    private static final char LB_DOOR_TYPE = 'a';
    private static final char DB_DOOR_TYPE = 'b';
    private static final char RED_DOOR_TYPE = 'c';

    public static final int TOTAL_HEIGHT = Board.BORDER_WIDTH * 2;

    protected final ModularSquare modularSquare;
    private final WallsOfSquare.Direction direction;
    protected int totalWidth;

    protected FrameLayout.LayoutParams layoutParams;
    protected View wall;

    /**
     * @param code the code :
     *             <br>
     *             Like : T...
     *             <br>
     *             T : Type (0 : Normal)
     */
    @Nullable
    public static ModularWall create(ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        if(modularSquare == null || direction == null || code == null || code.isEmpty()) {
            return null;
        }
        char wallType = code.charAt(0);
        switch (wallType) {
            case SIMPLE_WALL_TYPE: return new SimpleWall(modularSquare, direction);
            case OUTLINE_WALL_TYPE: return new OutlineWall(modularSquare, direction);
            case LB_DOOR_TYPE: return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.BleuClair, 1);
            case DB_DOOR_TYPE: return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.BleuFonce, 2);
            case RED_DOOR_TYPE: return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.Rouge, 1);
        }
        return new SimpleWall(modularSquare, direction);
    }

    public ModularWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction) {
        super(modularSquare.getBoard());
        this.modularSquare = modularSquare;
        this.direction = direction;

        Board board = modularSquare.getBoard();
        board.addView(this);
    }

    protected void buildVisual(WallAspect wallAspect) {
        if(wallAspect == null) {
            return;
        }
        totalWidth = Board.SQUARE_SIZE + wallAspect.thickness;
        int leftMargin = modularSquare.getLayoutParams().leftMargin - wallAspect.thickness / 2;
        int topMargin = modularSquare.getLayoutParams().topMargin + modularSquare.getLayoutParams().width - TOTAL_HEIGHT / 2;
        layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                totalWidth, TOTAL_HEIGHT, leftMargin, topMargin
        );
        setLayoutParams(layoutParams);
        setElevation(wallAspect.elevation);
        addWall(wallAspect);

        if(direction == null) return;

        if (direction.equals(WallsOfSquare.Direction.Top)) {
            layoutParams.topMargin -= Board.SQUARE_SIZE;
        }

        if (direction.equals(WallsOfSquare.Direction.Left)) {
            setPivotX((float) wallAspect.thickness / 2);
            setPivotY((float) TOTAL_HEIGHT / 2);
            setRotation(-90);
        }

        if (direction.equals(WallsOfSquare.Direction.Right)) {
            setPivotX(totalWidth - (float) wallAspect.thickness / 2);
            setPivotY((float) TOTAL_HEIGHT / 2);
            setRotation(90);
        }
    }

    private void addWall(WallAspect wallAspect) {
        if(wallAspect == null) {
            return;
        }
        wall = new View(getContext());
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int topMargin = (getLayoutParams().height - wallAspect.thickness) / 2;
        ConstraintLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourConstraintLayout(
                width, wallAspect.thickness, 0, topMargin);
        wall.setLayoutParams(layoutParams);
        wall.setBackground(wallAspect.drawable);
        addView(wall);
    }

    public ZdecimalCoordinates getSquareCoordinates() {
        return modularSquare.getCord();
    }

    public WallsOfSquare.Direction getDirection() {
        return direction;
    }

    /**
     * The traversable property is essential for the blobs movements system. All kinds of {@link ModularWall}
     * must be able to say if is possible to get through it.
     * @return true if a blob can get through the wall.
     */
    public abstract boolean isTraversable();

    public static class WallAspect {

        private final GradientDrawable drawable;
        private final int thickness;
        private final int elevation;

        public WallAspect(GradientDrawable drawable, int thickness, int elevation) {
            this.drawable = drawable;
            this.thickness = thickness;
            this.elevation = elevation;
        }

        public GradientDrawable getDrawable() {
            return drawable;
        }
        public int getThickness() {
            return thickness;
        }
    }

    @Override
    public void remove() {
        super.remove();
        modularSquare.getWalls().remove(direction);
    }

    public String getEntireCode() {
        if(this instanceof OutlineWall) {
            return "";
        }
        char direction = getDirection().getChar();
        char type = getType();
        String code = this instanceof ModularDoor ? ((ModularDoor) this).getCode() : "";
        String entireCode = type + code;
        return CodeBuilder.buildKeyValue(direction, entireCode);
    }

    private char getType() {
        if(this instanceof SimpleWall) {
            return SIMPLE_WALL_TYPE;
        } else if(this instanceof ModularDoor) {
            int fillColor = ((ModularDoor) this).getFillColor();
            if(fillColor == CouleurDuJeu.BleuClair.Int()) {
                return LB_DOOR_TYPE;
            } else if(fillColor == CouleurDuJeu.BleuFonce.Int()) {
                return DB_DOOR_TYPE;
            } else if(fillColor == CouleurDuJeu.Rouge.Int()) {
                return RED_DOOR_TYPE;
            }
        }
        return SIMPLE_WALL_TYPE;
    }
}
