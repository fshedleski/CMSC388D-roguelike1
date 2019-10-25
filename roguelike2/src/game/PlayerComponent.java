package game;

import engine.CollisionHandler;
import engine.Component;
import engine.EngineCore;
import engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class PlayerComponent extends Component {

    public Direction dir;
    private static String assetsPlayerSheet = "boiS.png";
    AffineTransform afBody;
    public int MAXX, MAXY;
    public final int SPEED = 4;

    public PlayerComponent(GameObject _parent) {
        super(_parent);
        dir = Direction.Up;
        afBody = new AffineTransform();
        afBody.setToIdentity();
        afBody.scale(1, 1);
        MAXX = 1366 - 56;
        MAXY = 768 - 68;
    }

    @Override
    // player movement logic
    public void logic() {
        if(engine.EngineCore.LCount % 2 == 0) { // only update player every other frame
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_W)) {
                this.dir = Direction.Up;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_S)) {
                this.dir = Direction.Down;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_A)) {
                this.dir = Direction.Left;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_D)) {
                this.dir = Direction.Right;
                this.move();
            }

            // Check collision state
            /*parent.getLogicComponent(CollisionHandler.CollisionHandlerComponent.class).logic();
            if(Main.collisionHandler1.containsCollisionWith(parent)) {
                parent.row = prevRow;
                parent.col = prevCol;
            }

            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_SPACE)) {
                GameObject bullet = new GameObject(parent.row, parent.col);
                BulletComponent bulletComponent = new BulletComponent(bullet, dir);
                bullet.addComponent(bulletComponent);
                bullet.addLogicComponent(Main.collisionHandler1.newCollisionHandlerComponent(bullet));
            }*/


        }
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(parent.af.getTranslateY() - SPEED < 0) {
                    parent.af.setToTranslation(parent.af.getTranslateX(), MAXY-1);
                } else {
                    parent.af.translate(0,-SPEED);
                }
                break;
            case Down:
                if(parent.af.getTranslateY() + SPEED >= MAXY) {
                    parent.af.setToTranslation(parent.af.getTranslateX(),0);
                } else {
                    parent.af.translate(0,SPEED);
                }
                break;
            case Left:
                if(parent.af.getTranslateX() - SPEED < 0) {
                    parent.af.setToTranslation(MAXX-1, parent.af.getTranslateY());
                } else {
                    parent.af.translate(-SPEED,0);
                }
                break;
            case Right:
                if(parent.af.getTranslateX() + SPEED >= MAXX) {
                    parent.af.setToTranslation(0, parent.af.getTranslateY());
                } else {
                    parent.af.translate(SPEED,0);
                }
                break;
        }
    }

    @Override
    // player visualization logic
    public void graphics(Graphics2D g) {
        try {
            Image im = EngineCore.assetsCenter.getImage(assetsPlayerSheet,19);
            afBody.setToTranslation(parent.af.getTranslateX()+11, parent.af.getTranslateY()+42);
            ((Graphics2D) g).drawImage(im, afBody, null);
            im = EngineCore.assetsCenter.getImage(assetsPlayerSheet,5);
            ((Graphics2D) g).drawImage(im, parent.af, null);
        } catch (engine.ResourceNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
