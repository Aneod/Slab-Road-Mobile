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

import com.example.veritablejeu.LittleWindow.WindowProposal.WindowProposal;
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
    public static int getWIDTH() {
        return WIDTH;
    }
    public static int getProposalHeight() {
        return PROPOSAL_HEIGHT;
    }


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
    public void setPosition(Point position) {
        setHorizontalPosition(position);
        setVerticalPosition(position);
    }

    @NonNull
    @Contract("_ -> new")
    private ConstraintLayout.LayoutParams generateConstraintLayoutByTopMargin(int topMargin) {
        return new LayoutParamsDeBase_pourConstraintLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT, PROPOSAL_HEIGHT, 0, topMargin
        );
    }

    private void addProposalAtHeight(WindowProposal windowProposal, int topMargin) {
        if(windowProposal == null) return;
        TextView textView = windowProposal.generateTextViewForWindow(this);
        ConstraintLayout.LayoutParams layoutParams = generateConstraintLayoutByTopMargin(topMargin);
        textView.setLayoutParams(layoutParams);
        addView(textView);
    }

    private void addProposals(List<WindowProposal> list) {
        if(list == null) return;
        int topMargin = 0;
        for(WindowProposal windowProposal : list) {
            addProposalAtHeight(windowProposal, topMargin);
            topMargin += PROPOSAL_HEIGHT;
        }
    }

    @NonNull
    private List<WindowProposal> getCloseProposal() {
        List<WindowProposal> oneChoiceList = new ArrayList<>();
        WindowProposal closeProposal = WindowProposal.getCloseProposal(this);
        oneChoiceList.add(closeProposal);
        return oneChoiceList;
    }

    @NonNull
    private List<WindowProposal> addCloseProposalToList(List<WindowProposal> list) {
        List<WindowProposal> closeProposal = getCloseProposal();
        if(list == null)
            return closeProposal;
        list.addAll(closeProposal);
        return list;
    }

    private void setContent(List<WindowProposal> list) {
        List<WindowProposal> listWithClose = addCloseProposalToList(list);
        addProposals(listWithClose);
        int newHeight = listWithClose.size() * PROPOSAL_HEIGHT;
        setHeight(newHeight);
    }

    @Override
    public void set(List<WindowProposal> list) {
        clear();
        setContent(list);
        show();
    }
}
