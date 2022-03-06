package com.mygdx.game.pantalla;

import static com.mygdx.game.Constantes.ENEMIGO;
import static com.mygdx.game.Constantes.HERO;
import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.SUELO;
import static com.mygdx.game.Constantes.WORLD_HEIGHT;
import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

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
import java.util.Iterator;

public class GameScreen extends Pantalla implements ContactListener {

    private float tiempo;
    private static float ratio = 0.5f; //el ratio de bichos que spawnean
    private final Music musiquita;
    private Hero hero;
    private Body suelo;
    private final Stage stage;
    private OrthographicCamera CamaraPuntos;
    private OrthographicCamera camaraFps;
    private BitmapFont fuente;

    private World world;
    public Enemigo miEnemigo;
    private ArrayList<tirito> balas;
    public static SpriteBatch batch;
    //Objeto joystick
    public JoyStick joyStick;
    public int puntos =0;
    int roll;
    private final ArrayList<Enemigo> entidades;

    public GameScreen(MainGame m) {
        super(m);
        this.world = new World(new Vector2(0f, -9.8f), false); //le pongo graveda de -9,8
        this.world.setContactListener(this);
        roll= 0;
        FillViewport fillViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT);

        this.stage = new Stage(fillViewport);
        this.balas = new ArrayList<tirito>();
        this.entidades = new ArrayList<Enemigo>();
        this.tiempo = 0F;
        musiquita = main.mainManager.getMusica();
        batch = new SpriteBatch();
        //Actor Joystick
        joyStick = new JoyStick();
        init();


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
    public void input() {     //le añado las fisicas correspondientes

        if (joyStick.isRightPressed())
            hero.getHero_body().setLinearVelocity(new Vector2(1, 0));
        else if (joyStick.isLeftPressed())
            hero.getHero_body().setLinearVelocity(new Vector2(-1, 0));
        if (joyStick.isUpPressed() && hero.getHero_body().getLinearVelocity().y == 0)
            hero.getHero_body().applyLinearImpulse(new Vector2(0, 2), hero.getHero_body().getWorldCenter(), true);
        //es decir ya esta en el aire
        if (joyStick.isLeftPressed() && (joyStick.isUpPressed())) {
            hero.getHero_body().applyForce(new Vector2(-4, +1), hero.getHero_body().getWorldCenter(), false);

        }                //para que salte en diagonal y caiga, aunque la idea es que vuelve
        if (joyStick.isRightPressed() && (joyStick.isUpPressed())) {
            hero.getHero_body().applyForce(new Vector2(+4, +1), hero.getHero_body().getWorldCenter(), true);

        }

        if(joyStick.isTirito()){
            hero.cogelaescopeta(); //si me da tiempo a terminarlo queda chulo si no pos nada
        }




    }


    //para añadir enemigos

    public void addEnemigo(float delta) {
        int numberRamdon = MathUtils.random(1, 3);
        Animation<TextureRegion> Entidad;
        float ry = MathUtils.random(3f, 4f);
        float rx = MathUtils.random(1f, 2f);
        Enemigo enemigo = null;
        if (this.hero.esta == Hero.VIVO)
            this.tiempo += delta;
        if (this.tiempo >= ratio) {
            this.tiempo -= ratio;
            switch (numberRamdon) {
                case 1:
                    Entidad = main.mainManager.getBichoVolandoAbeja();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH /rx, ry));
                    break;

                case 2:
                    Entidad = main.mainManager.getMoscardaVolando();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH /(rx), ry));
                    break;

                case 3:
                    Entidad = main.mainManager.getBabosaAnimation();
                     miEnemigo = new Enemigo(this.world, Entidad, new Vector2(WORLD_WIDTH /rx, ry));
                    break;

            }

            entidades.add(miEnemigo);
            this.stage.addActor(miEnemigo);
        }
    }



    public void hide(){
        this.miEnemigo.detach();
        this.miEnemigo.remove();
        this.hero.detach();
        this.hero.remove();
            }


        //metodo para spawnear al heroe
    public Actor SpawnHero(World world, Animation<TextureRegion> animation, Vector2 vector) {
        hero = new Hero(world, animation, vector);
        this.stage.addActor(this.hero);
        return hero;
    }



    public void show() {
        this.stage.addActor(main.mainManager.addBackground());
        createSuelo();
        SpawnHero(this.world, main.mainManager.getHeroAnimation(), new Vector2(WORLD_WIDTH / 4f, 2F));

        this.musiquita.setVolume(0.20f);
       this.musiquita.play();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        addEnemigo(delta);

        this.world.step(delta, 6, 2);

        this.stage.act();

        this.stage.draw();
        joyStick.draw();
        input();   //los botones

        puntos = entidades.size();
        //por ahora esto es asi por que

        //camara con el numero de entes honestamente he estado esperando tod0 el rato a ver que entraba y que no
        this.stage.getBatch().setProjectionMatrix(this.CamaraPuntos.combined);
        this.stage.getBatch().begin();
        this.fuente.draw(this.stage.getBatch(), "entes: "+this.puntos,SCREEN_WIDTH/3f,SCREEN_HEIGHT/4f);
        this.stage.getBatch().end();

        //fps
        this.stage.getBatch().setProjectionMatrix(this.CamaraPuntos.combined);
        this.stage.getBatch().begin();
        this.fuente.draw(this.stage.getBatch(), "fps:" + Gdx.graphics.getFramesPerSecond(), SCREEN_WIDTH / 8f, SCREEN_HEIGHT-2f);
        this.stage.getBatch().end();

    }






    //metodos colisiones:
        //me falta los disparos

            //compruebo si los objetos se tocan
    public boolean areColider(Contact contact, Object objA, Object objB){
        return (contact.getFixtureA().getUserData().equals(objA) && contact.getFixtureB().getUserData().equals(objB)) ||
                (contact.getFixtureA().getUserData().equals(objB) && contact.getFixtureB().getUserData().equals(objA));
    }

    @Override
    public void beginContact(Contact contact) {


        if (areColider(contact, HERO, ENEMIGO)) {
            Animation<TextureRegion> samatao = main.mainManager.getMuerte();
            hero.setAnimation(samatao);
             //en el caso de que si quito al heroe, bor
            //para eliminar todos los cuerpos de mi enemigos si no me da error de que el mundo esta locked y es horrorso, principal motivo por el que mi bicho no pega tiros

            Iterator<Enemigo> i = entidades.iterator();
            if(!world.isLocked()){
                while(i.hasNext()){
                    Enemigo e = i.next();
                    world.destroyBody(e.getEnemigo_body());
                }
            }
            this.musiquita.stop();
          this.stage.addActor(main.mainManager.addBackgroundPierdes());

            this.stage.addAction(Actions.sequence(
                    Actions.delay(2.5f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            main.setScreen(main.samataoa);
                        }


                    })
            ));

        }

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


    public void init(){
        this.fuente = this.main.mainManager.getFuente();
        puntos = 0;
        this.fuente.getData().scale(WORLD_WIDTH/20F);
        //considerando que mi mundo es a lo largo y
        //los asssets son enanos
         CamaraPuntos = new OrthographicCamera();
        this.CamaraPuntos.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.CamaraPuntos.update();



        this.fuente = this.main.mainManager.getFuente();
        this.camaraFps = new OrthographicCamera();
        this.camaraFps.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.camaraFps.update();
   }


    @Override
    //Se llama para liberar los recursos de la pantalla
    public void dispose() {
        super.dispose();
        this.stage.dispose();
    }
}






