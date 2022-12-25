package one.empty3.library;

import java.awt.*;

public class PolygonsDistinctUV extends Polygons {
    private StructureMatrix<Point3D> uvMap;
    private ITexture texture2;

    public PolygonsDistinctUV() {
        coefficients = new StructureMatrix<>(2, Point3D.class);
        uvMap = new StructureMatrix<Point3D>(2, Point3D.class);
    }
    public PolygonsDistinctUV(StructureMatrix<Point3D> coefficients,
                              StructureMatrix<Point3D> uvMap) {
        this.coefficients = coefficients;
        this.uvMap = uvMap;
    }
    @Override
    public Point3D calculerPoint3D(double u, double v) {
        try {

            int indexU0 = (int) (u * coefficients.getData2d().get(0).size());
            int indexV0 = (int) (v * coefficients.getData2d().size());
            if (indexU0 > coefficients.getData2d().get(0).size() - 1) {
                indexU0 = coefficients.getData2d().get(0).size() - 1;
            }
            if (indexV0 > coefficients.getData2d().size() - 1) {
                indexV0 = coefficients.getData2d().size() - 1;
            }
            int indexU1 = (int) (indexU0 + 1.);
            int indexV1 = (int) (indexV0 + 1.);
            if (indexU1 > coefficients.getData2d().get(0).size() - 1) {
                indexU1 = coefficients.getData2d().get(0).size() - 1;
            }
            if (indexV1 > coefficients.getData2d().size() - 1) {
                indexV1 = coefficients.getData2d().size() - 1;
            }
            Point3D[] points = new Point3D[]{
                    coefficients.getElem(indexU0, indexV0), coefficients.getElem(indexU1, indexV0),
                    coefficients.getElem(indexU1, indexV1), coefficients.getElem(indexU0, indexV1)
            };
            //Sum ==>u0
            //Sum ==>u1
            //Sum ==>v0
            //Sum ==>v1
            Point3D[] uvElem = new Point3D[] {
                    uvMap.getElem(indexU0, indexV0), uvMap.getElem(indexU1, indexV0),
                    uvMap.getElem(indexU1, indexV1), uvMap.getElem(indexU0, indexV1)
            };
            double U = u * (coefficients.getData2d().get(0).size()) - indexU0;
            double V = v * (coefficients.getData2d().size()) - indexV0;
            assert U>=0 && U<=1 && V>=0 && V<=1;
            Point3D pUv0 = vectorPointPercent(points[0], points[1], U);
            Point3D pUv1 = vectorPointPercent(points[3], points[2], U);
            Point3D pU0v = vectorPointPercent(points[3], points[0], V);
            Point3D pU1v = vectorPointPercent(points[3], points[2], V);

            Point3D uvUv0 = vectorPointPercent(uvElem[0], uvElem[1], U);
            Point3D uvUv1 = vectorPointPercent(uvElem[3], uvElem[2], U);
            Point3D uvU0v = vectorPointPercent(uvElem[3], uvElem[0], V);
            Point3D uvU1v = vectorPointPercent(uvElem[3], uvElem[2], V);


            Point3D point3D = vectorPointPercent(pUv0, pUv1, V);// Discutable
            return point3D;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Point3D vectorPointPercent(Point3D a, Point3D b, double pc) {
        return a.plus(b.moins(a).mult(a));
    }

    @Override
    public void texture(ITexture t) {
        this.texture2 = t;
        texture = new TextureOp2D() {
            @Override
            public MatrixPropertiesObject copy() throws CopyRepresentableError, IllegalAccessException, InstantiationException {
                return null;
            }

            @Override
            public int getColorAt(double u, double v) {
                try {
                    int indexU0 = (int) (u * coefficients.getData2d().get(0).size());
                    int indexV0 = (int) (v * coefficients.getData2d().size());
                    if (indexU0 > coefficients.getData2d().get(0).size() - 1) {
                        indexU0 = coefficients.getData2d().get(0).size() - 1;
                    }
                    if (indexV0 > coefficients.getData2d().size() - 1) {
                        indexV0 = coefficients.getData2d().size() - 1;
                    }
                    int indexU1 = (int) (indexU0 + 1.);
                    int indexV1 = (int) (indexV0 + 1.);
                    if (indexU1 > coefficients.getData2d().get(0).size() - 1) {
                        indexU1 = coefficients.getData2d().get(0).size() - 1;
                    }
                    if (indexV1 > coefficients.getData2d().size() - 1) {
                        indexV1 = coefficients.getData2d().size() - 1;
                    }



                    Point3D[] points = new Point3D[]{
                            coefficients.getElem(indexU0, indexV0), coefficients.getElem(indexU1, indexV0),
                            coefficients.getElem(indexU1, indexV1), coefficients.getElem(indexU0, indexV1)
                    };
                    //Sum ==>u0
                    //Sum ==>u1
                    //Sum ==>v0
                    //Sum ==>v1
                    Point3D[] uvElem = new Point3D[]{
                            uvMap.getElem(indexU0, indexV0), uvMap.getElem(indexU1, indexV0),
                            uvMap.getElem(indexU1, indexV1), uvMap.getElem(indexU0, indexV1)
                    };
                    double U = u * (coefficients.getData2d().get(0).size()) - indexU0;
                    double V = v * (coefficients.getData2d().size()) - indexV0;
                    assert U >= 0 && U <= 1 && V >= 0 && V <= 1;
                    Point3D pUv0 = vectorPointPercent(points[0], points[1], U);
                    Point3D pUv1 = vectorPointPercent(points[3], points[2], U);
                    Point3D pU0v = vectorPointPercent(points[3], points[0], V);
                    Point3D pU1v = vectorPointPercent(points[3], points[2], V);

                    Point3D uvUv0 = vectorPointPercent(uvElem[0], uvElem[1], U);
                    Point3D uvUv1 = vectorPointPercent(uvElem[3], uvElem[2], U);
                    Point3D uvU0v = vectorPointPercent(uvElem[3], uvElem[0], V);
                    Point3D uvU1v = vectorPointPercent(uvElem[3], uvElem[2], V);


                    Point3D point3D = vectorPointPercent(pUv0, pUv1, V);// Discutable
                    return texture2.getColorAt(point3D.getX(), point3D.getY());
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                return 0;
            }
        };
    }

    public StructureMatrix<Point3D> getUvMap() {
        return uvMap;
    }

    public ITexture getTexture2() {
        return texture2;
    }

    public void setTexture2(ITexture texture2) {
        this.texture2 = texture2;
    }

    public void setUvMap(StructureMatrix<Point3D> uvMap) {
        this.uvMap = uvMap;
    }
}
