/*
 * Copyright (c) 2022-2023. Manuel Daniel Dahmen
 *
 *
 *    Copyright 2012-2023 Manuel Daniel Dahmen
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tests.tests2.spherestournent;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * Created by Win on 24-01-16.
 */
public class TestSpheresMove extends TestObjetSub {
    private TRISphere[] sps;
    private Trajectoire tr;
    private Matrix33[] matricess = new Matrix33[]{Matrix33.I, Matrix33.XYZ, Matrix33.YZX, Matrix33.ZXY};
    private Matrix33 matrix;

    public static void main(String[] args) {
        TestSpheresMove ts = new TestSpheresMove();

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
