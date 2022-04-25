/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <manuel.dahmen@gmx.com>

 Creation time 25-oct.-2014
 ***/


package one.empty3.testscopy.tests.tests2.coeur;

import one.empty3.library.Point3D;
import one.empty3.library.core.tribase.TRIObjetGenerateurAbstract;

/*__
 * @author Manuel Dahmen <manuel.dahmen@gmx.com>
 */
public class Coeur extends TRIObjetGenerateurAbstract {
    private double b;

    public void param01(double b) {
        this.b = b;
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        double a = 1.0 * x / getMaxX();
        double t = 1.0 * y / getMaxY() * 2 * Math.PI;
        return new Point3D(a * Math.sin(t + b * 2 * Math.PI) * (1 + Math.cos(t)), a * Math.cos(t + b * Math.PI * 2) * (1 + Math.cos(t)), 0d);
    }

}
