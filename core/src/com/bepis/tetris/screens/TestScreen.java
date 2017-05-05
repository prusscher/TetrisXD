package com.bepis.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bepis.tetris.Assets;
import com.bepis.tetris.Piece;

/**
 * Created by UPS on 4/20/2017.
 */

public class TestScreen implements Screen {

    SpriteBatch batch;
    Stage stage;
    Assets assets;
    Piece[] test;

    public TestScreen(Assets assets) {
        stage = new Stage(new FitViewport(360, 480, new OrthographicCamera()));
        batch = new SpriteBatch();
        this.assets = assets;

        test = new Piece[7];

        for(int j = 0; j < 7; j++) {
            test[j] = new Piece(assets, j);
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        stage.act();
//        stage.draw();
        batch.begin();
        int x=0;
        int y=0;
        for(int j = 0; j < 7; j++) {
            TextureRegion[][] tex = test[j].getTextures();
            for(int s = 0; s < test[j].getSize(); s++) {
                for(int t = test[j].getSize()-1; t >= 0 ; t--) {
//                    batch.draw(tex[s][t], x+(s*24), y+(t*24), 0, 0, 24, 24, 1, 1, test[j].getRot());
                    batch.draw(tex[s][t], x+(s*24), y+(t*24));
                }
            }

            y += (test[j].getSize()*24);
        }
        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            for(int j = 0; j < 7; j++)
                test[j].rotateRight();
        }
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
