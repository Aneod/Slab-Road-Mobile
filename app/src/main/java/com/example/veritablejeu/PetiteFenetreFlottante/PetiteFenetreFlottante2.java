package com.example.veritablejeu.PetiteFenetreFlottante;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.R;

import java.util.ArrayList;
import java.util.List;

public class PetiteFenetreFlottante2 extends FrameLayout implements IPetiteFenetreFlottante2 {

    private static final int WIDTH = 300;
    private static final int PROPOSAL_HEIGHT = 80;

    private View objectInMemory;

    public PetiteFenetreFlottante2(@NonNull Context context) {
        super(context);
        initializeView();
    }

    private void initializeView() {
        ConstraintLayout.LayoutParams layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(WIDTH, 0, 0, 0);
        setLayoutParams(layoutParams);
        setBackgroundColor(Color.WHITE);
        setElevation(Elevation.Fenetre.getElevation());
    }

    @Override
    public void attachToActivity(@NonNull AppCompatActivity activity) {
        if(this.getParent() == null) {
            ConstraintLayout container = activity.findViewById(R.id.main);
            container.addView(this);
        }
    }

    private void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(GONE);
        setObjectInMemory(null);
    }

    private void clear() {
        removeAllViews();
    }

    private void setHeight(int hauteurTotale) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.height = hauteurTotale;
    }

    private void setPosition(@NonNull Point position) {
        int halfScreenWidth = ScreenUtils.getScreenWidth() / 2;
        int halfScreenHeight = ScreenUtils.getScreenHeight() / 2;
        boolean tooOnLeft = position.x <= halfScreenWidth;
        boolean tooOnTop = position.y <= halfScreenHeight;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();

        int leftMarginOnLeft = position.x - layoutParams.width;
        int leftMarginOnRight = position.x;
        int topMarginOnTop = position.y - layoutParams.height - PROPOSAL_HEIGHT;
        int topMarginOnBottom = position.y;

        layoutParams.leftMargin = tooOnLeft ? leftMarginOnRight : leftMarginOnLeft;
        layoutParams.topMargin = tooOnTop ? topMarginOnBottom : topMarginOnTop;
        setLayoutParams(layoutParams);
    }

    private void addProposalAtHeight(@NonNull StringRunnablePair stringRunnablePair, int topMargin) {
        TextView textView = new TextView(getContext());
        textView.setText(stringRunnablePair.str);
        textView.setTextColor(stringRunnablePair.textColor);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(v -> {
            stringRunnablePair.runnable.run();
            boolean autoClose = stringRunnablePair.autoClose;
            if(autoClose) hide();
        });

        ConstraintLayout.LayoutParams layoutParamsTextView =
                new LayoutParamsDeBase_pourConstraintLayout(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        PROPOSAL_HEIGHT,
                        0,
                        topMargin
                );
        textView.setLayoutParams(layoutParamsTextView);
        addView(textView);
    }

    private void addProposals(@NonNull List<StringRunnablePair> liste) {
        int topMargin = 0;
        for(StringRunnablePair stringRunnablePair : liste) {
            addProposalAtHeight(stringRunnablePair, topMargin);
            topMargin += PROPOSAL_HEIGHT;
        }
    }

    private void setContent(@NonNull List<StringRunnablePair> liste) {
        clear();
        StringRunnablePair closeProposal = new StringRunnablePair("Close", this::hide, Color.BLUE);
        List<StringRunnablePair> listeWithAutoClose = new ArrayList<>(liste);
        listeWithAutoClose.add(closeProposal);
        addProposals(listeWithAutoClose);
        setHeight(listeWithAutoClose.size() * PROPOSAL_HEIGHT);
    }

    @Override
    public void set(Point position, @Nullable List<StringRunnablePair> liste) {
        if(liste == null) {
            hide();
            return;
        }
        setContent(liste);
        setPosition(position);
        show();
    }

    @Nullable
    @Override
    public View getObjectInMemory() {
        return objectInMemory;
    }

    @Override
    public void setObjectInMemory(View objectInMemory) {
        this.objectInMemory = objectInMemory;
    }


    /**
     * La fenêtre est constituée d'une liste de plusieurs associations de String et de
     * Runnable. La classe interne statique {@link #StringRunnablePair} est une sous-classe
     * utilisable uniquement à l'intérieur de la classe actuelle, et permet justement de
     * créer des associations de String et Runnable.
     */
    public static class StringRunnablePair {

        private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
        private static final boolean DEFAULT_AUTOCLOSE = false;

        private final String str;
        private final Runnable runnable;
        private final int textColor;
        private final boolean autoClose;

        public StringRunnablePair(String str, Runnable runnable) {
            this(str, runnable, DEFAULT_TEXT_COLOR, DEFAULT_AUTOCLOSE);
        }

        public StringRunnablePair(String str, Runnable runnable, int textColor) {
            this(str, runnable, textColor, DEFAULT_AUTOCLOSE);
        }

        public StringRunnablePair(String str, Runnable runnable, boolean autoClose) {
            this(str, runnable, DEFAULT_TEXT_COLOR, autoClose);
        }

        public StringRunnablePair(String str, Runnable runnable, int textColor, boolean autoClose) {
            this.str = str;
            this.runnable = runnable;
            this.textColor = textColor;
            this.autoClose = autoClose;
        }
    }

}
