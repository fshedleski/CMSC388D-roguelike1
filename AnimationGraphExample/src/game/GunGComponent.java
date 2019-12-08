package game;

import engine.Component;
import engine.EngineCore;
import engine.GameObject;
import engine.ResourceNotFound;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GunGComponent extends Component{

    private String assetsPlayerSheet = "boiS.png";
    private int headIdx;
    public Direction dir;

    public GunGComponent(GameObject _parent) {
        super(_parent);
        headIdx = 0;
        dir = Direction.Down;
    }

    @Override
    public void graphics(Graphics2D g) {
        boolean animateHead = false;

        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_UP)) {
            this.dir = Direction.Up;
            animateHead = true;
        } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_DOWN)) {
            this.dir = Direction.Down;
            animateHead = true;
        } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_LEFT)) {
            this.dir = Direction.Left;
            animateHead = true;
        } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_RIGHT)) {
            this.dir = Direction.Right;
            animateHead = true;
        }

        try {
            switch (dir) {
                case Up:
                    if (animateHead) {
                        headIdx = 4;
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 5);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Down:
                    if (animateHead) {
                        headIdx = 0;
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 1);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Left:
                    if(animateHead) {
                        headIdx = 6;
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 7);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Right:
                    if(animateHead) {
                        headIdx = 2;
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 3);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
            }
        } catch (ResourceNotFound e) {
            e.printStackTrace();
        }
    }
}
