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
        enemySettings(type);
        x = MathUtils.random(width/2, SCR_WIDTH-width/2);
        y = MathUtils.random(SCR_HEIGHT+height, SCR_HEIGHT*2);
    }

    @Override
    public void move() {
        super.move();
        changePhase();
    }

    public boolean outOfScreen(){
        return y<-height/2;
    }

    public void changePhase(){
        if(TimeUtils.millis() > timeLastPhase+timePhaseInterval) {
            if (++phase == nPhases) phase = 0;
            timeLastPhase = TimeUtils.millis();
        }
    }

    private void enemySettings(int type){
        switch (type){
            case 0:
                hp = 1;
                vy = MathUtils.random(-0.8f, -0.6f);
                break;
            case 1:
                hp = 2;
                vy = MathUtils.random(-0.6f, -0.4f);
                break;
            case 2:
                hp = 3;
                vy = MathUtils.random(-0.5f, -0.20f);
                break;
        }
    }
}
