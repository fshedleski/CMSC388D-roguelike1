package game;

import engine.Component;
import engine.EngineCore;
import engine.GameObject;
import engine.ResourceNotFound;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BulletGComponent extends Component {
    private String assetsPlayerSheet = "boiS.png";

    public Direction dir;
    public int MAXX, MAXY;
    private final double SCALE = .4;

    public BulletGComponent(GameObject _parent, Direction _dir) {
        super(_parent);
        dir = _dir;
        MAXX = 1366 - 56;
        MAXY = 768 - 68;
        parent.af.scale(SCALE, SCALE);
    }

    @Override
    public void graphics(Graphics2D g) {
        try {
            Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 38);

            ((Graphics2D) g).drawImage(im, parent.af, null);
        } catch(engine.ResourceNotFound e) {
            e.printStackTrace();
        }
    }
}
