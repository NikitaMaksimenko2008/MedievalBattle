package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class ScreenGame implements Screen {


    private final Main main;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Vector3 touch;
    private final BitmapFont font;

    Texture imgJoystick;
    Texture imgBG;
    Texture imgSoldierAtlas;
    Texture imgShotAtlas;
    TextureRegion[] imgSoldier = new TextureRegion[8];
    TextureRegion imgShot;
    TextureRegion[][] imgEnemy = new TextureRegion[3][8];

    BattleButton btnBack;

    Soldier soldier;
    List<Enemy> enemies = new ArrayList<>();
    List<Shot> shots = new ArrayList<>();

    private long timeLastSpawnEnemy, timeSpawnEnemyInterval = 4000;
    private long timeLastSpawnShot, timeSpawnShotsInterval = 1000;

    public ScreenGame(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.fontLight;
        Gdx.input.setInputProcessor(new MedievalProcessor());

        imgJoystick = new Texture("joystick.png");
        imgBG = new Texture("bg6.jpg");
        imgSoldierAtlas = new Texture("Soldiers.png");
        imgShotAtlas = new Texture("strela.png");
        imgShot = new TextureRegion(imgShotAtlas, 0, 0, 1080, 1080);
        for (int i = 0; i < imgSoldier.length; i++){
            imgSoldier[i] = new TextureRegion(imgSoldierAtlas, (i<5?i:8-i)*256, 0, 256, 256);
        }
        for (int j = 0; j < imgEnemy.length; j++) {
            for (int i = 0; i < imgEnemy[j].length; i++){
                imgEnemy[j][i] = new TextureRegion(imgSoldierAtlas, (i<5?i:8-i)*256, (j+1)*256, 256, 256);
            }
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

            if(btnBack.hit(touch)){
                main.setScreen(main.screenMenu);
            }
        }

        spawnEnemy();
        for (Enemy e: enemies) e.move();
        spawnShot();
        for (Shot s: shots) s.move();
        soldier.move();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        if(controls == JOYSTICK){
            batch.draw(imgJoystick, main.joystick.scrX(), main.joystick.scrY(), main.joystick.width, main.joystick.height);
        }
        for (Enemy e: enemies) {
            batch.draw(imgEnemy[e.type][e.phase], e.scrX(), e.scrY(), e.width, e.height);
        }
        for (Shot s: shots){
            batch.draw(imgShot, s.scrX(), s.scrY(), s.width, s.height);
        }
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

    private void spawnEnemy(){
        if(TimeUtils.millis()>timeLastSpawnEnemy+timeSpawnEnemyInterval){
            timeLastSpawnEnemy = TimeUtils.millis();
            enemies.add(new Enemy());
        }
    }

    private void spawnShot(){
        if(TimeUtils.millis()>timeLastSpawnShot+timeSpawnShotsInterval){
            timeLastSpawnShot = TimeUtils.millis();
            shots.add(new Shot(soldier.x-25, soldier.y+30));
        }
    }

    class MedievalProcessor implements InputProcessor{

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
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            if(controls == SCREEN){
                soldier.touchScreen(touch);
            }
            if(controls == JOYSTICK) {
                if (main.joystick.isTouchInside(touch)) {
                    soldier.touchJoystick(touch, main.joystick);
                }
            }
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            soldier.stop();
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            if(controls == SCREEN){
                soldier.touchScreen(touch);
            }
            if(controls == JOYSTICK) {
                if (main.joystick.isTouchInside(touch)) {
                    soldier.touchJoystick(touch, main.joystick);
                }
            }
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
