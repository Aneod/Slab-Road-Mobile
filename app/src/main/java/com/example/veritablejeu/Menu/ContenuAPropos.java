package com.example.veritablejeu.Menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.OutilsEnEnum.ScreenUtils;

public class ContenuAPropos extends FrameLayout {

    public ContenuAPropos(@NonNull Context context) {
        super(context);

        String lien = "https://www.youtube.com/watch?v=pLcw3dK1yU0&ab_channel=Stomp%27sPlaylist";

        AppCompatTextView textView = new AppCompatTextView(context);
        String texte =
                "Slab Road est une application Android indépendante développée par Alexis Guibert.\n\n" +
                        "Crédits Musiques :\n\n" +
                        "Toutes les musiques utilisées proviennent de la vidéo YouTube intitulée " +
                        "\"Lofi Hiphop Mix 2022 | Lofi beats to study | No Copyright Lofi Hip Hop " +
                        "2022\" de la chaîne Youtube Stomp's Playlist disponible " +
                        "via le lien suivant : " + lien +
                        "\n\nÀ ce jour, aucun revenu n'a été généré avec cette application.";

        int start = texte.indexOf(lien);
        int end = start + lien.length();

        SpannableString spannableString = new SpannableString(texte);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(lien));
                context.startActivity(intent);
            }
        };

        // Appliquez le ClickableSpan à la partie cliquable du texte
        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Définissez le texte du TextView avec le SpannableString
        textView.setText(spannableString);

        // Activez le mouvement de lien pour que les liens soient cliquables
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Réglez les autres propriétés du TextView
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(24);

        int margesGD = 50;
        int margesHB = 150;
        int widthTexte = ScreenUtils.getScreenWidth() - 2 * margesGD;
        int heightText = ScreenUtils.getScreenHeight() - 2 * margesHB;
        ConstraintLayout.LayoutParams layoutParamsText = new ConstraintLayout.LayoutParams(widthTexte, heightText);
        layoutParamsText.leftToLeft = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        layoutParamsText.topToTop = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        layoutParamsText.leftMargin = margesGD;
        layoutParamsText.topMargin = margesHB;
        textView.setLayoutParams(layoutParamsText);

        addView(textView);
    }
}
