package tests.tests2.pieuvre;

import one.empty3.library.*;
import one.empty3.library.core.nurbs.ParametricSurface;

import java.awt.*;

/*__
 * Created by Win on 15-11-18.
 */
public class Pieuvre extends RepresentableConteneur {
    private Color color;
    private int nbrBras;
    private ParametricSurface bras;
    private ParametricSurface tete;
    private double t;
    double angle;
    Matrix33[] matrix22;


    public Pieuvre(int nbrBras, Color color) {

        this.nbrBras = nbrBras;
        this.color = color;
        matrix22 = new Matrix33[nbrBras];
        for (int i = 0; i < nbrBras; i++) {
            matrix22[i] = new Matrix33(
                    new Double[]{Math.sin(angle), Math.cos(angle), 0d,
                            -Math.cos(angle), Math.sin(angle), 0d,
                            0d, 0d, 1d});
        }

    }

    public void setT(double t) {
        this.t = t;
    }

    class Bras extends ParametricSurface {
        private int noBras;
        private ITexture text;


        public Bras(int noBras, int nbrBras, ITexture text) {
            this.noBras = noBras;
        }

        @Override
        public Point3D calculerPoint3D(double u, double v) {
            Point3D point3D = new Point3D(u * t * Math.sin(2 * Math.PI), Math.cos(2 * Math.PI * v), 0d);

            angle = 1.0 * noBras / nbrBras;
            return matrix22[noBras].mult(point3D);
        }

    }

    public Pieuvre() {
        for (int i = 0; i < nbrBras; i++) {
            int noBras = i;
            bras = new Bras(noBras, nbrBras, new TextureCol(Color.YELLOW));


            add(bras);
        }
        tete = new Sphere(new Axe(Point3D.O0, Point3D.X), 2.0);
        tete.texture(new TextureCol(Color.RED));

        add(tete);
    }


}
