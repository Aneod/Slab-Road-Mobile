package com.slabroad.veritablejeu.PopUp.ComposedComponents;

import com.slabroad.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.SimpleImage;
import com.slabroad.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.slabroad.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

public class Manual {

    private static final String POPUP_TITLE = "HOW TO PLAY";

    public static String getPopupTitle() {
        return POPUP_TITLE;
    }

    private static Manual instance;
    private final PopUp popUp;
    private final InlineComponent[] components;

    private Manual(PopUp popUp) {
        this.popUp = popUp;
        components = getComponents(popUp);
    }

    public static Manual getInstance(PopUp popUp) {
        if(instance == null) {
            instance = new Manual(popUp);
        }
        return instance;
    }

    private InlineComponent[] getComponents(PopUp popUp) {
        if(popUp == null) return null;

        return new InlineComponent[]{

                new SimpleText(popUp, "Very simple gameplay\n :\n\n" +
                        "Click on a blob to select it. It will then change color."),

                new SimpleImage(popUp, R.drawable.img1),

                new SimpleText(popUp, "Then click on one of the squares in the game so that it moves there."),

                new SimpleImage(popUp, R.drawable.img2),

                new SimpleText(popUp, "Simple objective :\n" +
                        "You will find orange slabs on the ground.\n" +
                        "The game is over if all the orange slabs are completed.\n" +
                        "A square is completed as long as there are as many blobs on it as the number of points."),

                new SimpleImage(popUp, R.drawable.img3),

                new SimpleText(popUp, "If there are no points, then a single blob is enough.\n" +
                        "But there are walls between your blobs and these orange slabs.\n" +
                        "Blobs do not pass through walls."),

                new SimpleImage(popUp, R.drawable.img5),

                new SimpleText(popUp, "However, the walls are dotted with doors that you can open."),

                new SimpleImage(popUp, R.drawable.img6),

                new SimpleText(popUp, "How to open the doors?\n" +
                        "Each door is connected to one or more slabs."),

                new SimpleImage(popUp, R.drawable.img7),

                new SimpleText(popUp, "Placing a blob on a slab activates that slab. And removing a blob from the slab deactivates it."),

                new SimpleImage(popUp, R.drawable.img8),

                new SimpleText(popUp, "A door remains open as long as there are enough connected slabs activated.\n\n" +
                        "Light blue doors open if at least one slab is activated."),

                new SimpleImage(popUp, R.drawable.img9),

                new SimpleText(popUp, "At least two for dark blue ones."),

                new SimpleImage(popUp, R.drawable.img10),

                new SimpleText(popUp, "Red doors open if at least one red slab is activated.\n" +
                        "But the red slabs are larger and require several blobs to activate."),

                new SimpleImage(popUp, R.drawable.img11),

                new SimpleText(popUp, "Special slabs :\n" +
                        "There are slabs that do not open doors but have special effects.\n" +
                        "And once activated, these slabs do not deactivate.\n\n" +
                        "Sometimes you will find green doors. These doors cannot be " +
                        "open as long as they have this color. But by activating a green slab, " +
                        "you will return these doors to their normal colors."),

                new SimpleImage(popUp, R.drawable.img12),

                new SimpleText(popUp, "It will then be possible to open them.\n" +
                        "By activating a yellow slab, you will obtain as many blobs as the number of " +
                        "points on the slab. And if there is none, then the number of blobs you " +
                        "will get is one."),

                new SimpleImage(popUp, R.drawable.img13),

                new SimpleText(popUp, "Finally, there are the purple slabs. Nearby you will find " +
                        "transparent slabs impossible to reach."),

                new SimpleImage(popUp, R.drawable.img14),

                new SimpleText(popUp, "By activating a purple slab, you make it possible to access the \n" +
                        "adjacent transparent slabs."),

                new SimpleImage(popUp, R.drawable.img15)
        };
    }

    public InlineComponent[] getComponents() {
        return components;
    }

    public void show() {
        if(popUp == null || components == null) return;
        popUp.setContent(POPUP_TITLE, components);
    }

}
