package game;

import engine.EngineCore;
import engine.GameObject2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BackgroundGComp extends engine.Component {
    private static String assetsBackGroundImg = "empt.png";

    public BackgroundGComp(GameObject2D _parent) {
        super(_parent);
    }

    @Override
    public void graphics(Graphics2D g) {
        try {
            Image im = EngineCore.assetsCenter.getImage(assetsBackGroundImg,0);
            ((Graphics2D) g).drawImage(im, parent.getConstAf(), null);
        } catch (engine.ResourceNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
