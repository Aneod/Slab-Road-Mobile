package com.example.veritablejeu.PopUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.PopUp.ComposedComponents.Manual;
import com.example.veritablejeu.PopUp.ComposedComponents.Question;
import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.InlineComponent.Preset.SimpleText;
import com.example.veritablejeu.PopUp.TopBarElements.PopUpCross;
import com.example.veritablejeu.PopUp.TopBarElements.PopUpTitle;
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

    public int getInitialHeight() {
        return INITIAL_HEIGHT;
    }

    public int getBORDER_WIDTH() {
        return BORDER_WIDTH;
    }


    private static PopUp instance;
    private final List<InlineComponent> contents = new ArrayList<>();
    private final PopUpTitle title;
    private final int largeur = ScreenUtils.getScreenWidth() - 2 * HORIZONTAL_MARGINS;

    private void setLayoutParams() {
        LayoutParamsDeBase_pourConstraintLayout layoutParams =
                new LayoutParamsDeBase_pourConstraintLayout(largeur, INITIAL_HEIGHT, HORIZONTAL_MARGINS, VERTICAL_MARGINS);
        this.setLayoutParams(layoutParams);
    }

    private void setBackground() {
        GradientDrawable drawable = SimpleBackground.create(Color.WHITE, Color.BLACK, BORDER_WIDTH);
        setBackground(drawable);
    }

    private PopUp(@NonNull Context context) {
        super(context);
        setLayoutParams();
        setBackground();
        setOnClickListener(v -> {});
        setElevation(Elevation.PopUp.getElevation());

        title = new PopUpTitle(this);
        addView(title);

        PopUpCross cross = new PopUpCross(this);
        addView(cross);
    }

    private PopUp(@NonNull AppCompatActivity activity) {
        this((Context) activity);
        setConstraintLayout(activity);
    }

    public static PopUp getInstance(@NonNull AppCompatActivity activity) {
        if(instance == null) {
            instance = new PopUp(activity);
        }
        return instance;
    }

    public static PopUp getInstance(@NonNull Context context) {
        if(instance == null) {
            instance = new PopUp(context);
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

    public PopUpTitle getTitle() {
        return title;
    }

    public void setTitle(String nouveauTitre) {
        title.setTexte(nouveauTitre);
    }

    @Override
    public void show() {
        PopUpAnimations.show(this);
    }

    @Override
    public void hide() {
        PopUpAnimations.hide(this);
    }

    /// CONTENT MANAGER
    @Override
    public void setContent(String title, @NonNull InlineComponent... contents) {
        setTitle(title);
        clearContents();
        Arrays.stream(contents)
                .sequential()
                .forEach(this::addContent);
        show();
    }

    @Override
    public void addContent(InlineComponent popUpContent) {
        if(popUpContent == null || popUpContent.getParent() != null) return;
        LayoutParams layoutParams = popUpContent.getLayoutParams();
        if(layoutParams != null) {
            layoutParams.topMargin = getLayoutParams().height;
        }
        popUpContent.setLayoutParams(popUpContent.getLayoutParams());
        addView(popUpContent);
        contents.add(popUpContent);
        addHeight(popUpContent.getLayoutParams().height);
    }

    @Override
    public void clearContents() {
        contents.forEach(this::removeView);
        contents.clear();
        resetHeight();
    }


    /// HEIGHT MANAGER
    @Override
    public void refreshHeight() {
        resetHeight();
        for(InlineComponent inlineComponent : contents) {
            LayoutParams layoutParams = inlineComponent.getLayoutParams();
            if(layoutParams != null) {
                layoutParams.topMargin = getLayoutParams().height;
            }
            addHeight(inlineComponent.getLayoutParams().height);
        }
    }

    private void setHeight(int height) {
        getLayoutParams().height = Math.max(0, height);
    }

    private void addHeight(int height) {
        int newHeight = getLayoutParams().height + height + HEIGHT_BETWEEN_COMPONENTS;
        setHeight(Math.min(newHeight, MAX_HEIGHT));
    }

    private void resetHeight() {
        setHeight(INITIAL_HEIGHT);
    }


    /// PRESET
    @Override
    public void showQuestion(String title, String text, String reponseA, @Nullable Runnable runnableA, String reponseB, @Nullable Runnable runnableB) {
        Question question = new Question(this, text, reponseA, runnableA, reponseB, runnableB);
        setContent(title, question.getSimpleText(), question.getButtons());
    }

    @Override
    public void showMessage(String title, String text) {
        SimpleText affirmation = new SimpleText(this, text, Gravity.CENTER);
        setContent(title, affirmation);
    }

    @Override
    public void showManual() {
        Manual.getInstance(this).show();
    }

}
