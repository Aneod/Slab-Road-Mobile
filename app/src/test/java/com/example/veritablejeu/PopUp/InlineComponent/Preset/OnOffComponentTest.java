package com.example.veritablejeu.PopUp.InlineComponent.Preset;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.veritablejeu.PopUp.PopUp;
import com.example.veritablejeu.R;

import org.junit.Test;

public class OnOffComponentTest {

    private final PopUp popUp = PopUp.getInstance((Context) ApplicationProvider.getApplicationContext());
    private int WITNESS = 0;
    private final OnOffComponent onOffComponent = new OnOffComponent(
            popUp, "TITLE", false,
            "YES", () -> WITNESS = 1,
            "NO", () -> WITNESS = 2
    );

    @Test
    public void setHeight() {
    }
}