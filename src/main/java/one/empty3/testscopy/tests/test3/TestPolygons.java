package one.empty3.testscopy.tests.test3;
/*__
 * ect 14-08-17 2022
 */


import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestPolygons extends TestObjetSub {
    private final Point3D[][] controls = new Point3D[][]{
            {Point3D.n(2, -2, 0), Point3D.n(2, -1, 0), Point3D.n(2, 0, 0), Point3D.n(2, 1, 0), Point3D.n(2, 2, 0)},
            {Point3D.n(1, -2, 0), Point3D.n(1, -1, 0), Point3D.n(1, 0, 0), Point3D.n(1, 1, 0), Point3D.n(1, 2, 0)},
            {Point3D.n(0, -2, 0), Point3D.n(0, -1, 0), Point3D.n(0, 0, 0), Point3D.n(0, 1, 0), Point3D.n(0, 2, 0)},
            {Point3D.n(-1, -2, 0), Point3D.n(-1, -1, 0), Point3D.n(-1, 0, 0), Point3D.n(-1, 1, 0), Point3D.n(-1, 2, 0)},
            {Point3D.n(-2, -2, 0), Point3D.n(-2, -1, 0), Point3D.n(-2, 0, 0), Point3D.n(-2, 1, 0), Point3D.n(-2, 2, 0)}
    };
    ITexture texture;
    private Polygons s = new Polygons();

    public TestPolygons() {
        setMaxFrames(25 * 15);
    }


    @Override
    public void testScene(File f) {
    }

    @Override
    public void ginit() {
        z().setFORCE_POSITIVE_NORMALS(true);
        z().setDisplayType(ZBufferImpl.SURFACE_DISPLAY_TEXT_TRI);
        //setMaxFrames(1);
        StructureMatrix<Point3D> point3DStructureMatrix = new StructureMatrix<>(2, Point3D.class);
        point3DStructureMatrix.setAll(controls);
        s = new Polygons();
        s.setCoefficients(point3DStructureMatrix);
        s.texture(texture);
        s.setIncrU(1./10);
        s.setIncrV(1./10);
        scene().add(s);
        scene().cameraActive(new Camera(Point3D.Z.mult(-10.0), Point3D.O0));
        try {
            texture = new TextureImg(ECBufferedImage.getFromFile(
                    new File("resources/img/2020-10-19 13.24.58.jpg")));
            texture = new TextureMov("resources/mov/VID_20200416_201314.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.texture(texture);
    }


    @Override
    public void testScene() {
        for (int i = 0; i < s.getCoefficients().getData2d().size(); i++)
            for (int j = 0; j < s.getCoefficients().getData2d().get(i).size(); j++) {
                Point3D point3D = Point3D.random2(0.1);
                for (int k = 0; k < 3; k++)
                    s.getCoefficients().getElem(i,j).set(k, s.getCoefficients().getElem(i,j).get(k)+point3D.get(k));
            }
        //((TextureMov)texture).timeNext();
    }


    @Override
    public void finit() throws Exception {
        super.finit();
        scene().texture(new TextureCol(Color.WHITE));

    }

    public static void main(String[] args) {
        TestPolygons testPolygons = new TestPolygons();
        testPolygons.setResolution(Resolution.HD1080RESOLUTION.x()*2, Resolution.HD1080RESOLUTION.y()*2);
        new Thread(testPolygons).start();


    }
}
