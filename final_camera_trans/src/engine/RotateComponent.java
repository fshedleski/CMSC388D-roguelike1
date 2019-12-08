package engine;

import java.awt.event.KeyEvent;

public class RotateComponent extends Component {

    public double SPEED;
    public double xoffset, yoffset;

    public RotateComponent(GameObject2D _parent, double _xoffset, double _yoffset) {
        super(_parent);
        xoffset = _xoffset;
        yoffset = _yoffset;
        SPEED = 0.25;
    }

    public RotateComponent(GameObject2D _parent, double _xoffset, double _yoffset, double _speed) {
        super(_parent);
        xoffset = _xoffset;
        yoffset = _yoffset;
        SPEED = _speed;
    }

    @Override
    // player movement logic
    public void logic() {
        if(engine.EngineCore.LCount % 2 == 0) { // only update player every other frame
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_Q)) {
                parent.getMutAf().rotate(-SPEED,
                       xoffset,
                       yoffset);
            }
            if (engine.EngineCore.inputs.pending.contains(KeyEvent.VK_E)) {
                parent.getMutAf().rotate(SPEED,
                        xoffset,
                        yoffset);
            }
        }
    }
}
