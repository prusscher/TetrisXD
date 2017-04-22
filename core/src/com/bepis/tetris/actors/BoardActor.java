package com.bepis.tetris.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;

/**
 * Created by UPS on 4/18/2017.
 */

public class BoardActor extends Actor {

    // Frame of the board
    private TextureRegion frame;

    // Shape renderer for drawing the background of the play area
    private TextureRegion bg;

    // Colors for the background go here

    private int mode = 0;

    // This is where the actual piece stuff goes
    private boolean[][] board;
    private TextureRegion[][] boardTex;

    private boolean drawBG = true, drawFrame = true, drawTiles = true;

    public BoardActor(Assets assets, int mode, int high) {
        this(assets, mode);

        setupBoard(assets, high);
    }

    public BoardActor(Assets assets, int mode) {

        // Set mode and check to be sure we aren't out of area
        this.mode = mode;
        if(mode < 0 || mode > 2)
            this.mode = 0;

        // Set the border tile texture and background texture
        frame = assets.borderTile;
        bg = assets.boardBackgrounds[0];

        // Setup the board
        setupBoard(assets, 0);

        // Set the location
        setX(36);
        setY(0);
        setWidth(288);
        setHeight(480);
    }

    public void setPiece(int x, int y, TextureRegion piece) {
        board[x][y] = true;
        boardTex[x][y] = piece;
    }

    public void setupBoard(Assets assets, int high) {
        board = new boolean[GameMode.BOARD_WIDTH][GameMode.BOARD_HEIGHT];
        boardTex = new TextureRegion[GameMode.BOARD_WIDTH][GameMode.BOARD_HEIGHT];

        // Set the board to be clear
        for(int y = 0; y < GameMode.BOARD_HEIGHT; y++)
            for (int x = 0; x < GameMode.BOARD_WIDTH; x++)
                boardTex[x][y] = new TextureRegion(assets.clearTile);

        if(high > 0) {
            for(int y = 0; y < high; y++)
                // Set each line for the mode boi
                for (int x = 0; x < board[0].length; x++) {
                    board[x][y] = true;
                    boardTex[x][y].setRegion(assets.borderTile);
                }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE);

        if(drawBG) {
            // Draw the background
            batch.draw(bg, getX() + 24, getY() + 24, 240, 432);
        }

        if(drawFrame) {
            // Draw the border frame
            for (int x = 0; x < 12; x++) {  // Draw the top and bottom
                batch.draw(frame, getX() + (24 * x), getY());
                batch.draw(frame, getX() + (24 * x), getY() + 456);
            }
            for (int y = 0; y < 18; y++) {  // Draw the sides
                batch.draw(frame, getX(), getY() + ((y + 1) * 24));
                batch.draw(frame, getX() + 264, getY() + ((y + 1) * 24));
            }
        }
        if(drawTiles) {
            // Draw the pieces
            for (int y = 0; y < GameMode.BOARD_HEIGHT; y++) {
                for (int x = 0; x < GameMode.BOARD_WIDTH; x++) {
                    batch.draw(boardTex[x][y], getX()+24*(x+1), getY()+24*(y+1));
                }
            }
        }

    }
}
