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

package one.empty3.apps.tree.altree;

import java.util.HashMap;

import one.empty3.apps.tree.altree.TreeNode;

/*__
 * Created by manuel on 25-12-16.
 */
public class EquationSystem extends TreeNode {
    private String[] inconnues;
    private HashMap<String, Double> constantes;

    public EquationSystem(String expStr, String[] inconnue, HashMap<String, Double> values) {
        super(expStr);
        this.inconnues = inconnue;
        this.constantes = values;
        // Fonctions...
    }
}
