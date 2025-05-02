package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.SCR_HEIGHT;
import static ru.samsung.medievalbattle.Main.SCR_WIDTH;
import static ru.samsung.medievalbattle.Main.joystickX;
import static ru.samsung.medievalbattle.Main.joystickY;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Soldier extends Object{
    public int phase, nPhases = 8;
    private long timeLastPhase, timePhaseInterval = 300;

    public Soldier(float x, float y) {
        super(x, y);
        width = 200;
        height = 200;
    }

    @Override
    public void move() {
        super.move();
        changePhase();
        outOfScreen();
    }

    public void changePhase(){
        if(TimeUtils.millis() > timeLastPhase+timePhaseInterval) {
            if (++phase == 8) phase = 0;
            timeLastPhase = TimeUtils.millis();
        }
    }

    private void outOfScreen(){
        if (x<width/2) {
            vx = 0;
            x = width/2;
        }
        if (x>SCR_WIDTH-width/2){
            vx = 0;
            x = SCR_WIDTH-width/2;
        }
        if (y<height/2) {
            vy = 0;
            y = height/2;
        }
        if (y>SCR_HEIGHT-height/2) {
            vy = 0;
            y = SCR_HEIGHT - height / 2;
        }
    }

    public void touchScreen(float tx, float ty){
        vx = (tx-x) / 250;
        vy = (ty-y) / 250;
    }

    public void touchScreen(Vector3 t){
        vx = (t.x-x) / 250;
        vy = (t.y-y) / 250;
    }

    public void touchJoystick(Vector3 t){
        vx = (t.x - joystickX) / 10;
        vy = (t.y - joystickY) / 10;
    }

    public void stop(){
        vx = 0;
        vy = 0;
    }
}
