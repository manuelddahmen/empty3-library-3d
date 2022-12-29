/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package one.empty3.tests;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.Tubulaire3;

import java.awt.*;

public class TestHumainMarche2 extends TestObjetSub {
    private HumainMarche humainMarche;

    public void tubeAddPoint(Tubulaire3 tube, Point3D p) {
        tube.getSoulCurve().getElem().getCoefficients().getData1d().add(p);
    }

    public void ginit() {
        setMaxFrames(180);
        z.setDisplayType(ZBufferImpl.DISPLAY_ALL);
        scene().lumieres().add(new LumierePonctuelle(new Point3D(10., 10., 2.), Color.BLUE));
        humainMarche = new HumainMarche();
        scene().add(humainMarche);
        humainMarche.setT(frame()/25.);
        humainMarche.init();
    }

    public void finit() {
        z().idzpp();
        scene().cameras().clear();
        Camera c = new Camera(Point3D.Z.mult(-80.), Point3D.O0, Point3D.Y);
        scene().cameras().add(c);
        c.declareProperties();
        scene().cameraActive(c);
        humainMarche.setT(frame()/25.);

    }

    public static void main(String[] args) {
        TestHumainMarche2 testHumain = new TestHumainMarche2();
        testHumain.setPublish(true);
        testHumain.setGenerate(testHumain.getGenerate()|GENERATE_MODEL);
        new Thread(testHumain).start();
    }

}

