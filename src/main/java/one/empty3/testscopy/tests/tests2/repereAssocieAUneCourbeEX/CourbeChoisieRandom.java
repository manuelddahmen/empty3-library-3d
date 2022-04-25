/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one.empty3.testscopy.tests.tests2.repereAssocieAUneCourbeEX;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricCurve;


/*__
 * @author Se7en
 */
public class CourbeChoisieRandom extends ParametricCurve {
    private final double h;
    private final double L;
    private final double l;
    private final double n;

    /*__
     * Dimension de la spire
     *
     * @param h
     * @param l
     * @param L
     */
    public CourbeChoisieRandom(double h, double l, double L, double n) {
        this.h = h;
        this.l = l;
        this.L = L;
        this.n = n;
    }


    @Override
    public Point3D calculerPoint3D(double t) {
        double m = 1;
        Math.sqrt(L * L + l * l);
        m = m * t / Math.sqrt(1 - t * t);
        return new Point3D(L * Math.cos(2 * Math.PI / n * m), l * Math.sin(2 * Math.PI / n * m), h * n * m);
    }

    @Override
    public Point3D calculerVitesse3D(double t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
