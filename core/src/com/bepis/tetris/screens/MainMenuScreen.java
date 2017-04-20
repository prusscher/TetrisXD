package com.bepis.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.bepis.tetris.Assets;
import com.bepis.tetris.GameMode;
import com.bepis.tetris.TetrisXD;
import com.bepis.tetris.actors.MainMenuActor;
import com.bepis.tetris.actors.SelectorActor;
import com.kotcrab.vis.ui.VisUI;

/**
 * Created by Parker on 4/14/2017.
 */

public class MainMenuScreen implements Screen {
    private static TetrisXD game;

    private Stage stage;
    private Assets assets;

    private float timer = 1;

    private int mode;

    private SelectorActor select;
    private ButtonGroup<ImageButton> group;

    private ImageButton startGameBig;
    private ImageButton startGameSmall;
    private boolean udTouched;
    private boolean lrTouched;

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
        final ImageButton marathonButton = new ImageButton(new TextureRegionDrawable(assets.marathonButton[1]), new TextureRegionDrawable(assets.marathonButton[0]), new TextureRegionDrawable(assets.marathonButton[0]));
        marathonButton.setPosition(64, 380); //256

        // Ultra Button
        final ImageButton ultraButton = new ImageButton(new TextureRegionDrawable(assets.ultraButton[1]), new TextureRegionDrawable(assets.ultraButton[0]), new TextureRegionDrawable(assets.ultraButton[0]));
        ultraButton.setPosition(64, 324); //200

        // 40 Lines Button
        final ImageButton fortyLinesButton = new ImageButton(new TextureRegionDrawable(assets.fortyLinesButton[1]), new TextureRegionDrawable(assets.fortyLinesButton[0]),new TextureRegionDrawable(assets.fortyLinesButton[0]));
        fortyLinesButton.setPosition(64, 268); //144

        // Create the start game buttons
        startGameBig = new ImageButton(new TextureRegionDrawable(assets.startButton[1]), new TextureRegionDrawable(assets.startButton[0]));
        startGameSmall = new ImageButton(new TextureRegionDrawable(assets.startButton[3]), new TextureRegionDrawable(assets.startButton[2]));
        startGameBig.setPosition(64, 72);
        startGameSmall.setPosition(64, 72);

        // Listener to start the game with selected options.
        ChangeListener start = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(mode == GameMode.MARATHON)
                    game.setScreen(new GameScreen(game, mode, select.getLevel()));
                if(mode == GameMode.ULTRA)
                    game.setScreen(new GameScreen(game, mode, select.getLevel()));
                if(mode == GameMode.FORTYLINES)
                    game.setScreen(new GameScreen(game, mode, select.getLevel(), select.getHigh()));
            }
        };

        // Add the listener to both start buttons and set them to be invisible on start
        startGameBig.addListener(start);
        startGameSmall.addListener(start);
        startGameBig.setVisible(false);
        startGameSmall.setVisible(false);

        stage.addActor(startGameBig);
        stage.addActor(startGameSmall);

        // Button Listeners. RADIO BUTTONS BOI
        group = new ButtonGroup<ImageButton>(marathonButton, ultraButton, fortyLinesButton);
        group.setMaxCheckCount(1);
        group.setMinCheckCount(0);
        group.setUncheckLast(true);
        mode = -1;

        // Add the buttons to the stage
        stage.addActor(marathonButton);
        stage.addActor(ultraButton);
        stage.addActor(fortyLinesButton);

        // Create the select actor
        select = new SelectorActor(assets);
        stage.addActor(select);

        // Done adding actors
        stage.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if(rectTouched(select.getLR(), x, y)) {
                    lrTouched = true;
                }

                if(rectTouched(select.getUD(), x, y)) {
                    udTouched = true;
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                lrTouched = false;
                udTouched = false;
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                super.pan(event, x, y, deltaX, deltaY);
                if(lrTouched){
                    select.setLevel( (int)((x-58)/26) );
                } else if (udTouched) {
                    select.setHigh( (int)((y-61)/12) );
                }
            }
        });

        // Fade in the Menu on start
        mmActor.addAction(fadeToMenu());

//        stage.setDebugAll(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        // Set the mode so the correct selection menu is selected
        if(group.getCheckedIndex() != mode) {
            mode = group.getCheckedIndex();
            select.setMode(mode);

            // Set the correct start button to be the front
            if(mode == 2) {
                startGameBig.setVisible(false);
                startGameSmall.setVisible(true);
                startGameSmall.toFront();
            } else if(mode == 0 || mode == 1) {
                startGameBig.setVisible(true);
                startGameSmall.setVisible(false);
                startGameBig.toFront();
            } else {
                startGameBig.setVisible(false);
                startGameSmall.setVisible(false);
            }

        }


        if(mode != -1) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                select.setLevel(select.getLevel() - 1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                select.setLevel(select.getLevel() + 1);
            }

            if(mode == 2) {
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                    select.setHigh(select.getHigh() - 1);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                    select.setHigh(select.getHigh() + 1);
                }
            }
        }

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

    private boolean rectTouched(Rectangle rect, float x, float y) {
        if (rect.contains(x, y))
            return true;
        else
            return false;
    }
}
