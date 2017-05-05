package com.bepis.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static java.lang.System.out;

/**
 * Created by Parker on 4/30/2017.
 */

public class Piece {
    public final int ITile = 0;
    public final int TTile = 1;
    public final int ZTile = 2;
    public final int STile = 3;
    public final int LTile = 4;
    public final int JTile = 5;
    public final int OTile = 6;

    private int type;
    private int size;

    public int x;
    public int y;

    private float rot;

    private boolean[][] tileLogic;
    private TextureRegion[][] tilesTex;
    private Assets assets;

    public Piece(Assets assets, int type) {
        if(type >= 0 && type <= 6)
            this.type = type;
        else
            this.type = 0;

        this.assets = assets;

        create();
    }

    public void changeType(int type) {
        if(type >= 0 && type <= 6)
            this.type = type;
        else
            this.type = 0;

        create();
    }

    public int getSize() { return size; }
    public int getType() { return type; }
    public boolean[][] getLogic() { return tileLogic; }
    public TextureRegion[][] getTextures () { return tilesTex; }
    public float getRot() { return rot; }

    private void create() {
        rot = 0;

        // Create the bounding box and the textures for each tile
        // Also set piece x and y (Centered)
        switch (type) {
            case ITile:
                /*
                ITile
                Size : 4

                . . . .
                x x x x
                . . . .
                . . . .

                *rotates around the center (obv)
                 */

                size = 4;
                setArrays();
                x = 3;
                y = GameMode.BOARD_HEIGHT-3;

                for(int j = 0; j < 4; j++) {
                    tileLogic[j][2] = true;
                    tilesTex[j][2] = assets.iTile[j];
                }

                break;
            case TTile:
                /*
                TTile
                Size : 3

                . . .
                x o x
                . x .
                 */

                size = 3;
                setArrays();
                x = 4;
                y = GameMode.BOARD_HEIGHT-2;    // 18-2=16


                // Set the logic and textures
                tileLogic[1][0] = true;
                tilesTex[1][0] = assets.tTile;
                tileLogic[0][1] = true;
                tilesTex[0][1] = assets.tTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.tTile;
                tileLogic[2][1] = true;
                tilesTex[2][1] = assets.tTile;

                break;
            case ZTile:
                /*
                ZTile
                Size : 3

                x x .
                . o x
                . . .
                 */

                size = 3;
                setArrays();
                x = 4;
                y = GameMode.BOARD_HEIGHT-3;

                tileLogic[0][2] = true;
                tilesTex[0][2] = assets.zTile;
                tileLogic[1][2] = true;
                tilesTex[1][2] = assets.zTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.zTile;
                tileLogic[2][1] = true;
                tilesTex[2][1] = assets.zTile;

                break;
            case STile:
                /*
                STile
                Size : 3

                . x x
                x o .
                . . .
                 */

                size = 3;
                setArrays();
                x = 3;
                y = GameMode.BOARD_HEIGHT-3;

                tileLogic[0][1] = true;
                tilesTex[0][1] = assets.sTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.sTile;
                tileLogic[1][2] = true;
                tilesTex[1][2] = assets.sTile;
                tileLogic[2][2] = true;
                tilesTex[2][2] = assets.sTile;

                break;
            case LTile:
                /*
                LTile
                Size : 3

                . . .
                x o x
                x . .
                 */

                size = 3;
                setArrays();
                x = 4;
                y = GameMode.BOARD_HEIGHT-2;

                tileLogic[0][1] = true;
                tilesTex[0][1] = assets.lTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.lTile;
                tileLogic[2][1] = true;
                tilesTex[2][1] = assets.lTile;
                tileLogic[0][0] = true;
                tilesTex[0][0] = assets.lTile;

                break;
            case JTile:
                /*
                JTile
                Size : 3

                . . .
                x o x
                . . x
                 */

                size = 3;
                setArrays();
                x = 3;
                y = GameMode.BOARD_HEIGHT-2;

                tileLogic[0][1] = true;
                tilesTex[0][1] = assets.jTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.jTile;
                tileLogic[2][1] = true;
                tilesTex[2][1] = assets.jTile;
                tileLogic[2][0] = true;
                tilesTex[2][0] = assets.jTile;

                break;
            case OTile:
                /*
                OTile
                Size : 3

                x x
                x x
                 */

                size = 2;
                setArrays();
                x = 4;
                y = GameMode.BOARD_HEIGHT-2;

                tileLogic[0][0] = true;
                tilesTex[0][0] = assets.oTile;
                tileLogic[1][0] = true;
                tilesTex[1][0] = assets.oTile;
                tileLogic[0][1] = true;
                tilesTex[0][1] = assets.oTile;
                tileLogic[1][1] = true;
                tilesTex[1][1] = assets.oTile;

                break;
        }
    }

    /**
     * Rotate the piece to the right (Both the textures and the logical hitbox)
     */
    public void rotateRight() {
//        tileLogic = getRotateRight();
        tileLogic = getRotateLeft();
//        rotateRightTex();
        rotateLeftTex();
    }

    /**
     * Rotate the piece to the left (Both the textures and the logical hitbox)
     */
    public void rotateLeft() {
        // I fucked up? Rotations are reversed, just gonna switch em
//        tileLogic = getRotateLeft();
        tileLogic = getRotateRight();
//        rotateLeftTex();
        rotateRightTex();
    }

    /**
     * For checking if the piece can be rotated right
     * @return Logical position of the piece within the bounding box
     */
    public boolean[][] getRotateRight() {
        boolean[][] out = new boolean[size][size];
        for(int y = 0; y < size; y++)
            for(int x = 0; x < size; x++)
                out[x][y] = tileLogic[y][size - x - 1];
        return out;
    }

    /**
     * For checking if the piece can be rotated left
     * @return Logical position of the piece within the bounding box
     */
    public boolean[][] getRotateLeft() {
        boolean[][] out = new boolean[size][size];
        for(int y = 0; y < size; y++)
            for(int x = 0; x < size; x++)
                out[x][y] = tileLogic[size - y - 1][x];
        return out;
    }

    private void rotateRightTex() {
        TextureRegion[][] rotate = new TextureRegion[size][size];
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                rotate[x][y] = tilesTex[y][size - x - 1];
            }
        }
        tilesTex = rotate;
        rot += 90;
        if(rot >= 360)
            rot = 0;

        if(type == ITile) {
            // alright fam, this is where you need to replace the textures for the rotated I Piece
            // Problem is, there is no easy way to do the rotation with the texture region
            // SO. youre going to rotate the pieces manually, add them to the tile sheet,
            // and swap them here for the correct one. Thanks
        }
    }

    private void rotateLeftTex() {
        TextureRegion[][] rotate = new TextureRegion[size][size];
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                rotate[x][y] = tilesTex[size - y - 1][x];
            }
        }
        tilesTex = rotate;
        rot -= 90;
        if(rot < 0)
            rot = 270;

        if(type == ITile) {
            // alright fam, this is where you need to replace the textures for the rotated I Piece
            // Problem is, there is no easy way to do the rotation with the texture region
            // SO. youre going to rotate the pieces manually, add them to the tile sheet,
            // and swap them here for the correct one. Thanks
        }
    }

    /**
     * Create the arrays (logic and texture) of preset size and set them all to blank tiles
     */
    private void setArrays() {
        // Set the tile arrays
        tileLogic = new boolean[size][size];
        tilesTex = new TextureRegion[size][size];

        // Set all tilesTex to clear
        for(int y = 0; y < size; y++)
            for(int x = 0; x < size; x++)
                tilesTex[x][y] = new TextureRegion(assets.clearTile);
    }
}
