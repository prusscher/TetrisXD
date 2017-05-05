package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.PieceGenerator;
import com.bepis.tetris.TetrisXD;
import com.bepis.tetris.actors.BackgroundActor;
import com.bepis.tetris.actors.BoardActor;
import com.bepis.tetris.actors.NextPieceTileActor;
import com.bepis.tetris.actors.PieceActor;
import com.bepis.tetris.actors.StatsActor;
import com.bepis.tetris.actors.TitleActor;

/**
 * Created by UPS on 4/17/2017.
 */

public class GameScreen implements Screen {
    private TetrisXD game;

    private Stage stage;

    private BoardActor board;
    private StatsActor stats;
    private NextPieceTileActor nextPiece;
    private PieceActor piece;

    private Assets assets;
    private PieceGenerator pieceGen;

    private int mode;
    private int level, high;

    private float dropTimer = 1;
    private float dropMult = 1;

    private final int BLOCK_SIZE = 24;  // Specify the pixel size of each block

    public GameScreen(TetrisXD game, int mode, int level) {
        this(game, mode);
        this.level = level;

        stats.setLevel(this.level);

    }

    public GameScreen(TetrisXD game, int mode, int level, int high) {
        this(game, mode, level);
        this.high = high;

        //board.setHigh(this.high);
    }

    public GameScreen(TetrisXD game, int mode) {
        this.game = game;
        this.mode = mode;

        assets = game.assets;
        pieceGen = new PieceGenerator();

        stage = new Stage(new FitViewport(360, 640, new OrthographicCamera()));
        Gdx.input.setInputProcessor(stage);

        level = 0;
        high = 0;

        nextPiece = new NextPieceTileActor(assets, pieceGen);
        board = new BoardActor(assets, mode);
        stats = new StatsActor(assets, mode);
        piece = new PieceActor(assets, pieceGen);

        stats.setLevel(level);

        //        Image testImage = new Image(assets.testImage);
//        testImage.setBounds(0, 0, 360, 640);
//        stage.addActor(testImage);

        // Add the game actors
        /* actor order
            background              0
            title                   1
            stats, nextpiece, board 2
            piece                   3
        */
        boolean drawBoard = true;
        if(drawBoard) {
            // 0
            stage.addActor(new BackgroundActor(assets, mode));

            // 1
            stage.addActor(new TitleActor(assets, mode));

            // 2
            stage.addActor(nextPiece);
            stage.addActor(stats);
            stage.addActor(board);

            // 3
            stage.addActor(piece);
            piece.toFront();
        }

        stage.setDebugAll(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        Gdx.input.setCatchBackKey(true);

        if(Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.BACKSPACE) && !Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            game.setScreen(new MainMenuScreen(game));
        }

        // GAME LOGIC
        dropTimer -= delta;
        if(dropTimer < 0) {
            dropTimer = 1;

            if(piece.canMoveDown(board.getBoard()))
                piece.moveDown(board.getBoard());
            else {  // Setting the piece to the board
                attachPiece();
                piece.newPiece(nextPiece.next());
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            piece.newPiece(nextPiece.next());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(piece.canMoveDown(board.getBoard())) { // if we can drop, drop and reset timer
                piece.moveDown(board.getBoard());
                dropTimer = 1;
            } else {    // Otherwise, speed up drop timer
                dropTimer -= .25;
            }

        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            piece.moveLeft(board.getBoard());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            piece.moveRight(board.getBoard());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.COMMA) && Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) {
            piece.rotateLeft(board.getBoard());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PERIOD) && Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) {
            piece.rotateRight(board.getBoard());
        }
    }

    private void attachPiece() {
        boolean[][] l = piece.getLogic();
        TextureRegion[][] t = piece.getTexture();
        int size = l.length;
        int relX = (int) piece.getPieceX();
        int relY = (int) piece.getPieceY();

        // For each actual tile, set the piece on the board to the tile
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                if (l[x][y])
                    board.setPiece(relX + x, relY + y, t[x][y]);
        stats.addScore(board.clearRows());

//      piece.printBoard(board.getBoard());
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
