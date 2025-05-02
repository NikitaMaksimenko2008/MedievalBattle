package ru.samsung.medievalbattle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Main extends Game {
    public static final float SCR_WIDTH = 900;
    public static final float SCR_HEIGHT = 1600;
    public static final int SCREEN = 0, JOYSTICK = 1, ACCELEROMETER = 2;
    public static final boolean LEFT = false, RIGHT = true;
    public static int controls = SCREEN;
    public static boolean isSoundOn = true;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont fontLight;
    public BitmapFont fontLightRed;
    public BitmapFont srf;

    Joystick joystick;
    public ScreenMenu screenMenu;
    public ScreenGame screenGame;
    public ScreenSettings screenSettings;
    public ScreenRules screenRules;
    public ScreenAbout screenAbout;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH,SCR_HEIGHT);
        touch = new Vector3();
        fontLight= new BitmapFont(Gdx.files.internal("shrift.fnt"));
        fontLightRed = new BitmapFont(Gdx.files.internal("shrift3.fnt"));
        srf = new BitmapFont(Gdx.files.internal("shrift2.fnt"));

        joystick = new Joystick(360, RIGHT);
        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        screenSettings = new ScreenSettings(this);
        screenRules = new ScreenRules(this);
        screenAbout = new ScreenAbout(this);
        setScreen(screenMenu);
    }

    @Override
    public void dispose() {
        batch.dispose();
        fontLight.dispose();
        fontLightRed.dispose();
        srf.dispose();
    }
}
