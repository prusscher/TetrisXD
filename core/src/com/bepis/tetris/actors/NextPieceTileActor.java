package com.bepis.tetris.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;
import com.bepis.tetris.Piece;
import com.bepis.tetris.PieceGenerator;

/**
 * Created by UPS on 4/18/2017.
 */

public class NextPieceTileActor extends Actor {

    private TextureRegion frame;
    private PieceGenerator pieceGen;
    private Assets assets;

    // Next piece stuff will go here eventually, this is just for looks atm
    private int curType;
    private TextureRegion[][] currentPiece;
    private Piece piece;

    public NextPieceTileActor(Assets assets, PieceGenerator pieceGen) {
        frame = assets.nextPieceFrame;
        this.assets = assets;
        this.pieceGen = pieceGen;

        // Get the next piece from the piece generator
        curType = pieceGen.nextPiece();
        piece = new Piece(assets, curType);
        currentPiece = piece.getTextures();

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
        int startPos = (4-piece.getSize())*24;
        for(int y = 0; y < piece.getSize(); y++) {
            for (int x = 0; x < piece.getSize(); x++) {
                batch.draw(currentPiece[x][y], getX() + startPos + 24*x, getY() + startPos + 24*y);
            }
        }
    }

    /**
     * Get the next piece held by the NextPieceTileActor
     * @return int 0-6 of piece type (See piece.java)
     */
    public int next() {
        int out = curType;
        curType = pieceGen.nextPiece();
        piece.changeType(curType);
        currentPiece = piece.getTextures();
        return out;
    }
}
