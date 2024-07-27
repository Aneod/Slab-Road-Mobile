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

@SuppressLint("ViewConstructor")
public class DemanderUnUserName extends FrameLayout {

    private final MainActivity mainActivity;
    private final AppCompatButton buttonSubmit;
    private final AppCompatEditText editTextUserInput;

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
        String texte = "How do you want to be called ? " +
                "Each user has a unique nickname, so you won't be able to change it later.";
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
        editTextUserInput = new AppCompatEditText(mainActivity);
        editTextUserInput.setHint("Your nickname...");

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
        buttonSubmit.setOnClickListener(v -> submit());
        this.addView(buttonSubmit);
    }

    private void submit() {
        buttonSubmit.setText("...");
        Editable editable = editTextUserInput.getText();
        String pseudonym = editable == null ? "" : editable.toString();
        DatabaseFirestorePseudo.add(pseudonym, new DatabaseFirestorePseudo.Callback() {
            @Override
            public void onSuccess() {
                UserData.saveUsername(mainActivity, pseudonym);
                mainActivity.userNameValide();
            }

            @Override
            public void invalidePseudonym() {
                submitUncorrect("Uncorrect pseudnym. Please try another one.");
            }

            @Override
            public void alreadyTaken() {
                submitUncorrect("Pseudonym already taken. Please try another one.");
            }

            @Override
            public void disconnected() {
                submitUncorrect("Unstable connection. Please retry later.");
            }
        });
    }

    private void submitUncorrect(String message) {
        String texteRetry = "Retry";
        buttonSubmit.setText(texteRetry);
        Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
    }

}
