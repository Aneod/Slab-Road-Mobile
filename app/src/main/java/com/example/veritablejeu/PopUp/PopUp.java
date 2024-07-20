package com.example.veritablejeu.PopUp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.Manuel.Manuel;
import com.example.veritablejeu.PopUp.PopUpComponent.PopUpComponent;
import com.example.veritablejeu.PopUp.PopUpComponent.ComposedComponents.Question;
import com.example.veritablejeu.PopUp.PopUpComponent.InlineComponents.SimpleText;
import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.R;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.Tools.SimpleBackground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("ViewConstructor")
public class PopUp extends FrameLayout implements IPopUp {

    private static final int HORIZONTAL_MARGINS = 50;
    private static final int VERTICAL_MARGINS = 175;
    private static final int MAX_HEIGHT = ScreenUtils.getScreenHeight() - 2 * VERTICAL_MARGINS;
    private static final int INITIAL_HEIGHT = 90;
    private static final int HEIGHT_BETWEEN_COMPONENTS = 30;
    private static final int BORDER_WIDTH = 5;
    private static final int SHOW_ANIMATION_DURATION = 400;
    private static final int HIDE_ANIMATION_DURATION = 300;

    private static PopUp instance;
    private final List<PopUpComponent> contents = new ArrayList<>();
    private final PopUpTitle title;
    public final PopUpCross cross;
    private final int largeur;

    private PopUp(@NonNull AppCompatActivity activity) {
        super(activity);
        setConstraintLayout(activity);

        largeur = ScreenUtils.getScreenWidth() - 2 * HORIZONTAL_MARGINS;

        LayoutParamsDeBase_pourConstraintLayout layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(largeur, INITIAL_HEIGHT, HORIZONTAL_MARGINS, VERTICAL_MARGINS);
        this.setLayoutParams(layoutParams);

        GradientDrawable drawable = SimpleBackground.create(Color.WHITE, Color.BLACK, BORDER_WIDTH);
        setBackground(drawable);

        title = new PopUpTitle(this);
        addView(title);

        cross = new PopUpCross(this);
        addView(cross);

        setOnClickListener(v -> {});
        setElevation(Elevation.PopUp.getElevation());
    }

    public static PopUp getInstance(@NonNull AppCompatActivity activity) {
        if(instance == null) {
            instance = new PopUp(activity);
        }
        return instance;
    }

    @Override
    public void setConstraintLayout(@NonNull AppCompatActivity activity) {
        ViewParent parent = getParent();
        if(parent instanceof ConstraintLayout) {
            ((ConstraintLayout) parent).removeView(this);
        }
        ConstraintLayout constraintLayout = activity.findViewById(R.id.main);
        constraintLayout.addView(this);
    }

    @Override
    public int get_width() {
        return largeur;
    }

    @Override
    public int getInitialHeight() { return INITIAL_HEIGHT; }

    @Override
    public int getBORDER_WIDTH() {
        return BORDER_WIDTH;
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f, 1.0f)
        );
        objectAnimator.setDuration(SHOW_ANIMATION_DURATION);
        objectAnimator.start();
    }

    @Override
    public void hide() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                this,
                PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0.0f)
        );
        objectAnimator.setDuration(HIDE_ANIMATION_DURATION);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });
        objectAnimator.start();
    }

    public void setContent(String title, @NonNull PopUpComponent... contents) {
        setTitle(title);
        clear();
        Arrays.stream(contents)
                .sequential()
                .forEach(this::addContent);
        show();
    }

    public void addContent(PopUpComponent popUpContent) {
        if(popUpContent == null || popUpContent.getParent() != null) return;
        ViewGroup.LayoutParams layoutParams = popUpContent.getLayoutParams();
        if(layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).topMargin = get_Height();
        }
        popUpContent.setLayoutParams(popUpContent.getLayoutParams());
        addView(popUpContent);
        contents.add(popUpContent);
        addHeight(popUpContent.getLayoutParams().height);
    }

    public void clear() {
        contents.forEach(this::removeView);
        contents.clear();
        resetHeight();
    }

    public void setTitle(String nouveauTitre) {
        title.setTexte(nouveauTitre);
    }

    public int get_Height() {
        return getLayoutParams().height;
    }

    private void setHeight(int height) {
        getLayoutParams().height = Math.max(0, height);
    }

    public void refreshHeight() {
        resetHeight();
        for(PopUpComponent popUpComponent : contents) {
            ViewGroup.LayoutParams layoutParams = popUpComponent.getLayoutParams();
            if(layoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams).topMargin = get_Height();
            }
            addHeight(popUpComponent.getLayoutParams().height);
        }
    }

    private void addHeight(int height) {
        int newHeight = get_Height() + height + HEIGHT_BETWEEN_COMPONENTS;
        setHeight(Math.min(newHeight, MAX_HEIGHT));
    }

    private void resetHeight() {
        setHeight(INITIAL_HEIGHT);
    }

    public void showQuestion(String title, String text, String reponseA, @Nullable Runnable runnableA, String reponseB, @Nullable Runnable runnableB) {
        Question question = new Question(this, text, reponseA, runnableA, reponseB, runnableB);
        setContent(title, question.getSimpleText(), question.getButtons());
    }

    public void showMessage(String title, String text) {
        SimpleText affirmation = new SimpleText(this, text);
        setContent(title, affirmation);
    }

    @Override
    public void showManual() {
        setContent("HOW TO PLAY", Manuel.getInstance(this));
    }

}
