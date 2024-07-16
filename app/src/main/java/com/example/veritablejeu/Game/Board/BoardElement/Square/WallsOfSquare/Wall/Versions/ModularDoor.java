package com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.Versions;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.Cable;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSquare;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.Wall.ModularWall;
import com.example.veritablejeu.Game.Board.BoardElement.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.Game.Board.BoardsMovements.OnTouchForElement;
import com.example.veritablejeu.Tools.CreateSimpleBackground;
import com.example.veritablejeu.Game.Board.BoardElement.Square.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.LittleWindow.LittleWindow;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.BackEnd.sequentialCode.Code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressLint("ViewConstructor")
public class ModularDoor extends ModularWall {

    private static final int DEFAULT_FILL_COLOR = Color.WHITE;
    private static final int THINKNESS = 15;
    private static final int ELEVATION = Elevation.Door.getElevation();

    private final int necessarySlabNumber;
    private final Set<ComponentsStorage> connectedCables = new HashSet<>();
    protected WallAspect wallAspect;
    private final int fillColor;
    protected boolean subjectToSealing = false;
    protected boolean sealed = false;

    public ModularDoor(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code, CouleurDuJeu fillColor, int necessarySlab) {
        super(modularSquare, direction);

        necessarySlabNumber = necessarySlab;
        this.fillColor = fillColor == null ? DEFAULT_FILL_COLOR : fillColor.Int();
        GradientDrawable background = CreateSimpleBackground.create(this.fillColor, Color.BLACK, 2);
        wallAspect = new WallAspect(background, THINKNESS, ELEVATION);
        buildVisual(wallAspect);

        if(code != null && !code.isEmpty()) {
            String subcode = code.substring(1);
            Code.apply(subcode,
                    'c', (Consumer<String>) codex -> sealing()
            );
        }
    }

    public boolean isSealed() {
        return sealed;
    }

    public void sealUnseal() {
        if(isSealed()) {
            unsealing();
        } else {
            sealing();
        }
    }

    public void sealing() {
        subjectToSealing = true;
        sealed = true;
        wall.setBackground(CreateSimpleBackground.create(CouleurDuJeu.Vert.Int(), Color.BLACK, 2));
        verify();
    }

    public void unsealing() {
        sealed = false;
        wall.setBackground(wallAspect.getDrawable());
        verify();
    }

    public Set<ModularSlab> getConnectedSlab() {
        return connectedCables.stream()
                .map(ComponentsStorage::getSlab)
                .collect(Collectors.toSet());
    }

    public void addConnectedCable(ComponentsStorage modularCable) {
        if(modularCable == null) {
            return;
        }
        connectedCables.add(modularCable);
    }

    public void removeConnectedCable(ComponentsStorage modularCable) {
        connectedCables.remove(modularCable);
    }

    public void deleteAllConnectedCables() {
        Set<ComponentsStorage> toDelete = new HashSet<>(connectedCables);
        toDelete.forEach(ComponentsStorage::deleteCable);
    }

    @Override
    public boolean isTraversable() {
        return !sealed && isOpenWithoutMaster();
    }

    public boolean isOpenWithoutMaster() {
        return getHowManyActiveSlabs_withoutMaster() >= necessarySlabNumber;
    }

    private long getHowManyActiveSlabs_withoutMaster() {
        return getConnectedSlab().stream()
                .filter(ModularSlab::isActive_withoutMaster)
                .count();
    }

    public void verify() {
        boolean haveEnoughBlobs = getHowManyActiveSlabs() >= necessarySlabNumber;
        setVisualOpen(!sealed && haveEnoughBlobs);
    }

    private long getHowManyActiveSlabs() {
        return getConnectedSlab().stream()
                .filter(ModularSlab::isActive)
                .count();
    }

    public void setVisualOpen(boolean open) {
        if(open) {
            openingAnimation();
        } else {
            closingAnimation();
        }
    }

    public void openingAnimation() {
        if(wallAspect != null) {
            changerLargeurProgressivement(wallAspect.getThickness(), 500);
        }
    }

    public void closingAnimation() {
        changerLargeurProgressivement(totalWidth, 300);
    }

    private void changerLargeurProgressivement(int nouvelleLargeur, int duree) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.width, nouvelleLargeur);
        valueAnimator.setDuration(duree);
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.addUpdateListener(animation -> layoutParams.width = (int) animation.getAnimatedValue());
        valueAnimator.start();
    }

    @Override
    public void enableInGameListeners() {

    }

    public void enableCableEditorListener(Cable cable) {

        new OnTouchForElement(this) {

            @Override
            public Consumer<MotionEvent> clickEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> longPressWithoutMoveEvent() {
                return event -> {};
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
                return event -> swapConnectionWithCable(cable);
            }

            @Override
            public Consumer<MotionEvent> fastStopTouchWithMoveEvent() {
                return event -> {};
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithoutMoveAfterLongPressEvent() {
                return event -> swapConnectionWithCable(cable);
            }

            @Override
            public Consumer<MotionEvent> stopTouchWithMoveAfterLongPressEvent() {
                return event -> {};
            }
        };
    }

    private void swapConnectionWithCable(Cable cable) {
        if(cable == null) return;
        boolean isConnectedCable = connectedCables.contains(cable.getComponentsStorage());
        if(isConnectedCable) {
            disconnectToCable(cable);
        } else {
            connectToCable(cable);
        }
    }

    private void connectToCable(Cable cable) {
        if(cableColorMatch(cable)) {
            cable.connectDoor(this);
        } else {
            game.getPopUp().showMessage("WARNING", "Doors and cables must have the same color.", 1500);
        }
    }

    private boolean cableColorMatch(Cable cable) {
        if(cable == null) return false;
        int cableColor = cable.getComponentsStorage().getSlab().getFillColor();
        return cableColor == fillColor;
    }

    private void disconnectToCable(Cable cable) {
        if(cable == null) return;
        cable.disconnectDoor();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void disableCableEditorListener() {
        setOnTouchListener((v, event) -> false);
    }

    @Override
    public List<LittleWindow.StringRunnablePair> getEditPropositions() {
        List<LittleWindow.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new LittleWindow.StringRunnablePair("Sealing", this::sealUnseal, true));
        propositions.add(new LittleWindow.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }

    @Override
    public void remove() {
        super.remove();
        deleteAllConnectedCables();
    }
}
