/**
 *


package com.mygdx.deneme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.deneme.balloon.BlackBalloon;
import com.mygdx.deneme.balloon.GreenBalloon;
import com.mygdx.deneme.balloon.RedBalloon;
import com.mygdx.deneme.balloon.YellowBalloon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class MyGdxGame2 extends Game implements InputProcessor, Screen {
    private GreenBalloon greenBalloon;
    private RedBalloon redBalloon;
    private YellowBalloon yellowBalloon;
    private BlackBalloon blackBalloon;

    private ArrayList <com.mygdx.deneme.balloon.Balloon> balloonList;
    private ArrayList <com.mygdx.deneme.balloon.Balloon> yellowBalloonList;
    private ArrayList <com.mygdx.deneme.balloon.Balloon> blackBalloonList;

    private ArrayList <com.mygdx.deneme.balloon.Balloon> allBlackBalloonList;
    private ArrayList <com.mygdx.deneme.balloon.Balloon> allGreenBalloonList;

    private Stage stage;


    //Texture img;
    //Sprite imgSprite;
    Timer timer;
    int screenWidth, screenHeight;
    boolean flag = true;

    int width = 64;
    int height = 64;
    float x = 10, y;
    float moveDelta = 0;

    int generalCount = 0;

    boolean sariGoster= true;

    Texture blackTexture;
    Texture greenTexture;


    @Override
    public void create() {




        Gdx.input.setInputProcessor(this);
        //batch = new SpriteBatch();
        //img = new Texture("blue_balloon.png");
        //img = new Texture(Gdx.files.internal("blue_balloon.png"));
        //img = new Texture(Gdx.files.internal("badlogic.jpg"));
        //img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //imgSprite = new Sprite(img);
        timer = new Timer();

        blackTexture = new Texture(Gdx.files.internal("black_balloon.png"));
        greenTexture = new Texture(Gdx.files.internal("green_balloon.png"));

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        Random r = new Random(); //random sınıfı

        int randX, randY;

        balloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();
        yellowBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();
        blackBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();

        allBlackBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();
        allGreenBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();

        // Kenarda tutulan balonlar
        for (int i = 0; i < 10 ; i++) {
            GreenBalloon greenBalloon = new GreenBalloon((float) 0, (float) 0, screenWidth, screenHeight, "green_balloon.png",greenTexture);
            allGreenBalloonList.add(greenBalloon);

            BlackBalloon blackBalloon = new BlackBalloon((float) 0, (float) 0, screenWidth, screenHeight, "black_balloon.png",blackTexture);
            allBlackBalloonList.add(blackBalloon);
        }

        for(int i = 0; i < r.nextInt(5) + 1; i++ ) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            GreenBalloon greenBalloon = new GreenBalloon((float) randX, (float) randY, screenWidth, screenHeight, "green_balloon.png",greenTexture);
            balloonList.add(greenBalloon);
        }

        for(int i = 0; i < r.nextInt(5) + 1; i++ ) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            RedBalloon redBalloon = new RedBalloon((float) randX, (float) randY, screenWidth, screenHeight, "red_balloon.png");
            balloonList.add(redBalloon);
        }

        for(int i = 0; i < r.nextInt(5) + 1; i++ ) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            YellowBalloon yellowBalloon = new YellowBalloon((float) randX, (float) randY, screenWidth, screenHeight, "yellow_balloon.png");
            //balloonList.add(yellowBalloon);
            yellowBalloonList.add(yellowBalloon);
        }

        for(int i = 0; i < r.nextInt(5) + 1; i++ ) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            BlackBalloon blackBalloon = new BlackBalloon((float) randX, (float) randY, screenWidth, screenHeight, "black_balloon.png",blackTexture);
            balloonList.add(blackBalloon);
        }



        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Random rand = new Random();

                ArrayList<com.mygdx.deneme.balloon.Balloon> greenBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();
                ArrayList<com.mygdx.deneme.balloon.Balloon> blackBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();

                ArrayList<com.mygdx.deneme.balloon.Balloon> tempBlackBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();
                ArrayList<com.mygdx.deneme.balloon.Balloon> tempGreenBalloonList = new ArrayList<com.mygdx.deneme.balloon.Balloon>();

                // green to black
                for(int i = 0; i< balloonList.size();i++){
                    if(balloonList.get(i) instanceof GreenBalloon){
                        ((GreenBalloon)balloonList.get(i)).countGreen -=1;

                        if(((GreenBalloon)balloonList.get(i)).countGreen == 0){

                            ((GreenBalloon)balloonList.get(i)).countGreen = rand.nextInt(20)+ 1;


                            // BlackBalloon blackBalloon = new BlackBalloon((float) balloonList.get(i).getX(), (float) balloonList.get(i).getY(), screenWidth, screenHeight, "black_balloon.png",blackTexture);
                            BlackBalloon blackBalloon = (BlackBalloon)allBlackBalloonList.get(0);
                            allBlackBalloonList.remove(0);

                            blackBalloon.setX(balloonList.get(i).getX());
                            blackBalloon.setY(balloonList.get(i).getY());

                            allGreenBalloonList.add(balloonList.get(i));

                            greenBalloonList.add(balloonList.get(i));
                            //balloonList.add(blackBalloon);
                            tempBlackBalloonList.add(blackBalloon);
                        }
                    }
                }

                for (int i=0;i<tempBlackBalloonList.size();i++) {
                    balloonList.add(tempBlackBalloonList.get(i));
                }

                for (int i=0;i<greenBalloonList.size();i++){
                    balloonList.remove(greenBalloonList.get(i));
                }

                // #################################################

                // black to green

                for(int i = 0; i< balloonList.size();i++){
                    if(balloonList.get(i) instanceof BlackBalloon){
                        ((BlackBalloon)balloonList.get(i)).countBlack -=1;

                        if(((BlackBalloon)balloonList.get(i)).countBlack == 0){

                            ((BlackBalloon)balloonList.get(i)).countBlack = rand.nextInt(7)+ 1;


                            // BlackBalloon blackBalloon = new BlackBalloon((float) balloonList.get(i).getX(), (float) balloonList.get(i).getY(), screenWidth, screenHeight, "black_balloon.png",blackTexture);
                            GreenBalloon greenBalloon = (GreenBalloon)allGreenBalloonList.get(0);
                            allGreenBalloonList.remove(0);

                            greenBalloon.setX(balloonList.get(i).getX());
                            greenBalloon.setY(balloonList.get(i).getY());

                            allBlackBalloonList.add(balloonList.get(i));

                            blackBalloonList.add(balloonList.get(i));
                            //balloonList.add(blackBalloon);
                            tempGreenBalloonList.add(greenBalloon);
                        }
                    }
                }

                for (int i=0;i<tempGreenBalloonList.size();i++) {
                    balloonList.add(tempGreenBalloonList.get(i));
                }

                for (int i=0;i<blackBalloonList.size();i++){
                    balloonList.remove(blackBalloonList.get(i));
                }

                ////////////////////////////////////////
                if( sariGoster == true ) {
                    for(int i = 0; i< yellowBalloonList.size();i++){
                        balloonList.add(yellowBalloonList.get(i));

                    }
                    yellowBalloonList.clear();
                    sariGoster = false;
                }
                else {
                    for(int i = 0; i< balloonList.size();i++){
                        if(balloonList.get(i) instanceof YellowBalloon){
                            ((YellowBalloon) balloonList.get(i)).teleport();
                            yellowBalloonList.add(balloonList.get(i));

                            //balloonList.remove(i);
                            // Listelerdeki transfer işlemi sonucu
                            // oluşan kaymalardan dolayı dışarda bir for döngüsü ile
                            // ballonList ten silme yaptık... ( Doğal randomluk )
                        }
                    }

                    for (int i=0;i<yellowBalloonList.size();i++){
                        balloonList.remove(yellowBalloonList.get(i));
                    }

                    sariGoster = true;
                }
            }

        },1000,1000);




        //imgSprite.scale(30);
    }







    private void kenaraDegdimi() {
        /*
        balloon.kenaraDegdimi();
        greenBalloon.kenaraDegdimi();
        redBalloon.kenaraDegdimi();
        yellowBalloon.kenaraDegdimi();
        blackBalloon.kenaraDegdimi();
        */
/*
        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).kenaraDegdimi();
        }

    }

    private void moveBalloons(float moveDelta) {
        /*
        balloon.hareketEt(moveDelta);
        greenBalloon.hareketEt(moveDelta);
        redBalloon.hareketEt(moveDelta);
        yellowBalloon.hareketEt(moveDelta);
        blackBalloon.hareketEt(moveDelta);
        */
/*
        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).hareketEt(moveDelta);
        }
    }

    private void birbirineCarptimi() {

    }

    @Override
    public void render() {
        moveDelta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f);

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
        balloon.render();
        greenBalloon.render();
        redBalloon.render();
        yellowBalloon.render();
        blackBalloon.render();
        */
/*
        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).render();
        }

       /* batch.begin();
        if (img != null)
            batch.draw(img, x, y, 256, 256);
        batch.end();


        if (img != null)
            if (x >= screenWidth - 256) {
                // turns to false
                flag = false;
            } else if (x < 0) {
                // turns to true
                flag = true;
            }
        */
  /*      moveBalloons(moveDelta);
        kenaraDegdimi();

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
        // Telefon y ile libgdx y si farklı
        screenY = screenHeight - screenY;

        boolean x1 = false;
        boolean x2 = false;
        boolean y1 = false;
        boolean y2 = false;

        for (int i = 0; i< balloonList.size(); i++) {
            if (balloonList.get(i) != null) {
                x1 = screenX > balloonList.get(i).getX();
                x2 = screenX < (balloonList.get(i).getX() + balloonList.get(i).width);

                y1 = screenY > balloonList.get(i).getY();
                y2 = screenY < (balloonList.get(i).getY() + balloonList.get(i).height);
            }

            if (x1 && x2 && y1 && y2) {
                // y = y + 100;
                balloonList.remove(balloonList.get(i));
            }
        }

        return true;
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

    @Override
    public void show() {
        stage = new Stage();


        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}

*/