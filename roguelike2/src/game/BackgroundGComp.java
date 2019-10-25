package game;

import engine.EngineCore;
import engine.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BackgroundGComp extends engine.Component {

    private AffineTransform af;
    private static String assetsBackGroundImg = "empt.png";

    public BackgroundGComp(GameObject _parent) {
        super(_parent);
        af = new AffineTransform();
        af.setToIdentity();
    }

    @Override
    public void graphics(Graphics2D g) {
        try {
            Image im = EngineCore.assetsCenter.getImage(assetsBackGroundImg,0);
            ((Graphics2D) g).drawImage(im, af, null);
        } catch (engine.ResourceNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
