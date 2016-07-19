package com.mygdx.deneme.balloon;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class GreenBalloon extends Balloon {

    public int countGreen;
    Random rand = new Random();

    public GreenBalloon(float x, float y, int screenWidth, int screenHeight, String textureName, Texture texture, int point) {
        super(x, y, screenWidth, screenHeight);

        this.img = texture;
        this.point = point;

        countGreen = rand.nextInt(20) + 1;
    }

    @Override
    public void hareketEt(float moveDelta) {

        if (flagY) {
            this.y = this.y + 700 * moveDelta;
        } else {
            this.y = this.y - 700 * moveDelta;
        }

        rectangle.setX(x);
        rectangle.setY(y);

    }

    @Override
    public void goBack() {
        flagY = !flagY;
    }

    public int getCountGreen() {
        return countGreen;
    }

    public void setCountGreen(int countGreen) {
        this.countGreen = countGreen;
    }
}


