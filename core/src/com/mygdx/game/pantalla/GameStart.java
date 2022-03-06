package com.mygdx.game.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MainGame;

import static com.mygdx.game.Constantes.*;


public class GameStart extends Pantalla {
    private final World WORLD;
    Image a;
    private final Stage stage;
        private final Music musica;

        public GameStart(MainGame m) {
            super(m);
            this.WORLD = new World(new Vector2(0, 0), false);
            FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);// Esto es la camara
            this.stage = new Stage(fillViewport);

             a = new Image(main.mainManager.getInicio());

            this.musica = m.mainManager.getMusica();
            this.stage.addActor(m.mainManager.addBackgroundBienvenido());

        }






    public void Pulsa(){
        a.sizeBy(3f);
        a.setPosition(WORLD_WIDTH,WORLD_HEIGHT);
        a.setPosition(8f,5f);
        this.stage.addActor(a);
    }



    @Override
    public void render(float delta) {
        super.render(delta);
        this.stage.draw();
        musica.play();
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(62)) {
            main.setScreen(new GameScreen(main));
            dispose();
        }
    }

    @Override
    public void show() {
        super.show();
        this.musica.setLooping(true);

        Pulsa();

    }

    @Override
    public void dispose() {
        super.dispose();
        a.remove();

    }
}



