package com.example.veritablejeu.Game.PlateauModulaire.ModularBlob;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.BlobDesign.ModularBlobDesign;
import com.example.veritablejeu.Game.PlateauModulaire.ModularBlob.CadrageMaster.CadrageMaster;
import com.example.veritablejeu.Game.PlateauModulaire.ModularObject;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Board;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.ZdecimalCoordinates.ZdecimalCoordinates;
import com.example.veritablejeu.OutilsEnEnum.Elevation;
import com.example.veritablejeu.OutilsEnEnum.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class ModularBlob extends ModularObject {

    private final FrameLayout.LayoutParams layoutParams;
    private ModularSquare currentLocation;
    private final ModularBlobDesign modularBlobDesign;
    private final CadrageMaster cadrageMaster;
    private boolean estEnDeplacement = false;

    public ModularBlob(@NonNull ModularSquare squareOfStart, @Nullable String code) {
        super(squareOfStart.getBoard());
        this.currentLocation = squareOfStart;

        int widthHeight = Board.SQUARE_SIZE;
        int xPos = squareOfStart.getLayoutParams().leftMargin;
        int yPos = squareOfStart.getLayoutParams().topMargin;
        layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                widthHeight, widthHeight, xPos, yPos
        );
        this.setLayoutParams(layoutParams);
        this.setElevation(Elevation.Blob.getElevation());
        currentLocation.getBoard().addView(this);

        modularBlobDesign = new ModularBlobDesign(this);
        addView(modularBlobDesign);

        cadrageMaster = new CadrageMaster(this);
        addView(cadrageMaster);
    }

    public ModularSquare getCurrentLocation() {
        return currentLocation;
    }

    public boolean isOnThisSquare(ModularSquare modularSquare) {
        if(modularSquare == null) {
            return false;
        }
        return currentLocation.equals(modularSquare);
    }

    public Board getBoard() {
        return currentLocation.getBoard();
    }

    public int getColor() {
        return modularBlobDesign.getColor();
    }

    public void setColor(int color) {
        modularBlobDesign.setColor(color);
    }

    public boolean isMaster() {
        ModularBlob master = currentLocation.getBoard().getMaster();
        return master != null && master.equals(this);
    }

    public void setMaster(boolean isMaster) {
        if(isMaster) {
            cadrageMaster.show_and_resume();
        } else {
            cadrageMaster.hide_and_pause();
        }
    }

    public void moveTo(ZdecimalCoordinates coordinates) {
        if(estEnDeplacement) return;
        ArrayList<ZdecimalCoordinates> itinerary = BestItinerary.get(getBoard(), currentLocation.getCord(), coordinates);
        estEnDeplacement = true;
        ModularSlab slab = getBoard().getSlabAt(currentLocation.getCord());
        if(slab != null) {
            slab.removeBlob(this);
        }
        movesTo(itinerary);
        game.nombreDeCoups++;
    }

    public void movesTo(ArrayList<ZdecimalCoordinates> coordinates) {
        if(coordinates == null || coordinates.isEmpty()) {
            estEnDeplacement = false;
            return;
        }

        ModularSquare destinationSquare = getBoard().getSquareAt(coordinates.get(0));
        if(destinationSquare == null) {
            movesTo(new ArrayList<>());
            return;
        }

        boolean accessible = BestItinerary.sontAdjacentsSansMur(currentLocation.getBoard(),
                currentLocation.getCord(), destinationSquare.getCord()
        );
        if(!accessible) {
            movesTo(new ArrayList<>());
            return;
        }

        int leftMarginDestination = destinationSquare.getLayoutParams().leftMargin;
        int topMarginDestination = destinationSquare.getLayoutParams().topMargin;
        int currentLeftMargin = currentLocation.getLayoutParams().leftMargin;
        int currentTopMargin = currentLocation.getLayoutParams().topMargin;
        int leftMarginToBrowse = leftMarginDestination - currentLeftMargin;
        int topMarginToBrowse = topMarginDestination - currentTopMargin;

        ValueAnimator marginsAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        marginsAnimator.setDuration(500);
        marginsAnimator.addUpdateListener(animation -> {
            float current = (float) marginsAnimator.getAnimatedValue();
            layoutParams.leftMargin = (int) (currentLeftMargin + leftMarginToBrowse * current);
            layoutParams.topMargin = (int) (currentTopMargin + topMarginToBrowse * current);
            setLayoutParams(layoutParams);

            if(current == 1.0) {
                coordinates.remove(0);
                movesTo(coordinates);
            }
        });
        marginsAnimator.start();

        ModularSlab startingSlab = currentLocation.getModularSlab();
        if(startingSlab != null) {
            startingSlab.removeBlob(this);
        }
        currentLocation = destinationSquare;
        ModularSlab arrivalSlab = currentLocation.getModularSlab();
        if(arrivalSlab != null) {
            arrivalSlab.addBlob(this);
        }
    }

    public void birdFlyTo(@NonNull ZdecimalCoordinates coordinates) {
        ArrayList<ZdecimalCoordinates> moves = new ArrayList<>();
        moves.add(coordinates);
        movesTo(moves);
    }

    @Override
    public void enableInGameListeners() {

    }

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Change color", () -> {}));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }

}
