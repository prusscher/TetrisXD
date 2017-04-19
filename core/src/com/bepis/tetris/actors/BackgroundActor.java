package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

public class BackgroundActor extends Actor {

    Animation<TextureRegion> basic;

    float statetime = 0;

    public BackgroundActor(Assets assets, int mode) {
        // Create the animation and set to loop
        basic = new Animation<TextureRegion>(.25f, assets.marathonBG);
        basic.setPlayMode(Animation.PlayMode.LOOP);

        setX(0);
        setY(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        statetime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        // hack to tile the background
        for(int y = 0; y < 27; y++)
            for(int x = 0; x < 15; x++)
                batch.draw(basic.getKeyFrame(statetime), getX()+24*x, getY()+24*y);

    }
}
