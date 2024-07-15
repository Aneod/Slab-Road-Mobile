package com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.ModularObject;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

@SuppressLint("ViewConstructor")
public abstract class ModularWall extends ModularObject {
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
            case '0': return new SimpleWall(modularSquare, direction, code);
            case '1': return new OutlineWall(modularSquare, direction, code);
            case 'a': return ModularDoor.lightBlue(modularSquare, direction, code);
            case 'b': return ModularDoor.darkBlue(modularSquare, direction, code);
            case 'c': return ModularDoor.red(modularSquare, direction, code);
        }
        return new SimpleWall(modularSquare, direction, code);
    }

    public ModularWall(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
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

}
