package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
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

    private Image img;

    ShapeRenderer shape;

    public MainMenuScreen(TetrisXD game) {
        this.game = game;

        stage = new Stage(new FitViewport(240, 400, new OrthographicCamera()));
        Gdx.input.setInputProcessor(stage);

        shape = new ShapeRenderer();

        VisUI.load();

        img = new Image();
        img.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("badlogic.jpg"))));
        img.setBounds(10, 100, 220, 220);

        // Create the buttons
        VisTextButton moveLeftButton = new VisTextButton("<");
        VisTextButton moveRightButton = new VisTextButton(">");

        // Set Left and Right button bounds
        moveLeftButton.setBounds(                   4,  4,  50, 50);
        moveRightButton.setBounds(stage.getWidth()-54,  4,  50, 50);

        moveLeftButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                img.moveBy(-1, 0);
            }
        });

        moveRightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                img.moveBy(1, 0);
            }
        });

        VisTextButton bottomButton = new VisTextButton("Move that image up");
        bottomButton.setBounds(4, 4, 232, 25);
        bottomButton.setColor(Color.RED);
        bottomButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("BUTTON", "Bottom Button Pressed " + img.getY() + " " + img.getHeight() + " " + stage.getHeight());
                Gdx.input.vibrate(50);
                img.setY(img.getY() + 4);

                if( img.getY() + img.getHeight() > stage.getHeight() )
                    img.setY(0);
            }
        });

        stage.addActor(img);
//        stage.addActor(bottomButton);
        stage.addActor(moveLeftButton);
        stage.addActor(moveRightButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        // Draw a grid
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
}
