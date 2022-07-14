package one.empty3.feature.tryocr;

import one.empty3.feature.shape.Rectangle;

public class Rectangle2 {
    private int x, y, w, h;

    public Rectangle2(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rectangle2(Rectangle rectangle) {
        this.x = (int)rectangle.getX();
        this.y = (int)rectangle.getY();
        this.w = (int)rectangle.getWidth();
        this.h = (int)rectangle.getHeight();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
