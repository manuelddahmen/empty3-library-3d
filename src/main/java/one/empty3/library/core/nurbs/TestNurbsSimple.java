/*
 *
 *  * Copyright (c) 2024. Manuel Daniel Dahmen
 *  *
 *  *
 *  *    Copyright 2024 Manuel Daniel Dahmen
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

/*__
 * *
 * Global license : * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen _manuel.dahmen@gmx.com_
 * <p>
 * *
 */
package one.empty3.library.core.nurbs;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*__
 * Meta Description missing
 * @author Manuel Dahmen dathewolf@gmail.com
 */
public class TestNurbsSimple extends TestObjetSub {

    public static void main(String[] args) {

        TestNurbsSimple n = new TestNurbsSimple();

        n.setGenerate(GENERATE_MODEL | GENERATE_IMAGE);

        n.setMaxFrames(30);

        n.loop(true);

        new Thread(n).start();

    }

    @Override
    public void testScene() throws Exception {
        scene().getObjets().getData1d().clear();

        NurbsSurface n = new NurbsSurface();
        n.setMaillage(new Point3D[][]{
                        {
                                new Point3D(-1d, -1d, 0d),
                                new Point3D(-1d, 1d, 0d)
                        },
                        {
                                new Point3D(1d, -1d, 0d),
                                new Point3D(1d, 1d, 0d)}
                },
                new double[][]{
                        {1, 1},
                        {1, 1}
                });

        n.setDegreU(2);
        n.setDegreV(2);

        n.setReseauFonction(new double[][]{
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        });

        n.texture(new TextureCol(Color.WHITE));

        n.setStartU(0.0);
        n.setStartV(0.0);
        n.setEndU(1.0);
        n.setEndV(1.0);
        n.setIncrU(0.01);
        n.setIncrV(0.01);

        n.creerNurbs();

        scene().add(n);
        Logger.getAnonymousLogger().log(Level.INFO, "" + n);

        scene().cameraActive(new Camera(Point3D.Z.mult(-2d), Point3D.O0));
    }

    @Override
    public void finit() {
    }

    @Override
    public void ginit() {
    }
}
