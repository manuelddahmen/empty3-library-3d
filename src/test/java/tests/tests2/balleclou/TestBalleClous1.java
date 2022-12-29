/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package tests.tests2.balleclou;

import one.empty3.library.*;
import one.empty3.library.core.extra.BalleClous;
import one.empty3.library.core.testing.TestObjetSub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*__
 * @author Se7en
 */
public class TestBalleClous1 extends TestObjetSub {

    public int MAXFRAMES = 2000;
    public int N = 21;
    private ITexture tc = new TextureCol(Color.red);
    private BalleClous ballec;
    private Point3D[] s;
    private Point3D[] v;
    private double V = 0.03;
    private double D = 1;

    public static void main(String[] args) {
        TestBalleClous1 th = new TestBalleClous1();

        th.loop(true);

        th.setResx(1920);

        th.setResy(480);

        th.MAXFRAMES = 4000;

        th.setGenerate(GENERATE_IMAGE);

        th.run();
    }

    @Override
    public void ginit() {
        s = new Point3D[N];
        v = new Point3D[N];
        for (int i = 0; i < N; i++) {
            s[i] = new Point3D(Point3D.O0);

            s[i].texture(new TextureCol(Color.WHITE));

            v[i] = new Point3D(Math.random() * (V / 2 - V), Math.random() * (V / 2 - V), Math.random() * (V / 2 - V));

        }
        try {

            tc =
                    new TextureImg(
                            new ECBufferedImage(ImageIO.read(new File("samples/img/manu.jpg"))));


        } catch (IOException ex) {
            Logger.getLogger(TestBalleClous1.class.getName()).log(Level.SEVERE, null, ex);
        }


        ballec = new BalleClous(Point3D.O0, 50);


        //ballec.setMaxX(65);

        //ballec.setMaxY(65);


        ballec.param(0.03);


        ballec.texture(tc);

        scene().add(ballec);


        scene().lumieres().add(new LumierePonctuelle(Point3D.Z, Color.YELLOW));


        Camera camera;
        camera = new Camera(new Point3D(0d, 0d, -1700d),
                new Point3D(0d, 0d, 0d));

        scene().cameraActive(camera);

    }

    public void bounce(int i) {
        s[i] = s[i].plus(v[i]);


        if (s[i].getX() > D && v[i].getX() > 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getX() < -D && v[i].getX() < 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getY() > D && v[i].getY() > 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getY() < -D && v[i].getY() < 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getZ() > D && v[i].getZ() > 0) {
            v[i].setZ(-v[i].getZ());
        }
        if (s[i].getZ() < -D && v[i].getZ() < 0) {
            v[i].setZ(-v[i].getZ());
        }
    }

    @Override
    public void testScene() throws Exception {
        for (int i = 0; i < s.length; i++) {
            bounce(i);
        }

        ballec.points().clear();

        for (int j = 0; j < s.length; j++) {
            if (s[j].getX() < 0) {
                s[j].setX(s[j].getX() + D);
            }
            if (s[j].getY() < 0) {
                s[j].setY(s[j].getY() + D);
            }
            if (s[j].getX() > D) {
                s[j].setX(s[j].getX() - D);
            }
            if (s[j].getY() > D) {
                s[j].setY(s[j].getY() - D);
            }
            ballec.addPoint(new Point2D(s[j].getX(), s[j].getY()));
        }


    }

}
