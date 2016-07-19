package com.mygdx.deneme.screen.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.deneme.PatlatGitsin;
import com.mygdx.deneme.screen.MenuScreen;

public class LevelScreen implements Screen, InputProcessor {

    private Stage stage;
    private Image levelScreenBackground;
    private Button backButton;
    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private Button lockButton;
    private Button newGameButton;


    private LevelScreen1 levelScreen1;
    private LevelScreen2 levelScreen2;
    private LevelScreen3 levelScreen3;
    private MenuScreen menuScreen;

    public LevelScreen() {
    }

    @Override
    public void show() {
        stage = new Stage();

        PatlatGitsin.poppedBalloons = new int[]{0, 0, 0, 0};

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);

        float scaleX = Gdx.graphics.getWidth() / 1280.0f;
        float scaleY = Gdx.graphics.getHeight() / 720.0f;


        menuScreen = new MenuScreen();
        levelScreen1 = new LevelScreen1();
        levelScreen2 = new LevelScreen2();
        levelScreen3 = new LevelScreen3();

        levelScreenBackground = new Image(PatlatGitsin.skin, "map");

        final Preferences prefs = Gdx.app.getPreferences("LevelPoints");

        int level1Score = prefs.getInteger("Level1", 0);
        int level2Score = prefs.getInteger("Level2", 0);
        int level3Score = prefs.getInteger("Level3", 0);

        backButton = new Button(PatlatGitsin.skin, "backbutton");
        lockButton = new Button(PatlatGitsin.skin, "lock");
        newGameButton = new Button(PatlatGitsin.skin, "Pink");

        level1Button = new Button(PatlatGitsin.skin, "level1");

        if (level1Score < 100)
            level2Button = new Button(PatlatGitsin.skin, "lock");
        else
            level2Button = new Button(PatlatGitsin.skin, "level2");

        if (level2Score < 100)
            level3Button = new Button(PatlatGitsin.skin, "lock");
        else
            level3Button = new Button(PatlatGitsin.skin, "level3");


        levelScreenBackground.setPosition(0.0f, 0.0f);
        levelScreenBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        newGameButton.setPosition(20 * scaleX, 20 * scaleY);
        newGameButton.setBounds(newGameButton.getX(), newGameButton.getY(), newGameButton.getWidth() / 2.0f, newGameButton.getHeight() / 2.0f);

        lockButton.setPosition(300 * scaleX, (Gdx.graphics.getHeight() - 382) * scaleY);
        lockButton.setBounds(lockButton.getX(), lockButton.getY(), 100, 100);

        backButton.setPosition((Gdx.graphics.getWidth() - 230) * scaleX, 20 * scaleY);
        backButton.setBounds(backButton.getX(), backButton.getY(), 150, 150);

        level1Button.setPosition(435 * scaleX, 600 * scaleY);
        level1Button.setBounds(level1Button.getX(), level1Button.getY(), 100, 100);

        level2Button.setPosition(705 * scaleX, 400 * scaleY);
        level2Button.setBounds(level2Button.getX(), level2Button.getY(), 100, 100);

        level3Button.setPosition(810 * scaleX, (Gdx.graphics.getHeight() - 535) * scaleY);
        level3Button.setBounds(level3Button.getX(), level3Button.getY(), 100, 100);

        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PatlatGitsin.menuost.stop();
                if (PatlatGitsin.isPlaying == true)
                    PatlatGitsin.desertost.play();
                ((Game) Gdx.app.getApplicationListener()).setScreen(levelScreen1);
            }
        });

        if (level1Score >= 100) {
            level2Button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    PatlatGitsin.menuost.stop();
                    if (PatlatGitsin.isPlaying == true)
                        PatlatGitsin.forestost.play();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(levelScreen2);
                }
            });
        }
        if (level2Score >= 100) {
            level3Button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    PatlatGitsin.menuost.stop();
                    if (PatlatGitsin.isPlaying == true)
                        PatlatGitsin.winterost.play();
                    ((Game) Gdx.app.getApplicationListener()).setScreen(levelScreen3);
                }
            });
        }


        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });


        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                prefs.putInteger("Level1", 0);
                prefs.putInteger("Level2", 0);
                prefs.putInteger("Level3", 0);
                prefs.flush();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelScreen());
            }
        });


        stage.addActor(levelScreenBackground);
        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);
        stage.addActor(lockButton);
        stage.addActor(newGameButton);
        stage.addActor(backButton);
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
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
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

