package com.example.veritablejeu.PopUp.ContenuPopUp.Manuel.PageInstruction.ModificateurDePage;

import com.example.veritablejeu.PopUp.ContenuPopUp.Manuel.PageInstruction.PageInstruction;
import com.example.veritablejeu.R;

public class ModificateurDePage implements IModificateurDePage {

    private final PageInstruction pageInstruction;
    private final int imageVide = R.drawable.blanc;

    private void setPage1() {
        pageInstruction.setTouteLaPage(
                "Gameplay très simple :\n\n" +
                        "Cliquer sur un blob pour le sélectionner. Il changera alors de couleur.",
                210,
                R.drawable.img1,
                "Puis cliquer sur une des cases du jeu pour qu’il s’y déplace.",
                90,
                R.drawable.img2
        );
    }

    private void setPage2() {
        pageInstruction.setTouteLaPage(
                "Objectif simple :\n\n" +
                        "Vous trouverez des cases oranges sur le terrain.\n" +
                        "Le jeu est terminé si toutes les cases oranges sont complétées.\n" +
                        "Une case est complétée tant qu'il y a autant de blob dessus que le nombre de point.",
                390,
                R.drawable.img3,
                "S’il n’y a aucun point, alors un seul blob suffit.",
                100,
                imageVide
        );
    }

    private void setPage3() {
        pageInstruction.setTouteLaPage(
                "Mais il y a des murs entre vos blobs et ces cases oranges.\n" +
                        "Les blobs ne traversent pas les murs.",
                175,
                R.drawable.img5,
                "En revanche les murs sont parsemés de portes que vous pouvez ouvrir.",
                130,
                R.drawable.img6
        );
    }

    private void setPage4() {
        pageInstruction.setTouteLaPage(
                "Comment ouvrir les portes ?\n" +
                        "Chaque porte est connectée à une ou plusieurs cases.",
                130,
                R.drawable.img7,
                "Mettre un blob sur une case active cette case. Et retirer un blob de la case la désactive.",
                135,
                R.drawable.img8
        );
    }

    private void setPage5() {
        pageInstruction.setTouteLaPage(
                "Une porte reste ouverte tant qu’il y a assez de cases connectées activées.\n\n" +
                        "Les portes bleues clairs s’ouvrent si au moins une case est activée.",
                255,
                R.drawable.img9,
                "Au moins deux pour les bleues foncées.",
                85,
                R.drawable.img10
        );
    }

    private void setPage6() {
        pageInstruction.setTouteLaPage(
                "Les portes rouges s’ouvrent si au moins une case rouge est activée.\n" +
                        "Mais les cases rouges sont plus grandes et nécessitent plusieurs blobs pour être activées.",
                255,
                R.drawable.img11,
                "",
                0,
                imageVide
        );
    }

    private void setPage7() {
        pageInstruction.setTouteLaPage(
                "Les cases spéciales :\n\n" +
                        "Il existe des cases qui n’ouvrent pas de portes mais qui ont des effets spéciaux.\n\n" +
                        "Et une fois activés, ces cases ne se désactivent pas.",
                340,
                imageVide,
                "",
                0,
                imageVide
        );
    }

    private void setPage8() {
        pageInstruction.setTouteLaPage(
                "Parfois, vous trouverez des portes vertes. Ces portes ne pourront êtres " +
                        "ouvertes tant qu’elles auront cette couleur. Mais en activant une case verte, " +
                        "vous rendrez à ces portes leurs couleurs normales.",
                297,
                R.drawable.img12,
                "Il sera alors possible de les ouvrir.",
                90,
                imageVide
        );
    }

    private void setPage9() {
        pageInstruction.setTouteLaPage(
                "En activant une case jaune, vous obtiendrez autant de blob que le nombre de " +
                        "points sur la case. Et s’il n’y en a pas, alors le nombre de blob que vous " +
                        "obtiendrez est de un.",
                265,
                R.drawable.img13,
                "",
                0,
                imageVide
        );
    }

    private void setPage10() {
        pageInstruction.setTouteLaPage(
                "Enfin, il existe les cases violettes. A proximité vous trouverez des " +
                        "cases transparentes impossibles à atteindre.",
                185,
                R.drawable.img14,
                "En activant une case violette, vous rendez possible l’accès aux" +
                        " cases transparents adjacentes.",
                128,
                R.drawable.img15
        );
    }

    @Override
    public int getNbDePages() {
        return 10;
    }

    @Override
    public void setALaPage(int numeroDePage) {
        switch (numeroDePage) {
            case 1 : setPage1(); break;
            case 2 : setPage2(); break;
            case 3 : setPage3(); break;
            case 4 : setPage4(); break;
            case 5 : setPage5(); break;
            case 6 : setPage6(); break;
            case 7 : setPage7(); break;
            case 8 : setPage8(); break;
            case 9 : setPage9(); break;
            case 10 : setPage10(); break;
        }
    }

    public ModificateurDePage(PageInstruction pageInstruction) {
        this.pageInstruction = pageInstruction;
    }
}
