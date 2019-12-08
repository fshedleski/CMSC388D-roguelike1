package game;

import engine.Component;
import engine.EngineCore;
import engine.GameObject2D;

import java.awt.*;

public class PlayerBodyGComponent extends Component {
    private String assetsPlayerSheet = "boiS.png";

    public PlayerBodyGComponent(GameObject2D _parent) {
        super(_parent);
    }

    @Override
    // player visualization logic
    public void graphics(Graphics2D g) {
        try {
            Image im;
            im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 8);
            ((Graphics2D) g).drawImage(im, parent.getConstAf(), null);
        } catch (engine.ResourceNotFound e) {
            e.printStackTrace();
        }
    }
}