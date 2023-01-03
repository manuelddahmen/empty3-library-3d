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

package one.empty3.feature.facemorph;

import one.empty3.feature.PixM;
import one.empty3.feature.app.replace.javax.imageio.ImageIO;
import one.empty3.feature.myfacedetect.WeightingFunction;
import one.empty3.io.ProcessFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RunFeatures extends ProcessFile {
    WeightingFunction function;
    @Override
    public boolean process(File in, File out) {
        PixM imageIn = PixM.getPixM(Objects.requireNonNull(ImageIO.read(in)), maxRes);

        double ratioFace = 1;
        double ratioXy = (1.0)*imageIn.getColumns()/imageIn.getLines();

        int filterMaxRes = 10;
        function = new WeightingFunction(filterMaxRes, (int)(filterMaxRes*ratioXy));

        PixM imageOut = imageIn.applyFilter(function);

        try {
            ImageIO.write(imageOut.normalize(0.,1.).getImage(), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
