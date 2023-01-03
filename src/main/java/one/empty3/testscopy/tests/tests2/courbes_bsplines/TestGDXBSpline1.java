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

package one.empty3.testscopy.tests.tests2.courbes_bsplines;

import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.gdximports.gdx_BSplineCurve;
import one.empty3.library.core.testing.TestObjet;
import one.empty3.library.core.tribase.TubulaireN2;

import java.awt.*;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestGDXBSpline1 extends TestObjet {
    private static final double INCR_PRECISION = 0.00001;
    TubulaireN2 t = new TubulaireN2();
    private gdx_BSplineCurve b;

    public static void main(String[] args) {
        TestGDXBSpline1 ts = new TestGDXBSpline1();

        ts.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        ts.loop(false);

        ts.setMaxFrames(10);

        new Thread(ts).start();

    }

    @Override
    public void finit() {


        b = new gdx_BSplineCurve();

        b.getParameters().setIncrU(INCR_PRECISION);


        b.instantiate(TestsBSpline.p2(frame()), 6);

        b.texture(new TextureCol(Color.WHITE));

        t.setSoulCurve(b);

        t.nbrAnneaux((int) (1 / INCR_PRECISION));
        t.setDiameter(1);
        t.nbrRotations(10);
        t.texture(new TextureCol(Color.WHITE));

        scene().add(t);

        scene.cameraActive().setEye(Point3D.Z.mult(-(2d * frame() + 2)));

    }

    @Override
    public void afterRenderFrame() {
    }

    @Override
    public void ginit() {

    }

    @Override
    public void testScene() throws Exception {
    }

    public void afterRender() {
    }
}
