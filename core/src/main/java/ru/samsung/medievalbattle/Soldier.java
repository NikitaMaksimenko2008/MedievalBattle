package ru.samsung.medievalbattle;

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
    }

    public void changePhase(){
        if(TimeUtils.millis() > timeLastPhase+timePhaseInterval) {
            if (++phase == 8) phase = 0;
            timeLastPhase = TimeUtils.millis();
        }
    }
}
