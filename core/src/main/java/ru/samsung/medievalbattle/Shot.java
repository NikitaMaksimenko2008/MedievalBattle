package ru.samsung.medievalbattle;

import static ru.samsung.medievalbattle.Main.SCR_HEIGHT;
import static ru.samsung.medievalbattle.Main.SCR_WIDTH;

public class Shot extends Object{
    public Shot(float x, float y) {
        super(x, y);
        width = 10;
        height = 120;
        vy = 30;
    }

    public boolean outOfScreen(){
        return y>SCR_HEIGHT+height/2 || y<-height/2 || x>SCR_WIDTH+width/2 || x<-width/2;
    }
}
