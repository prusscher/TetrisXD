package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.TetrisXD;
import com.bepis.tetris.actors.BackgroundActor;
import com.bepis.tetris.actors.BoardActor;
import com.bepis.tetris.actors.NextPieceTileActor;
import com.bepis.tetris.actors.StatsActor;
import com.bepis.tetris.actors.TitleActor;
import com.kotcrab.vis.ui.VisUI;

/**
 * Created by Parker on 4/14/2017.
 */

public class MainMenuScreen implements Screen {
    private TetrisXD game;

    private Stage stage;
    private Assets assets;

    private float timer = 1;

    private Image piece;

    ShapeRenderer shape;

    public MainMenuScreen(TetrisXD game) {
        this.game = game;

        // Create the stage and set the input processor
        stage = new Stage(new FitViewport(360, 640, new OrthographicCamera()));
        Gdx.input.setInputProcessor(stage);

        // Create the assets object
        assets = new Assets();

        // Create the shape renderer. Just here because its nice
        shape = new ShapeRenderer();

        // Load VisUI so I can use the nice buttons
        VisUI.load();

        /*
        Were going to do stuff now
        */


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

        // Done adding actors

        stage.setDebugAll(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
        VisUI.dispose();
        stage.dispose();
    }


    public void drawGrid() {
        shape.begin(ShapeRenderer.ShapeType.Line);

        int gridSize = (int)Gdx.graphics.getWidth()/10 - 4;

        shape.setColor(Color.RED);

        for(int y = 0; y < 18; y++) {
            for(int x = 0; x < 10; x++) {
                shape.rect( (x * gridSize) + 2, (y * (gridSize+1)) + 1, gridSize, gridSize);
            }
        }

        shape.end();
    }
}
