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

package one.empty3.tests;

import one.empty3.library.*;
import one.empty3.library.core.tribase.*;
import one.empty3.library.core.nurbs.*;
import one.empty3.library.core.testing.*;
import java.awt.Color;
import one.empty3.library.core.move.Trajectoires;
import org.apache.xpath.operations.Bool;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class TestChien2 extends TestObjetSub {

    public void ginit() {
        z.setDisplayType(ZBufferImpl.SURFACE_DISPLAY_COL_TRI);
     //   scene().cameraActive().eye().changeTo(new Point3D(16., 0., 0.));
       Tubulaire3 [] patte = new Tubulaire3[4];
      // ts[0] =
//Artifact : empty3-library-3d-gui ...
       Point3D tete = new Point3D(0.,0.,0. ); //tête 
       Point3D queue = new Point3D(1.,0.,1.); // queue
       Sphere tetes = new Sphere(tete, 0.4); //sphère 
tetes.texture(new TextureCol(Color.RED));
queue.texture(new TextureCol(Color.BLACK));

/*Parallelepiped corps = new Parallelepiped(tete,
   new Point3D(0.,0.,0.5), 
   new Point3D( 1.,0.,0.5),
   new Point3D(0.,0.,0.5 ),
            new TextureCol(Color.BLUE)
                   );*///parallel polyèdres largeur y 0.5
   for(int i=0;i<4; i++) {
       patte[i] = new Tubulaire3();
       patte[i].texture(new TextureCol(Color.ORANGE));
      ((FctXY)( patte[i].getDiameterFunction().getElem())).setFormulaX("0.6");
   }
Tubulaire3 corp;
  corp = new Tubulaire3();
       corp.texture(new TextureCol(Color.ORANGE));
        ((FctXY)( corp.getDiameterFunction().getElem())).setFormulaX("0.6");
       ((CourbeParametriquePolynomialeBezier)(patte[0].getSoulCurve().getElem())). getCoefficients().setElem(new Point3D(0.,0.25,0.), 0);
       ((CourbeParametriquePolynomialeBezier)(patte[0].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(0.,0.25,1.), 1); //patte avant
        ((CourbeParametriquePolynomialeBezier)(patte[0].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(0.,-0.25,1.), 2);
        ((CourbeParametriquePolynomialeBezier)(patte[0].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(0.,-0.25,0.), 3);
// patte avant #2
((CourbeParametriquePolynomialeBezier)(patte[2].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(1.,0.25,0.), 0);
((CourbeParametriquePolynomialeBezier)(patte[2].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(1.,0.25,1.), 1); //patte arrière #1 
((CourbeParametriquePolynomialeBezier)(patte[2].getSoulCurve().getElem())).getCoefficients().setElem( new Point3D(1.,-0.25,1.), 2);// patte avant #2
((CourbeParametriquePolynomialeBezier)(patte[2].getSoulCurve().getElem())).getCoefficients().setElem(new Point3D(1.,-0.25,0.), 3);
        //1,0,0 //etx queue.
    
        //scene().add(corps);
        scene().add(tetes);
        for(int i=0;i<4; i+=2) {
            scene().add(patte[i]);

        } 
//        scene().cameraActive().getEye().setZ(-10.)

        LumierePonctuelle lumierePonctuelle = new LumierePonctuelle();
        StructureMatrix<Boolean> objectStructureMatrix = new StructureMatrix<>(0, Boolean.class);
        objectStructureMatrix.setElem(true);
        lumierePonctuelle.setDirectional(objectStructureMatrix);
        lumierePonctuelle.setOrig(Point3D.O0);
        scene().lumieres().add(lumierePonctuelle) ;
   }
    public void finit() {
        Point3D sphere = Trajectoires.sphere(/*Point3D.O0, Point3D.Z, Point3D.X,*/
          1.0 * frame() / 360,
                0.0, 16.0);
        Point3D circlePoint = P.n(Math.cos(1.0 * frame() / 360),
            Math.sin(1.0 * frame() / 360),
            Math.cos(1.0*frame()/360)).mult(3.0);
        //scene().cameras().clear();
        Camera c = new Camera(circlePoint, Point3D.O0, Point3D.Y);
        //c.setMatrix(c.getMatrice().tild());
        c.declareProperties();
        scene().cameraActive(c);
        z().camera(c);
    }

    public static void main(String [] args) {
        TestChien2 testChien2 = new TestChien2();
        testChien2.setMaxFrames(900);
        new Thread(testChien2).start();
    }
}
