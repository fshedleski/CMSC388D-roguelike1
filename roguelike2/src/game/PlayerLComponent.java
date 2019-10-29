package game;

import engine.Component;
import engine.GameObject;

import java.awt.event.KeyEvent;

public class PlayerLComponent extends Component {

    public Direction dir;
    public int MAXX, MAXY;
    public final int SPEED = 4;
    private int shootDelay = 0;
    private boolean shootEye = false;

    public PlayerLComponent(GameObject _parent) {
        super(_parent);
        dir = Direction.Down;
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
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_SPACE) && shootDelay == 0) {
                GameObject bullet;
                if(dir == Direction.Left || dir == Direction.Right) {
                    bullet = new GameObject(parent.corePtr, (int)parent.af.getTranslateX(), (int)parent.af.getTranslateY()+15);
                } else {
                    if(shootEye) {
                        bullet = new GameObject(parent.corePtr, (int)parent.af.getTranslateX()+2, (int)parent.af.getTranslateY()+8);
                    } else {
                        bullet = new GameObject(parent.corePtr, (int)parent.af.getTranslateX()+30, (int)parent.af.getTranslateY()+8);
                    }
                    shootEye = !shootEye;
                }
                BulletLComponent bulletLComponent = new BulletLComponent(bullet, dir);
                BulletGComponent bulletGComponent = new BulletGComponent(bullet, dir);
                if(dir == Direction.Left || dir == Direction.Right || dir == Direction.Down) {
                    bulletLComponent.Priority = 3;
                    bulletGComponent.Priority = 3;
                } else {
                    bulletLComponent.Priority = 1;
                    bulletGComponent.Priority = 1;
                }
                bullet.addLogicComponent(bulletLComponent);
                bullet.addGraphicsComponent(bulletGComponent);
                shootDelay = 15;
            }
        }
        if(shootDelay > 0) {
            shootDelay--;
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
}
