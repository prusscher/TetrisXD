package com.bepis.tetris.actors;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

public class StatsActor extends Actor {

    private int mode;
//    private BitmapFont font;    // THIS WILL BE SETUP LATER WITH THE ACTUAL FONT FROM THE GAME

    private TextureRegion tab;

    public StatsActor(Assets assets, int mode) {
        tab = assets.statsTab;
        setX(0);
        setY(494);
        setWidth(168);
        setHeight(32);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        // Bottom
        batch.draw(tab, getX(), getY());
        // Draw text here

        // Middle
        batch.draw(tab, getX(), getY()+40);
        // Draw text here

        // Top
        batch.draw(tab, getX(), getY()+80);
        // Draw text here
    }
}
