package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenMenu implements Screen {

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;

    Texture imgBG;

    BattleButton btnPlay;
    BattleButton btnSettings;
    BattleButton btnLeaderBoard;
    BattleButton btnAbout;
    BattleButton btnExit;

    public ScreenMenu(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.fontLight;

        imgBG = new Texture("bg1.jpg");

        btnPlay = new BattleButton(font, "Play", 250, 1000);
        btnSettings = new BattleButton(font, "Settings", 250, 900);
        btnLeaderBoard = new BattleButton(font, "Rules", 250, 800);
        btnAbout = new BattleButton(font, "About", 250, 700);
        btnExit = new BattleButton(font, "Exit", 250, 600);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnPlay.hit(touch.x, touch.y)){
                main.setScreen(main.screenGame);
            }
            if(btnSettings.hit(touch.x, touch.y)){
                main.setScreen(main.screenSettings);
            }
            if(btnLeaderBoard.hit(touch.x, touch.y)){
                main.setScreen(main.screenRules);
            }
            if(btnAbout.hit(touch.x, touch.y)){
                main.setScreen(main.screenAbout);
            }
            if(btnExit.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch,"Menu", 350, 1200);
        btnPlay.font.draw(batch, btnPlay.text, btnPlay.x, btnPlay.y);
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        btnLeaderBoard.font.draw(batch, btnLeaderBoard.text, btnLeaderBoard.x, btnLeaderBoard.y);
        btnAbout.font.draw(batch, btnAbout.text, btnAbout.x, btnAbout.y);
        btnExit.font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
        batch.end();


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
}
