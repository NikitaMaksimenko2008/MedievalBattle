package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Object{
    public int hp;
    public int phase, nPhases = 8;
    private long timeLastPhase, timePhaseInterval = 300;

    public Enemy() {
        width = height = 200;
        type = MathUtils.random(0, 2);
        hp = getHealthTypes(type);
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
            if (++phase == nPhases) phase = 0;
            timeLastPhase = TimeUtils.millis();
        }
    }

    private int getHealthTypes(int type){
        switch (type){
            case 0: return 1;
            case 1: return 2;
            case 2: return 3;
        }
        return 0;
    }
}
