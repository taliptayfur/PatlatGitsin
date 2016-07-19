package com.mygdx.deneme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.deneme.screen.SplashScreen;

public class PatlatGitsin extends Game {

    public static Music menuost;
    public static Music desertost;
    public static Music forestost;
    public static Music winterost;

    public static Sound blasteffect;
    public static Sound blackblasteffect;
    public static Sound iceeffect;

    public static boolean isPlaying = true;

    public static boolean gamePlaying = false; //Level ekranında çalışacak

    public static boolean iceShowing = false;

    public static BitmapFont font;
    public static BitmapFont fontFinish;

    public static int totalScore;
    public static int level1Score;
    public static int level2Score;
    public static int level3Score;


    public static int[] poppedBalloons = new int[]{0, 0, 0, 0};

    SplashScreen splashScreen;

    public static Skin skin;

    @Override
    public void create() {

        skin = new Skin(Gdx.files.internal("mainmenu.json"), new TextureAtlas("mainmenu.pack"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/carterone.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        font = generator.generateFont(parameter);

        FreeTypeFontGenerator.FreeTypeFontParameter parameterFinish = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterFinish.size = 60;
        fontFinish = generator.generateFont(parameterFinish);
        generator.dispose();

        font.setColor(Color.RED);
        font.getData().setScale(2.0f);

        menuost = Gdx.audio.newMusic(Gdx.files.internal("musics/menuost.ogg"));

        desertost = Gdx.audio.newMusic(Gdx.files.internal("musics/desertost.ogg"));
        desertost.setVolume(0.7f);

        forestost = Gdx.audio.newMusic(Gdx.files.internal("musics/forestost.ogg"));
        forestost.setVolume(0.7f);

        winterost = Gdx.audio.newMusic(Gdx.files.internal("musics/winterost.ogg"));
        winterost.setVolume(0.7f);

        blasteffect = Gdx.audio.newSound(Gdx.files.internal("musics/blasteffect.ogg"));

        blackblasteffect = Gdx.audio.newSound(Gdx.files.internal("musics/blackblasteffect.ogg"));

        iceeffect = Gdx.audio.newSound(Gdx.files.internal("musics/iceeffect.ogg"));

        menuost.play();
        menuost.setLooping(true);

        splashScreen = new SplashScreen();
        setScreen(splashScreen);

    }
}
