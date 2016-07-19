package com.mygdx.deneme.balloon;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.deneme.PatlatGitsin;

import java.util.Random;


public class BlackBalloon extends Balloon {

    public int countBlack;

    Random rand = new Random();

    private double angle = 45.0;

    public BlackBalloon(float x, float y, int screenWidth, int screenHeight, String textureName, Texture texture, int point) {

        super(x, y, screenWidth, screenHeight);
        this.img = texture;

        countBlack = rand.nextInt(20) + 1;
        this.point = point;

    }

    @Override
    public void hareketEt(float moveDelta) {

        if (flagX) {
            this.x = this.x + (float) Math.sin(angle) * 700 * moveDelta;
        } else {
            this.x = this.x - (float) Math.sin(angle) * 700 * moveDelta;
        }

        if (flagY) {
            this.y = this.y + (float) Math.cos(angle) * 700 * moveDelta;
        } else {
            this.y = this.y - (float) Math.cos(angle) * 700 * moveDelta;
        }

        rectangle.setX(x);
        rectangle.setY(y);

    }

    @Override
    public void goBack() {
        flagX = !flagX;
        flagY = !flagY;
    }

    @Override
    public void playSound() {
        if (PatlatGitsin.isPlaying == true)
            PatlatGitsin.blackblasteffect.play();
    }

    public int getCountBlack() {
        return countBlack;
    }

    public void setCountBlack(int countBlack) {
        this.countBlack = countBlack;
    }
}


