package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.TetrisXD;
import com.bepis.tetris.actors.BackgroundActor;
import com.bepis.tetris.actors.BoardActor;
import com.bepis.tetris.actors.NextPieceTileActor;
import com.bepis.tetris.actors.StatsActor;
import com.bepis.tetris.actors.TitleActor;

/**
 * Created by UPS on 4/17/2017.
 */

public class GameScreen implements Screen {

    // GameState, Specific state of the game (GameOver, Pause, Dropping, Clearing)
    interface GameState {
        void draw();
        void update(float delta);
    }

    private TetrisXD game;

    private Stage stage;

    private Assets assets;

    private int mode;

    private float dropTimer = 1;

    private boolean[][] grid;               // Logical Block Grid
    private TextureRegion[][] gridTexture;  // Graphic Block Grid

    private final int BLOCK_SIZE = 24;  // Specify the pixel size of each block

    private final int GRID_WIDTH = 10;   // 10x18 grid, faithful to DX
    private final int GRID_HEIGHT = 18;

    private GameState state;

    public GameScreen(TetrisXD game, int mode) {
        this.game = game;
        this.mode = mode;

        assets = game.assets;

        stage = new Stage(new FitViewport(360, 640, new OrthographicCamera()));
        Gdx.input.setInputProcessor(stage);

        // Create the grid
        grid = new boolean[GRID_WIDTH][GRID_HEIGHT];

        state = new PieceState();

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
            stage.addActor(new BackgroundActor(assets, GameMode.FORTYLINES));

            // 1
            stage.addActor(new TitleActor(assets, GameMode.FORTYLINES));

            // 2
            stage.addActor(new NextPieceTileActor(assets));
            stage.addActor(new StatsActor(assets, GameMode.FORTYLINES));
            stage.addActor(new BoardActor(assets, GameMode.FORTYLINES));

            // 3
            //stage.addActore(new PieceActor());
        }
    }

    class PieceState implements GameState {

//        boolean[][] piece =

        @Override
        public void draw() {
            // Draw the game
        }

        @Override
        public void update(float delta) {
            // Check if the player has lost the game

            // Check if the game should be paused

            // increment the drop counter

            // if the drop counter is less than 0, drop the piece

            // handle player input

        }

        private void handleInput() {
            // handle left piece move

            // handle right piece move

            // handle piece rotation left

            // handle piece rotation right

            // handle down movement
                // DX didn't have instant drop, should just decrease the drop counter by more, I think
        }

        private void attachPiece() {

        }
    }

    boolean[][] getPiece() {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
