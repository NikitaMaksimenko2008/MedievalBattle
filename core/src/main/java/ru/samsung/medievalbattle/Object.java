package ru.samsung.medievalbattle;

public class Object {
    public float x, y;
    public float width, height;
    public float vx, vy;

    public Object(float x, float y) {
        this.x = x;
        this.y = y;

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
}
