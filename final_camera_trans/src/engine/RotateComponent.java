package engine;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RotateComponent extends Component {
    Keybind kCW, kCCW;
    public double SPEED;
    public double xoffset, yoffset;

    public RotateComponent(GameObject2D _parent, double _xoffset, double _yoffset) {
        super(_parent);
        xoffset = _xoffset;
        yoffset = _yoffset;
        SPEED = 0.25;
        kCW = new Keybind(KeyEvent.VK_Q, "Rotate CW");
        kCCW = new Keybind(KeyEvent.VK_E, "Rotate CCW");
        EngineCore.kbmenu.addKeybind(kCW);
        EngineCore.kbmenu.addKeybind(kCCW);
    }

    public RotateComponent(GameObject2D _parent, double _xoffset, double _yoffset, double _speed) {
        super(_parent);
        xoffset = _xoffset;
        yoffset = _yoffset;
        SPEED = _speed;
        kCW = new Keybind(KeyEvent.VK_Q, "Rotate CW");
        kCCW = new Keybind(KeyEvent.VK_E, "Rotate CCW");
        EngineCore.kbmenu.addKeybind(kCW);
        EngineCore.kbmenu.addKeybind(kCCW);
    }

    public RotateComponent(GameObject2D _parent, double _xoffset, double _yoffset, double _speed,
                           Keybind _kCW, Keybind _kCCW) {
        super(_parent);
        xoffset = _xoffset;
        yoffset = _yoffset;
        SPEED = _speed;
        kCW = _kCW;
        kCCW = _kCCW;
        EngineCore.kbmenu.addKeybind(kCW);
        EngineCore.kbmenu.addKeybind(kCCW);
    }

    @Override
    // player movement logic
    public void logic() {
        if(engine.EngineCore.LCount % 2 == 0) { // only update player every other frame
            if (engine.EngineCore.inputs.pending.contains(kCCW.bind)) {
                parent.getMutAfRot().rotate(SPEED,
                       xoffset,
                       yoffset);
            }
            if (engine.EngineCore.inputs.pending.contains(kCW.bind)) {
                parent.getMutAfRot().rotate(-SPEED,
                        xoffset,
                        yoffset);
            }
        }
    }
}
