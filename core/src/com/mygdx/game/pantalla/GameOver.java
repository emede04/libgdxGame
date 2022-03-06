package com.mygdx.game.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.controlores.JoyStick;

import static com.mygdx.game.Constantes.*;


public class GameOver extends Pantalla {
    private final World WORLD;
    private Stage stage;
    private Image gameOver;
    private Image background;

    public GameOver(MainGame m) {
        super(m);
        this.WORLD = new World(new Vector2(0, 0), false);
        FitViewport fitViewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.stage = new Stage(fitViewport);
        this.stage.addActor(m.mainManager.addBackground());

        // Añadir botones volver a intentar y menú
    }


    // Añadir fondo de pantalla
    public void addBackground() {
        this.background = new Image(main.mainManager.getGameOver());
        background.setPosition(0, 0);
        background.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage.addActor(this.background);
    }

    // Añadir imagén Get Ready
    public void addGetReady() {
        this.gameOver = new Image(main.mainManager.getGameOver());
        this.gameOver.setPosition(SCREEN_WIDTH / 2 - 400 / 2, 370);
        this.gameOver.setSize(400, 80);
        this.stage.addActor(this.gameOver);
    }
    public void render(float delta) {
        super.render(delta);
        this.WORLD.step(delta, 6, 2);

    }
    public void show() {
        addBackground();

    }
    public void dispose() {
        super.dispose();
        this.stage.dispose();
    }

}



