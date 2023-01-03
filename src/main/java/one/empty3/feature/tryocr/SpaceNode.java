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

package one.empty3.feature.tryocr;

import one.empty3.feature.PixM;
import one.empty3.library.Point3D;

import java.util.ArrayList;
import java.util.List;

public class SpaceNode {
    private PixM image;
    private double x, y;
    private List<Point3D> directions;

    public SpaceNode(PixM image, double x, double y, List<Point3D> directions) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.directions = directions;
    }

    public SpaceNode(PixM image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.directions = new ArrayList<>();
    }

    public PixM getImage() {
        return image;
    }

    public void setImage(PixM image) {
        this.image = image;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Point3D> getDirections() {
        return directions;
    }

    public void setDirections(List<Point3D> directions) {
        this.directions = directions;
    }

    // Point de la surface circulaire avec plusieurs directions en aires (quartiers) de cercles.
}
