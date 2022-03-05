package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.atlas.ControladorAsset;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;

	public ControladorAsset mainManager;

	public MainGame(){



	}


	@Override
	public void create() {
		this.mainManager = new ControladorAsset();
		//this.SaMataoScreen = new SaMataoScreen(this);
		//this.LetsGooScreen = new LetsGooScreen(this);
		//setScreen(LetsGooScreen);



	}
}
