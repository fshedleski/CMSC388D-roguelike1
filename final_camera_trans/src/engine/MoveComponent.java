package engine;

import java.awt.event.KeyEvent;

public class MoveComponent extends Component {
    private Keybind kUp, kDown, kLeft, kRight;
    public Direction dir;
    public double MAXX, MAXY, SPEED;

    public MoveComponent(GameObject2D _parent) {
        super(_parent);
        dir = Direction.Down;
        MAXX = 1366 - 56;
        MAXY = 768 - 68;
        SPEED = 4;
        kUp = new Keybind(KeyEvent.VK_W, "Move Up");
        kDown = new Keybind(KeyEvent.VK_S, "Move Down");
        kLeft = new Keybind(KeyEvent.VK_A, "Move Left");
        kRight = new Keybind(KeyEvent.VK_D, "Move Right");
        EngineCore.kbmenu.addKeybind(kUp);
        EngineCore.kbmenu.addKeybind(kDown);
        EngineCore.kbmenu.addKeybind(kLeft);
        EngineCore.kbmenu.addKeybind(kRight);
    }

    public MoveComponent(GameObject2D _parent, int _maxX, int _maxY, double _speed) {
        super(_parent);
        dir = Direction.Down;
        MAXX = _maxX;
        MAXY = _maxY;
        SPEED = _speed;
        kUp = new Keybind(KeyEvent.VK_W, "Move Up");
        kDown = new Keybind(KeyEvent.VK_S, "Move Down");
        kLeft = new Keybind(KeyEvent.VK_A, "Move Left");
        kRight = new Keybind(KeyEvent.VK_D, "Move Right");
        EngineCore.kbmenu.addKeybind(kUp);
        EngineCore.kbmenu.addKeybind(kDown);
        EngineCore.kbmenu.addKeybind(kLeft);
        EngineCore.kbmenu.addKeybind(kRight);
    }

    public MoveComponent(GameObject2D _parent, int _maxX, int _maxY, double _speed,
                         Keybind _kUp, Keybind _kDown, Keybind _kLeft, Keybind _kRight) {
        super(_parent);
        dir = Direction.Down;
        MAXX = _maxX;
        MAXY = _maxY;
        SPEED = _speed;
        kUp = _kUp;
        kDown = _kDown;
        kLeft = _kLeft;
        kRight = _kRight;
        EngineCore.kbmenu.addKeybind(_kUp);
        EngineCore.kbmenu.addKeybind(_kDown);
        EngineCore.kbmenu.addKeybind(_kLeft);
        EngineCore.kbmenu.addKeybind(_kRight);
    }

    @Override
    // player movement logic
    public void logic() {
        if(engine.EngineCore.LCount % 2 == 0) { // only update player every other frame
            if (engine.EngineCore.inputs.pending.contains(kUp.bind)) {
                this.dir = Direction.Up;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(kDown.bind)) {
                this.dir = Direction.Down;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(kLeft.bind)) {
                this.dir = Direction.Left;
                this.move();
            }
            if (engine.EngineCore.inputs.pending.contains(kRight.bind)) {
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
                    parent.getMutAfTrans().translate(0,-SPEED);
                    parent.ycoord -= SPEED;
                }
                break;
            case Down:
                if(parent.getConstAf().getTranslateY() + SPEED >= MAXY) {
                    //parent.af.setToTranslation(parent.af.getTranslateX(),0);
                } else {
                    parent.getMutAfTrans().translate(0,SPEED);
                    parent.ycoord += SPEED;
                }
                break;
            case Left:
                if(parent.getConstAf().getTranslateX() - SPEED < 0) {
                    //parent.af.setToTranslation(MAXX-1, parent.af.getTranslateY());
                } else {
                    parent.getMutAfTrans().translate(-SPEED,0);
                    parent.xcoord -= SPEED;
                }
                break;
            case Right:
                if(parent.getConstAf().getTranslateX() + SPEED >= MAXX) {
                    //parent.af.setToTranslation(0, parent.af.getTranslateY());
                } else {
                    parent.getMutAfTrans().translate(SPEED,0);
                    parent.xcoord += SPEED;
                }
                break;
        }
    }
}
