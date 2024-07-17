package com.example.veritablejeu.LittleWindow.WindowProposal;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.veritablejeu.LittleWindow.LittleWindow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class WindowProposalTest {

    private LittleWindow littleWindow;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.getApplication();
        littleWindow = Mockito.mock(LittleWindow.class);
        Mockito.when(littleWindow.getContext()).thenReturn(context);
    }

    @Test
    public void testDefaultConstructor() {
        Runnable runnable = () -> {};
        WindowProposal proposal = new WindowProposal("Test", runnable);

        assertEquals("Test", proposal.getText());
        assertEquals(runnable, proposal.getRunnable());
        assertEquals(WindowProposal.getDefaultTextColor(), proposal.getTextColor());
        assertEquals(WindowProposal.isDefaultAutoclose(), proposal.isAutoClose());
        assertFalse(proposal.isAutoClose());
    }

    @Test
    public void testConstructorWithTextColor() {
        Runnable runnable = () -> {};
        WindowProposal proposal = new WindowProposal("Test", runnable, Color.RED);

        assertEquals("Test", proposal.getText());
        assertEquals(Color.RED, proposal.getTextColor());
        assertEquals(WindowProposal.isDefaultAutoclose(), proposal.isAutoClose());
        assertFalse(proposal.isAutoClose());
    }

    @Test
    public void testConstructorWithAutoClose() {
        Runnable runnable = () -> {};
        WindowProposal proposal = new WindowProposal("Test", runnable, true);

        assertEquals("Test", proposal.getText());
        assertEquals(WindowProposal.getDefaultTextColor(), proposal.getTextColor());
        assertTrue(proposal.isAutoClose());
    }

    @Test
    public void testFullConstructor() {
        Runnable runnable = () -> {};
        WindowProposal proposal = new WindowProposal("Test", runnable, Color.RED, true);

        assertEquals("Test", proposal.getText());
        assertEquals(runnable, proposal.getRunnable());
        assertEquals(Color.RED, proposal.getTextColor());
        assertTrue(proposal.isAutoClose());
    }

    @Test
    public void testGetCloseProposal() {
        WindowProposal closeProposal = WindowProposal.getCloseProposal(littleWindow);

        assertEquals("Close", closeProposal.getText());
        assertEquals(Color.BLUE, closeProposal.getTextColor());
        assertNotNull(closeProposal.getRunnable());
    }

    @Test
    public void testGenerateTextViewForWindow() {
        Runnable runnable = () -> {};
        WindowProposal proposal = new WindowProposal("Test", runnable, Color.RED, true);
        TextView textView = proposal.generateTextViewForWindow(littleWindow);

        assertNotNull(textView);
        assertEquals("Test", textView.getText().toString());
        assertEquals(Color.RED, textView.getCurrentTextColor());
    }

    @Test
    public void testRunnableOfProposal() {
        Runnable runnable = Mockito.mock(Runnable.class);
        WindowProposal proposal = new WindowProposal("Test", runnable, true);
        proposal.generateTextViewForWindow(littleWindow).performClick();

        verify(runnable).run();
        verify(littleWindow).hide();
    }

    @Test
    public void testRunnableOfProposalWithoutAutoClose() {
        Runnable runnable = Mockito.mock(Runnable.class);
        WindowProposal proposal = new WindowProposal("Test", runnable, false);
        proposal.generateTextViewForWindow(littleWindow).performClick();

        verify(runnable).run();
        verify(littleWindow, Mockito.never()).hide();
    }
}
