package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.bepis.tetris.Assets;
import com.bepis.tetris.TetrisXD;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTextButton;

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

        Image testImage = new Image(assets.testImage);
        testImage.setBounds(0, 0, 360, 640);

//      stage.addActor(testImage);

        piece = new Image(assets.oTile);
        piece.setBounds(0, stage.getHeight()-48, assets.oTile.getRegionWidth(), assets.oTile.getRegionHeight());

        stage.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                super.pan(event, x, y, deltaX, deltaY);
                int trunX = (int)(x/(stage.getWidth()/14));

                System.out.println(trunX + " " + x + " " + 24*trunX);

                if(24 * trunX >= 0 && 24 * trunX <= stage.getWidth()-piece.getWidth())
                    piece.setX(24 * trunX);
            }
        });


        stage.addActor(piece);
        stage.setDebugAll(true);
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

        timer -= delta;

        if(timer < 0 && piece.getY() >= 0) {
            piece.moveBy(0, -24);
            timer = 1;
        }
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
