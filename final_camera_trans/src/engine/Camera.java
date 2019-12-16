package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Camera extends GameObject2D {
    double oldX, oldY, scaleFactor;
    Keybind camUp, camDown, camLeft, camRight, camCW, camCCW, camZoomIn, camZoomOut;

    private void constructor() {
        camUp = new Keybind(KeyEvent.VK_UP, "Camera Trans Up");
        camDown = new Keybind(KeyEvent.VK_DOWN, "Camera Trans Down");
        camLeft = new Keybind(KeyEvent.VK_LEFT, "Camera Trans Left");
        camRight = new Keybind(KeyEvent.VK_RIGHT, "Camera Trans Right");
        camCW = new Keybind(KeyEvent.VK_O, "Camera Rotate CW");
        camCCW = new Keybind(KeyEvent.VK_P, "Camera Rotate CCW");
        camZoomIn = new Keybind(KeyEvent.VK_K, "Camera Zoom In");
        camZoomOut = new Keybind(KeyEvent.VK_L, "Camera Zoom Out");

        EngineCore.kbmenu.addKeybind(camUp);
        EngineCore.kbmenu.addKeybind(camDown);
        EngineCore.kbmenu.addKeybind(camLeft);
        EngineCore.kbmenu.addKeybind(camRight);
        EngineCore.kbmenu.addKeybind(camCW);
        EngineCore.kbmenu.addKeybind(camCCW);
        EngineCore.kbmenu.addKeybind(camZoomIn);
        EngineCore.kbmenu.addKeybind(camZoomOut);
    }

    public Camera(EngineCore _core, double posX, double posY) {
        super(_core, posX, posY);
        oldX = posX;
        oldY = posY;
        scaleFactor = 1;
        this.constructor();
    }

    public void proj(ArrayList<GameObject2D> eles) {
        int count = 0;
        for (GameObject2D ele: eles) {
            if(!ele.equals(this) && ele.parent == null) {
                if (engine.EngineCore.inputs.pending.contains(camUp.bind)) {
                    ele.getMutAfTrans().translate(0, -0.5);
                }
                if (engine.EngineCore.inputs.pending.contains(camDown.bind)) {
                    ele.getMutAfTrans().translate(0, 0.5);
                }
                if (engine.EngineCore.inputs.pending.contains(camLeft.bind)) {
                    ele.getMutAfTrans().translate(-0.5, 0);
                }
                if (engine.EngineCore.inputs.pending.contains(camRight.bind)) {
                    ele.getMutAfTrans().translate(0.5, 0);
                }

                if (engine.EngineCore.inputs.pending.contains(camCW.bind)) {
                    ele.getMutAfRot().rotate(Math.toRadians(-0.1),
                            (corePtr.Width / 2) - ele.xcoord,
                            (corePtr.Height / 2) - ele.ycoord);

                }

                if (engine.EngineCore.inputs.pending.contains(camCCW.bind)) {
                    ele.getMutAfRot().rotate(Math.toRadians(0.1),
                            (corePtr.Width / 2) - ele.xcoord,
                            (corePtr.Height / 2) - ele.ycoord);
                }

                if (engine.EngineCore.inputs.pending.contains(camZoomIn.bind)) {
                    if (scaleFactor < 2.0) {
                        scaleFactor += 0.00001;
                        ele.getMutAfScale().scale(scaleFactor, scaleFactor);
                        if (count != 1)
                            ele.getMutAfScale().translate(scaleFactor, scaleFactor);
                    }
                }
                if (engine.EngineCore.inputs.pending.contains(camZoomOut.bind)) {
                    if (scaleFactor > 0.25) {
                        scaleFactor -= 0.00001;
                        ele.getMutAfScale().scale(scaleFactor, scaleFactor);
                        if (count != 1)
                            ele.getMutAfScale().translate(-scaleFactor, -scaleFactor);
                    }
                }
                count += 1;
            }
        }
        oldX = getConstAf().getTranslateX();
        oldY = getConstAf().getTranslateY();
    }
}
