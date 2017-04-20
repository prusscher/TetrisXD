package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/18/2017.
 */

public class StatsActor extends Actor {

    private TextureRegion tab;

    private Label label1, label2, label3;
    private Label value1, value2, value3;

    private boolean isMarathon = false, isFortyLines = false;

    private int score;
    private int lines;
    private int level;
    private int min, sec;

    public StatsActor(Assets assets, int mode) {
        setX(0);
        setY(494);
        setWidth(168);
        setHeight(32);

        tab = assets.statsTab;

        Label.LabelStyle l = new Label.LabelStyle(assets.fontMed, Color.BLACK);

        // Tab Labels
        label1 = new Label("LINES", l);
        label1.setPosition(getX()+26, getY()+16);
        label2 = new Label("ERROR", l); // Set to error by default. If the mode is wrong, it doesnt change
        label2.setPosition(getX()+26, getY()+56);
        label3 = new Label("SCORE", l);
        label3.setPosition(getX()+26, getY()+96);

        // Determine if the current mode is marathon/fortylines to determine how the stats are layed out
        if(mode == 0)
            isMarathon = true;
        else if(mode == 2)
            isFortyLines = true;

        // Set the second label to the appropriate title
        if(isMarathon) { // Marathon
            label2.setText("LEVEL");
        } else { // Ultra and 40 Lines
            label2.setText("TIME");
        }

        // Set the initial values for the
        score = 0;
        lines = 0;
        level = 0;
        min = 0;
        sec = 0;

        if(isFortyLines) // DefaultTime
            min = 3;

        // Tab Values

        // Lines value
        value1 = new Label(Integer.toString(lines), l);
        value1.setAlignment(Align.right);
        value1.setPosition(getX(), getY()+2);
        value1.setWidth(164f);

        // Level/Time value
        value2 = new Label("", l);
        value2.setAlignment(Align.right);
        value2.setPosition(getX(), getY()+48);

        if(isMarathon) { // Level
            value2.setWidth(164f);
            value2.setText(Integer.toString(level));
        } else {           // Time
            value2.setWidth(124f);
            value2.setText(getTimeString());
        }

        // Score value
        value3 = new Label(Integer.toString(score), l);
        value3.setAlignment(Align.right);
        value3.setPosition(getX(), getY()+82);
        value3.setWidth(164f);
    }

    float tempTime = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        tempTime += delta;

        if(tempTime >= 1) {
            nextSec();
            updateValues();
            tempTime = 0;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        // Bottom
        batch.draw(tab, getX(), getY());
        label1.draw(batch, parentAlpha);
        value1.draw(batch, parentAlpha);

        // Middle
        batch.draw(tab, getX(), getY()+40);
        label2.draw(batch, parentAlpha);
        value2.draw(batch, parentAlpha);

        // Top
        batch.draw(tab, getX(), getY()+80);
        label3.draw(batch, parentAlpha);
        value3.draw(batch, parentAlpha);
    }

    private String getTimeString() {
        if(sec < 10)
            return min+":0"+sec;
        return min+":"+sec;
    }

    /**
     * Update the value labels to reflect the current state of the game values
     */
    private void updateValues() {
        value1.setText(Integer.toString(lines));
        value3.setText(Integer.toString(score));

        if(isMarathon)
            value2.setText(Integer.toString(level));
        else
            value2.setText(getTimeString());
    }

    // Getters and Setters bwaah
    public int getLines() { return lines; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getTimeSec() { return 60*min + sec; }

    public void setLines(int lines) { this.lines = lines; updateValues(); }
    public void addLines()          { this.lines++; updateValues(); }
    public void addLines(int lines) { this.lines += lines; updateValues(); }

    public void setScore(int score) { this.score = score; updateValues(); }
    public void addScore()          { this.score++; updateValues(); }
    public void addScore(int score) { this.score += score; updateValues(); }

    public void setLevel(int level) { this.level = level; updateValues(); }

    public void setTime(int min, int sec) { this.min = min; this.sec = (sec > 60) ? sec : 0; updateValues();}

    public boolean outOfTime() { return (min == 0 && sec == 0) ? true : false; }

    /**
     * Increment the seconds for modes that use the timer
     * Increments for 40 Lines, Decrements for Ultra
     * Used so that we don't change the seconds when the game shouldn't (When clearing a line at 0:00)
     */
    public void nextSec() {
        if(!isFortyLines) {
            // Check if were not going out of bounds
            if (sec < 59)
                sec++;
            else {
                min++;
                sec = 0;
            }

        } else {
            // Check if were not going out of bounds
            if(min >= 0 && sec >= 0) {

                if (sec > 0) {
                    sec--;
                } else if (min > 0 && sec == 0) {
                    min--;
                    sec = 59;
                } else {
                    System.out.println("We hit 0 were done here");
                }
            }
        }
    }
}
