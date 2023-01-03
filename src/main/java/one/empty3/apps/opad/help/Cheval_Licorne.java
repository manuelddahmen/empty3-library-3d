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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one.empty3.apps.opad.help;

import one.empty3.apps.opad.Bonus;
import one.empty3.library.core.gdximports.gdx_BSplineCurve;

/*__
 *  Court et vole pendant un laps de temps prédéfini.
 * le long d'un b-spline 30 objets/1000 en 20 secondes.
 * @author Se7en
 */
public class Cheval_Licorne  extends BonusClass {
    public Cheval_Licorne() {
        super();
        value = Double.parseDouble(bundle.getString("licorne.point"));
    }

    public gdx_BSplineCurve bspline(Bonus b, double chevalSize, double chevalVelocity) {
        throw new UnsupportedOperationException("Pas encore implanté");

    }
}
