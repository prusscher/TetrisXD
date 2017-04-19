package com.bepis.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.TetrisXD;
import com.bepis.tetris.actors.BackgroundActor;
import com.bepis.tetris.actors.BoardActor;
import com.bepis.tetris.actors.MainMenuActor;
import com.bepis.tetris.actors.NextPieceTileActor;
import com.bepis.tetris.actors.StatsActor;
import com.bepis.tetris.actors.TitleActor;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Created by Parker on 4/14/2017.
 */

public class MainMenuScreen implements Screen {
    private static TetrisXD game;

    private Stage stage;
    private Assets assets;

    private float timer = 1;

    private Image piece;

    ShapeRenderer shape;

    public MainMenuScreen(final TetrisXD game) {
        this.game = game;

        // Create the stage and set the input processor
        stage = new Stage(new FitViewport(360, 640, new OrthographicCamera()));
        //Gdx.input.setInputProcessor(stage);

        // Set the assets object to the game's asset object
        assets = game.assets;

        // Create the shape renderer. Just here because its nice
        shape = new ShapeRenderer();

        // Load VisUI so I can use the nice buttons
        VisUI.load();

        // Add the Main Menu background, title, and visual tab for buttons
        stage.addActor(new MainMenuActor(assets));

        // WIP Start Button
        VisTextButton start = new VisTextButton("Start");
        start.setBounds(64, 256, 224, 40);
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addAction(fadeToGameAction(GameMode.FORTYLINES));
            }
        });
        stage.addActor(start);

        // Done adding actors

        // Fade in the Menu on start
        stage.addAction(fadeToMenu());

        stage.setDebugAll(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
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


    /**
     * Fades the current stage to 0 over .8 seconds
     * @param mode GameMode to start the game with (0,1,2)
     * @return Action to give to the stage to fade
     */
    public SequenceAction fadeToGameAction(final int mode) {
        return sequence(
                alpha(0f, .8f),
                run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameScreen(game, mode));
                    }
                })
        );
    }

    /**
     * Fades in when the application starts. Nicer than a hard cut to the first screen
     * @return Action to step alpha up from 0 to 1 over .8 seconds
     */
    public SequenceAction fadeToMenu() {
        return sequence(
                alpha(0),
                alpha(1f, .8f),
                parallel(alpha(1f), run(new Runnable() {
                    @Override
                    public void run() {
                        Gdx.input.setInputProcessor(stage);
                    }
                }))
        );
    }
}
