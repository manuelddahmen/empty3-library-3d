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

package one.empty3.feature.jviolajones;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    final static int LEFT = 0;
    final static int RIGHT = 1;

    List<Feature> features;

    public Tree() {
        features = new ArrayList<Feature>();
    }

    public void addFeature(Feature f) {
        features.add(f);
    }

    public float getVal(int[][] grayImage, int[][] squares, int i, int j, float scale) {
        Feature cur_node = features.get(0);
        while (true) {
            int where = cur_node.getLeftOrRight(grayImage, squares, i, j, scale);
            if (where == LEFT) {
                if (cur_node.has_left_val) {
                    //Logger.getAnonymousLogger().log(Level.INFO, "LEFT");
                    return cur_node.left_val;
                } else {
                    //Logger.getAnonymousLogger().log(Level.INFO, "REDIRECTION !");
                    //System.exit(0);
                    cur_node = features.get(cur_node.left_node);
                }
            } else {
                if (cur_node.has_right_val) {

                    //Logger.getAnonymousLogger().log(Level.INFO, "RIGHT");
                    return cur_node.right_val;
                } else {
                    //Logger.getAnonymousLogger().log(Level.INFO, "REDIRECTION !");
                    //System.exit(0);
                    cur_node = features.get(cur_node.right_node);
                }
            }
        }
    }

}
