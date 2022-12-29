/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package one.empty3.testscopy.tests;

import one.empty3.library.Camera;
import one.empty3.library.LumierePonctuelle;
import one.empty3.library.Point3D;
import one.empty3.library.ZBufferImpl;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomialeBezier;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;

public class TestBezierSans0 extends TestObjetSub {

    public void ginit() {
        setMaxFrames(18);
        z.setDisplayType(ZBufferImpl.DISPLAY_ALL);
        setDimension(HD1080);
        CourbeParametriquePolynomialeBezier patte = new CourbeParametriquePolynomialeBezier();

        patte.getCoefficients().setElem(new Point3D(0., -1.,  0.), 0);
        patte.getCoefficients().setElem(new Point3D(0., 1.,  -1.), 2);
        patte.getCoefficients().setElem(new Point3D(0., 1.,   0.), 1); //patte avant
        patte.getCoefficients().setElem(new Point3D(0., -1., -1.), 3);

        scene().add(patte);

        scene().lumieres().add(new LumierePonctuelle(new Point3D(0., 0., 2.), Color.BLUE/*.YELLOW*/));
    }

    public void finit() {
        scene().cameras().clear();
        Camera c = new Camera(Point3D.X.mult(4.), Point3D.O0, Point3D.Z);
        scene().cameras().add(c);
        c.declareProperties();
        scene().cameraActive(c);
    }

    public static void main(String [] args) {
        TestBezierSans0 testBezierSans0 = new TestBezierSans0();
        testBezierSans0.setPublish(true);
        new Thread(testBezierSans0).start();
    }

}
