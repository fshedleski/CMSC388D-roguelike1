package game;

import engine.Component;
import engine.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CubeLineComponent extends Component {
    private static String assetsCubeImg = "Cube.png";

    double d = 1270;
    int drate = 40;
    double s = 150;
    int srate = 5;


    public CubeLineComponent(GameObject2D _parent) {
        super(_parent);
        logic();
    }

    @Override
    public void logic() {
        this.Priority = (int)(-((GameObject25D)parent).x + -((GameObject25D)parent).y + ((GameObject25D)parent).z);
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_W)) {
            d+=drate;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_S)) {
            d-=drate;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_A)) {
            s-=srate;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_D)) {
            s+=srate;
        }
        /*
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_UP)) {
            ((GameObject25D)parent).y-=5;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_DOWN)) {
            ((GameObject25D)parent).y+=5;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_LEFT)) {
            ((GameObject25D)parent).x-=5;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_RIGHT)) {
            ((GameObject25D)parent).x+=5;
        }
        */
    }

    @Override
    public void graphics(Graphics2D g) {
        double p000[] = proj(((GameObject25D)parent).x, ((GameObject25D)parent).y, ((GameObject25D)parent).z, d);
        double p100[] = proj(((GameObject25D)parent).x+s, ((GameObject25D)parent).y, ((GameObject25D)parent).z, d);
        double p010[] = proj(((GameObject25D)parent).x, ((GameObject25D)parent).y+s, ((GameObject25D)parent).z, d);
        double p110[] = proj(((GameObject25D)parent).x+s, ((GameObject25D)parent).y+s, ((GameObject25D)parent).z, d);
        double p001[] = proj(((GameObject25D)parent).x, ((GameObject25D)parent).y, ((GameObject25D)parent).z+s, d);
        double p101[] = proj(((GameObject25D)parent).x+s, ((GameObject25D)parent).y, ((GameObject25D)parent).z+s, d);
        double p011[] = proj(((GameObject25D)parent).x, ((GameObject25D)parent).y+s, ((GameObject25D)parent).z+s, d);
        double p111[] = proj(((GameObject25D)parent).x+s, ((GameObject25D)parent).y+s, ((GameObject25D)parent).z+s, d);
        g.setColor(Color.BLUE);
        drawLineAux(g, p000, p100);
        drawLineAux(g, p000, p001);
        drawLineAux(g, p000, p010);
        drawLineAux(g, p110, p100);
        drawLineAux(g, p110, p010);
        drawLineAux(g, p110, p111);
        drawLineAux(g, p101, p001);
        drawLineAux(g, p101, p111);
        drawLineAux(g, p101, p100);
        drawLineAux(g, p011, p111);
        drawLineAux(g, p011, p001);
        drawLineAux(g, p011, p010);
    }

    private double[] proj(double x, double y, double z, double d) {
        double[] ret = new double[2];
        ret[0] = x/(1-z/d);
        ret[1] = y/(1-z/d);
        return ret;
    }

    private void drawLineAux(Graphics2D g, double[] p1, double[] p2) {
        g.drawLine((int)p1[0], (int)p1[1], (int)p2[0], (int)p2[1]);
    }
}
