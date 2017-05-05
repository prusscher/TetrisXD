package com.bepis.tetris.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.Piece;
import com.bepis.tetris.PieceGenerator;

/**
 * Created by UPS on 4/18/2017.
 */
public class PieceActor extends Actor {

    private TextureRegion[] pieces;

    private Piece piece;

    private Assets assets;
    private PieceGenerator pieceGen;

    public PieceActor(Assets assets, PieceGenerator pieceGen) {
        this.assets = assets;
        this.pieceGen = pieceGen;

        piece = new Piece(assets, this.pieceGen.nextPiece());
        setToTop();
    }

    public int getPieceX() { return piece.x; }
    public int getPieceY() { return piece.y; }

    private void setToTop() {
        setX(piece.x*24 + 36 + 24);
        setY(piece.y*24 + 24);
        setWidth(piece.getSize()*24);
        setHeight(piece.getSize()*24);
    }

    /**
     * Set the piece actor to a new piece and set to the top of the board
     * @param index int 0-6 inticating the piece type (See Piece.java)
     */
    public void newPiece(int index) {
        piece = new Piece(assets, index);
        setToTop();
    }

    public void moveDown(boolean[][] board) {
        if(checkCollision(MOVE_DOWN, board)) {
            piece.y--;
            setY(piece.y * 24 + BoardActor.BOARD_START_Y);
        }
        System.out.println(piece.x + " " + piece.y);
    }

    public void moveLeft(boolean[][] board) {
        if(checkCollision(MOVE_LEFT, board)) {
            piece.x--;
            setX(piece.x * 24 + BoardActor.BOARD_START_X);
        }

        System.out.println(piece.x + " " + piece.y);
    }

    public void moveRight(boolean[][] board) {
        if(checkCollision(MOVE_RIGHT, board)) {
            piece.x++;
            setX(piece.x * 24 + BoardActor.BOARD_START_X);
        }

        System.out.println(piece.x + " " + piece.y);
    }

    public void rotateLeft(boolean[][] board) {
        if(checkCollision(ROT_LEFT, board)) {
            piece.rotateLeft();
        }
    }

    public void rotateRight(boolean[][] board) {
        if(checkCollision(ROT_LEFT, board)) {
            piece.rotateRight();
        }
    }

    public void printBoard(boolean[][] board) {
        boolean[][] l = piece.getLogic();

        System.out.println("Print Board: ");
        int yBoundBot = piece.y;
        int yBoundTop = piece.y + piece.getSize();
        int xBoundBot = piece.x;
        int xBoundTop = piece.x + piece.getSize();

        System.out.println("\tBounds: " + xBoundBot + ", " + xBoundTop + ": " + yBoundBot + ", " + yBoundTop);
        System.out.print("X Range: ");
        for(int x = 0; x < GameMode.BOARD_WIDTH; x++)
            System.out.print(x + ">" + ((piece.getSize()-1)-(x-piece.x)) + ", ");
        System.out.println();

        for(int y = GameMode.BOARD_HEIGHT-1; y >= -1; y--) {
            if(y == -1) {
                // Print the numbers along the bottom axis
                System.out.print("\t");
                for (int x = 0; x < GameMode.BOARD_WIDTH; x++) {
                    System.out.print(x + " ");
                }
            } else {
                // Print the piece and board
                for (int x = -1; x < GameMode.BOARD_WIDTH; x++) {
                    if (x == -1) { // Print y on the left axis
                        System.out.print(y + "\t");
                    } else {
                        if (x >= xBoundBot && x < xBoundTop && y >= yBoundBot && y < yBoundTop) {
                            int relX = (x - piece.x);
                            int relY = (y - piece.y);
                            if ((relX >= 0) && (relX < piece.getSize())
                                    && (relY >= 0) && (relY < piece.getSize())
                                    && l[relX][relY]) // Print piece and bounding box
                                System.out.print("x ");
                            else
                                System.out.print("* ");
                        } else {
                            if (board[x][y]) // Print board and blank spaces
                                System.out.print("o ");
                            else
                                System.out.print(". ");
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println();

    }

    public static final int MOVE_LEFT = 0;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_DOWN = 2;
    public static final int ROT_LEFT = 3;
    public static final int ROT_RIGHT = 4;

    /**
     * Check the collision of the current piece of the board
     * with the specific move type
     * @param moveType int describing the move (This class holds the movetypes)
     * @param board boolean[][] the current board
     * @return true when there is NO collision (Move can be performed), false when there is collision
     */
    public boolean checkCollision(int moveType, boolean[][] board) {
        // Piece Logical array
        boolean[][] l = piece.getLogic();

        if(getDebug())
            printBoard(board);

        // Respond to move type
        switch (moveType) {
            case MOVE_LEFT:
                for (int y = 0; y < piece.getSize(); y++) {
                    for(int x = 0; x < piece.getSize(); x++) {
                        if(l[x][y]) {
                            int relX = piece.x + x;
                            int relY = piece.y + y;

                            // Out of bounds
                            if(relX-1 < 0)
                                return false;

                            // Board collision
                            if(board[relX-1][relY])
                                return false;
                        }
                    }
                }

                // No collision and no out of bounds, good to go
                return true;

            case MOVE_RIGHT:
                for (int y = 0; y < piece.getSize(); y++) {
                    for(int x = 0; x < piece.getSize(); x++) {
                        if(l[x][y]) {
                            int relX = piece.x + x;
                            int relY = piece.y + y;

                            // Out of bounds
                            if(relX+1 > GameMode.BOARD_WIDTH-1)
                                return false;

                            // Board collision
                            if(board[relX+1][relY])
                                return false;
                        }
                    }
                }

                // No collision and no out of bounds, good to go
                return true;

            case MOVE_DOWN:
                for (int y = 0; y < piece.getSize(); y++) {
                    for(int x = 0; x < piece.getSize(); x++) {
                        if (l[x][y]) {
                            int relX = piece.x + x;
                            int relY = piece.y + y;

                            // Out of bounds
                            if(relY-1 < 0)
                                return false;

                            // Board collision
                            if(board[relX][relY-1])
                                return false;
                        }
                    }
                }

                // No collision and no out of bounds, good to go
                return true;

            case ROT_LEFT:
                l = piece.getRotateLeft();
                for (int y = 0; y < piece.getSize(); y++) {
                    for(int x = 0; x < piece.getSize(); x++) {
                        if (l[x][y]) {
                            int relX = piece.x + x;
                            int relY = piece.y + y;

                            // Out of bounds
                            if(relY < 0 || relY > GameMode.BOARD_HEIGHT-1 || relX < 0 || relX > GameMode.BOARD_WIDTH-1)
                                return false;

                            // Board collision
                            if(board[relX][relY])
                                return false;
                        }
                    }
                }

                // No collision and no out of bounds, good to go
                return true;

            case ROT_RIGHT:
                l = piece.getRotateRight();
                for (int y = 0; y < piece.getSize(); y++) {
                    for(int x = 0; x < piece.getSize(); x++) {
                        if (l[x][y]) {
                            int relX = piece.x + x;
                            int relY = piece.y + y;

                            // Out of bounds
                            if(relY < 0 || relY > GameMode.BOARD_HEIGHT-1 || relX < 0 || relX > GameMode.BOARD_WIDTH-1)
                                return false;

                            // Board collision
                            if(board[relX][relY])
                                return false;
                        }
                    }
                }

                // No collision and no out of bounds, good to go
                return true;
        }

        // Last Resort big ol problem false
        System.out.println("THERE WAS A BIG Ol\' PROBLEM YA DUNGLE. checkCollision skipped the switch. please check");
        Gdx.app.log("PieceActor", "THERE WAS A BIG Ol\' PROBLEM YA DUNGLE. checkCollision skipped the switch. please check");
        return false;
    }

    public boolean canMoveDown(boolean[][] board) {
        return checkCollision(MOVE_DOWN, board);
    }

    public boolean[][] getLogic() { return piece.getLogic(); }
    public TextureRegion[][] getTexture() { return piece.getTextures(); }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(this.getColor());
        TextureRegion[][] tex = piece.getTextures();
        for(int y = 0; y < piece.getSize(); y++) {
            for (int x = 0; x < piece.getSize(); x++) {
                batch.draw(tex[x][y], getX()+24*x, (y*24)+getY());
            }
        }
    }
}
