package com.bepis.tetris;

import com.badlogic.gdx.Game;
import com.bepis.tetris.screens.MainMenuScreen;
import com.bepis.tetris.screens.TestScreen;

public class TetrisXD extends Game {

	public Assets assets;

	@Override
	public void create () {
		assets = new Assets();
		this.setScreen(new MainMenuScreen(this));

//		this.setScreen(new TestScreen(assets));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
