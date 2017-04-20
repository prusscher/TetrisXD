package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/20/2017.
 */

public class SelectorActor extends Actor{

    private Animation<TextureRegion> lrAnim;
    private Animation<TextureRegion> udAnim;
    private TextureRegion[] bg;

    private Rectangle lr, ud;

    private int mode = -1;

    private Label levelLabel, highLabel;
    private int level, high;

    private int size;
    private float statetime = 0;

    public SelectorActor(Assets assets) {
        //64, 144, 228, 96
        setX(64);
        setY(144);
        setWidth(228);
        setHeight(96);  // 184 for 40 lines

        // Set the animations for the selector dinglies
        lrAnim = new Animation<TextureRegion>(.1f, assets.lrSelector);
        lrAnim.setPlayMode(Animation.PlayMode.LOOP);
        udAnim = new Animation<TextureRegion>(.1f, assets.udSelector);
        udAnim.setPlayMode(Animation.PlayMode.LOOP);

        lr = new Rectangle(0, 0, 0, 0);
        ud = new Rectangle(0, 0, 0, 0);
        updateRect();

        // Set the selector backgrounds
        bg = new TextureRegion[3];
        bg[0] = assets.selectorMenus[0];
        bg[1] = assets.selectorMenus[1];
        bg[2] = assets.selectorMenus[2];

        // Scale factor for drawing because apparently I cant decide between 1:1 or 3:1
        size = assets.lrSelector[0].getRegionWidth() * 3;

        // Default level and high settings
        level = 0;
        high = 0;

        Label.LabelStyle l = new Label.LabelStyle(assets.font, Color.BLACK);
        levelLabel = new Label(Integer.toString(level), l);
        levelLabel.setBounds(getX()+76, getY()+72, 20, 20);
        highLabel = new Label(Integer.toString(high), l);
        highLabel.setBounds(getX()+216, getY()+72, 20, 20); // Doesnt
    }

    public void setMode(int mode) {
        this.mode = mode;

        // Out of bounds check
        if(mode < 0 || mode > 2)
            this.mode = -1;

        // Set the correct height and y value for the mode selected
        if(this.mode == 2) {
            setHeight(184);
            setY(56);
            levelLabel.setY(getY() + 164);
            highLabel.setY(getY() + 164);   // When, its initially set, its not where it should be lol
        } else {
            setY(144);
            setHeight(96);
            levelLabel.setY(getY() + 76);
        }
    }

    public void setLevel(int level) {
        if(level >= 0 && level <= 9)
            this.level = level;
        levelLabel.setText(Integer.toString(this.level));
    }

    public void setHigh(int high) {
        if(high >= 0 && high <= 5)
            this.high = high;
        highLabel.setText(Integer.toString(this.high));
    }

    public int getLevel() { return level; }
    public int getHigh() { return high; }

    public Rectangle getUD() { return ud; }
    public Rectangle getLR() { return lr; }

    @Override
    public void act(float delta) {
        super.act(delta);
        statetime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Update the position of the dingles before drawing
        updateRect();

        // Dont draw if the mode is not set
        if(mode == -1)
            return;

        batch.draw(bg[mode], getX(), getY());

        // Draw Level selector
        if(mode == 2) {
            batch.draw(lrAnim.getKeyFrame(statetime), lr.getX(), lr.getY(), size, size);
            levelLabel.draw(batch, parentAlpha);
            batch.draw(udAnim.getKeyFrame(statetime), ud.getX(), ud.getY(), size, size);
            highLabel.draw(batch, parentAlpha);
        } else {
            batch.draw(lrAnim.getKeyFrame(statetime), lr.getX(), lr.getY(), size, size);
            levelLabel.draw(batch, parentAlpha);
        }
    }

    private void updateRect() {
        if(mode == 2)
            lr.set(getX()-6 + 24*level, getY()+103, 24, 24);
        else
            lr.set(getX()-6 + 24*level, getY()+15, 24, 24);
        ud.set(getX()+180, getY()+3 + 12*high, 24, 24);
    }
}
