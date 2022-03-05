package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.WORLD_HEIGTH;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.actor.Hero;

public class GameScreen extends Pantalla implements ContactListener {
   private Stage stage;
   private World world;
   private Hero hero;
   private Body suelo;

   //camara
   private OrthographicCamera fuente;
   private BitmapFont score;
   public static int puntuacion;

    private BitmapFont fps;
    private OrthographicCamera cFps;


    public GameScreen(MainGame m) {
        super(m);

    }











    public boolean sanmataopaco(Contact contact, Object a, Object b) {
        return (contact.getFixtureA().getUserData().equals(a) && contact.getFixtureB().getUserData().equals(b)
                || contact.getFixtureA().getUserData().equals(b) && contact.getFixtureB().getUserData().equals(a));
    }


  //metodos de las colisiones
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
public void InitComponents(){
    FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGTH);// Esto es la camara
    this.stage = new Stage(fillViewport);
    this.stage.addActor(main.mainManager.addBackground());



}

}
