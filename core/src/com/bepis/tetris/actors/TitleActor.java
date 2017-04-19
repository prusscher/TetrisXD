package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

/**
 * The top title for the game screen
 * Displays the current game mode and thats about it
 */
public class TitleActor extends Actor {
    TextureRegion background;
    TextureRegion[] titles;

    int mode = 0; // Marathon = 0, Ultra = 1, 40 Lines = 2

    /**
     * Create a new title. Auto places itself, dont change x and y babe
     * @param assets The assets object
     * @param mode The current game mode (See GameMode)
     */
    public TitleActor(Assets assets, int mode) {
        // Set mode and check to be sure we aren't out of area
        this.mode = mode;
        if(mode < 0 || mode > 2)
            this.mode = 0;

        // Set the title texturs
        titles = new TextureRegion[3]; // Dumb way of doing this because arrays are no fun
        titles[0] = assets.titles[0]; // Marathon, Ultra, and 40 Lines
        titles[1] = assets.titles[1];
        titles[2] = assets.titles[2];

        // Set the background of the title
        background = new TextureRegion(assets.titleBackground);

        // Set Default Postion (Top of the screen) and size
        setX(0);
        setY(616);
        setWidth(360);
        setHeight(24);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        batch.draw(background, getX(), getY());
        batch.draw(titles[mode], getX()+16, getY());
    }
}
