package com.bepis.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by UPS on 4/17/2017.
 */

public class Assets {
    public ShapeRenderer shape;

    // Game Tiles
    public TextureRegion iTile, tTile, zTile, sTile, lTile, jTile, oTile;
    public Texture tiles;
    public Skin skin;
    public BitmapFont font;
    public BitmapFont fontMed;

    // TITLE OBJECT ASSETS
    public TextureRegion titleBackground;
    public TextureRegion[] titles;

    // BACKGROUND OBJECT ASSETS
    public TextureRegion[] marathonBG;

    // NEXTPIECETILE OBJECT ASSETS
    public TextureRegion nextPieceFrame;

    // STATSACTOR OBJECT ASSETS
    public TextureRegion statsTab;

    // BOARDACTOR OBJECT ASSETS
    public TextureRegion borderTile;
    public TextureRegion[] boardBackgrounds;

    // MAINMENUACTOR OBJECT ASSETS
    public TextureRegion overlay;
    public TextureRegion mainMenuTitle;
    public TextureRegion mainMenuTab;
    public TextureRegion[] marathonButton;
    public TextureRegion[] ultraButton;
    public TextureRegion[] fortyLinesButton;

    public TextureRegion fadeScreen;

    // Dev stuff/test stuff
    public Texture testImage;

    public Assets() {
        Texture temp = null;    // Temporary texture for reading and storing

        shape = new ShapeRenderer();
        tiles = new Texture(Gdx.files.internal("tiles.png"));
        testImage = new Texture(Gdx.files.internal("testScreen.png"));

        // Fonts b o i
        font = new BitmapFont(Gdx.files.internal("fonts/tetris.fnt"));
        fontMed = new BitmapFont(Gdx.files.internal("fonts/tetrisMed.fnt"));

//        skin = new Skin(Gdx.files.internal("uiskin.json"));

        final int MUL = 3;

        // Set the Piece textures
        iTile = new TextureRegion(tiles, 16*MUL,    0,      8*MUL,  32*MUL);
        tTile = new TextureRegion(tiles, 32*MUL,    8*MUL,  24*MUL, 16*MUL);
        zTile = new TextureRegion(tiles, 64*MUL,    8*MUL,  24*MUL, 16*MUL);
        sTile = new TextureRegion(tiles, 96*MUL,    8*MUL,  24*MUL, 16*MUL);
        lTile = new TextureRegion(tiles, 136*MUL,   0,      16*MUL, 24*MUL);
        jTile = new TextureRegion(tiles, 160*MUL,   0,      16*MUL, 24*MUL);
        oTile = new TextureRegion(tiles, 192*MUL,   48,     16*MUL, 16*MUL);    // I dont know why this. fuck this dude.

        // TitleActor Stuff
        temp = new Texture(Gdx.files.internal("titles.png"));
        titleBackground = new TextureRegion(temp, 0, 24, 120, 8); // Lel mayo

        titles = new TextureRegion[3];
        titles[0] = new TextureRegion(temp, 0, 0, 56, 8);   // Marathon
        titles[1] = new TextureRegion(temp, 0, 8, 56, 8);   // Ultra
        titles[2] = new TextureRegion(temp, 0, 16, 56, 8);   // 40 Lines

        // BackgroundActor stuff
        temp = new Texture(Gdx.files.internal("marathonBG.png"));
        TextureRegion[][] tmp = TextureRegion.split(temp, temp.getWidth()/4, temp.getHeight());

        marathonBG = new TextureRegion[4];
        marathonBG[0] = tmp[0][0];
        marathonBG[1] = tmp[0][1];
        marathonBG[2] = tmp[0][2];
        marathonBG[3] = tmp[0][3];

        // NextPieceTileActor stuff
        nextPieceFrame = new TextureRegion(new Texture(Gdx.files.internal("nextPieceFrame.png")), 0, 0, 144, 144);

        // StatsActor stuff
        statsTab = new TextureRegion(new Texture(Gdx.files.internal("statsTab.png")), 0, 0, 168, 32);

        // BoardActor stuff
        borderTile = new TextureRegion(new Texture(Gdx.files.internal("borderTile.png")));

        // Load the colors. These are 1x1 textures... This is probably bad but the shape renderer was broken.
        temp = new Texture(Gdx.files.internal("backgrounds.png"));
        boardBackgrounds = new TextureRegion[7];
        for(int j = 0; j < 7; j++)
            boardBackgrounds[j] = new TextureRegion(temp, j, 1, 1, 1);

        // MainMenuActor stuff
        overlay = new TextureRegion(new Texture(Gdx.files.internal("mainMenuTopBG.png")));
        mainMenuTitle = new TextureRegion(new Texture(Gdx.files.internal("title.png")));
        mainMenuTab = new TextureRegion(new Texture(Gdx.files.internal("buttonTab.png")));

        temp = new Texture(Gdx.files.internal("mainMenuButtons.png"));
        marathonButton = new TextureRegion[2];
        marathonButton[0] = new TextureRegion(temp, 0, 0, 216, 48);
        marathonButton[1] = new TextureRegion(temp, 216, 0, 216, 48);
        ultraButton = new TextureRegion[2];
        ultraButton[0] = new TextureRegion(temp, 0, 48, 216, 48);
        ultraButton[1] = new TextureRegion(temp, 216, 48, 216, 48);
        fortyLinesButton = new TextureRegion[2];
        fortyLinesButton[0] = new TextureRegion(temp, 0, 96, 216, 48);
        fortyLinesButton[1] = new TextureRegion(temp, 216, 96, 216, 48);

        fadeScreen = new TextureRegion(new Texture(Gdx.files.internal("fadeScreen.png")));

    }

}
