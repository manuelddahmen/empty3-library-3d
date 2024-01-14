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

package tests.tests2.texturevideo;

import one.empty3.library.*;
import one.empty3.library.core.testing.Resolution;
import one.empty3.library.core.testing.TestObjetStub;
import one.empty3.library.core.tribase.Plan3D;
import one.empty3.testscopy.tests.test4.Balade;

/*__
 * @author Dahmen Manuel
 */
public class PlansVideo extends TestObjetStub {
    private 
        String avi1;
    private  String avi2;
    private  String avi3;
    private  String avi4;

    public void ginit() {

        z().setDisplayType(ZBufferImpl.SURFACE_DISPLAY_TEXT_QUADS);
        this.avi1 = avi1;
        this.avi2 = avi2;
        this.avi3 = avi3;
        this.avi4 = avi4;
    
        String f1;
        String f2;
        String f3;
        String f4;
            f1 = "resources/mov/file_example_AVI_480_750kB-mc.mp4";
            f2 = "resources/mov/VID_20200528_105353.mp4";
            f3 = "resources/mov/VID_20200416_201314.mp4";
            f4 = "resources/mov/bulles.mp4";
       /*}


       /* if (new File(f1).isFile() && new File(f2).isFile() && new File(f3).isFile() && new File(f4).isFile()) {

            //PlansVideo pc = new PlansVideo(f1, f2, f3, f4);

            pc.loop(true);

            pc.run();

        } else {

            System.err.println("Erreur un fichier ou l'autre n'existe pas");

        }*/
    

    
        ITexture tc1, tc2, tc3, tc4;

        tc1 = new TextureMov(f1);
        tc2 = new TextureMov(f2);
        tc3 = new TextureMov(f3);
        tc4 = new TextureMov(f4);

        Plan3D p1, p2, p3, p4;

        p1 = new Plan3D();
        p1.texture(tc1);
        p1.texture(tc1);
        p1.pointOrigine(Point3D.O0);
        p1.pointXExtremite(Point3D.X);
        p1.pointYExtremite(Point3D.Y);

        p2 = new Plan3D();
        p2.texture(tc2);
        p2.pointOrigine(Point3D.O0);
        p2.pointXExtremite(Point3D.X.mult(-1d));
        p2.pointYExtremite(Point3D.Y);

        p3 = new Plan3D();
        p3.texture(tc3);
        p3.pointOrigine(Point3D.O0);
        p3.pointXExtremite(Point3D.X);
        p3.pointYExtremite(Point3D.Y.mult(-1d));

        p4 = new Plan3D();
        p4.texture(tc4);
        p4.pointOrigine(Point3D.O0);
        p4.pointXExtremite(Point3D.X.mult(-1d));
        p4.pointYExtremite(Point3D.Y.mult(-1d));
        
        scene().add(p1);
        scene().add(p2);
        scene().add(p3);
        scene().add(p4);
    }
    public void finit() {
        scene().cameraActive(
                new Camera(
                        new Point3D(0d, 0d, 2d),
                        Point3D.O0,
                        Point3D.Y
                )
        );
        z().camera(scene().cameraActive());
     }


    public static void main(String[] args) {
        PlansVideo plansVideo = new PlansVideo();
        plansVideo.loop(true);
        plansVideo.setMaxFrames(2000);
        plansVideo.setDimension(new Resolution(1920, 1080));
        //plansVideo.setDimension(new Resolution(640, 480));

        new Thread(plansVideo).start();

    }

}
