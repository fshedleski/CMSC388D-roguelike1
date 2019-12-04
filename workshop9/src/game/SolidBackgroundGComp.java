package game;

import engine.GameObject2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SolidBackgroundGComp extends engine.Component {

    private AffineTransform af;
    private static String assetsBackGroundImg = "empt.png";

    public SolidBackgroundGComp(GameObject2D _parent) {
        super(_parent);
        af = new AffineTransform();
        af.setToIdentity();
    }

    @Override
    public void graphics(Graphics2D g) {

    }
}
