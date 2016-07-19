package com.mygdx.deneme.balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.deneme.PatlatGitsin;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Ice extends Balloon {

    Random rand = new Random();
    public boolean showIce;

    public Timer iceTimer;


    public Ice(float x, float y, int screenWidth, int screenHeight, String textureName, Texture texture, int point) {
        super(x, y, screenWidth, screenHeight, textureName, point);

        showIce = false;

        iceTimer = new Timer();
        Random random = new Random();


        iceTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                showIce = true;
            }
        }, 10000, 40000);

    }
    public void render(){

        if(img != null && showIce){
            batch.begin();
            batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }

    }

    @Override
    public void hareketEt(float moveDelta) {

        // Hareket etmeyeceği için hareketEt fonksiyonunu boş bıraktık

    }

    @Override
    public void playSound() {
        if (PatlatGitsin.isPlaying == true)
            PatlatGitsin.iceeffect.play();
    }


}
