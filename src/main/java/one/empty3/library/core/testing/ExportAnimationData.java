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

package one.empty3.library.core.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/*__
 * Created by manuel on 01-06-17.
 */
public class ExportAnimationData {
    private final TestObjet animation;
    private final File file;
    private PrintWriter printWriter;

    public ExportAnimationData(File file, TestObjet animation) throws FileNotFoundException {
        this.file = file;
        this.animation = animation;

        printWriter = new PrintWriter(new FileOutputStream(file));

        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

        printWriter.println("<exportData filename='" + file.getAbsoluteFile() +
                "' width='" + animation.getResx() + "' height='" + animation.getResy() + "' length='" + animation.getMaxFrames() + "'>");
    }

    public void writeGlobalData(String key, Object data) {

        printWriter.println("<global key='" + key + "'>" + data + "</global>");

    }

    public void writeFrameData(int frameNo, Object data) {

        printWriter.println("<frame frameNo='" + frameNo +
                "' type='" + data.getClass().getSimpleName() + "' javaType='" + data.getClass().getCanonicalName() +
                "'>" + data + "</global>");

    }

    public void end() {

        printWriter.println("</exportData>");
        printWriter.flush();
        printWriter.close();

    }
}
