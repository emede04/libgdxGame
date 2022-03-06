package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.atlas.ControladorAsset;
import com.mygdx.game.controlores.JoyStick;
import com.mygdx.game.pantalla.GameOver;
import com.mygdx.game.pantalla.GameScreen;
import com.mygdx.game.pantalla.GameStart;

public class MainGame extends Game {
	public SpriteBatch batch;
	public Texture img;
	public GameScreen juego;
	public GameOver SaMataoScreen;
	public GameStart LetsGooScreen;

	public ControladorAsset mainManager;


	@Override
	public void create() {
		this.mainManager = new ControladorAsset();
		this.LetsGooScreen = new GameStart(this);
		this.SaMataoScreen = new GameOver(this);
		this.juego = new GameScreen(this);
		setScreen(juego);


	}
}
