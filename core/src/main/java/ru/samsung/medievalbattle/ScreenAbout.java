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

public class ScreenAbout implements Screen {

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private BitmapFont srf;

    Texture imgBG;

    BattleButton btnBack;

    public ScreenAbout(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.fontLight;
        srf = main.srf;

        imgBG = new Texture("bg5.jpeg");

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
        font.draw(batch,"About", 300, 1500);
        srf.draw(batch, "   You wake up in the ward, the only memory you have is the recent", 25, 1350);
        srf.draw(batch, "massacre you took part in. Getting up heavily, you come across a note", 25, 1320);
        srf.draw(batch, "lying next to your bed, on which it says:", 25, 1290);
        srf.draw(batch, "   If you're reading this, it means you've regained consciousness, and", 25, 1200);
        srf.draw(batch, "you're probably wondering, 'Where is everyone?' I will say this:", 25, 1170);
        srf.draw(batch, "due to the planned enemy attack, we were forced to retreat and", 25, 1140);
        srf.draw(batch, "abandon our positions. We didn't take the wounded because speed", 25, 1110);
        srf.draw(batch, "was important, that's why you woke up here. Now, your task is to delay", 25, 1080);
        srf.draw(batch, "the enemy as long as possible. Do not try to escape, otherwise I will", 25, 1050);
        srf.draw(batch, "regard it as disobeying orders and betrayal, for which you may well", 25, 1020);
        srf.draw(batch, "be hanged.", 25, 990);
        srf.draw(batch, "Commander Edward Lewis", 440, 940);
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
