package com.mygdx.game.atlas;

import static com.mygdx.game.Constantes.ATLAS_MAP;
import static com.mygdx.game.Constantes.HERO;
import static com.mygdx.game.Constantes.SCREEN_HEIGHT;
import static com.mygdx.game.Constantes.SCREEN_WIDTH;
import static com.mygdx.game.Constantes.WORLD_HEIGTH;
import static com.mygdx.game.Constantes.WORLD_WIDTH;
import static com.mygdx.game.Constantes.musica_fondo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Constantes;
import com.mygdx.game.actor.Hero;


public class ControladorAsset {

    private final AssetManager manager;
    private final TextureAtlas atlasMundo;

    public ControladorAsset(){
        System.out.println("hola");
        this.manager = new AssetManager();
        manager.load(ATLAS_MAP,TextureAtlas.class);
        manager.load(musica_fondo,Music.class);


        manager.finishLoading();
        atlasMundo = manager.get(ATLAS_MAP);



    }
    public Image addBackground(){
       Image fondo;
       fondo = new Image(getBackground());
        System.out.println(fondo);
       fondo.setPosition(0,0);
       fondo.setSize(WORLD_WIDTH,WORLD_HEIGTH);
       return fondo;
    }

    public TextureRegion getBackground() {
        System.out.println((float) this.atlasMundo.findRegion(Constantes.fondo).getRegionHeight());
        System.out.println((float) this.atlasMundo.findRegion(Constantes.fondo).getRegionWidth());
        return this.atlasMundo.findRegion(Constantes.fondo);

    }

    public Music getMusica(){
        return manager.get(musica_fondo);
    }


    public Animation<TextureRegion> getHeroAnimation(){
        return new Animation<TextureRegion>(0.25f,
                atlasMundo.findRegion("hero3"),
                atlasMundo.findRegion("hero4"),
                atlasMundo.findRegion("hero5")
                );
    }


}
