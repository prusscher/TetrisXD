package com.bepis.tetris;

import com.badlogic.gdx.Game;
import com.bepis.tetris.screens.MainMenuScreen;

public class TetrisXD extends Game {

	public Assets assets;

	@Override
	public void create () {
		assets = new Assets();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
