package game;

import engine.Component;
import engine.GameObject;

import java.awt.*;

public class BulletLComponent extends Component {
    public Direction dir;
    public int MAXX, MAXY;
    public final int SPEED = 8;


    public BulletLComponent(GameObject _parent, Direction _dir) {
        super(_parent);
        dir = _dir;
        MAXX = 1366 - 56;
        MAXY = 768 - 68;
        move(); // handles initially placing the bullet 1 square away from the player
    }

    @Override
    // player movement logic
    public void logic() {
        move();
        move();
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(parent.af.getTranslateY() - SPEED < 0) {
                    parent.corePtr.elements.remove(parent);
                } else {
                    parent.af.translate(0,-SPEED);
                }
                break;
            case Down:
                if(parent.af.getTranslateY() + SPEED >= MAXY) {
                    parent.corePtr.elements.remove(parent);
                } else {
                    parent.af.translate(0,SPEED);
                }
                break;
            case Left:
                if(parent.af.getTranslateX() - SPEED < 0) {
                    parent.corePtr.elements.remove(parent);
                } else {
                    parent.af.translate(-SPEED,0);
                }
                break;
            case Right:
                if(parent.af.getTranslateX() + SPEED >= MAXX) {
                    parent.corePtr.elements.remove(parent);
                } else {
                    parent.af.translate(SPEED,0);
                }
                break;
        }
    }
}
