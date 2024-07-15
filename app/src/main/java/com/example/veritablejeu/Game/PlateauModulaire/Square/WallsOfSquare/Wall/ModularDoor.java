package com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.Wall;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.Game.PlateauModulaire.CreateSimpleBackground;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.Version.CabledSlab.Cable.CableComponentsStorage.ComponentsStorage;
import com.example.veritablejeu.Game.PlateauModulaire.ModularSlab.ModularSlab;
import com.example.veritablejeu.Game.PlateauModulaire.Square.ModularSquare;
import com.example.veritablejeu.Game.PlateauModulaire.Square.WallsOfSquare.WallsOfSquare;
import com.example.veritablejeu.OutilsEnEnum.CouleurDuJeu;
import com.example.veritablejeu.PetiteFenetreFlottante.PetiteFenetreFlottante2;
import com.example.veritablejeu.sequentialCode.Code;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressLint("ViewConstructor")
public class ModularDoor extends ModularWall {

    private static final int DEFAULT_FILL_COLOR = Color.WHITE;

    private final int necessarySlabNumber;
    private final Set<ModularSlab> connectedSlab = new HashSet<>();
    private final Set<ComponentsStorage> connectedCables = new HashSet<>();
    protected WallAspect wallAspect;
    protected boolean subjectToSealing = false;
    protected boolean sealed = false;

    @NonNull
    @Contract("_, _, _ -> new")
    public static ModularDoor lightBlue(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.BleuClair, 1);
    }

    @NonNull
    @Contract("_, _, _ -> new")
    public static ModularDoor darkBlue(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.BleuFonce, 2);
    }

    @NonNull
    @Contract("_, _, _ -> new")
    public static ModularDoor red(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code) {
        return new ModularDoor(modularSquare, direction, code, CouleurDuJeu.Rouge, 1);
    }

    public ModularDoor(@NonNull ModularSquare modularSquare, WallsOfSquare.Direction direction, String code, CouleurDuJeu fillColor, int necessarySlab) {
        super(modularSquare, direction, code);

        necessarySlabNumber = necessarySlab;
        int color = fillColor == null ? DEFAULT_FILL_COLOR : fillColor.Int();
        GradientDrawable background = CreateSimpleBackground.create(color, Color.BLACK, 2);
        wallAspect = new WallAspect(background, 15, 6);
        buildVisual(wallAspect);

        String subcode = code.substring(1);
        Code.apply(subcode,
                'c', (Consumer<String>) codex -> sealing()
        );
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

    public void addConnectedSlab(ModularSlab modularSlab) {
        if(modularSlab == null) {
            return;
        }
        connectedSlab.add(modularSlab);
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

    @Override
    public boolean isTraversable() {
        return !sealed && isOpenWithoutMaster();
    }

    public boolean isOpenWithoutMaster() {
        return getHowManyActiveSlabs_withoutMaster() >= necessarySlabNumber;
    }

    private long getHowManyActiveSlabs_withoutMaster() {
        return connectedSlab.stream()
                .filter(ModularSlab::isActive_withoutMaster)
                .count();
    }

    public void verify() {
        boolean haveEnoughBlobs = getHowManyActiveSlabs() >= necessarySlabNumber;
        setVisualOpen(!sealed && haveEnoughBlobs);
    }

    private long getHowManyActiveSlabs() {
        return connectedSlab.stream()
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

    @Override
    public List<PetiteFenetreFlottante2.StringRunnablePair> getEditPropositions() {
        List<PetiteFenetreFlottante2.StringRunnablePair> propositions = new ArrayList<>();
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Sealing", this::sealUnseal, true));
        propositions.add(new PetiteFenetreFlottante2.StringRunnablePair("Delete", this::remove, Color.RED, true));
        return propositions;
    }
}
