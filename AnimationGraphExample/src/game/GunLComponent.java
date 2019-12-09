package game;

import engine.GameObject;
import engine.Component;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GunLComponent extends Component {

    private int shootDelay;
    private boolean shootEye;
    public Direction dir;


    public GunLComponent(GameObject _parent) {
        super(_parent);
        shootEye = false;
        shootDelay = 0;
        dir = Direction.Down;
    }

    @Override
    public void logic() {
        if(engine.EngineCore.LCount % 2 == 0) { // only update player every other frame
            if(shootDelay == 0) {
                if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_UP)) {
                    this.dir = Direction.Up;
                    shoot();
                } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_DOWN)) {
                    this.dir = Direction.Down;
                    shoot();
                } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_LEFT)) {
                    this.dir = Direction.Left;
                    shoot();
                } else if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_RIGHT)) {
                    this.dir = Direction.Right;
                    shoot();
                }
            } else {
                shootDelay--;
            }
        }
    }

    private void shoot() {
        GameObject bullet;
        if (dir == Direction.Left || dir == Direction.Right) {
            bullet = new GameObject(parent.corePtr, (int) parent.af.getTranslateX(), (int) parent.af.getTranslateY() + 15);
        } else {
            if (shootEye) {
                bullet = new GameObject(parent.corePtr, (int) parent.af.getTranslateX() + 2, (int) parent.af.getTranslateY() + 8);
            } else {
                bullet = new GameObject(parent.corePtr, (int) parent.af.getTranslateX() + 30, (int) parent.af.getTranslateY() + 8);
            }
            shootEye = !shootEye;
        }
        BulletLComponent bulletLComponent = new BulletLComponent(bullet, dir);
        BulletGComponent bulletGComponent = new BulletGComponent(bullet, dir);
        if (dir == Direction.Left || dir == Direction.Right || dir == Direction.Down) {
            bulletLComponent.Priority = 3;
            bulletGComponent.Priority = 3;
        } else {
            bulletLComponent.Priority = 1;
            bulletGComponent.Priority = 1;
        }
        bullet.addLogicComponent(bulletLComponent);
        bullet.addGraphicsComponent(bulletGComponent);
        shootDelay = 3;
    }
}
