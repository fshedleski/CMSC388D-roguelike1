package engine;

import java.awt.event.KeyEvent;

public class MoveComponent extends Component {

    public Direction dir;
    public double MAXX, MAXY, SPEED = 1;

    public MoveComponent(GameObject2D _parent) {
        super(_parent);
        dir = Direction.Down;
        MAXX = 1366 - 56;
        MAXY = 768 - 68;
    }

    public MoveComponent(GameObject2D _parent, int _maxX, int _maxY, double _speed) {
        super(_parent);
        dir = Direction.Down;
        MAXX = _maxX;
        MAXY = _maxY;
        SPEED = _speed;
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
        }
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(parent.getConstAf().getTranslateY() - SPEED < 0) {
                    //parent.af.setToTranslation(parent.af.getTranslateX(), MAXY-1);
                } else {
                    parent.getMutAf().translate(0,-SPEED);
                }
                break;
            case Down:
                if(parent.getConstAf().getTranslateY() + SPEED >= MAXY) {
                    //parent.af.setToTranslation(parent.af.getTranslateX(),0);
                } else {
                    parent.getMutAf().translate(0,SPEED);
                }
                break;
            case Left:
                if(parent.getConstAf().getTranslateX() - SPEED < 0) {
                    //parent.af.setToTranslation(MAXX-1, parent.af.getTranslateY());
                } else {
                    parent.getMutAf().translate(-SPEED,0);
                }
                break;
            case Right:
                if(parent.getConstAf().getTranslateX() + SPEED >= MAXX) {
                    //parent.af.setToTranslation(0, parent.af.getTranslateY());
                } else {
                    parent.getMutAf().translate(SPEED,0);
                }
                break;
        }
    }
}
