package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.WORLD_HEIGTH;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.actor.Hero;

public class GameScreen extends Pantalla implements ContactListener {
    private final Music musiquita;
    private Hero hero;
    private Body suelo;
    private final Stage stage;
    private World world;
    private OrthographicCamera camara;

    public GameScreen(MainGame m) {

        super(m);
        this.world = new World(new Vector2(0, -9.8f), true);//el true hace que cuando el elemento ya no se va a mover, deja de ejercer fuerza
        this.world.setContactListener(this);
        FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGTH);// Esto es la camara
        this.stage = new Stage(fillViewport);
        this.musiquita = m.mainManager.getMusica();
        this.stage.addActor(m.mainManager.addBackground());
        createSuelo();
        preparaCamara();

    }



    public void show(){
        addHero(this.world, main.mainManager.getHeroAnimation(),new Vector2(WORLD_WIDTH/2.5F, 1.5f));
        this.musiquita.setVolume(0.20f);
        this.musiquita.play();
    }


    public Actor addHero(World world, Animation<TextureRegion> animation, Vector2 vector) {
        hero = new Hero(world, animation, vector);
        this.stage.addActor(this.hero);
        return hero;
    }


    public void render(float delta){
        this.stage.act();
        this.world.step(delta,1,2);
        this.stage.draw();

        preparaCamara();
    }


    public void preparaCamara(){
        this.camara = new  OrthographicCamera();
        this.camara.setToOrtho(false,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.camara.update();



    }


    @Override
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
    
    
    public void createSuelo(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.suelo = this.world.createBody(bodyDef);
        createFixtureFloor();
        
        
    }
    public void createFixtureFloor() {
        EdgeShape boxShape = new EdgeShape();
        boxShape.set(1f, 1f, WORLD_WIDTH, 1f);
        Fixture fixture_floor = this.suelo.createFixture(boxShape, 3);
        fixture_floor.setUserData("SUELO");
        boxShape.dispose();

    }
}
