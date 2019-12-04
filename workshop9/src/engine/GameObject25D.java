package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject25D extends GameObject2D {
    public static double vX[] = {370, 0};
    public static double vY[] = {0, 362};
    public static double vZ[] = {163, 98};
    public static double scalex = 1, scaley = 1;

    public double x, y, z;

    public GameObject25D(EngineCore _core) {
        super(_core);
        x = 0;
        y = 0;
        z = 0;
        af.scale(scalex, scaley);
    }

    // constructor that indicates a start position for game object
    public GameObject25D(EngineCore _core, double _x, double _y, double _z) {
        super(_core, vX[0]*_x+vY[0]*_y+vZ[0]*_z, vX[1]*_x+vY[1]*_y+vZ[1]*_z);
        x = _x;
        y = _y;
        z = _z;
        af.scale(scalex, scaley);
    }

    public static void scale(double sx, double sy) {
        vX[0] *= sx;
        vY[0] *= sx;
        vZ[0] *= sx;
        vX[1] *= sy;
        vY[1] *= sy;
        vZ[1] *= sy;
        scalex = sx;
        scaley = sy;
    }

}
