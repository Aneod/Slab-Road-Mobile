package com.example.veritablejeu.PopUp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ContenuPopUp.ContenuPopUp;
import com.example.veritablejeu.PopUp.ContenuPopUp.Manuel.Manuel;
import com.example.veritablejeu.PopUp.ContenuPopUp.Message.Message;
import com.example.veritablejeu.PopUp.PopUpInitiale.PopUpInitiale;
import com.example.veritablejeu.OutilsEnEnum.Elevation;
import com.example.veritablejeu.R;

import org.jetbrains.annotations.Contract;

public class PopUp extends PopUpInitiale implements IPopUp {

    private static PopUp instance;
    private final ObjectAnimator animationOuverture;
    private final ObjectAnimator animationFermeture;
    private ContenuPopUp contenuActuel;
    private boolean animationEnCours = false;

    public static PopUp getInstance(@NonNull Context activity) {
        if(instance == null) {
            instance = new PopUp(activity);
        }
        return instance;
    }

    private PopUp(@NonNull Context context) {
        super(context);
        if(context instanceof AppCompatActivity) {
            ConstraintLayout constraintLayout = ((AppCompatActivity) context).findViewById(R.id.main);
            constraintLayout.addView(this);
        }
        croixPopUp.setOnClickListener(v -> cacher());
        setOnClickListener(v -> {});
        setElevation(Elevation.PopUp.getElevation());
        animationOuverture = creationAnimationOuverture();
        animationFermeture = creationAnimationFermeture();
    }

    @NonNull
    @Contract(" -> new")
    private ObjectAnimator creationAnimationOuverture() {
        Runnable startEffectDeOuverture = () -> {
            setVisibility(VISIBLE);
            animationEnCours = true;
        };
        Runnable endEffectDeOuverture = () -> animationEnCours = false;
        return new AnimationFonduePourPopUp(
                this, 10, 0.0f, 1.0f, 400, startEffectDeOuverture, endEffectDeOuverture
        ).getObjectAnimator();
    }

    @NonNull
    @Contract(" -> new")
    private ObjectAnimator creationAnimationFermeture() {
        Runnable startEffectDeFermeture = () -> animationEnCours = true;
        Runnable endEffectDeFermeture = () -> {
            animationEnCours = false;
            setVisibility(GONE);
        };
        return new AnimationFonduePourPopUp(
                this, -10, 1.0f, 0.0f, 300, startEffectDeFermeture, endEffectDeFermeture
        ).getObjectAnimator();
    }

    @Override
    public void setConstraintLayout(@NonNull AppCompatActivity activity) {
        ViewParent parent = getParent();
        ConstraintLayout constraintLayout = activity.findViewById(R.id.main);
        if(parent instanceof ConstraintLayout) {
            ((ConstraintLayout) parent).removeView(this);
            constraintLayout.addView(this);
        }
    }

    @Override
    public void montrer() {
        if(animationEnCours) return;
        animationOuverture.start();
    }

    @Override
    public void cacher() {
        if(animationEnCours) return;
        animationFermeture.start();
    }

    @Override
    public boolean isVisible() {
        return getVisibility() == VISIBLE;
    }

    @Override
    public ContenuPopUp getContenu() {
        return contenuActuel;
    }

    @Override
    public void setContenu(@NonNull ContenuPopUp contenuPopUp) {
        if(animationEnCours) return;
        removeView(contenuActuel);
        contenuActuel = contenuPopUp;
        addView(contenuActuel);
        setTitre(contenuPopUp.getTitre());
        int hauteurQuestion = contenuPopUp.getHauteurTotale();
        setHauteurInitiale_plus_autre(hauteurQuestion);
        montrer();
    }

    public void showMessage(String titre, String texte, int duration) {
        Message message = new Message(this, titre, texte, duration);
        setContenu(message);
    }

    @Override
    public void afficherManuel() {
        setContenu(Manuel.getInstance(this));
    }

}
