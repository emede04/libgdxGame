package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.atlas.ControladorAsset;
import com.mygdx.game.pantalla.GameOver;
import com.mygdx.game.pantalla.GameScreen;
import com.mygdx.game.pantalla.GameStart;

public class MainGame extends Game {
	public SpriteBatch batch;
	public Texture img;
	public GameScreen juego;
	public GameStart LetsGooScreen;
	public GameOver samataoa;
	public ControladorAsset mainManager;


	@Override
	public void create() {
		this.mainManager = new ControladorAsset();
		this.LetsGooScreen = new GameStart(this);
		this.samataoa = new GameOver(this);

		this.juego = new GameScreen(this);
		setScreen(LetsGooScreen);


	}
}
