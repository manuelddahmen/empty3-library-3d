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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.tests2.courbes_bsplines;


import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.nurbs.BSpline;
import one.empty3.library.core.nurbs.CourbeParametriqueBSpline;
import one.empty3.library.core.testing.TestObjet;

import java.awt.*;

/*__
 * Test
 *
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class
TestCParamBSpline extends TestObjet {
    private BSpline b;

    public static void main(String[] args) {
        TestCParamBSpline ts = new TestCParamBSpline();

        ts.publishResult(true);

        ts.setGenerate(GENERATE_IMAGE | GENERATE_MOVIE);

        ts.unterminable(false);

        ts.loop(false);

        ts.setMaxFrames(1);

        new Thread(ts).start();


    }

    @Override
    public void afterRenderFrame() {
    }

    @Override
    public void finit() {
        scene().clear();

        b = new CourbeParametriqueBSpline();
        /*
        double[] u = TestsBSpline.u(frame() + 1);
        Point3D[] p = TestsBSpline.p(frame()+1);
        for(int i=0; i<u.length; i++)
        b.add(u[i], p[i]);
*/
        b.texture(new TextureCol(Color.WHITE));
        scene().add(b);

        scene.cameraActive().setEye(Point3D.Z.mult(-50.0));

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
