package com.entity.fractured;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Arrays;

public class FracturedSettings {
    String version = "1.0";
    String preferencesName = "fractured-preferences";
    int screenCounter = 0;
    boolean debugLogging = true;
    boolean debugGUI = false;
    float width = 0f, height = 0f;
    float aspectRatio;

    // 0 = auto; 1 = force standard; 2 = force large
    int guiMode = 0;
    float zoomSpeed = 0.5f;
    float uiPadding = 5f, uiPaddingLarge = 10f;
    float uiWidth = 50f, uiWidthLarge = 80f;


    int fractalType = 0, fractalColor = 1;
    int previewSetting = 2, renderSetting = 3;

    FracturedSettings() {
        Arrays.sort(fractalColors);

        loadFromShared();
    }

    public void updateDisplaySettings() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        aspectRatio = width / height;
    }

    public void saveToShared() {
        Preferences prefs = Gdx.app.getPreferences(preferencesName);
        prefs.clear();

        prefs.putString("version", version);
        prefs.putInteger("screenCounter", screenCounter);
        prefs.putInteger("guiMode", guiMode);
        prefs.putInteger("previewSetting", previewSetting);
        prefs.putInteger("renderSetting", renderSetting);
        prefs.flush();

        Gdx.app.debug("fractured!", "saved shared settings");
    }

    public void loadFromShared() {
        Preferences prefs = Gdx.app.getPreferences(preferencesName);

        if (prefs.contains("version")) {
            if (prefs.getString("version").equals(version)) {
                screenCounter = prefs.getInteger("screenCounter", screenCounter);
                guiMode = prefs.getInteger("guiMode", guiMode);
                previewSetting = prefs.getInteger("previewSetting", previewSetting);
                renderSetting = prefs.getInteger("renderSetting", renderSetting);
            } else {
                Gdx.app.debug("fractured!", "ignoring an outdated version of shared settings");
            }
        }
    }

    float[] renderQualities = {8f, 4f, 2f, 1.25f, 1f, 0.8f, 0.66f, 0.5f};
    String[] renderQualityNames = {"12.5%", "25%", "50%", "80%", "100%", "125%", "150%", "200%"};

    String[] fractalTypes = {"fractals/julia_z2.frag",
            "fractals/julia_z3.frag",
            "fractals/julia_expz2.frag",
            "fractals/julia_expz3.frag",
    };

    String[] fractalNames = {"z^2 + c",
            "z^3 + c",
            "exp(z^2) - c",
            "exp(z^3) - c"};

    String[] fractalColors = {"gradients/aneurism.png",
            "gradients/full_spectrum.png",
            "gradients/incandescent.png",
            "gradients/metallic_something.png",
            "gradients/purples.png",
            "gradients/shadows1.png",
            "gradients/shadows3.png",
            "gradients/skyline.png",
            "gradients/three_bars_sin.png",
            "gradients/tropical_colors.png",
            "gradients/yellow_contrast.png",
    };
}
