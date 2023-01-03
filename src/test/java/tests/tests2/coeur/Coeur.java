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

/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <manuel.dahmen@gmx.com>

 Creation time 25-oct.-2014
 ***/


package tests.tests2.coeur;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricSurface;
import one.empty3.library.core.tribase.TRIObjetGenerateurAbstract;

/*__
 * @author Manuel Dahmen <manuel.dahmen@gmx.com>
 */
public class Coeur extends ParametricSurface {
    private double b;

    public void param01(double b) {
        this.b = b;
    }

    @Override
    public Point3D calculerPoint3D(double x, double y) {
        double a = x;
        double t = y * 2 * Math.PI;
        return new Point3D(a * Math.sin(t + b * 2 * Math.PI) * (1 + Math.cos(t)), a * Math.cos(t + b * Math.PI * 2) * (1 + Math.cos(t)), 0d);
    }

}
