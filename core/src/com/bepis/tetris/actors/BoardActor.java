package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

public class BoardActor extends Actor {

    // Frame of the board
    private TextureRegion frame;

    // Shape renderer for drawing the background of the play area
    private TextureRegion bg;

    // Colors for the background go here
//    private Color color;
//    private final Color yellow = new Color((248/256), (232/256), 0, 1);

    private int mode = 0;

    // This is where the actual piece stuff goes


    public BoardActor(Assets assets, int mode) {

        // Set mode and check to be sure we aren't out of area
        this.mode = mode;
        if(mode < 0 || mode > 2)
            this.mode = 0;

        // Set the border tile texture and background texture
        frame = assets.borderTile;
        bg = assets.boardBackgrounds[0];

        // Set the location
        setX(36);
        setY(0);
        setWidth(288);
        setHeight(480);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        // Draw the background
        batch.draw(bg, getX()+24, getY()+24, 240, 432);

        // Draw the border frame
        for(int x = 0; x < 12; x++) {  // Draw the top and bottom
            batch.draw(frame, getX() + (24 * x),  getY());
            batch.draw(frame, getX() + (24 * x),  getY() + 456);
        }
        for(int y = 0; y < 18; y++) {  // Draw the sides
            batch.draw(frame, getX(),       getY() + ((y + 1) * 24));
            batch.draw(frame, getX() + 264, getY() + ((y + 1) * 24));
        }



        // Draw the pieces

    }
}
