package com.mygdx.deneme.balloon;

import com.mygdx.deneme.PatlatGitsin;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class YellowBalloon extends Balloon {

    public boolean showYellow;
    public Timer yellowTimer;
    public Timer yellowTimer2;
    public boolean teleport = true;
    Random rand = new Random();

    public YellowBalloon(float x, float y, int screenWidth, int screenHeight, String textureName, int point) {
        super(x, y, screenWidth, screenHeight, textureName, point);

        showYellow = true;

        createYellowTimer();

    }

    public void createYellowTimer() {
        yellowTimer = new Timer();
        yellowTimer2 = new Timer();
        Random random = new Random();

        yellowTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                showYellow = true;
                yellowTimer2.cancel();
                yellowTimer2 = new Timer();

                yellowTimer2.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        showYellow = false;
                        yellowTimer2.cancel();
                        teleport();
                    }
                }, 1000, 1000);

            }

        }, 1000, 1000 + random.nextInt(3000));
    }


    @Override
    public void render() {

        if (img != null && showYellow) {
            batch.begin();
            batch.draw(img, x, y, width, height);
            batch.end();
        }
    }

    @Override
    public void hareketEt(float moveDelta) {

        if (moveDelta < 100) {
            teleport = false;
        } else {
            teleport = true;
        }

    }

    public void teleport() {

        if (!PatlatGitsin.iceShowing) {
            x = rand.nextInt(screenWidth - width);
            y = rand.nextInt(screenHeight - height);

            rectangle.setX(x);
            rectangle.setY(y);
        }

    }

}

