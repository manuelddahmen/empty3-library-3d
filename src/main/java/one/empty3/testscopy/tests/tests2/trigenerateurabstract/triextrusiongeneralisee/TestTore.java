/*
 * Copyright (c) 2022. Manuel Daniel Dahmen
 */

/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen _manuel.dahmen@gmx.com_
 ***/


package one.empty3.testscopy.tests.tests2.trigenerateurabstract.triextrusiongeneralisee;

import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.*;

import java.awt.*;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class TestTore extends TestObjetSub {
    public static void main(String[] args) {
        TestTore tp = new TestTore();
        tp.setGenerate(GENERATE_IMAGE | GENERATE_MODEL);
        tp.loop(false);
        new Thread(tp).start();
    }

    @Override
    public void ginit() {
        Surface s = new SurfaceCercle(1);
        Chemin c = new CheminCercle(10);

        TRIExtrusionGeneralisee tri = new TRIExtrusionGeneralisee();


        tri.texture(new TextureCol(Color.WHITE));

        scene().add(tri);
    }

    @Override
    public void finit() {
    }

    @Override
    public void testScene() throws Exception {
    }

}
