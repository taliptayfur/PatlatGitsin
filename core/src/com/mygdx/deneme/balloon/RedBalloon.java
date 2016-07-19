package com.mygdx.deneme.balloon;

import java.util.Random;

public class RedBalloon extends Balloon {

    int redCount;
    Random random;

    public RedBalloon(float x, float y, int screenWidth, int screenHeight, String textureName, int point) {

        super(x, y, screenWidth, screenHeight, textureName, point);
        random = new Random();

        redCount = random.nextInt(10000);

    }

    @Override
    public void hareketEt(float moveDelta) {

        if (redCount > 0) {
            redCount -= 2000 * moveDelta;
        } else {
            flagX = !flagX;
            flagY = !flagY;

            redCount = random.nextInt(10000);
        }

        if (flagX) {
            this.x = this.x + 1000 * moveDelta;
        } else {
            this.x = this.x - 1000 * moveDelta;
        }

        if (flagY) {
            this.y = this.y + 1000 * moveDelta;
        } else {
            this.y = this.y - 1000 * moveDelta;
        }

        rectangle.setX(x);
        rectangle.setY(y);
    }

    @Override
    public void goBack() {
        flagX = !flagX;
    }
}