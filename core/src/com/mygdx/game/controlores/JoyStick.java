package com.mygdx.game.controlores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.MainGame;
import static  com.mygdx.game.Constantes.*;
import com.mygdx.game.pantalla.GameScreen;

    public class JoyStick {
        Viewport viewport;
        Stage stage;
        boolean upPressed, downPressed, leftPressed, rightPressed, tirito;
        OrthographicCamera cam;

        public JoyStick(){
            cam = new OrthographicCamera();
            viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, cam);
            stage = new Stage(viewport, GameScreen.batch);
            stage.addListener(new InputListener(){

                @Override
                public boolean keyDown(InputEvent event, int keycode) {
                    switch(keycode){
                        case Input.Keys.UP:
                            upPressed = true;
                            break;
                        case Input.Keys.DOWN:
                            downPressed = true;
                            break;
                        case Input.Keys.LEFT:
                            leftPressed = true;
                            break;
                        case Input.Keys.RIGHT:
                            rightPressed = true;
                            break;
                    }
                    return true;
                }

                @Override
                public boolean keyUp(InputEvent event, int keycode) {
                    switch(keycode){
                        case Input.Keys.UP:
                            upPressed = false;
                            break;
                        case Input.Keys.DOWN:
                            downPressed = false;
                            break;
                        case Input.Keys.LEFT:
                            leftPressed = false;
                            break;
                        case Input.Keys.RIGHT:
                            rightPressed = false;
                            break;
                    }
                    return true;
                }
            });

            Gdx.input.setInputProcessor(stage);




            Table table = new Table();
            table.bottom().left();
            Image upImg = new Image(new Texture("flatDark25.png"));
            upImg.setSize(70, 70);

            upImg.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    upPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    upPressed = false;
                }
            });

            Image downImg = new Image(new Texture("flatDark26.png"));
            downImg.setSize(70, 70);
            downImg.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    downPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    downPressed = false;
                }
            });

            Image rightImg = new Image(new Texture("flatDark24.png"));
            rightImg.setSize(70, 70);
            rightImg.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    rightPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    rightPressed = false;
                }
            });

            Image leftImg = new Image(new Texture("flatDark23.png"));
            leftImg.setSize(70, 70);
            leftImg.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    leftPressed = true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    leftPressed = false;
                }
            });


            Image ShooterImg = new Image(new Texture("flatDark23.png"));
            ShooterImg.setSize(70,70);
            table.add();
            table.add(upImg).size(upImg.getWidth(), upImg.getHeight());
            table.add();
            table.row().pad(5, 5, 5, 5);
            table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
            table.add();
            table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
            table.row().padBottom(5);
            table.add();
            table.add(downImg).size(downImg.getWidth(), downImg.getHeight());
            table.add();

            stage.addActor(table);

            Table table1 = new Table();
            table1.top().right();
            table1.add(ShooterImg).size(ShooterImg.getWidth(),ShooterImg.getHeight());


        }

        public void draw(){
            stage.draw();
        }

        public boolean isUpPressed() {
            return upPressed;
        }

        public boolean isDownPressed() {
            return downPressed;
        }

        public boolean isLeftPressed() {
            return leftPressed;
        }

        public boolean isRightPressed() {
            return rightPressed;
        }

        public boolean isTirito(){
            return tirito;
        }

        public void resize(int width, int height){
            viewport.update(width, height);
        }

        public OrthographicCamera getCam() {
            return cam;
        }

        public void setCam(OrthographicCamera cam) {
            this.cam = cam;
        }

    }

