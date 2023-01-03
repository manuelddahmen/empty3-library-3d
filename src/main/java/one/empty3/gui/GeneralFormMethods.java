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

package one.empty3.gui;

import one.empty3.library.ITexture;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by manue on 11-07-19.
 */
public class GeneralFormMethods {
    public static double getD(JTextField f)
    {
        return Double.parseDouble(f.getText());
    }
    public static int getInt(JTextField f)
    {
        return Integer.parseInt(f.getText());
    }
    public static Point3D getP(JTextField [] f)
    {
        return new Point3D(getD(f[0]), getD(f[1]), getD(f[2]));
    }
    public static ITexture getTextColInt(JTextField [] f)
    {
        return new TextureCol(new Color(getInt(f[0]), getInt(f[0]), getInt(f[0])));
    }
    /*
    public static ITexture getTextImg(JTextField [] f)
    {

    }
    public static ITexture getTextMov(JTextField [] f)
    {

    }
    */
}
