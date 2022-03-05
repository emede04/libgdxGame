package com.mygdx.game.atlas;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ControladorAsset {
    private final AssetManager manager;
    private final TextureAtlas Mundo;
    private final TextureAtlas Hero;



    public ControladorAsset(){

    }

    public ControladorAsset(AssetManager manager, TextureAtlas mundo, TextureAtlas hero){

        this.manager = manager;
        Mundo = mundo;
        Hero = hero;
    }

    public Animation<TextureRegion> getHeroAnimation(){
        return new Animation<TextureRegion>(0.25f,
        Hero.findRegion("Hero_1"),
        Hero.findRegion("Hero_2"),
        Hero.findRegion("Hero_3"),
        Hero.findRegion("Hero_1")






        );
    }
    public Animation<TextureRegion> getEnemigoAnimation(){
        return new Animation<TextureRegion>(
                0.25f,
                Hero.findRegion("enemigo_1"),
                Hero.findRegion("enemigo_2"),
                Hero.findRegion("enemigo_3"),
                Hero.findRegion("enemigo_1")



        );
    }
    public Music getMusica(){


        return manager.get("MUSICA_DIRE");
    }

    public Sound getSoundImpacto() {
        return manager.get("golpe");
    }






}
