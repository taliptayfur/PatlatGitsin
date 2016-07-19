package com.mygdx.deneme.screen.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.deneme.PatlatGitsin;
import com.mygdx.deneme.balloon.Balloon;
import com.mygdx.deneme.balloon.BlackBalloon;
import com.mygdx.deneme.balloon.GreenBalloon;
import com.mygdx.deneme.balloon.RedBalloon;
import com.mygdx.deneme.balloon.YellowBalloon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LevelScreen1 implements Screen, InputProcessor {

    private GreenBalloon greenBalloon;
    private RedBalloon redBalloon;
    private YellowBalloon yellowBalloon;
    private BlackBalloon blackBalloon;

    private ArrayList<Balloon> balloonList;

    private ArrayList<Balloon> allBlackBalloonList;
    private ArrayList<Balloon> allGreenBalloonList;

    Texture backgroundTexture;
    Timer timer;
    int screenWidth, screenHeight;

    int width = 128;
    int height = 128;

    float moveDelta = 0;

    float totalTime = 30;
    boolean sariGoster = false;
    boolean savePoint = false;

    SpriteBatch batch;

    Texture blackTexture;
    Texture greenTexture;
    Texture backTexture;
    Texture nextTexture;

    @Override
    public void show() {

        PatlatGitsin.poppedBalloons = new int[]{0, 0, 0, 0};

        PatlatGitsin.level1Score = 0;

        PatlatGitsin.gamePlaying = true;

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        batch = new SpriteBatch();

        backgroundTexture = new Texture("desertbg.png");
        timer = new Timer();

        blackTexture = new Texture(Gdx.files.internal("black_balloon.png"));
        greenTexture = new Texture(Gdx.files.internal("green_balloon.png"));

        backTexture = new Texture(Gdx.files.internal("backbutton.png"));
        nextTexture = new Texture(Gdx.files.internal("nextbutton.png"));

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        Random r = new Random(); //random sınıfı

        int randX, randY;

        balloonList = new ArrayList<Balloon>();


        allBlackBalloonList = new ArrayList<Balloon>();
        allGreenBalloonList = new ArrayList<Balloon>();

        int blackCount = 1;
        int greenCount = 4;
        int redCount = 8;
        int yellowCount = 8;

        int sidedBalloonsCount = 10;

        // Kenarda tutulan balonlar
        for (int i = 0; i < sidedBalloonsCount; i++) {
            greenBalloon = new GreenBalloon((float) 0, (float) 0, screenWidth, screenHeight, "green_balloon.png", greenTexture, 5);
            allGreenBalloonList.add(greenBalloon);

            blackBalloon = new BlackBalloon((float) 0, (float) 0, screenWidth, screenHeight, "black_balloon.png", blackTexture, -10);
            allBlackBalloonList.add(blackBalloon);
        }

        for (int i = 0; i < blackCount; i++) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            blackBalloon = new BlackBalloon((float) randX, (float) randY, screenWidth, screenHeight, "black_balloon.png", blackTexture, -10);
            balloonList.add(blackBalloon);
        }

        for (int i = 0; i < greenCount; i++) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            greenBalloon = new GreenBalloon((float) randX, (float) randY, screenWidth, screenHeight, "green_balloon.png", greenTexture, 5);
            balloonList.add(greenBalloon);
        }

        for (int i = 0; i < redCount; i++) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            redBalloon = new RedBalloon((float) randX, (float) randY, screenWidth, screenHeight, "red_balloon.png", 10);
            balloonList.add(redBalloon);
        }

        for (int i = 0; i < yellowCount; i++) {
            randX = r.nextInt(screenWidth - width);
            randY = r.nextInt(screenHeight - height);
            yellowBalloon = new YellowBalloon((float) randX, (float) randY, screenWidth, screenHeight, "yellow_balloon.png", 20);
            // yellowBalloonList.add(yellowBalloon);
            balloonList.add(yellowBalloon);
        }


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random rand = new Random();

                ArrayList<Balloon> greenBalloonList = new ArrayList<Balloon>();
                ArrayList<Balloon> blackBalloonList = new ArrayList<Balloon>();

                ArrayList<Balloon> tempBlackBalloonList = new ArrayList<Balloon>();
                ArrayList<Balloon> tempGreenBalloonList = new ArrayList<Balloon>();

                // green to black
                for (int i = 0; i < balloonList.size(); i++) {
                    if (balloonList.get(i) instanceof GreenBalloon) {
                        ((GreenBalloon) balloonList.get(i)).countGreen -= 1;

                        if (((GreenBalloon) balloonList.get(i)).countGreen == 0) {

                            ((GreenBalloon) balloonList.get(i)).countGreen = rand.nextInt(20) + 1;

                            // BlackBalloon blackBalloon = new BlackBalloon((float) balloonList.get(i).getX(), (float) balloonList.get(i).getY(), screenWidth, screenHeight, "black_balloon.png",blackTexture);
                            BlackBalloon blackBalloon = (BlackBalloon) allBlackBalloonList.get(0);
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

                for (int i = 0; i < tempBlackBalloonList.size(); i++) {
                    balloonList.add(tempBlackBalloonList.get(i));
                }

                for (int i = 0; i < greenBalloonList.size(); i++) {
                    balloonList.remove(greenBalloonList.get(i));
                }

                // #################################################

                // black to green

                for (int i = 0; i < balloonList.size(); i++) {
                    if (balloonList.get(i) instanceof BlackBalloon) {
                        ((BlackBalloon) balloonList.get(i)).countBlack -= 1;

                        if (((BlackBalloon) balloonList.get(i)).countBlack == 0) {

                            ((BlackBalloon) balloonList.get(i)).countBlack = rand.nextInt(7) + 1;

                            // BlackBalloon blackBalloon = new BlackBalloon((float) balloonList.get(i).getX(), (float) balloonList.get(i).getY(), screenWidth, screenHeight, "black_balloon.png",blackTexture);
                            GreenBalloon greenBalloon = (GreenBalloon) allGreenBalloonList.get(0);
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

                for (int i = 0; i < tempGreenBalloonList.size(); i++) {
                    balloonList.add(tempGreenBalloonList.get(i));
                }

                for (int i = 0; i < blackBalloonList.size(); i++) {
                    balloonList.remove(blackBalloonList.get(i));
                }

            }
        }, 1000, 1000);

    }

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
    public void render(float delta) {
        moveDelta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f); //1 saniyede 1

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (backgroundTexture != null) {
            batch.begin();
            batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }

        batch.begin();
        PatlatGitsin.font.draw(batch, "Kalan Sure: " + (int) totalTime, 10, 40);
        PatlatGitsin.font.draw(batch, "Puan : " + PatlatGitsin.level1Score, (Gdx.graphics.getWidth() - 230), 40);

        String poppedBalloonsMessage = "B: " + PatlatGitsin.poppedBalloons[0] + "  G: " + PatlatGitsin.poppedBalloons[1]
                + "  R: " + PatlatGitsin.poppedBalloons[2] + "  Y: " + PatlatGitsin.poppedBalloons[3];

        PatlatGitsin.font.draw(batch, poppedBalloonsMessage, 450, 40);

        batch.end();

        for (int i = 0; i < balloonList.size(); i++) {
            if (balloonList.get(i) != null)
                balloonList.get(i).render();
        }

        if (PatlatGitsin.gamePlaying) {

            if (totalTime >= 0) {
                totalTime -= moveDelta; //if counting down
            } else {
                PatlatGitsin.gamePlaying = false;
                timer.cancel();

                for (int i = 0; i < balloonList.size(); i++) {
                    if (balloonList.get(i) != null) {
                        if (balloonList.get(i) instanceof YellowBalloon) {
                            ((YellowBalloon) balloonList.get(i)).yellowTimer2.cancel();
                            ((YellowBalloon) balloonList.get(i)).yellowTimer.cancel();
                        }
                    }
                }

            }

            moveBalloons(moveDelta);
            kenaraDegdimi();
            // birbirineCarptimi();

            if (balloonList.size() <= 0) {
                PatlatGitsin.gamePlaying = false;
                timer.cancel();
            }

        } else {
            // yeterli sayıda balon patlatılmış ise
            if (PatlatGitsin.poppedBalloons[0] >= 1 && PatlatGitsin.poppedBalloons[1] >= 1 &&
                    PatlatGitsin.poppedBalloons[2] >= 1 && PatlatGitsin.poppedBalloons[3] >= 1) {
                batch.begin();
                PatlatGitsin.fontFinish.draw(batch, "OYUN SONU : " + PatlatGitsin.level1Score, (Gdx.graphics.getWidth() / 2) - 200, Gdx.graphics.getHeight() / 2);
                batch.end();
                savePoint = true;
            } else {
                batch.begin();
                PatlatGitsin.fontFinish.draw(batch, "Yeterli Sayida Balon Patlatilamadi!! " + PatlatGitsin.level1Score, 100, Gdx.graphics.getHeight() / 2);
                batch.end();
                savePoint = false;
            }

            if (PatlatGitsin.level1Score < 100) {
                batch.begin();
                PatlatGitsin.fontFinish.draw(batch, "100 Puan Toplanamadi!! Puan : " + PatlatGitsin.level1Score, 100, Gdx.graphics.getHeight() / 2 - 50);
                batch.end();
                savePoint = false;
            }

            batch.begin();
            batch.draw(backTexture, (Gdx.graphics.getWidth() - 100), (Gdx.graphics.getHeight()-100), 100, 100);
            batch.draw(nextTexture, (Gdx.graphics.getWidth() - 100), (Gdx.graphics.getHeight()-230), 100, 100);
            batch.end();
        }
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
        if (keycode == Input.Keys.BACK) {
            if (PatlatGitsin.isPlaying )
                PatlatGitsin.menuost.play();

            PatlatGitsin.desertost.stop();
            ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
        }
        return true;
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
        if (PatlatGitsin.gamePlaying) {
            // Telefon y si ile libgdx y si farklı
            // Telefon y si üst taraftan aşağı doğru artıyor
            // Kütüphane y si aşağıdan yukarı doğru artıyor

            screenY = screenHeight - screenY;

            boolean x1, x2, y1, y2;

            for (int i = balloonList.size() - 1; i >= 0; i--) {

                Balloon tmpBalloon = balloonList.get(i);

                if (tmpBalloon instanceof YellowBalloon) {
                    if (!((YellowBalloon) tmpBalloon).showYellow) {
                        continue;
                    }
                }

                if (tmpBalloon != null) {
                    x1 = screenX > tmpBalloon.getX();
                    x2 = screenX < (tmpBalloon.getX() + tmpBalloon.getWidth());

                    y1 = screenY > tmpBalloon.getY();
                    y2 = screenY < (tmpBalloon.getY() + tmpBalloon.getHeight());
                } else {
                    continue;
                }

                if (x1 && x2 && y1 && y2) {
                    // y = y + 100;
                    tmpBalloon.playSound();
                    //balloonList.remove(i);

                    PatlatGitsin.level1Score += tmpBalloon.getPoint();

                    if (tmpBalloon instanceof BlackBalloon) {
                        PatlatGitsin.poppedBalloons[0] += 1;
                    } else if (tmpBalloon instanceof GreenBalloon) {
                        PatlatGitsin.poppedBalloons[1] += 1;
                    } else if (tmpBalloon instanceof RedBalloon) {
                        PatlatGitsin.poppedBalloons[2] += 1;
                    } else if (tmpBalloon instanceof YellowBalloon) {
                        PatlatGitsin.poppedBalloons[3] += 1;
                    }

                    // balloonList.remove(balloonList.get(i));
                    try {
                        balloonList.set(i, null);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.toString());
                        continue;
                    }

                    System.out.println("Balon uzunluğu: " + balloonList.size());

                    break;
                }
            }

            balloonList.removeAll(Collections.singleton(null));

        } else {

            boolean x1 = screenX > (Gdx.graphics.getWidth()-100);
            boolean x2 = screenX < ((Gdx.graphics.getWidth()-100) + 100);

            boolean y1 = screenY > 0;
            boolean y2 = screenY < 0 + 100;

            if (x1 && x2 && y1 && y2) {

                if(savePoint && PatlatGitsin.level1Score >= 100) {
                    Preferences prefs = Gdx.app.getPreferences("LevelPoints");
                    prefs.putInteger("Level1", PatlatGitsin.level1Score);
                    prefs.flush();
                }

                if (PatlatGitsin.isPlaying)
                    PatlatGitsin.menuost.play();

                PatlatGitsin.desertost.stop();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
            }

            boolean x11 = screenX > (Gdx.graphics.getWidth()-100);
            boolean x22 = screenX < ((Gdx.graphics.getWidth()-100) + 100);

            boolean y11 = screenY > 130;
            boolean y22 = screenY < 130 + 100;

            if (x11 && x22 && y11 && y22) {

                if(savePoint && PatlatGitsin.level1Score >= 100) {
                    Preferences prefs = Gdx.app.getPreferences("LevelPoints");
                    prefs.putInteger("Level1", PatlatGitsin.level1Score);
                    prefs.flush();


                    if (PatlatGitsin.isPlaying)
                        PatlatGitsin.forestost.play();

                    PatlatGitsin.desertost.stop();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen2());

                }


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
}
