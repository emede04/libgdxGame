package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.ENEMIGO;
import static com.mygdx.game.Constantes.HERO;
import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.SUELO;
import static com.mygdx.game.Constantes.WORLD_HEIGHT;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.actor.Enemigo;
import com.mygdx.game.actor.Hero;
import com.mygdx.game.actor.tirito;
import com.mygdx.game.controlores.JoyStick;

import java.util.ArrayList;

public class GameScreen extends Pantalla implements ContactListener {

    private float tiempo;
    private static float ratio = 3.5f;
    private final Music musiquita;
    private Hero hero;
    private Body suelo;
    private final Stage stage;
    private OrthographicCamera CamaraPuntos;
    private OrthographicCamera fps;
    private BitmapFont fuente;
    private World world;
    public Enemigo miEnemigo;
    private ArrayList<tirito> balas;
    public static SpriteBatch batch;
    public JoyStick joyStick;
    public int puntos;
    int roll;
    private final Array<Enemigo> entidades;

    public GameScreen(MainGame m) {
        super(m);
        this.world = new World(new Vector2(0f, -9.8f), true); //fisicas
        this.world.setContactListener(this);
        roll= 0;
        FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);

        this.stage = new Stage(fillViewport);
        this.balas = new ArrayList<tirito>();
        this.entidades = new Array<Enemigo>();
        this.tiempo = 0F;

        musiquita = main.mainManager.getMusica();
        batch = new SpriteBatch();
        joyStick = new JoyStick();

        //camara con los puntos
    }


    //creo el suelo
    public void createSuelo() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.suelo = this.world.createBody(bodyDef);
        EdgeShape boxShape = new EdgeShape();
        boxShape.set(-2F, 0.2F, WORLD_WIDTH, 0.2F);
        Fixture fixture_floor = this.suelo.createFixture(boxShape, 2);
        fixture_floor.setUserData(SUELO);
        boxShape.dispose();
    }

    //asigno la fisica a los controles
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
            hero.getHero_body().applyForce(new Vector2(+4, +1), hero.getHero_body().getWorldCenter(), true);

        }

        if(joyStick.isTirito()){
            hero.cogelaescopeta();
        }




    }


    public void addEnemigo(float delta) {
        int numberRamdon = MathUtils.random(1, 3);
        Animation<TextureRegion> Entidad;
        float posRandomY = MathUtils.random(3f, 4f);
        float posRandomX = MathUtils.random(0f, 4f);
        Enemigo enemigo = null;
        if (this.hero.esta == Hero.VIVO)
            this.tiempo += delta;
        if (this.tiempo >= ratio) {
            this.tiempo -= ratio;
            switch (numberRamdon) {
                case 1:
                    Entidad = main.mainManager.getBichoVolandoAbeja();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH - posRandomX, posRandomY));
                    break;

                case 2:
                    Entidad = main.mainManager.getMoscardaVolando();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH / posRandomX, posRandomY));
                    break;

                case 3:
                    Entidad = main.mainManager.getBabosaAnimation();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH / posRandomX, posRandomY));
                    break;

            }

            entidades.add(miEnemigo);
            this.stage.addActor(miEnemigo);
        }
    }






    public Actor addHero(World world, Animation<TextureRegion> animation, Vector2 vector) {
        hero = new Hero(world, animation, vector);
        this.stage.addActor(this.hero);
        return hero;
    }



    public void show() {
        this.stage.addActor(main.mainManager.addBackground());
        createSuelo();
        addHero(this.world, main.mainManager.getHeroAnimation(), new Vector2(WORLD_WIDTH / 4f, 2F));

        this.musiquita.setVolume(0.20f);
       this.musiquita.play();
    }


    @Override
    public void render(float delta) {
        addEnemigo(delta);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        joyStick.draw();
        input();

    }




    public void hide() {
        this.hero.detach();
        this.hero.remove();



    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }

    //metodos colisiones:

    public boolean areColider(Contact contact, Object objA, Object objB){
        return (contact.getFixtureA().getUserData().equals(objA) && contact.getFixtureB().getUserData().equals(objB)) ||
                (contact.getFixtureA().getUserData().equals(objB) && contact.getFixtureB().getUserData().equals(objA));
    }

    @Override
    public void beginContact(Contact contact) {

        if (areColider(contact, HERO, ENEMIGO)) {
            Animation<TextureRegion> samatao = main.mainManager.getMuerte();
            hero.setAnimation(samatao);
            this.musiquita.stop();
            for (Enemigo e : entidades) {
                entidades.removeValue(e, true);
            }

            this.stage.addAction(Actions.sequence(
                    Actions.delay(0.9f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            main.setScreen(main.SaMataoScreen);
                        }
                    })));
        };


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





    public void addScore(Contact contact){
        balas = null;



    }





}

