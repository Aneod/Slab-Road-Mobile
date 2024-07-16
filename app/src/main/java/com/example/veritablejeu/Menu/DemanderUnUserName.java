package com.example.veritablejeu.Menu;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.BackEnd.DataBases.FireStore.Pseudo.DatabaseFirestorePseudo;
import com.example.veritablejeu.BackEnd.DataBases.Local.UserData;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.CouleurDuJeu;
import com.example.veritablejeu.Tools.ScreenUtils;

import org.jetbrains.annotations.Contract;

@SuppressLint("ViewConstructor")
public class DemanderUnUserName extends FrameLayout {

    private final MainActivity mainActivity;
    private final AppCompatButton buttonSubmit;

    public DemanderUnUserName(@NonNull MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;

        int margesGD = 50;
        int margesHB = 100;

        // ConstraintLayout parameters
        ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(constraintLayoutParams);

        // TextView
        AppCompatTextView textView = new AppCompatTextView(mainActivity);
        String texte = "Bienvenue sur Slab Road !\n\n" +
                "Avant d'aller plus loin, veuillez nous donner votre pseudonyme (Vous ne pourrez pas le changer !) :";
        textView.setText(texte);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setId(generateViewId());

        ConstraintLayout.LayoutParams layoutParamsText = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsText.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsText.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsText.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsText.setMargins(margesGD, margesHB, margesGD, 0);
        textView.setLayoutParams(layoutParamsText);
        this.addView(textView);

        // EditText
        AppCompatEditText editTextUserInput = new AppCompatEditText(mainActivity);
        editTextUserInput.setHint("Votre pseudonyme...");

        ConstraintLayout.LayoutParams layoutParamsEditText = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsEditText.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsEditText.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParamsEditText.setMargins(margesGD, 400, margesGD, 0);
        editTextUserInput.setLayoutParams(layoutParamsEditText);
        this.addView(editTextUserInput);

        // FrameLayout (Button)
        buttonSubmit = new AppCompatButton(mainActivity);
        buttonSubmit.setBackgroundColor(Color.LTGRAY);
        String texteBoutonSubmit = "Valider";
        buttonSubmit.setText(texteBoutonSubmit);
        buttonSubmit.setTextColor(Color.BLACK);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setStroke(1, Color.BLACK);
        drawable.setColor(CouleurDuJeu.Vert.Int());
        buttonSubmit.setBackground(drawable);

        int widthBoutonSubmit = 200;
        int heightBoutonSubmit = 100;
        int leftMarginBoutonSubmit = (ScreenUtils.getScreenWidth() - widthBoutonSubmit) / 2;
        ConstraintLayout.LayoutParams layoutParamsButton = new LayoutParamsDeBase_pourConstraintLayout(
                widthBoutonSubmit, heightBoutonSubmit, leftMarginBoutonSubmit, 550
        );
        buttonSubmit.setLayoutParams(layoutParamsButton);
        this.addView(buttonSubmit);

        buttonSubmit.setOnClickListener(v -> {
            buttonSubmit.setText("...");
            String texteRetry = "Réessayer";

            Editable editable = editTextUserInput.getText();
            if(editable == null) {
                Toast.makeText(mainActivity, "Veuillez entrer un pseudonyme", Toast.LENGTH_SHORT).show();
                buttonSubmit.setText(texteRetry);
                return;
            }

            String userInput = editable.toString();
            if(userInput.isEmpty()) {
                Toast.makeText(mainActivity, "Veuillez entrer un pseudonyme", Toast.LENGTH_SHORT).show();
                buttonSubmit.setText(texteRetry);
                return;
            }

            verifierPseudo(userInput);
        });
    }

    private void verifierPseudo(String pseudo) {
        Pseudonym pseudonym = new Pseudonym(pseudo);
        DatabaseFirestorePseudo.add(pseudonym, isSuccess -> {
            if(isSuccess) {
                if(mainActivity != null) {
                    UserData.saveUsername(mainActivity, pseudo);
                    ((MainActivity) mainActivity).userNameValide();
                }
            }
            else {
                String texteRetry = "Réessayer";
                buttonSubmit.setText(texteRetry);
            }
        });
    }

    /**
     * This class should be developped (and tested), with a max length, some unauthorized characters, etc.
     */
    public static class Pseudonym {
        private static final String DEFAULT_PSEUDONYM = "Anonymous";
        private final String pseudo;

        @NonNull
        @Contract(value = "!null -> param1", pure = true)
        private String getCorrectedPseudo(String pseudo) {
            return pseudo == null ? DEFAULT_PSEUDONYM : pseudo;
        }

        public Pseudonym(String pseudo) {
            this.pseudo = getCorrectedPseudo(pseudo);
        }

        public String getPseudo() {
            return pseudo;
        }
    }
}
