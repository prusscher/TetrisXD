package com.bepis.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by UPS on 4/17/2017.
 */

public class Assets {

    public TextureRegion iTile, tTile, zTile, sTile, lTile, jTile, oTile;
    public Texture tiles;
    public Texture border;

    public Texture testImage;

    public Assets() {
        tiles = new Texture(Gdx.files.internal("tiles.png"));

//      border = new Texture(Gdx.files.internal("outline.png"));

        testImage = new Texture(Gdx.files.internal("testScreen.png"));

        final int MUL = 3;

        iTile = new TextureRegion(tiles, 16*MUL,    0,      8*MUL,  32*MUL);
        tTile = new TextureRegion(tiles, 32*MUL,    8*MUL,  24*MUL, 16*MUL);
        zTile = new TextureRegion(tiles, 64*MUL,    8*MUL,  24*MUL, 16*MUL);
        sTile = new TextureRegion(tiles, 96*MUL,    8*MUL,  24*MUL, 16*MUL);
        lTile = new TextureRegion(tiles, 136*MUL,   0,      16*MUL, 24*MUL);
        jTile = new TextureRegion(tiles, 160*MUL,   0,      16*MUL, 24*MUL);
        oTile = new TextureRegion(tiles, 192*MUL,   48,     16*MUL, 16*MUL);    // I dont know why this. fuck this dude.
    }

}
