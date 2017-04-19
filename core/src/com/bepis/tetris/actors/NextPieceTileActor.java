package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

public class NextPieceTileActor extends Actor {

    TextureRegion frame;

    // Next piece stuff will go here eventually, this is just for looks atm

    public NextPieceTileActor(Assets assets) {
        frame = assets.nextPieceFrame;
        setX(200);
        setY(490);
        setWidth(144);
        setHeight(144);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        batch.draw(frame, getX(), getY());

        // Draw next piece here
    }
}
