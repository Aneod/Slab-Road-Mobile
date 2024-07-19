package com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.Tools.LayoutParams.LayoutParamsDeBase_pourFrameLayout;

import java.util.List;

public class SettingsPanel extends FrameLayout {

    private static final int LEFT_RIGHT_MARGINS = 25;
    private static final int COMPONENTS_HEIGHT = 100;
    private static final int HEIGHT_BETWEEN_COMPONENTS = 15;

    public SettingsPanel(@NonNull Context context) {
        super(context);
    }

    public SettingsPanel(@NonNull PopUp popUp, @NonNull List<SettingComponent> components) {
        super(popUp.getContext());

        int width = popUp.getLargeur() - 2 * LEFT_RIGHT_MARGINS;
        int height = components.size() * (COMPONENTS_HEIGHT + HEIGHT_BETWEEN_COMPONENTS);

        FrameLayout.LayoutParams layoutParams = new LayoutParamsDeBase_pourFrameLayout(
                width, height, LEFT_RIGHT_MARGINS, 0
        );
        this.setLayoutParams(layoutParams);

        int topMargin = 0;
        for(SettingComponent component : components) {
            String title = component.title;
            if(component instanceof CursorComponent) {
                float startValue = ((CursorComponent) component).startValue;
                Consumer<Float> consumer = ((CursorComponent) component).consumer;
                int color = ((CursorComponent) component).color;
                com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.CursorComponent cursor = new com.example.veritablejeu.PopUp.ContenuPopUp.SettingsPanel.CursorComponent(
                        getContext(), title,
                        width, COMPONENTS_HEIGHT, 0, topMargin,
                        startValue, consumer, color
                );
                addView(cursor);
            }

            else if(component instanceof OnOffButtonComponent) {
                boolean startState = ((OnOffButtonComponent) component).startState;
                Runnable runnableA = ((OnOffButtonComponent) component).runnableA;
                Runnable runnableB = ((OnOffButtonComponent) component).runnableB;
                OnOffComponent onOffComponent = new OnOffComponent(
                        getContext(), title,
                        width, COMPONENTS_HEIGHT, 0, topMargin,
                        startState,
                        "ON", runnableA,
                        "OFF", runnableB
                );
                addView(onOffComponent);
            }

            topMargin += COMPONENTS_HEIGHT + HEIGHT_BETWEEN_COMPONENTS;
        }
    }

    /**
     * The basic setting component. Take a title and nothing more. Every setting components have a
     * title at their left. But this class can be extend for add somes tools at the right, like a
     * cursor or an on/off button, etc.
     */
    public static class SettingComponent {

        protected final String title;

        public SettingComponent(String title) {
            this.title = title;
        }

        public FrameLayout getUICOmponent() {
            return null;
        }
    }

    /**
     * The cursor component.
     */
    public static class CursorComponent extends SettingComponent {

        private final float startValue;
        private final Consumer<Float> consumer;
        private final int color;

        public CursorComponent(
                String title, Consumer<Float> consumer, @FloatRange(from = 0.0f, to = 1.0f) float startValue) {
            this(title, consumer, startValue, Color.BLACK);
        }

        public CursorComponent(String title, Consumer<Float> consumer, float startValue, int color) {
            super(title);
            this.startValue = startValue;
            this.consumer = consumer;
            this.color = color;
        }

        public FrameLayout getUICOmponent() {
            return null;
        }
    }

    /**
     * The on/off button component.
     */
    public static class OnOffButtonComponent extends SettingComponent {

        private final boolean startState;
        private final Runnable runnableA;
        private final Runnable runnableB;

        public OnOffButtonComponent(String title, Runnable runnableA, Runnable runnableB, boolean startState) {
            super(title);
            this.startState = startState;
            this.runnableA = runnableA;
            this.runnableB = runnableB;
        }

        public FrameLayout getUICOmponent() {
            return null;
        }
    }
}
