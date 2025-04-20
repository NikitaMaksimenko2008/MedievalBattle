package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class ScreenGame implements Screen {

    private final Main main;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Vector3 touch;
    private final BitmapFont font;

    Texture imgBG;
    Texture imgSoldierAtlas;
    TextureRegion[] imgSoldier = new TextureRegion[8];

    BattleButton btnBack;

    Soldier soldier;

    public ScreenGame(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBG = new Texture("bg6.jpg");
        imgSoldierAtlas = new Texture("Soldiers.png");
        for (int i = 0; i < imgSoldier.length; i++){
            imgSoldier[i] = new TextureRegion(imgSoldierAtlas, (i<5?i:8-i)*256, 0, 256, 256);
        }

        btnBack = new BattleButton(font, "x", 840, 1580);

        soldier = new Soldier(SCR_WIDTH/2, 200);
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

        soldier.move();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(imgSoldier[soldier.phase], soldier.scrX(), soldier.scrY(), soldier.width, soldier.height);
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
