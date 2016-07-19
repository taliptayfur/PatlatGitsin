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

public class CreditsScreen implements Screen, InputProcessor {

    private Stage stage;

    private Image creditsScreen;
    private Button backButton;

    private MenuScreen menuScreen;

    @Override
    public void show() {

        stage = new Stage();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);

        menuScreen = new MenuScreen();

        creditsScreen = new Image(PatlatGitsin.skin, "creditsbg");
        backButton = new Button(PatlatGitsin.skin, "backbutton");

        creditsScreen.setPosition(0.0f, 0.0f);
        creditsScreen.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backButton.setPosition( (Gdx.graphics.getWidth() - 150 ) / 2 , 20);
        backButton.setBounds(backButton.getX(), backButton.getY(), 150, 150);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(menuScreen);
            }
        });

        stage.addActor(creditsScreen);
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

