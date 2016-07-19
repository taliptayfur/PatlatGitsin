package com.mygdx.deneme.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.deneme.PatlatGitsin;
import com.mygdx.deneme.screen.level.LevelScreen;

public class MenuScreen implements Screen, InputProcessor {
    private Stage stage;

    private CreditsScreen creditsScreen = new CreditsScreen();
    private HighScoresScreen highScoresScreen = new HighScoresScreen();
    private LevelScreen levelScreen = new LevelScreen();


    private Image backgroundImage;
    private Button startButton;
    private Button creditsButton;
    private Button highScoresButton;
    private Button exitButton;
    private Button muteButton;
    private Button deMuteButton;

    private int spaceX = 0;
    private int spaceY = 0;
    private int spaceWidth = 0;
    private int spaceHeigth = 0;

    public MenuScreen() {
    }

    @Override
    public void show() {

        stage = new Stage();

        backgroundImage = new Image(PatlatGitsin.skin, "menubg");

        startButton = new Button(PatlatGitsin.skin, "startbutton");
        creditsButton = new Button(PatlatGitsin.skin, "creditsbutton");
        highScoresButton = new Button(PatlatGitsin.skin, "highscores");
        exitButton = new Button(PatlatGitsin.skin, "exit");

        muteButton = new Button(PatlatGitsin.skin, "mute");
        deMuteButton = new Button(PatlatGitsin.skin, "demute");

        spaceWidth = (int) (startButton.getWidth() / 1.25f);
        spaceHeigth = (int) (startButton.getHeight() / 1.25f);

        spaceX = (Gdx.graphics.getWidth() - 2 * spaceWidth) / 3;
        spaceY = (Gdx.graphics.getHeight() - 2 * spaceHeigth) / 3;

        spaceY -= 40;

        backgroundImage.setPosition(0f, 0f);
        backgroundImage.setBounds(backgroundImage.getX(), backgroundImage.getY(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        startButton.setPosition(spaceX, 2 * spaceY + spaceHeigth);
        startButton.setBounds(startButton.getX(), startButton.getY(), startButton.getWidth() / 1.25f, startButton.getHeight() / 1.25f);

        highScoresButton.setPosition(2 * spaceX + spaceWidth, 2 * spaceY + spaceHeigth);
        highScoresButton.setBounds(highScoresButton.getX(), highScoresButton.getY(), highScoresButton.getWidth() / 1.25f, highScoresButton.getHeight() / 1.25f);

        creditsButton.setPosition(spaceX, spaceY);
        creditsButton.setBounds(creditsButton.getX(), creditsButton.getY(), creditsButton.getWidth() / 1.25f, creditsButton.getHeight() / 1.25f);

        exitButton.setPosition(2 * spaceX + spaceWidth, spaceY);
        exitButton.setBounds(exitButton.getX(), exitButton.getY(), exitButton.getWidth() / 1.25f, exitButton.getHeight() / 1.25f);

        muteButton.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 80);
        muteButton.setBounds(muteButton.getX(), muteButton.getY(), 80, 80);

        deMuteButton.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 80);
        deMuteButton.setBounds(deMuteButton.getX(), deMuteButton.getY(), 80, 80);


        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(levelScreen);
            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(creditsScreen);
            }
        });

        highScoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(highScoresScreen);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PatlatGitsin.menuost.stop();
                PatlatGitsin.menuost.dispose();
                PatlatGitsin.skin.dispose();
                Gdx.app.exit();
            }
        });

        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(PatlatGitsin.menuost.isPlaying()){
                    PatlatGitsin.menuost.stop();
                    PatlatGitsin.isPlaying = false;
                }
            }
        });

        deMuteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if( !PatlatGitsin.menuost.isPlaying()) {
                    PatlatGitsin.menuost.stop();
                    PatlatGitsin.menuost.play();
                    PatlatGitsin.isPlaying = true;
                }
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(startButton);
        stage.addActor(creditsButton);
        stage.addActor(highScoresButton);
        stage.addActor(exitButton);
        stage.addActor(muteButton);
        stage.addActor(deMuteButton);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            Gdx.app.exit();
        }
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
