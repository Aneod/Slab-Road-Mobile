package com.example.veritablejeu.LittleWindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.veritablejeu.Tools.Elevation;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourConstraintLayout;
import com.example.veritablejeu.Tools.ScreenUtils;
import com.example.veritablejeu.R;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class LittleWindow extends FrameLayout implements ILittleWindow {

    private static final int WIDTH = 300;
    private static final int PROPOSAL_HEIGHT = 80;

    private final ConstraintLayout.LayoutParams layoutParams =
            new LayoutParamsDeBase_pourConstraintLayout(WIDTH, 0, 0, 0);

    private void initializeView() {
        setLayoutParams(layoutParams);
        setBackgroundColor(Color.WHITE);
        setElevation(Elevation.LittleWindow.getElevation());
    }

    public LittleWindow(@NonNull Context context) {
        super(context);
        initializeView();
    }

    @Override
    public void attachToActivity(AppCompatActivity activity) {
        if(activity == null) return;
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
    }

    private void clear() {
        removeAllViews();
    }

    @Override
    public ConstraintLayout.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    private void setHeight(int hauteurTotale) {
        layoutParams.height = hauteurTotale;
    }

    private boolean onScreenLeftSide(Point point) {
        if(point == null) return false;
        int halfScreenWidth = ScreenUtils.getScreenWidth() / 2;
        return point.x <= halfScreenWidth;
    }

    private boolean onScreenTopSide(Point point) {
        if(point == null) return false;
        int halfScreenHeight = ScreenUtils.getScreenHeight() / 2;
        return point.y <= halfScreenHeight;
    }

    private void setHorizontalPosition(Point point) {
        if(point == null) return;
        int leftMarginOnLeft = point.x - layoutParams.width;
        int leftMarginOnRight = point.x;
        boolean isOnLeftSide = onScreenLeftSide(point);
        layoutParams.leftMargin = isOnLeftSide ? leftMarginOnRight : leftMarginOnLeft;
        setLayoutParams(layoutParams);
    }

    private void setVerticalPosition(Point point) {
        if(point == null) return;
        int topMarginOnTop = point.y - (layoutParams.height + PROPOSAL_HEIGHT);
        int topMarginOnBottom = point.y;
        boolean isOnTopSide = onScreenTopSide(point);
        layoutParams.topMargin = isOnTopSide ? topMarginOnBottom : topMarginOnTop;
        setLayoutParams(layoutParams);
    }

    @Override
    public void setPosition(@NonNull Point position) {
        setHorizontalPosition(position);
        setVerticalPosition(position);
    }

    private void runnableOfProposal(StringRunnablePair stringRunnablePair) {
        if(stringRunnablePair != null) {
            stringRunnablePair.runnable.run();
            if(stringRunnablePair.autoClose) {
                hide();
            }
        }
    }

    @NonNull
    private TextView generateTextViewOfProposal(StringRunnablePair stringRunnablePair) {
        TextView textView = new TextView(getContext());
        if(stringRunnablePair != null) {
            textView.setText(stringRunnablePair.str);
            textView.setTextColor(stringRunnablePair.textColor);
            textView.setGravity(Gravity.CENTER);
            textView.setOnClickListener(v -> runnableOfProposal(stringRunnablePair));
        }
        return textView;
    }

    @NonNull
    @Contract("_ -> new")
    private ConstraintLayout.LayoutParams generateConstraintLayoutByTopMargin(int topMargin) {
        return new LayoutParamsDeBase_pourConstraintLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, PROPOSAL_HEIGHT, 0, topMargin
        );
    }

    private void addProposalAtHeight(@NonNull StringRunnablePair stringRunnablePair, int topMargin) {
        TextView textView = generateTextViewOfProposal(stringRunnablePair);
        ConstraintLayout.LayoutParams layoutParams = generateConstraintLayoutByTopMargin(topMargin);
        textView.setLayoutParams(layoutParams);
        addView(textView);
    }

    private void addProposals(List<StringRunnablePair> list) {
        if(list == null) return;
        int topMargin = 0;
        for(StringRunnablePair stringRunnablePair : list) {
            addProposalAtHeight(stringRunnablePair, topMargin);
            topMargin += PROPOSAL_HEIGHT;
        }
    }

    @NonNull
    private List<StringRunnablePair> getCloseProposal() {
        List<StringRunnablePair> oneChoiceList = new ArrayList<>();
        StringRunnablePair closeProposal = new StringRunnablePair("Close", this::hide, Color.BLUE);
        oneChoiceList.add(closeProposal);
        return oneChoiceList;
    }

    @NonNull
    private List<StringRunnablePair> addCloseProposalToList(List<StringRunnablePair> list) {
        List<StringRunnablePair> closeProposal = getCloseProposal();
        if(list == null)
            return closeProposal;
        list.addAll(closeProposal);
        return list;
    }

    private void setContent(List<StringRunnablePair> list) {
        List<StringRunnablePair> listWithClose = addCloseProposalToList(list);
        addProposals(listWithClose);
        int newHeight = listWithClose.size() * PROPOSAL_HEIGHT;
        setHeight(newHeight);
    }

    @Override
    public void set(List<StringRunnablePair> list) {
        clear();
        setContent(list);
        show();
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
