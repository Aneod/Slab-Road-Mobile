package com.example.veritablejeu.PopUp.InlineComponent.AtomicComponents;

import static org.junit.Assert.*;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.InlineComponent.InlineComponent;
import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ImageTest {

    private InlineComponent inlineComponent;
    private Image image;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        PopUp popUp = PopUp.getInstance(context);
        inlineComponent = new InlineComponent(popUp);

        Drawable drawable = context.getResources().getDrawable(R.drawable.img10, null);
        assertNotNull("Drawable should not be null", drawable);

        image = new Image(inlineComponent, R.drawable.img10); // 1339px x 609px
    }

    @Test
    public void testWidth() {
        int actual = image.getLayoutParams().width;
        int expected = (int) (inlineComponent.getLayoutParams().width * Image.getWidthPorcentage());
        assertEquals(expected, actual);
    }

    @Test
    public void testHeight() {
        int visualWidth = image.getLayoutParams().width;
        int actual = image.getLayoutParams().height;
        int expected = visualWidth * 609 / 1339;
        assertEquals(expected, actual);
    }

    @Test
    public void testLeftMargin() {
        int actual = image.getLayoutParams().leftMargin;
        int expected = (inlineComponent.getLayoutParams().width - image.getLayoutParams().width) / 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testTopMargin() {
        assertEquals(0, image.getLayoutParams().topMargin);
    }

}