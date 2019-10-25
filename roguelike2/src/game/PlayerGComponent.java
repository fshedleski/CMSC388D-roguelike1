package game;

import engine.CollisionHandler;
import engine.Component;
import engine.EngineCore;
import engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerGComponent extends Component {
    private String assetsPlayerSheet = "boiS.png";
    AffineTransform afBody;
    private int animateCounter = 0;
    

    public PlayerGComponent(GameObject _parent) {
        super(_parent);
        afBody = new AffineTransform();
        afBody.setToIdentity();
        afBody.scale(1, 1);
    }

    @Override
    // player visualization logic
    public void graphics(Graphics2D g) {
        Direction _dir = Direction.Down;
        boolean isMoving = false;

        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_W)) {
            _dir = Direction.Up;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_S)) {
            _dir = Direction.Down;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_A)) {
            _dir = Direction.Left;
            isMoving = true;
        }
        if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_D)) {
            _dir = Direction.Right;
            isMoving = true;
        }

        if (animateCounter <= 9 && isMoving && EngineCore.LCount % 20 == 0) {
            animateCounter = (animateCounter + 1) % 10;
        }
        System.out.println(animateCounter);

        try {
            Image im;
        	switch(_dir) {
                case Up:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 8 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 4);
                    ((Graphics2D) g).drawImage(im, parent.af, null);
                    break;
                case Down:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 8 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 0);
                    ((Graphics2D) g).drawImage(im, parent.af, null);
                    break;
                case Left:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 28 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 6);
                    ((Graphics2D) g).drawImage(im, parent.af, null);
                    break;
                case Right:
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 18 + animateCounter);
                    afBody.setToTranslation(parent.af.getTranslateX() + 10, parent.af.getTranslateY() + 42);
                    ((Graphics2D) g).drawImage(im, afBody, null);
                    im = EngineCore.assetsCenter.getImage(assetsPlayerSheet, 2);
                    ((Graphics2D) g).drawImage(im, parent.af, null);
                    break;

            }
        } catch (engine.ResourceNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
