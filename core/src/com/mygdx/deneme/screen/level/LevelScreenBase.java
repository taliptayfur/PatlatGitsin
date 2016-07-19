package com.mygdx.deneme.screen.level;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.deneme.balloon.Balloon;
import com.mygdx.deneme.balloon.BlackBalloon;
import com.mygdx.deneme.balloon.GreenBalloon;
import com.mygdx.deneme.balloon.RedBalloon;
import com.mygdx.deneme.balloon.YellowBalloon;

import java.util.ArrayList;
import java.util.Timer;


public class LevelScreenBase implements Screen, InputProcessor {

    private GreenBalloon greenBalloon;
    private RedBalloon redBalloon;
    private YellowBalloon yellowBalloon;
    private BlackBalloon blackBalloon;

    private ArrayList<Balloon> balloonList;
    private ArrayList<Balloon> yellowBalloonList;
    private ArrayList<Balloon> blackBalloonList;

    private ArrayList<Balloon> allBlackBalloonList;
    private ArrayList<Balloon> allGreenBalloonList;

    Texture backgroundTexture;
    Timer timer;
    int screenWidth, screenHeight;

    int width = 128;
    int height = 128;

    float moveDelta = 0;

    float totalTime = 30;
    boolean sariGoster = true;

    SpriteBatch batch;

    Texture blackTexture;
    Texture greenTexture;
    Texture backTexture;


    private void kenaraDegdimi() {
        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).kenaraDegdimi();
        }

    }

    private void moveBalloons(float moveDelta) {
        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).hareketEt(moveDelta);
        }
    }

    private void birbirineCarptimi() {

        ArrayList<Balloon> tmpBalloonList = (ArrayList<Balloon>) balloonList.clone();

        for (int i = 0; i < balloonList.size(); i++) {
            for (int j = 0; j < balloonList.size(); j++) {

                if (i != j) {

                    boolean contains = balloonList.get(i).getRectangle().overlaps(balloonList.get(j).getRectangle());

                    if (contains) {
                        balloonList.get(i).goBack();
                    }
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
