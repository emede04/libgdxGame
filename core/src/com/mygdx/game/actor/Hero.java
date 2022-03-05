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
import com.mygdx.game.Constantes.*;
public class Hero extends Actor {

    public float hero_width;
    public float hero_height;
    public static Vector2 posicion;

    private static boolean canJump = false;
    public static final int VIVO = 0;
    public static final int MUERTO = 1;
    private static final float JUM_SPEED = 6.8f;
    private final Animation<TextureRegion> animation;
    private final World world;
    private Fixture fixture;

    private float tiempo;
    private Body hero_body;


    public Hero(World w, Animation<TextureRegion> a, Vector2 pos) {
        this.animation = a;
        this.world = w;
        tiempo = 0f;
        hero_width = 0.6f;
        hero_height = 0.6f;
        posicion = pos;
        createBody(posicion);
        createFixture();
    }

    public void createBody(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);//la fisica lleva la misma posicion que el dibujo
        bodyDef.type = BodyDef.BodyType.DynamicBody;//dinamico es el que se mueve y le afecta la fisica
        this.hero_body = this.world.createBody(bodyDef);

    }

    public void createFixture() {
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox((hero_height - hero_width / 2.4f) / 2, hero_height / 2.1f);
        this.fixture = this.hero_body.createFixture(boxShape, 0);
        //Asinga un nombre a la fisica para poder hacerle luego referencia a la hora de ralizar las colisiones
        boxShape.dispose();
    }

    public void detach() {
        this.hero_body.destroyFixture(this.fixture);
        this.world.destroyBody(this.hero_body);
    }

    @Override
    //Dibuja el actor en pantalla en relacion a la posicion indicada
    public void draw(Batch batch, float parentAlpha) {
        setPosition(hero_body.getPosition().x - (hero_width / 2), hero_body.getPosition().y - (hero_height / 2));
        batch.draw(this.animation.getKeyFrame(tiempo, true), getX(), getY(), hero_width, hero_height);
        tiempo += Gdx.graphics.getDeltaTime(); //acumula el delta que le indicamos en AssetMan

    }

}
