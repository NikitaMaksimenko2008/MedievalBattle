package ru.samsung.medievalbattle;

import com.badlogic.gdx.math.MathUtils;

public class Object {
    public float x, y;
    public float width, height;
    public float vx, vy;
    public int type;

    public Object(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public Object() {

    }

    public void move(){
        x += vx;
        y += vy;
    }

    public float scrX() {
        return x - width / 2;
    }

    public float scrY() {
        return y - height / 2;
    }

    public boolean overlap(Object o){
        return Math.abs(x-o.x) < width/2+o.width/2 && Math.abs(y-o.y) < height/2+o.height/2;
    }
}
