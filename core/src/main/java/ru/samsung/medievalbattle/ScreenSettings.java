package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

public class ScreenSettings implements Screen {

    private Main main;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont fontLight;
    private BitmapFont fontLightRed;

    Texture imgBG;

    BattleButton btnControls;
    BattleButton btnScreen;
    BattleButton btnJoystick;
    BattleButton btnSound;
    BattleButton btnBack;

    public ScreenSettings(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        fontLight = main.fontLight;
        fontLightRed = main.fontLightRed;

        imgBG = new Texture("bg4.jpg");

        btnControls = new BattleButton(fontLight, "Controls", 170, 1300);
        btnScreen = new BattleButton(controls == SCREEN?fontLightRed:fontLight, "Screen", 270, 1200);
        btnJoystick = new BattleButton(controls == JOYSTICK?fontLightRed:fontLight, joystickText(), 270, 1100);
        btnSound = new BattleButton(fontLight, isSoundOn ? "Sound ON" : "Sound OFF", 200, 1000);
        btnBack = new BattleButton(fontLight, "Back", 450);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnScreen.hit(touch)){
                btnScreen.setFont(fontLightRed);
                btnJoystick.setFont(fontLight);
                controls = SCREEN;
            }
            if (btnJoystick.hit(touch)) {
                btnScreen.setFont(fontLight);
                btnJoystick.setFont(fontLightRed);
                if (controls == JOYSTICK) {
                    main.joystick.setSide(!main.joystick.side);
                    btnJoystick.setText(joystickText());
                } else {
                    controls = JOYSTICK;
                }
            }
            if (btnSound.hit(touch.x, touch.y)){
                isSoundOn = !isSoundOn;
                btnSound.setText(isSoundOn ? "Sound ON" : "Sound OFF");
            }
            if(btnBack.hit(touch.x, touch.y)){
                main.setScreen(main.screenMenu);
            }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        fontLight.draw(batch,"Settings", 0, 1500, SCR_WIDTH, Align.center, true);
        btnControls.font.draw(batch, btnControls.text, btnControls.x, btnControls.y);
        btnScreen.font.draw(batch, btnScreen.text, btnScreen.x, btnScreen.y);
        btnJoystick.font.draw(batch, btnJoystick.text, btnJoystick.x, btnJoystick.y);
        btnSound.font.draw(batch, btnSound.text, btnSound.x, btnSound.y);
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
        saveSettings();
    }

    @Override
    public void dispose() {

    }
    private String joystickText() {
        return main.joystick.side == RIGHT ? "Joystick RIGHT" : "Joystick LEFT";
    }

    private String SoundText() {
        return isSoundOn ? "Sound ON" : "Sound OFF";
    }

    private void saveSettings() {
        Preferences prefs = Gdx.app.getPreferences("MedievalBattleSettings");
        prefs.putBoolean("Joystick", main.joystick.side);
        prefs.putBoolean("Sound", isSoundOn);
        prefs.putInteger("Controls", controls);
        prefs.flush();
    }
}
