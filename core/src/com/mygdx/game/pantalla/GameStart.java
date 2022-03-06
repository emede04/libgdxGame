package com.mygdx.game.pantalla;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MainGame;

import static com.mygdx.game.Constantes.*;


public class GameStart extends Pantalla {
        private final Stage stage;
        private final Music musica;
        public GameStart(MainGame m) {
            super(m);

            FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);
            this.stage = new Stage(fillViewport);
            this.musica = m.mainManager.getMusica();
        }

    public void MiImagen(){


    }





    public void Pulsa(){
        Image a = new Image(main.mainManager.getInicio());
        a.sizeBy(4f);
        a.setPosition(WORLD_WIDTH,WORLD_HEIGHT);
        a.setPosition(5f,5f);
        this.stage.addActor(a);
    }



    @Override
    public void render(float delta) {
        super.render(delta);
        this.stage.draw();
        musica.play();
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(62)) {
            main.setScreen(new GameScreen(main));
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

        this.stage.dispose();
    }
}



