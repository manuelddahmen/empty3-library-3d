/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package one.empty3.testscopy.tests.tests2.trihole;

import one.empty3.library.*;
import one.empty3.library.core.extra.Polyhedron;
import one.empty3.library.core.testing.TestObjetStub;

import java.awt.*;

public class TestPolyhedron1 extends TestObjetStub {
    private Polyhedron polyhedron;

    @Override
    public void ginit() {
        z().setDisplayType(ZBufferImpl.SURFACE_DISPLAY_COL_TRI);

        z().setFORCE_POSITIVE_NORMALS(true);

        scene().cameraActive(new Camera(Point3D.Z.mult(-5),
                new Point3D(Point3D.O0), new Point3D(Point3D.Y)));


        scene().lumieres().add(new Lumiere() {
            @Override
            public int getCouleur(int base, Point3D p, Point3D n) {
                double[] doubles = Lumiere.getDoubles(base);
                return Lumiere.getInt(Point3D.toArray1d(new Point3D(doubles).prodVect(p.prodVect(n)).norme1(), doubles));
            }
        });


        polyhedron = new Polyhedron();

        polyhedron.texture(new TextureCol(Color.RED));

        scene().add(polyhedron);
    }

    public void finit() {
        polyhedron.clean();
        for(int i=0; i<polyhedron.points.size(); i++) {
            if(Math.random()>0.9) {
                polyhedron.add(polyhedron.points.get(i)
                        .plus(Point3D.random(2.0)));
            }
        }

        polyhedron.steps();
    }

    public static void main(String [] args) {
        TestPolyhedron testPolyhedron = new TestPolyhedron();

        testPolyhedron.loop(true);

        testPolyhedron.setMaxFrames(30);

        new Thread(testPolyhedron).start();
    }
}
