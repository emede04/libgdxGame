package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.WORLD_HEIGTH;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.actor.Hero;
import com.mygdx.game.controlores.JoyStick;

public class GameScreen extends Pantalla implements ContactListener {
    private final Music musiquita;
    private Hero hero;
    private Body suelo;
    private Body cielo;
    private final Stage stage;
    private World world;
    private OrthographicCamera camara;
    public static SpriteBatch batch;
    public JoyStick joyStick;
    public static final float PPM = 100;

    public GameScreen(MainGame m) {

        super(m);

        this.world = new World(new Vector2(0, -9.8f), true); //fisicas
        batch = new SpriteBatch();
        this.world.setContactListener(this);
        FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGTH);
        fillViewport.getTopGutterHeight();
        this.stage = new Stage(fillViewport);
        this.musiquita = m.mainManager.getMusica();
        this.stage.addActor(m.mainManager.addBackground());
        joyStick = new JoyStick();

        createSuelo();
        preparaCamara();

    }


    public void show() {
        addHero(this.world, main.mainManager.getHeroAnimation(), new Vector2(WORLD_WIDTH / 2f, 2F));
        this.musiquita.setVolume(0.20f);
        this.musiquita.play();
    }


    public Actor addHero(World world, Animation<TextureRegion> animation, Vector2 vector) {
        hero = new Hero(world, animation, vector);
        this.stage.addActor(this.hero);
        return hero;
    }


    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        this.stage.act();
        this.world.step(delta, 1, 2);
        this.stage.draw();
        joyStick.draw();
        preparaCamara();
    }


    public void preparaCamara() {
        this.camara = new OrthographicCamera();
        this.camara.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.camara.update();


    }

    public void update(float deltaTime) {
        input();
        world.step(1 / 60f, 6, 2);
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


    public void createSuelo() {
        BodyDef bodyDef = new BodyDef();
        //la fisica lleva la misma posicion que el dibujo,
        // como va ser un vector el cuerpo no necesita posicion porque se le asigan con los vectores
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.suelo = this.world.createBody(bodyDef);
        EdgeShape boxShape = new EdgeShape();
        boxShape.set(-2F, 0.2F, WORLD_WIDTH, 0.2F);
        Fixture fixture_floor = this.suelo.createFixture(boxShape, 2);
        fixture_floor.setUserData("SUELO");
        boxShape.dispose();
    }
    public void input() {
        if (joyStick.isRightPressed())
            hero.getHero_body().setLinearVelocity(new Vector2(1, 0));
        else if (joyStick.isLeftPressed())
            hero.getHero_body().setLinearVelocity(new Vector2(-1, 0));
        if (joyStick.isUpPressed() && hero.getHero_body().getLinearVelocity().y == 0)
            hero.getHero_body().applyLinearImpulse(new Vector2(0, 2), hero.getHero_body().getWorldCenter(), true);

            //es decir ya esta en el aire
            if (joyStick.isLeftPressed() && (joyStick.isUpPressed())) {
                hero.getHero_body().applyForce(new Vector2(-4, +1), hero.getHero_body().getWorldCenter(), false);

            }
            if (joyStick.isRightPressed() && (joyStick.isUpPressed())) {
                hero.getHero_body().applyForce(new Vector2(+4,+1),hero.getHero_body().getWorldCenter(), true);

            }


        }
    }


/*
*
*       suelo plano
        BodyDef bdef = new BodyDef();
        bdef.position.set(WORLD_HEIGTH, 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WORLD_WIDTH, 20 / PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        fdef.shape = shape;
        b2body.createFixture(fdef);
*
*
*
*
* */
