package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bepis.tetris.Assets;

/**
 * Created by UPS on 4/20/2017.
 */

public class TestScreen implements Screen {

    Stage stage;
    Assets assets;
    TextureRegion test;

    public TestScreen(Assets assets) {
        stage = new Stage(new FitViewport(360, 480, new OrthographicCamera()));
        this.assets = assets;

        test = assets.iTile;
        Image image = new Image(test);
        image.setBounds(0, 0, test.getRegionWidth(), test.getRegionHeight());

        stage.addActor(image);
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

    }
}
