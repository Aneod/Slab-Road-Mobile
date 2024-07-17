package com.example.veritablejeu.LittleWindow.WindowProposal;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.veritablejeu.LittleWindow.LittleWindow;

import org.jetbrains.annotations.Contract;

/**
 * This class make a combination of text, runnable and others parameters who compose a proposal for the little window.
 */
public class WindowProposal {

    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final boolean DEFAULT_AUTOCLOSE = false;
    static int getDefaultTextColor() {
        return WindowProposal.DEFAULT_TEXT_COLOR;
    }
    static boolean isDefaultAutoclose() {
        return WindowProposal.DEFAULT_AUTOCLOSE;
    }

    private final String text;
    private final Runnable runnable;
    private final int textColor;
    private final boolean autoClose;

    public String getText() {
        return text;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public int getTextColor() {
        return textColor;
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    /**
     * The close proposal is show for each little window.
     *
     * @param littleWindow the window who is closed by this proposal.
     * @return a proposal for close the given little window.
     */
    @NonNull
    @Contract(value = "_ -> new", pure = true)
    public static WindowProposal getCloseProposal(LittleWindow littleWindow) {
        Runnable runnable = (littleWindow == null) ? () -> {
        } : littleWindow::hide;
        return new WindowProposal("Close", runnable, Color.BLUE);
    }

    public WindowProposal(String text, Runnable runnable) {
        this(text, runnable, DEFAULT_TEXT_COLOR, DEFAULT_AUTOCLOSE);
    }

    public WindowProposal(String text, Runnable runnable, int textColor) {
        this(text, runnable, textColor, DEFAULT_AUTOCLOSE);
    }

    public WindowProposal(String text, Runnable runnable, boolean autoClose) {
        this(text, runnable, DEFAULT_TEXT_COLOR, autoClose);
    }

    public WindowProposal(String text, Runnable runnable, int textColor, boolean autoClose) {
        this.text = text;
        this.runnable = runnable;
        this.textColor = textColor;
        this.autoClose = autoClose;
    }

    public TextView generateTextViewForWindow(@NonNull LittleWindow littleWindow) {
        TextView textView = new TextView(littleWindow.getContext());
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(v -> runnableOfProposal(littleWindow));
        return textView;
    }

    private void runnableOfProposal(LittleWindow littleWindow) {
        runnable.run();
        if(autoClose) {
            littleWindow.hide();
        }
    }

}