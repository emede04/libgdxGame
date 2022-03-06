package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.WORLD_HEIGHT;
import static com.mygdx.game.Constantes.WORLD_WIDTH;
import com.badlogic.gdx.utils.Array;

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
import com.mygdx.game.actor.Abeja;
import com.mygdx.game.actor.Hero;
import com.mygdx.game.controlores.JoyStick;

public class GameScreen extends Pantalla implements ContactListener {
    private final Music musiquita;
    private Hero hero;
    private Body suelo;
    private Body cielo;
    private final Stage stage;
    private World world;
    private float spawnea;

    public static SpriteBatch batch;
    public JoyStick joyStick;
    private final Array<Abeja> arrayAbejas;

    public GameScreen(MainGame m) {
        super(m);
        this.world = new World(new Vector2(0, -9.8f), true); //fisicas
        this.world.setContactListener(this);
        FillViewport fillViewport = new FillViewport(WORLD_WIDTH,WORLD_HEIGHT);
        arrayAbejas = new Array<>();
        this.stage = new Stage(fillViewport);
        this.musiquita = m.mainManager.getMusica();
        this.stage.addActor(m.mainManager.addBackground());
        createSuelo();
        this.spawnea = 0f;
        batch = new SpriteBatch();
        joyStick = new JoyStick();

        puntospuntos();
    }

    private void puntospuntos() {


    }

    private void SpawneaAbejas(float delta){
        if(Hero.esta ==Hero.VIVO){
            this.spawnea *= delta;
            if(this.spawnea >= 1){
              this.spawnea -= 2;
              Abeja tabarrico = new Abeja(this.world, main.mainManager.getBichoVolandoAbeja());
              arrayAbejas.add(tabarrico);
            }
        }
    }
    private void BorroAbejas(){
         for (Abeja b : this.arrayAbejas) {
                if (!this.world.isLocked()) {
                    if (b.fuera()) {
                        b.detach();//eliminamos parte fisica
                        b.remove();//eliminamos parte grafica
                        this.arrayAbejas.removeValue(b, false);
                    }
                }
            }


        }



    public void show() {
        addHero(this.world, main.mainManager.getHeroAnimation(), new Vector2(WORLD_WIDTH / 2f, 2F));
        addAbeja(this.world,main.mainManager.getBichoVolandoAbeja());
        this.musiquita.setVolume(0.20f);
        this.musiquita.play();

    }

    public Abeja addAbeja(World world, Animation<TextureRegion> animation){
        Abeja abe;
        abe = new Abeja(world, animation);
        this.stage.addActor(this.hero);
        this.stage.addActor(abe);

        return abe;
    }


    public Actor addHero(World world, Animation<TextureRegion> animation, Vector2 vector) {
        hero = new Hero(world, animation, vector);
        this.stage.addActor(this.hero);
        return hero;
    }

    @Override
    public void render(float delta) {
        input();

        this.stage.act();
         this.stage.draw();
        joyStick.draw();
        update();
    }


    public void update() {
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

