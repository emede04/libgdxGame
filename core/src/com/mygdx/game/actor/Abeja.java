package com.mygdx.game.actor;

import static com.mygdx.game.Constantes.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;






public class Abeja extends Actor {

    public float abeja_width;
    public float abeja_height;
    public static Vector2 posicion;

    private static boolean canJump = false;
    public static final int VIVO = 0;
    public static final int MUERTO = 1;
    private static final float JUM_SPEED = 6.8f;
    private final Animation<TextureRegion> animation;
    private final World world;
    private Fixture fixture;

    private float tiempo;



    private Body abeja_body;


    public Abeja(World w, Animation<TextureRegion> a) {
        this.animation = a;
        this.world = w;
        tiempo = 0f;
        abeja_width = 0.5f;
        abeja_height = 0.5F;
        posicion = new Vector2(WORLD_WIDTH / 2f, 2F);
        int vida = 0;
        createBody(posicion);
        createFixture();
    }

    public void createBody(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);//la fisica lleva la misma posicion que el dibujo
        bodyDef.type = BodyDef.BodyType.DynamicBody;//dinamico es el que se mueve y le afecta la fisica
        this.abeja_body = this.world.createBody(bodyDef);

    }

    public void createFixture() {
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox((abeja_height - abeja_width / 2.4f) / 2, abeja_height / 2.1f);
        this.fixture = this.abeja_body.createFixture(boxShape, 0);
        //Asinga un nombre a la fisica para poder hacerle luego referencia a la hora de ralizar las colisiones
        boxShape.dispose();
    }

    public void detach() {
        this.abeja_body.destroyFixture(this.fixture);
        this.world.destroyBody(this.abeja_body);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(abeja_body.getPosition().x - (abeja_width / 2), abeja_body.getPosition().y - (abeja_height / 2));
        batch.draw(this.animation.getKeyFrame(tiempo, true), getX(), getY(), abeja_width, abeja_height);
        tiempo += Gdx.graphics.getDeltaTime();

    }
    public boolean fuera() {
        return this.abeja_body.getPosition().x <= -2f;
    }



}
