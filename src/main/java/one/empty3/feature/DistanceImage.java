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

package one.empty3.feature;

import one.empty3.feature.app.replace.javax.imageio.ImageIO;
import one.empty3.io.ProcessFile;
import one.empty3.library.Point3D;

import java.io.File;
import java.io.IOException;

public class DistanceImage extends ProcessFile {
    private PixM p1, p2;
    private PixM outP;
    private PixM countImage;

    @Override
    public boolean process(File in, File out) {

        outP = new PixM(p1.columns, p1.lines);

        for (int i = 0; i < p1.getColumns(); i++) {
            for (int j = 0; j < p1.getLines(); j++) {
                for (int c = 0; c < p1.getCompCount(); c++) {
                    p1.setCompNo(c);
                    p2.setCompNo(c);
                    outP.setCompNo(c);
                    searchProxymityFor(i, j, p2, p1);

                }
            }
        }
        try {
            ImageIO.write(outP.normalize(0, 1).getImage(), "jpg", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /***
     * Ajouter le poids du pixels (bombre de fois où il est utilisé
     * @param i
     * @param j
     * @param searchOn
     * @param origin
     */
    private void searchProxymityFor(int i, int j, PixM searchOn, PixM origin) {
        int iMax2 = Math.max(Math.max(p1.columns - i, 0), p1.columns);
        int jMax2 = Math.max(Math.max(p1.lines - i, 0), p1.lines);

        double weightTop = 1.0;
        for (int i1 = 0; i1 < iMax2; i1++) {
            for (int j1 = 0; j1 < jMax2; j1++) {
                double weight = p1.get(i1, j1)*(searchOn.get(i, j)/Math.min(1, Math.sqrt(i-i1)*(i-i1)+(j-j1)*(j-j1)));
                if(weight>0.1 && weight>weightTop)  {
                    double countUsage = (1/(1+count(i,j)));
                    outP.set(i1, j1, weight);
                }else if(weight<0.1 && weight<weightTop) {
                    double countUsage = (1/(1+count(i,j)));
                    outP.set(i1, j1, weight*countUsage1(i, j));

                }
            }
        }
    }

    private double countUsage1(int i, int j) {
        countImage.set(i, j, countImage.get(i,j));
        return countImage.get(i,j);
    }

    private double count(int i, int j) {
          return countImage.get(i, j);
    }
}
