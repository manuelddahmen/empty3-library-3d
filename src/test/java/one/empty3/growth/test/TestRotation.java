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

package one.empty3.growth.test;/*
 * This file is part of Plants-Growth-2
 *     Foobar is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import junit.framework.TestCase;
import one.empty3.growth.graphics.Rotation2;
import one.empty3.library.Point3D;

import static one.empty3.growth.graphics.Rotation.rotate;

public class TestRotation extends TestCase {


    public void testRotationIdent1() {
        Point3D x = rotate(Point3D.O0, Point3D.X,
                2 * Math.PI, Point3D.Y);
        Point3D y = Point3D.Y;

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotationIdent2() {
        Point3D x = rotate(Point3D.O0, Point3D.X,
                2 * Math.PI, Point3D.X);
        Point3D y = Point3D.X;

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotationIdent3() {
        Point3D x = rotate(Point3D.O0, Point3D.X,
                2 * Math.PI, Point3D.Z);
        Point3D y = Point3D.Z;

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotation90() {
        Point3D x = rotate(Point3D.O0, Point3D.X,
                Math.PI, Point3D.Z);
        Point3D y = Point3D.Z.mult(-1.);

        assertEqualsPoint3D(x, y, 0.1);


    }

    public void testRotationNonO() {
        Point3D x = rotate(Point3D.X, new Point3D(10., 0., 0.),
                Math.PI, new Point3D(3., 5., 5.));
        Point3D y = new Point3D(3., -5., -5.);

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotation180() {
        Point3D x = rotate(new Point3D(11., 0., 0.), new Point3D(10., 0., 0.),
                Math.PI, new Point3D(3., 5., 0.));
        Point3D y = new Point3D(3., -5., 0.);

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotation30deg() {
        Point3D x = new Point3D(3., 5., 5.);
        Point3D y = x;

        for (int i = 0; i < 12 * 2; i++) {
            x = rotate(Point3D.X, new Point3D(10., 0., 0.),
                    Math.PI / 6, x);
        }


        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotation0degRandomPoint() {
        Point3D x = Point3D.random(10.);

        Point3D y = rotate(Point3D.X, new Point3D(10., 0., 0.),
                0., x);

        assertEqualsPoint3D(x, y, 0.1);

    }

    public void testRotation0degRandomAxe() {
        Point3D a = Point3D.random(10.);
        Point3D b = Point3D.random(10.);

        Point3D y = rotate(a, b,
                0., Point3D.X);

        assertEqualsPoint3D(Point3D.X, y, 0.1);

    }

    public void testRotation360degRandomAxe() {
        Point3D a = Point3D.random(10.);
        Point3D b = Point3D.random(10.);

        Point3D y = rotate(a, b,
                2 * Math.PI, Point3D.X);

        assertEqualsPoint3D(Point3D.X, y, 0.1);

    }

    public void assertEqualsPoint3D(Point3D x, Point3D y, double delta) {
        for (int i = 0; i < 3; i++) {
            assertEquals(y.get(i), x.get(i), delta);
        }
    }

    public void testRotationMethode2() {
        Rotation2 rotation2 = new Rotation2();

        Point3D intersection = rotation2.projection(Point3D.X, Point3D.Y, new Point3D(6., 5., 6.));
        assertEqualsPoint3D(intersection, new Point3D(1., 5., 0.), 0.001);

    }

}
