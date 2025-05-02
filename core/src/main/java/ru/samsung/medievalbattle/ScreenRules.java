package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.SCR_HEIGHT;
import static ru.samsung.medievalbattle.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenRules implements Screen {

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private BitmapFont srf;

    Texture imgBG;

    BattleButton btnBack;

    public ScreenRules(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.fontLight;
        srf = main.srf;

        imgBG = new Texture("bg3.jpg");

        btnBack = new BattleButton(font, "Back", 325, 400);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnBack.hit(touch.x, touch.y)){
                main.setScreen(main.screenMenu);
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch,"Rules", 360, 1500);
        srf.draw(batch, "The rules are as follows: ", 40, 1350);
        srf.draw(batch, "You must destroy the enemies advancing on you.", 40, 1290);
        srf.draw(batch, "The task is to destroy as many opponents as possible.", 40, 1260);
        srf.draw(batch, "You can shoot with the button, and move with the joystick.", 40, 1230);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        batch.end();
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
}
