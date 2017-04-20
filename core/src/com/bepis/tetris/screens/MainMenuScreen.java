package com.bepis.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

        // No longer catch the back key boi
        Gdx.input.setCatchBackKey(false);

        // Set the assets object to the game's asset object
        assets = game.assets;

        // Create the shape renderer. Just here because its nice
        shape = new ShapeRenderer();

        // Add the Main Menu background, title, and visual tab for buttons
        MainMenuActor mmActor = new MainMenuActor(assets);
        stage.addActor(mmActor);

        TextButton.TextButtonStyle t = new TextButton.TextButtonStyle(null, null, null, assets.fontMed);

        // Marathon Button
        ImageButton marathonButton = new ImageButton(new TextureRegionDrawable(assets.marathonButton[1]), new TextureRegionDrawable(assets.marathonButton[0]));
        marathonButton.setPosition(64, 380); //256
        marathonButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addAction(fadeToGameAction(GameMode.MARATHON));
            }
        });

        // Ultra Button
        ImageButton ultraButton = new ImageButton(new TextureRegionDrawable(assets.ultraButton[1]), new TextureRegionDrawable(assets.ultraButton[0]));
        ultraButton.setPosition(64, 324); //200
        ultraButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addAction(fadeToGameAction(GameMode.ULTRA));
            }
        });

        // 40 Lines Button
        ImageButton fortyLinesButton = new ImageButton(new TextureRegionDrawable(assets.fortyLinesButton[1]), new TextureRegionDrawable(assets.fortyLinesButton[0]));
        fortyLinesButton.setPosition(64, 268); //144
        fortyLinesButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addAction(fadeToGameAction(GameMode.FORTYLINES));
            }
        });

        stage.addActor(marathonButton);
        stage.addActor(ultraButton);
        stage.addActor(fortyLinesButton);

        // Done adding actors

        // Fade in the Menu on start
        mmActor.addAction(fadeToMenu());

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
                alpha(1),
                alpha(0f, .8f),
                parallel(alpha(0f), run(new Runnable() {
                    @Override
                    public void run() {
                        Gdx.input.setInputProcessor(stage);
                    }
                }))
        );
    }
}
