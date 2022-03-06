package com.mygdx.game.atlas;

import static com.mygdx.game.Constantes.ATLAS_BICHOS;
import static com.mygdx.game.Constantes.ATLAS_MAP;
import static com.mygdx.game.Constantes.ATLAS_Pantalla;
import static com.mygdx.game.Constantes.WORLD_HEIGHT;
import static com.mygdx.game.Constantes.WORLD_WIDTH;
import static com.mygdx.game.Constantes.musica_fondo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Constantes;


public class ControladorAsset {

    private final AssetManager manager;
    private final TextureAtlas atlasMundo;
    private final TextureAtlas atlasBichos;
    private final TextureAtlas atlasPantalla;

    public ControladorAsset() {
        this.manager = new AssetManager();
        manager.load(ATLAS_MAP, TextureAtlas.class);
        manager.load(musica_fondo, Music.class);
        manager.load(ATLAS_BICHOS, TextureAtlas.class);
        manager.load(ATLAS_Pantalla, TextureAtlas.class);

        manager.finishLoading();
        atlasMundo = manager.get(ATLAS_MAP);
        atlasBichos = manager.get(ATLAS_BICHOS);
        atlasPantalla = manager.get(ATLAS_Pantalla);


    }



     public TextureRegion getB() {
         return this.atlasPantalla.findRegion("button");


     }
    public Image addBackground(){
       Image fondo;
       fondo = new Image(getBackground());
        System.out.println(fondo);
       fondo.setPosition(0,0);
       fondo.setSize(WORLD_WIDTH,WORLD_HEIGHT);
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

    public Animation <TextureRegion> getBichoVolandoAbeja(){

        return new Animation<TextureRegion>(0.25f,
                atlasBichos.findRegion("abeja1"),
                atlasBichos.findRegion("abeja2")
                );
    }


    public Animation <TextureRegion> getMoscardaVolando(){
        return new Animation<TextureRegion>(0.25f,
                atlasBichos.findRegion("moscarda1"),
                atlasBichos.findRegion("moscarda2")
        );
    }

    public Animation<TextureRegion> getBabosaAnimation(){
        return new Animation<TextureRegion>(0.25f,
                atlasBichos.findRegion("babosa1"),
                atlasBichos.findRegion("babosa2")
        );
    }
    public Animation<TextureRegion> getMuerte(){
        return new Animation<TextureRegion>(0.25f,
                atlasMundo.findRegion("hero1"),
                atlasMundo.findRegion("hero2")
        );
    }

    public TextureRegion getInicio() {

            return this.atlasMundo.findRegion(Constantes.getReady);


    }
    public TextureRegion getGameOver(){
         return this.atlasPantalla.findRegion(Constantes.gameOver);

    }

    public BitmapFont getFont(){
        return null;
    }


    // public Animation<TextureRegion> getZombiePorqueno(){

  //  }


}
