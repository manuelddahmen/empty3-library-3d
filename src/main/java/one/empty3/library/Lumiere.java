/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package one.empty3.library;
import java.awt.Color;
/*__
 * @author Atelier
 */
public abstract class Lumiere  extends Representable{
  // ambient specular diffuse shinyness
   protected Color La=Color.BLACK,Ls=Color.WHITE, Ld=Color.WHITE;
   
   protected double S=0.5;

    public abstract int getCouleur(int base, Point3D p, Point3D n);
    public int getLa() {return La.getRGB();}
    public int getLs() {return Ls.getRGB();}
    public int getLd() {return Ld.getRGB();}

    public static double [] getRgb(Color c) {
       return new double[] {(c.getRed()/255f),
          (c.getGreen()/255f),
          (c.getBlue()/255f)};
    }
  
  public static int  getInt(double [] d) {
       int res = 0xFF000000;
    for(int i=0 ;i<3;i++) {
        res += ((int)(float)(d[i]*0xff))<<((2-i)*8);
    }
    return res;//|0xFF000000;
  }

   public static double[] getDoubles(int c) {
        double [] res = new double[4];
       for(int i=0 ;i<3;i++) {
        res[i] = (((c&(0xff<<((2-i)*8)) )>>((2-i)*8)))/255.;
       }
       if(res.length>3)
           res[3] = 0.0;
       return res;
    }
    public static Color getColorD(double[] d) {
        return new Color((float)(d[0]), (float)(d[1]),(float)(d[2]));
    }
}
