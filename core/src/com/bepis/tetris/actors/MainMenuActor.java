package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;

/**
 * Created by Parker on 4/19/2017.
 */

public class MainMenuActor extends Actor {

    private float statetime;

    private Animation<TextureRegion> back;

    private TextureRegion overlay;
    private TextureRegion title;
    private TextureRegion tab;

    public MainMenuActor(Assets assets) {
        statetime = 0;

        // Create the animation and set to loop
        back = new Animation<TextureRegion>(.25f, assets.marathonBG);
        back.setPlayMode(Animation.PlayMode.LOOP);

        overlay = assets.overlay;

        title = assets.mainMenuTitle;

        tab = assets.mainMenuTab;

        setX(0);
        setY(0);
        setWidth(360);
        setHeight(640);
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

        // draw animated background
        for(int y = 0; y < 27; y++)
            for(int x = 0; x < 15; x++)
                batch.draw(back.getKeyFrame(statetime), getX()+24*x, getY()+24*y);

        // draw overlay BG
        batch.draw(overlay, 0, 0);

        // Draw title
        batch.draw(title,36, 448);

        // Draw box for buttons
        batch.draw(tab, 48, 36,  256, 412);
    }
}
