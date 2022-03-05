package com.mygdx.game.actor;

import static com.mygdx.game.Constantes.HERO_FIXTURE;

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
    //constantes
    public static final int STATE_NORMAL = 1;
    private static final float JUMP_SPEED = 6.8f;
    public static final int STATE_DIE = 0;
    public float hero_width;
    public static float hero_height;
    public static Vector2 pos;
    //estado del actor;
    private static boolean canJump = false;
    public static int  estado;
    private Body Body_Hero;

    //animacion
    private final Animation<TextureRegion> HeroAnimation;
    private World world;
    private Fixture fixture;

   public Hero(Body body_hero, Animation<TextureRegion> heroAnimation, World world, Fixture fixture){
       Body_Hero = body_hero;

       HeroAnimation = heroAnimation;
       this.world = world;
       this.fixture = fixture;
   }
   public void CreateBody(Vector2 posi){
       BodyDef bodyDef = new BodyDef();
       bodyDef.position.set(posi);
       bodyDef.type = BodyDef.BodyType.DynamicBody;
   }

   public void createFixture(){
        PolygonShape shape = new PolygonShape();
       shape.setAsBox((hero_height - hero_width / 2.4f) / 2, hero_height / 2.1f);
        this.fixture = this.Body_Hero.createFixture(shape,0);
        this.fixture.setUserData(HERO_FIXTURE);



   }
   public void setSalta(boolean siono){
       canJump = siono;

   }

   //acciones a realizar
   public void act(float delta){

   }
   public void draw(Batch b,float parent){
        setPosition(Body_Hero.getPosition().x - (hero_width/2),Body_Hero.getPosition().y -(hero_height/2));
        b.draw(this.HeroAnimation.getKeyFrame(estado,true),getX(), getY(), hero_width, hero_width);
        if(estado == STATE_NORMAL){
            estado += Gdx.graphics.getDeltaTime();
        }


   }



}
