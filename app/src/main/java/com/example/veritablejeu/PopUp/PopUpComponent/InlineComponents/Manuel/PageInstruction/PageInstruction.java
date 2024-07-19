package com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.Manuel;
import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction.ComposantsDePageInstruction.ImageInstruction.ImageInstruction;
import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction.ComposantsDePageInstruction.ParagrapheInstruction.ParagrapheInstruction;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;
import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.Manuel.PageInstruction.ModificateurDePage.ModificateurDePage;

@SuppressLint("ViewConstructor")
public class PageInstruction extends FrameLayout implements IPageInstruction {

    private final ModificateurDePage modificateurDePage = new ModificateurDePage(this);
    private final ParagrapheInstruction paragraphe1;
    private final ParagrapheInstruction paragraphe2;
    private final ImageInstruction image1;
    private final ImageInstruction image2;

    private void setTexteDuParagraphe1(String texteDuParagraphe1) {
        paragraphe1.setText(texteDuParagraphe1);
    }

    private void setTexteDuParagraphe2(String texteDuParagraphe2) {
        paragraphe2.setText(texteDuParagraphe2);
    }

    private void setImage1(int nouvelleImage) {
        image1.changerImage(nouvelleImage);
    }

    private void setImage2(int nouvelleImage) {
        image2.changerImage(nouvelleImage);
    }

    /**
     * Modifie le hauteur du premier paragraphe et ajuste la position des éléments
     * en dessus en conséquence.
     * @param nouvellehauteur le nouvelle hauteur du paragraphe.
     */
    private void setHauteurParagraphe1(int nouvellehauteur) {
        int differenceDeHauteur = nouvellehauteur - paragraphe1.getHauteur();
        paragraphe2.setTopMargin(paragraphe2.getTopMargin() + differenceDeHauteur);
        image1.setTopMargin(image1.getTopMargin() + differenceDeHauteur);
        image2.setTopMargin(image2.getTopMargin() + differenceDeHauteur);
        paragraphe1.setHauteur(nouvellehauteur);
    }

    /**
     * Modifie le hauteur du second paragraphe et ajuste la position des éléments
     * en dessus en conséquence.
     * @param nouvellehauteur le nouvelle hauteur du paragraphe.
     */
    private void setHauteurParagraphe2(int nouvellehauteur) {
        int differenceDeHauteur = nouvellehauteur - paragraphe2.getHauteur();
        image2.setTopMargin(image2.getTopMargin() + differenceDeHauteur);
        paragraphe2.setHauteur(nouvellehauteur);
    }

    @Override
    public void setTouteLaPage(String texteDuParagraphe1, int hauteurParagraphe1,
                               int nouvelleImg1, String texteDuParagraphe2, int hauteurParagraphe2, int nouvelleImg2) {
        setTexteDuParagraphe1(texteDuParagraphe1);
        setTexteDuParagraphe2(texteDuParagraphe2);
        setHauteurParagraphe1(hauteurParagraphe1);
        setHauteurParagraphe2(hauteurParagraphe2);
        setImage1(nouvelleImg1);
        setImage2(nouvelleImg2);
    }

    @Override
    public int getNbDePages() {
        return modificateurDePage.getNbDePages();
    }

    @Override
    public void setALaPage(int numeroDePage) {
        modificateurDePage.setALaPage(numeroDePage);
    }

    public PageInstruction(Manuel parent, int largeurAutorise) {
        super(parent.getContext());
        int hauteurImages = largeurAutorise / 2;

        FrameLayout.LayoutParams layoutParamsPage = new LayoutParamsDeBase_pourFrameLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintLayout.LayoutParams.PARENT_ID);
        this.setLayoutParams(layoutParamsPage);

        this.paragraphe1 = new ParagrapheInstruction(this, 0);
        this.addView(paragraphe1);

        this.image1 = new ImageInstruction(this, hauteurImages, 0);
        this.addView(image1);

        this.paragraphe2 = new ParagrapheInstruction(this, hauteurImages);
        this.addView(paragraphe2);

        this.image2 = new ImageInstruction(this, hauteurImages, hauteurImages);
        this.addView(image2);
    }
}
