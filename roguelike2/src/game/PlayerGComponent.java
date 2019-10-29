package game;

import engine.CollisionHandler;
import engine.Component;
import engine.EngineCore;
import engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class PlayerGComponent extends Component {
    private String assetsPlayerSheet = "boiS.png";
    AffineTransform afBody;
    private int animateCounter = 0;
    private int headIdx = 0;
    Direction dir;

    public PlayerGComponent(GameObject _parent) {
        super(_parent);
        afBody = new AffineTransform();
        afBody.setToIdentity();
        afBody.scale(1, 1);
        dir = Direction.Down;
    }

    @Override
    // player visualization logic
    public void graphics(Graphics2D g) {
        boolean isMoving = false;
        boolean animateHead = false;

        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_W)) {
            dir = Direction.Up;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_S)) {
            dir = Direction.Down;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_A)) {
            dir = Direction.Left;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_D)) {
            dir = Direction.Right;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_SPACE)) {
            animateHead = true;
        }

        if (animateCounter <= 9 && isMoving && EngineCore.FCount % 10 == 0) {
            animateCounter = (animateCounter + 1) % 10;
        }

        try {
            Image im;
        	switch(dir) {
                case Up:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 8 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    if(animateHead) {
                        headIdx = 4;
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 5);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Down:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 8 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    if(animateHead) {
                        headIdx = 0;
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 1);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Left:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 28 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    if(animateHead) {
                        headIdx = 6;
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 7);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;
                case Right:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 18 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    if(animateHead) {
                        headIdx = 2;
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 3);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    } else {
                        im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, headIdx);
                        ((Graphics2D) g).drawImage(im, parent.af, null);
                    }
                    break;

            }
        } catch (engine.ResourceNotFound e) {
            e.printStackTrace();
        }
    }
}
