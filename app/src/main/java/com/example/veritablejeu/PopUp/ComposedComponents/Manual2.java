package com.example.veritablejeu.PopUp.ComposedComponents;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.SimpleImage;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

public class Manual2 {

    private static Manual2 instance;
    private final PopUp popUp;
    private final InlineComponent[] components;

    private Manual2(PopUp popUp) {
        this.popUp = popUp;
        components = getComponents(popUp);
    }

    public static Manual2 getInstance(PopUp popUp) {
        if(instance == null) {
            instance = new Manual2(popUp);
        }
        return instance;
    }

    private InlineComponent[] getComponents(PopUp popUp) {
        if(popUp == null) return null;

        return new InlineComponent[]{

                new SimpleText(popUp, "Gameplay très simple :\n\n" +
                        "Cliquer sur un blob pour le sélectionner. Il changera alors de couleur."),

                new SimpleImage(popUp, R.drawable.img1),

                new SimpleText(popUp, "Puis cliquer sur une des cases du jeu pour qu’il s’y déplace."),

                new SimpleImage(popUp, R.drawable.img2),

                new SimpleText(popUp, "Objectif simple :\n" +
                        "Vous trouverez des cases oranges sur le terrain.\n" +
                        "Le jeu est terminé si toutes les cases oranges sont complétées.\n" +
                        "Une case est complétée tant qu'il y a autant de blob dessus que le nombre de point."),

                new SimpleImage(popUp, R.drawable.img3),

                new SimpleText(popUp, "S’il n’y a aucun point, alors un seul blob suffit.\n" +
                        "Mais il y a des murs entre vos blobs et ces cases oranges.\n" +
                        "Les blobs ne traversent pas les murs."),

                new SimpleImage(popUp, R.drawable.img5),

                new SimpleText(popUp, "En revanche les murs sont parsemés de portes que vous pouvez ouvrir."),

                new SimpleImage(popUp, R.drawable.img6),

                new SimpleText(popUp, "Comment ouvrir les portes ?\n" +
                        "Chaque porte est connectée à une ou plusieurs cases."),

                new SimpleImage(popUp, R.drawable.img7),

                new SimpleText(popUp, "Mettre un blob sur une case active cette case. Et retirer un blob de la case la désactive."),

                new SimpleImage(popUp, R.drawable.img8),

                new SimpleText(popUp, "Une porte reste ouverte tant qu’il y a assez de cases connectées activées.\n\n" +
                        "Les portes bleues clairs s’ouvrent si au moins une case est activée."),

                new SimpleImage(popUp, R.drawable.img9),

                new SimpleText(popUp, "Au moins deux pour les bleues foncées."),

                new SimpleImage(popUp, R.drawable.img10),

                new SimpleText(popUp, "Les portes rouges s’ouvrent si au moins une case rouge est activée.\n" +
                        "Mais les cases rouges sont plus grandes et nécessitent plusieurs blobs pour être activées."),

                new SimpleImage(popUp, R.drawable.img11),

                new SimpleText(popUp, "Les cases spéciales :\n" +
                        "Il existe des cases qui n’ouvrent pas de portes mais qui ont des effets spéciaux.\n" +
                        "Et une fois activés, ces cases ne se désactivent pas.\n\n" +
                        "Parfois, vous trouverez des portes vertes. Ces portes ne pourront êtres" +
                        "ouvertes tant qu’elles auront cette couleur. Mais en activant une case verte, " +
                        "vous rendrez à ces portes leurs couleurs normales."),

                new SimpleImage(popUp, R.drawable.img12),

                new SimpleText(popUp, "Il sera alors possible de les ouvrir.\n" +
                        "En activant une case jaune, vous obtiendrez autant de blob que le nombre de " +
                        "points sur la case. Et s’il n’y en a pas, alors le nombre de blob que vous " +
                        "obtiendrez est de un."),

                new SimpleImage(popUp, R.drawable.img13),

                new SimpleText(popUp, "Enfin, il existe les cases violettes. A proximité vous trouverez des " +
                        "cases transparentes impossibles à atteindre."),

                new SimpleImage(popUp, R.drawable.img14),

                new SimpleText(popUp, "En activant une case violette, vous rendez possible l’accès aux \n" +
                        "cases transparents adjacentes."),

                new SimpleImage(popUp, R.drawable.img15)
        };
    }

    public void show() {
        if(popUp == null || components == null) return;
        popUp.setContent("HOW TO PLAY", components);
    }

}
