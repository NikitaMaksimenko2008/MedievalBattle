package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.SCR_HEIGHT;
import static ru.samsung.medievalbattle.Main.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Object{
    public int type;
    private int health;

    public int phase, nPhases = 8;
    private long timeLastPhase, timePhaseInterval = 300;

    public Enemy() {
        width = height = 200;
        health = 1;
        type = MathUtils.random(0, 2);
        x = MathUtils.random(width/2, SCR_WIDTH-width/2);
        y = MathUtils.random(SCR_HEIGHT+height, SCR_HEIGHT*2);
        vy = MathUtils.random(-0.5f, -0.25f);
    }

    @Override
    public void move() {
        super.move();
        changePhase();
    }

    public void changePhase(){
        if(TimeUtils.millis() > timeLastPhase+timePhaseInterval) {
            if (++phase == 8) phase = 0;
            timeLastPhase = TimeUtils.millis();
        }
    }
}
