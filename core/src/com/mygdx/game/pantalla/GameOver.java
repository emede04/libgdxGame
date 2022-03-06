package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.WORLD_HEIGHT;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;



    public class GameOver extends Pantalla {
        private final World WORLD;
        private Stage stage;
        private Image background = new Image();

        public GameOver(MainGame m) {
            super(m);
            this.WORLD = new World(new Vector2(0, 0), false);
            FitViewport fitViewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
            this.stage = new Stage(fitViewport);
            this.stage.addActor(main.mainManager.addBackgroundPierdes());


        }


        // Añadir fondo de pantalla
        public void addBackground() {
            this.stage.addActor(main.mainManager.addBackgroundPierdes());
            background.setPosition(0, 0);
            background.setSize(WORLD_WIDTH, WORLD_HEIGHT);
            this.stage.addActor(this.background);
        }



        @Override
        public void render(float delta) {
            addBackground();

            this.stage.draw();

            if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(62)) {
                this.background.remove();

                main.setScreen(main.LetsGooScreen);

            }
        }
        public void show() {
            addBackground();
        }

        @Override
        public void hide() {
            super.hide();
        }

        public void dispose() {
            super.dispose();
            this.stage.dispose();

        }

    }



