package one.empty3.feature;

import one.empty3.library.Point2D;

import java.util.Objects;

public class P2P2 {
    private Point2D p0;
    private Point2D p1;

    public P2P2(Point2D p0, Point2D p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public Point2D getP0() {
        return p0;
    }

    public void setP0(Point2D p0) {
        this.p0 = p0;
    }

    public Point2D getP1() {
        return p1;
    }

    public void setP1(Point2D p1) {
        this.p1 = p1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof P2P2)) return false;
        P2P2 p2P2 = (P2P2) o;
        return Objects.equals(getP0(), p2P2.getP0()) &&
                Objects.equals(getP1(), p2P2.getP1());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP0(), getP1());
    }
}
