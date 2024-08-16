package one.empty3.apps.facedetect;

import one.empty3.library.Point3D;
import one.empty3.library.Polygons;
import one.empty3.library.core.nurbs.ParametricSurface;
import one.empty3.library.core.nurbs.SurfaceParametriquePolynomiale;
import one.empty3.library.core.nurbs.SurfaceParametriquePolynomialeBezier;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DistanceBezier3 extends DistanceAB {
    final double[][][] doubles;
    final double DIM_MAX = Double.MAX_VALUE;
    final int dim = 1000;

    public DistanceBezier3(List<Point3D> A, List<Point3D> B, Dimension2D aDimReal, Dimension2D bDimReal, boolean opt1, boolean optimizeGrid) {
        super();
        Logger.getAnonymousLogger().log(Level.INFO, "DistanceBezier3() constructor started");
        doubles = new double[5][dim][dim];
        for (int u = 0; u < dim; u++) {
            // Closer A point
            for (int v = 0; v < dim; v++) {
                //[0][u][v] =
                final double finalU = 1.0 * u / dim;
                final double finalV = 1.0 * v / dim;
                double[] dist_current_min = new double[A.size()];
                Arrays.fill(dist_current_min, DIM_MAX);
                for (int i = 0; i < B.size(); i++) {
                    Point3D b = B.get(i);
                    Double distance = Point3D.distance(b, new Point3D(finalU, finalV, 0.0));
                    if (distance < dist_current_min[i]) {
                        dist_current_min[i] = distance;
                        Point3D point3D1 = A.get(i);
                        // closer b point for B(u,v)
                        doubles[0][u][v] = point3D1.getX();
                        doubles[1][u][v] = point3D1.getY();
                        doubles[2][u][v] = dist_current_min[i];
                    }
                }
                double ub = doubles[0][u][v];
                double vb = doubles[1][u][v];
                for (int i = 0; i < A.size(); i++) {
                    Point3D aTest = A.get(i);
                    doubles[3][u][v] = aTest.getX();
                    doubles[4][u][v] = aTest.getY();
                }
            }
        }
        Logger.getAnonymousLogger().log(Level.INFO, "DistanceBezier3() constructor ended");
    }


    public Point3D findAxPointInB(double u, double v) {
        if((int)(u * dim) >= dim) System.exit(1);
        if((int)(v * dim) >= dim) System.exit(1);
        return new Point3D(doubles[3][(int) (u * dim)][(int) (v * dim)], doubles[4][(int) (u * dim)][(int) (v * dim)], 0.0);

    }


}