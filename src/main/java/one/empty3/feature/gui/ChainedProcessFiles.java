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

package one.empty3.feature.gui;

import one.empty3.feature.ClassSchemaBuilder;
import one.empty3.feature.TreeDiagram;

import java.util.Collections;
import java.util.List;

public class ChainedProcessFiles {
    private final TreeDiagram treeDiagram;

    ChainedProcessFiles(List<ClassSchemaBuilder.DiagramElement> diagram) {
        treeDiagram = new TreeDiagram(diagram);
    }


    public void execute() {

    }
}
