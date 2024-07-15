package com.example.veritablejeu.Tools;

import java.util.Arrays;
import java.util.List;

public enum Elevation {
    PopUp, LittleWindow, Blob, FillCable, BorderCable, Door, Wall, Slab, Square, SpecSquare, Bubble;

    private static final List<Elevation> order = Arrays.asList(
            PopUp,
            LittleWindow,
            Blob,
            Door,
            Slab,
            FillCable,
            BorderCable,
            Wall,
            Square,
            SpecSquare,
            Bubble
    );

    public int getElevation() {
        int howManyLevelsBelow = order.indexOf(this);
        return (order.size() - 1) - howManyLevelsBelow;
    }
}
