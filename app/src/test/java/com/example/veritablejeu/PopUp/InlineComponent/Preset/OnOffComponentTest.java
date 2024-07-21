package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Button.Button;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.OnOffButton.OnOffButton;
import com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents.Text;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class OnOffComponentTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private int WITNESS = 0;
    private final OnOffComponent onOffComponent = new OnOffComponent(
            popUp, "TITLE", false,
            "YES", () -> WITNESS = 1,
            "NO", () -> WITNESS = 2
    );

    @Test
    public void testChildNumber() {
        int actual = onOffComponent.getChildCount();
        assertEquals(2, actual);
    }

    @Test
    public void childsTypeCorresponding() {
        View text = onOffComponent.getChildAt(0);
        View onOffButton = onOffComponent.getChildAt(1);
        if(!(text instanceof Text)) {
            fail();
        }
        if(!(onOffButton instanceof OnOffButton)) {
            fail();
        }
    }

    @Test
    public void testTextContent() {
        View text = onOffComponent.getChildAt(0);
        if(!(text instanceof Text)) {
            fail();
        }
        assertEquals("TITLE", ((Text) text).getText());
        assertNotEquals("TILE", ((Text) text).getText());
    }

    @Test
    public void testTextWidthDistribution() {
        View text = onOffComponent.getChildAt(0);
        if(!(text instanceof Text)) {
            fail();
        }
        int actual = text.getLayoutParams().width;
        int expected = (int) (onOffComponent.getLayoutParams().width * OnOffComponent.getWidthTitledDistribution());
        assertEquals(expected, actual);
    }

    @Test
    public void testButtonCOrresponding() {
        View onOffButton = onOffComponent.getChildAt(1);
        if(!(onOffButton instanceof OnOffButton)) {
            fail();
        }
        OnOffButton expected = onOffComponent.getBouton();
        assertEquals(expected, onOffButton);
    }

    @Test
    public void testButtonStartState() {
        View onOffButton = onOffComponent.getChildAt(1);
        if(!(onOffButton instanceof OnOffButton)) {
            fail();
        }
        assertEquals(Button.getBlackAppearence(), onOffButton.getBackground());
    }

    @Test
    public void testNoRunnable_atStarting() {
        assertEquals(0, WITNESS);
    }

    @Test
    public void setHeight() {
        onOffComponent.setHeight(1000);
        int height = onOffComponent.getLayoutParams().height;
        assertEquals(1000, height);
    }

    @Test
    public void setHeight_buttonTopMargin() {
        onOffComponent.setHeight(1000);
        ViewGroup.LayoutParams layoutParams = onOffComponent.getBouton().getLayoutParams();
        if(layoutParams instanceof FrameLayout.LayoutParams) {
            int actual = ((FrameLayout.LayoutParams) layoutParams).topMargin;
            int expected = (onOffComponent.getLayoutParams().height - Button.getHEIGHT()) / 2;
            assertEquals(expected, actual);
        } else {
            fail();
        }
    }
}