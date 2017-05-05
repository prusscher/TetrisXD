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
    public TextureRegion[] iTile;
    public TextureRegion tTile, zTile, sTile, lTile, jTile, oTile;
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
    public TextureRegion[] startButton;
    public TextureRegion[] selectorMenus;
    public TextureRegion[] lrSelector;
    public TextureRegion[] udSelector;

    public TextureRegion fadeScreen;

    // Dev stuff/test stuff
    public TextureRegion clearTile;

    public Assets() {
        Texture temp = null;    // Temporary texture for reading and storing

        shape = new ShapeRenderer();
        tiles = new Texture(Gdx.files.internal("tiles.png"));

        // Fonts b o i
        font = new BitmapFont(Gdx.files.internal("fonts/tetris.fnt"));
        fontMed = new BitmapFont(Gdx.files.internal("fonts/tetrisMed.fnt"));


        // Set the Piece textures
        temp = new Texture(Gdx.files.internal("tileSheet.png"));
        iTile = new TextureRegion[4];
        iTile[0] = new TextureRegion(temp, 0, 0, 24, 24);
        iTile[1] = new TextureRegion(temp, 24, 0, 24, 24);
        iTile[2] = new TextureRegion(temp, 48, 0, 24, 24);
        iTile[3] = new TextureRegion(temp, 72, 0, 24, 24);

        tTile = new TextureRegion(temp, 96, 0, 24, 24);
        zTile = new TextureRegion(temp, 120, 0, 24, 24);
        sTile = new TextureRegion(temp, 144, 0, 24, 24);
        lTile = new TextureRegion(temp, 168, 0, 24, 24);
        jTile = new TextureRegion(temp, 192, 0, 24, 24);
        oTile = new TextureRegion(temp, 216, 0, 24, 24);

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
        marathonButton[0] = new TextureRegion(temp, 0, 0, 219, 51);
        marathonButton[1] = new TextureRegion(temp, 219, 0, 219, 51);
        ultraButton = new TextureRegion[2];
        ultraButton[0] = new TextureRegion(temp, 0, 51, 219, 51);
        ultraButton[1] = new TextureRegion(temp, 219, 51, 219, 51);
        fortyLinesButton = new TextureRegion[2];
        fortyLinesButton[0] = new TextureRegion(temp, 0, 102, 219, 51);
        fortyLinesButton[1] = new TextureRegion(temp, 219, 102, 219, 51);
        temp = new Texture(Gdx.files.internal("startButton.png"));
        startButton = new TextureRegion[4];
        startButton[0] = new TextureRegion(temp, 0, 0, 216, 51);
        startButton[1] = new TextureRegion(temp, 216, 0, 216, 51);
        startButton[2] = new TextureRegion(temp, 0, 51, 159, 51);
        startButton[3] = new TextureRegion(temp, 216, 51, 159, 51);

        // Set selector menus
        selectorMenus = new TextureRegion[3];
        selectorMenus[0] = new TextureRegion(new Texture(Gdx.files.internal("marathonSelector.png")));
        selectorMenus[1] = new TextureRegion(new Texture(Gdx.files.internal("marathonSelector.png")));
        selectorMenus[2] = new TextureRegion(new Texture(Gdx.files.internal("fortyLinesSelector.png")));

        // Set little animated selector
        temp = new Texture(Gdx.files.internal("lrSelector.png"));
        lrSelector = new TextureRegion[8];
        for(int j = 0; j < 8; j++)
            lrSelector[j] = new TextureRegion(temp, 8*j, 0, 8, 8);

        temp = new Texture(Gdx.files.internal("udSelector.png"));
        udSelector = new TextureRegion[8];
        for(int j = 0; j < 8; j++)
            udSelector[j] = new TextureRegion(temp, 8*j, 0, 8, 8);


        fadeScreen = new TextureRegion(new Texture(Gdx.files.internal("fadeScreen.png")));

        clearTile = new TextureRegion(new Texture(Gdx.files.internal("clearTile.png")));

    }

}
