/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

package one.empty3.apps.newboardgames;

import com.jogamp.graph.geom.Triangle;
import com.jogamp.nativewindow.AbstractGraphicsDevice;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.awt.TextureRenderer;
import one.empty3.apps.opad.*;
import one.empty3.apps.opad.Timer;
import one.empty3.apps.opad.help.PiloteAuto;
import one.empty3.library.*;
import one.empty3.library.core.nurbs.CourbeParametriquePolynomiale;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoglDrawerBoardGame extends JoglDrawer {

    private Board board;
    private Camera camera;

    public JoglDrawerBoardGame(WindowDrawing darkFortressGUI) {
        super(darkFortressGUI);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public void setBoard(Board board) {
        this.board = board;
        this.camera = board.camera();
    }
    @Override
    public void display(GLAutoDrawable gLDrawable) {
        if (!wasAnimating) {
            animator.start();
            wasAnimating = true;
        }

        millis = System.currentTimeMillis();
        Logger.getAnonymousLogger().log(Level.INFO, "FPS " + 1000/((millis - millis0)));
        millis0 = millis;

        try {
            if (glu == null )
                glu = GLU.createGLU();

            gl = gLDrawable.getGL().getGL2();
            //glu = GLU.createGLU();
        } catch (Exception e) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return;
        }
        // Change to projection matrix.
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        glu.gluPerspective(60, 1.33, 1.0, 100.0);
        gl.glLoadIdentity();

        if(board!=null && board.camera!=null) {
            //Logger.getAnonymousLogger().log(Level.INFO, "setCamera");
            //camera = board.camera();
        } else if(board==null)
            Logger.getAnonymousLogger().log(Level.INFO, "board==null");


        if(camera!=null) {
            Point3D pos = camera.getEye();
            Point3D dir = camera.getLookat().moins(pos).norme1();
            diff = dir.moins(pos).norme1();
            Point3D up = camera.getVerticale().getElem();


            Point3D posCam = pos;//.moins(dir.norme1());
            Point3D vertical = camera.getVerticale().getElem().norme1();
            Point3D vert2 = vertical.prodVect(dir).mult(-1);

            posCam = posCam.plus(camera.getLookat().moins(posCam).mult(-0.05));

            up = dir.prodVect(up.prodVect(dir));

            glu.gluLookAt(posCam.get(0), posCam.get(1), posCam.get(2),
                    dir.get(0), dir.get(1), dir.get(2),
                    up.get(0), up.get(1), up.get(2));

            if (toggleMenu.isDisplayScore())
                draw("Score :  " + 100, Color.WHITE, glu, gl);
            if (toggleMenu.isDisplayEnergy())
                draw("Life :  " + 100, new Dimension(30, 10), Color.GREEN, glu, gl);


            drawToggleMenu(glu, gl);

            drawTrajectory(getPlotter3D(), glu, gl);

            Sphere sphere = new Sphere(new Axe(Point3D.Y.mult(10), Point3D.Y.mult(-10)), 10);
            sphere.texture(new ColorTexture(Color.blue));
            sphere.setIncrU(0.1);
            sphere.setIncrV(0.1);

            draw(sphere, glu, gl);

            for(int i=0; i<board.getSize2D().getX(); i++)
                for(int j=0; j<board.getSize2D().getY(); j++)
                    draw((RepresentableConteneur) board.cellAt(i, j), glu, gl);
        } else {
            Logger.getAnonymousLogger().log(Level.INFO, "Camera == null");
        }


    }

}
