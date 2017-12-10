package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import FarmSimulator.StartParams;
import FarmSimulator.FarmSimulator;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = false;
        config.width = StartParams.WIDTH * StartParams.SCALE;
        config.height = StartParams.HEIGHT * StartParams.SCALE;
        config.title = StartParams.TITLE;
        new LwjglApplication(new FarmSimulator(), config);
    }
}
