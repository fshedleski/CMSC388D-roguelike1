package game;

import engine.*;
import engine.Component;

import java.awt.*;

public class CubeComponent extends Component {
    private static String assetsCubeImg = "Cube.png";

    public CubeComponent(GameObject2D _parent) {
        super(_parent);
        logic();
    }

    @Override
    public void logic() {
        this.Priority = (int)(-((GameObject25D)parent).x + -((GameObject25D)parent).y + ((GameObject25D)parent).z);
    }

    @Override
    public void graphics(Graphics2D g) {
        try {
            Image im = EngineCore.assetsCenter.getImage(assetsCubeImg, 0);
            ((Graphics2D) g).drawImage(im, parent.af, null);
        } catch (ResourceNotFound e) {
            e.printStackTrace();
        }
    }
}
