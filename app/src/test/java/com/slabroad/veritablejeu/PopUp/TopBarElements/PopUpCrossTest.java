package com.slabroad.veritablejeu.PopUp.TopBarElements;

import static org.junit.Assert.*;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.test.core.app.ApplicationProvider;

import com.slabroad.veritablejeu.PopUp.PopUp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class PopUpCrossTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private final PopUpCross cross = new PopUpCross(popUp);

    @Test
    public void crossHaveAImageView() {
        int childNumber = cross.getChildCount();
        assertEquals(1, childNumber);

        View onlyView = cross.getChildAt(0);
        assertTrue(onlyView instanceof ImageView);
    }

    @Test
    public void crossSize() {
        int actual = cross.getLayoutParams().width;
        int expected = PopUp.getInitialHeight();
        assertEquals(expected, actual);
    }

    @Test
    public void crossImageIsASquare() {
        View onlyView = cross.getChildAt(0);
        int actualWidth = onlyView.getLayoutParams().width;
        int actualHeight = onlyView.getLayoutParams().height;
        assertEquals(actualWidth, actualHeight);
    }

    @Test
    public void crossImageSize() {
        View onlyView = cross.getChildAt(0);
        int topBarHeight = PopUp.getInitialHeight();
        int actual = onlyView.getLayoutParams().width;
        int expected = (int) (topBarHeight * PopUpCross.getCrossHeightPercentage());
        assertEquals(expected, actual);
    }

}