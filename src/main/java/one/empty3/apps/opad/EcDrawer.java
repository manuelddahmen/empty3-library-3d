/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

package one.empty3.apps.opad;

import one.empty3.library.*;

import java.awt.*;

public class EcDrawer extends Drawer implements Runnable {

    protected DarkFortressGUI component;
    private Terrain terrain;
    private Bonus bonus;
    protected ZBuffer z;
    protected int w, h, aw, ah;
    private Vaisseau vaisseau;
    private PositionUpdate mover;

    public EcDrawer(DarkFortressGUI darkFortress) {

        super();


        this.component = darkFortress;

        z = new ZBufferImpl(640, 480);

        ((ZBufferImpl)z).setDisplayType(ZBufferImpl.SURFACE_DISPLAY_COL_TRI);

        darkFortress.setSize(640, 480);

        new Thread(this).start();

        initFrame(component);

    }

    public void resize() {
        z = ZBufferFactory.instance(w, h);
        z.couleurDeFond(new ColorTexture(Color.black));
        ((ZBufferImpl)z).setDisplayType(ZBufferImpl.SURFACE_DISPLAY_COL_TRI);

        ah = h;
        aw = w;
    }

    @Override
    public void setLogic(PositionUpdate m) {
        this.mover = m;
        vaisseau = new Vaisseau(mover);
        terrain = mover.getTerrain();
        bonus = new Bonus();
        mover.ennemi(bonus);
    }

    @Override
    public void run() {

        while (true) {
            dessiner();

            w = component.getWidth();
            h = component.getHeight();

            if (ah != h || aw != w) {
                resize();
            }
            /*try {
             Thread.sleep(10);
             } catch (InterruptedException e) {
             e.printStackTrace();
             }*/
        }
    }


    public void dessiner() {
        Graphics g = component.getGraphics();

        //z.couleurDeFond(new TColor(Color.BLACK));
        if (g != null && component.getWidth() > 0 && component.getHeight() > 0) {

            Scene scene = new Scene();

            if (mover != null) {
                scene.add(mover.getCircuit());
                scene.add(terrain);
                scene.add(bonus);
                scene.add(vaisseau.getObject());



                Camera camera;
                if (mover.getPlotter3D()!=null&&mover.getPlotter3D().isActive())
                    camera = mover.getPositionMobile().calcCameraMobile();
                else
                    camera = mover.getPositionMobile().calcCamera();

                Point3D pos = camera.getEye();
                Point3D dir = camera.getLookat().moins(pos).norme1();
                Point3D diff = dir.moins(pos).norme1();
                Point3D up = camera.getVerticale().getElem();

                Point3D posCam = pos;//.moins(dir.norme1());
                Point3D vertical = camera.getVerticale().getElem().norme1();
                Point3D vert2 = vertical.prodVect(dir).mult(-1);
                Point3D positionCamRear = posCam
                        .plus(camera.getLookat().moins(posCam).mult(-0.05));

                posCam = positionCamRear;

                scene.cameraActive(new Camera(posCam, dir, up));
                scene.cameraActive().declareProperties();
            }
            try {
                z.idzpp();
                z.scene(scene);
                z.draw(scene);
            } catch (Exception ex) {
                System.err.println(ex);
            }
            ECBufferedImage ri = z.image();

            Graphics g2 = ri.getGraphics();
            g2.setColor(Color.WHITE);
            //g2.drawString("Score : " + mover.score(), 0, ri.getHeight() - 40);

            g.drawImage(ri, 0, 0, component.getWidth(), component.getHeight(), null);

        }
    }

    public boolean isLocked() {
        return z.isLocked();
    }

    @Override
    public LineSegment click(Point2D p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
