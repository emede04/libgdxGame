package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.atlas.ControladorAsset;
import com.mygdx.game.pantalla.GameScreen;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;
	GameScreen juego;
	public ControladorAsset mainManager;


	@Override
	public void create() {
		this.mainManager = new ControladorAsset();
		//this.SaMataoScreen = new SaMataoScreen(this);
		//this.LetsGooScreen = new LetsGooScreen(this);
		//setScreen(LetsGooScreen);
		this.juego = new GameScreen(this);
		setScreen(juego);


	}
}
