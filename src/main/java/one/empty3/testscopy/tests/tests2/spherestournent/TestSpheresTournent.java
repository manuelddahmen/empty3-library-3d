/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package one.empty3.testscopy.tests.tests2.spherestournent;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * Created by Win on 24-01-16.
 */
public class TestSpheresTournent extends TestObjetSub {
    private TRISphere[] sps;
    private Trajectoire tr;
    private Matrix33[] matricess = new Matrix33[]{Matrix33.I, Matrix33.XYZ, Matrix33.YZX, Matrix33.ZXY};
    private Matrix33 matrix;

    public static void main(String[] args) {
        TestSpheresTournent ts = new TestSpheresTournent();

        ts.setMaxFrames(3000);

        new Thread(ts).start();
    }

    public void ginit() {
        sps = new TRISphere[1];

        int i = 0;
        for (i = 0; i < sps.length; i++) {
            sps[i] = new TRISphere(Point3D.X.mult(1d), 1d);

            sps[i].texture(new TextureCol(Color.blue));
        }

        camera(new Camera(Point3D.Z.mult((double) -sps.length), Point3D.O0));
    }

    public void testScene() {


    }
}
