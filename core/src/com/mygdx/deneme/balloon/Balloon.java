package com.mygdx.deneme.balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.deneme.PatlatGitsin;


public class Balloon {

    SpriteBatch batch;
    Texture img;

    protected float x;
    protected float y;

    int width = 128;
    int height = 128;

    int point;
    int screenWidth, screenHeight;
    Rectangle rectangle;

    boolean flagX = true;
    boolean flagY = true;

    public Balloon(float x, float y, int screenWidth, int screenHeight, String textureName, int point) {
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal(textureName));

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = x;
        this.y = y;

        this.point = point;

        rectangle = new Rectangle(x, y, width, height);
    }


    public Balloon(float x, float y, int screenWidth, int screenHeight) {

        batch = new SpriteBatch();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x, y, width, height);
    }

    public void kenaraDegdimi() {

        if (x >= screenWidth - width) {
            // turns to false
            flagX = false;
        } else if (x < 0) {
            // turns to true
            flagX = true;
        }

        if (y >= screenHeight - height) {
            // turns to false
            flagY = false;
        } else if (y < 0) {
            // turns to true
            flagY = true;
        }
    }

    public void render() {
        if (img != null) {
            batch.begin();
            batch.draw(img, x, y, width, height);
            batch.end();
        }
    }


    public void hareketEt(float moveDelta) {
        if (flagX) {
            x = x + 700 * moveDelta;
            rectangle.setX(x);
        } else {
            x = x - 700 * moveDelta;
            rectangle.setY(y);
        }
    }

    public void playSound() {
        if (PatlatGitsin.isPlaying)
            PatlatGitsin.blasteffect.play();
    }

    public void goBack() {
        flagX = !flagX;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isFlagX() {
        return flagX;
    }

    public void setFlagX(boolean flagX) {
        this.flagX = flagX;
    }

    public boolean isFlagY() {
        return flagY;
    }

    public void setFlagY(boolean flagY) {
        this.flagY = flagY;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
