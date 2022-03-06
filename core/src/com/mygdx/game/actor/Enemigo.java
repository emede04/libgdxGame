package com.mygdx.game.actor;

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
import com.mygdx.game.Constantes;


public class Enemigo extends Actor {

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



    private Body enemigo_body;


    public Enemigo(World w, Animation<TextureRegion> a, Vector2 pos) {
        this.animation = a;
        this.world = w;
        tiempo = 0f;
        abeja_width = 0.3f;
        abeja_height = 0.3f;
        posicion = pos;
        int vida = 0;
        createBody(posicion);
        createFixture();
    }

    public void createBody(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.enemigo_body = this.world.createBody(bodyDef);

    }

    public void createFixture() {
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox((abeja_height - abeja_width / 2.4f) / 2, abeja_height / 2.1f);
        this.fixture = this.enemigo_body.createFixture(boxShape, 0);
        this.fixture.setUserData(Constantes.ENEMIGO);
        boxShape.dispose();
    }

    public void detach() {
        this.enemigo_body.destroyFixture(this.fixture);
        this.world.destroyBody(this.enemigo_body);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(enemigo_body.getPosition().x - (abeja_width / 2), enemigo_body.getPosition().y - (abeja_height / 2));
        batch.draw(this.animation.getKeyFrame(tiempo, true), getX(), getY(), abeja_width, abeja_height);
        tiempo += Gdx.graphics.getDeltaTime();

    }
    public boolean fuera() {
        return this.enemigo_body.getPosition().x <= -2f;
    }



}
