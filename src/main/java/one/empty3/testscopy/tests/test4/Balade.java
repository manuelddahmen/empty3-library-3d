/*
 * Copyright (c) 2023. Manuel Daniel Dahmen
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

package one.empty3.testscopy.tests.test4;

import javax.imageio.ImageIO;
import one.empty3.library.*;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomialeBezier;
import one.empty3.library.core.nurbs.Fct1D_1D;
import one.empty3.library.core.nurbs.FctXY;
import one.empty3.library.core.testing.Resolution;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.Tubulaire3;
import one.empty3.neuralnetwork.Dimension;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Balade extends TestObjetSub {

    private static final int VUE_1 = 30;
    private static final int FPS = 50;
    Tubulaire3 polygonSol = new Tubulaire3();
    ImageTexture imageTextureTrunk;
    private boolean useRecursive = false;

    public static void main(String[] args) {
        Balade balade = new Balade();
        balade.loop(true);
        balade.setMaxFrames(VUE_1*FPS);
        //balade.setDimension(new Resolution(320, 200));
        balade.setDimension(new Resolution(1920, 1080));
        new Thread(balade).start();
    }

    @Override
    public void ginit() {
        useRecursive = false;
        super.ginit();
        ITexture ciel_ensoleille = new ColorTexture(Color.BLUE);
        ITexture sol_sableux = new ColorTexture(new Color(104, 78, 51));
        try {
            imageTextureTrunk = new ImageTexture(new ECBufferedImage(Objects.requireNonNull(ImageIO.read(new File("resources/img/CIMG0454-modif-cs4.jpg")))));
            ciel_ensoleille = new ImageTexture(new ECBufferedImage(Objects.requireNonNull(ImageIO.read(new File("resources/ciel_ensoleille.jpg")))));
            sol_sableux = new ImageTexture(new ECBufferedImage(Objects.requireNonNull(ImageIO.read(new File("res/img/planets2/8k_saturn_ring_alpha.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        polygonSol = new Tubulaire3();
        polygonSol.getSoulCurve().setElem(
                new CourbeParametriquePolynomialeBezier());

        for(int i=0; i<5; i++) {
            polygonSol.getSoulCurve().getElem().getCoefficients().setElem(Point3D.random(10.0), i);
        }
        polygonSol.getDiameterFunction().setElem(new FctXY() {
            @Override
            public double result(double input) {
                return 2.0;
            }
        });
        polygonSol.setIncrU(0.002);
        polygonSol.setIncrV(0.002);


        polygonSol.texture(sol_sableux);

        scene().add(polygonSol);

        if(!(z() instanceof ZBufferImplRecursive) && useRecursive()) {
            setZ(new ZBufferImplRecursive(z.la(), z.ha()));
            z().scene(scene());
        }
        z().texture(new ColorTexture(Color.blue));
        //z().setDisplayType(ZBufferImpl.SURFACE_DISPLAY_LINES);
        z().setDisplayType(ZBufferImpl.DISPLAY_ALL);
        frame = 102;
    }

    @Override
    public void finit() throws Exception {
        super.finit();

        if (frame() < VUE_1 * FPS) {
            //z().setDisplayType(Representable.DISPLAY_ALL);
            z().texture(new ColorTexture(Color.BLACK));
            StructureMatrix<Point3D> mat = new StructureMatrix<>(2, Point.class);
            mat.setElem(new Point3D(-10d, 0d, -10d), 0, 0);
            mat.setElem(new Point3D(10d, 0d, -10d), 1, 0);
            mat.setElem(new Point3D(10d, 0d, 10d), 1, 1);
            mat.setElem(new Point3D(-10d, 0d, 10d), 0, 1);

            Point3D[] vects = new Point3D[]{mat.getElem(0, 0), mat.getElem(0, 1), mat.getElem(1, 0)};

            StructureMatrix<Point3D>[] v = new StructureMatrix[] {
                    new StructureMatrix<Point3D>(0, Point3D.class),
                    new StructureMatrix<Point3D>(0, Point3D.class),
                    new StructureMatrix<Point3D>(0, Point3D.class)};


            v[0].setElem(vects[0]);
            v[1].setElem(vects[1]);
            v[2].setElem(vects[2]);

            StructureMatrix<Point3D>[] v1 = new StructureMatrix[] {
                    new StructureMatrix<Point3D>(0, Point3D.class),
                    new StructureMatrix<Point3D>(0, Point3D.class),
                    new StructureMatrix<Point3D>(0, Point3D.class)};


            v1[0].setElem(vects[0].plus(Point3D.Y));
            v1[1].setElem(vects[1].plus(Point3D.Y));
            v1[2].setElem(vects[2].plus(Point3D.Y));


            Point3D a = polygonSol.getSoulCurve().getElem().calculerPoint3D((frame() * 1.0) /getMaxFrames());
            Point3D b = polygonSol.getSoulCurve().getElem().calculerPoint3D((frame() + 2.0) /getMaxFrames());

            Point3D y  = polygonSol.calculerPoint3D(0.25, 1.0*frame()/getMaxFrames());
            Point3D ym = polygonSol.calculerPoint3D(0.75, 1.0*frame()/getMaxFrames());


            Camera camera = new Camera(a, b, y.moins(ym).mult(1.0/Point3D.distance(y, ym)));

            camera.getScale().setElem(200.0);
            scene().cameraActive(camera);
        }

    }

    private boolean useRecursive() {
        return useRecursive;
    }
}
