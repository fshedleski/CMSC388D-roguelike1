package engine;

import java.util.ArrayList;

public class Camera extends GameObject2D {
    double oldX, oldY;

    private void constructor() {
        this.addLogicComponent(new MoveComponent(this, corePtr.Width, corePtr.Height, 4));
    }

    public Camera(EngineCore _core, double posX, double posY) {
        super(_core, posX, posY);
        oldX = posX;
        oldY = posY;
        this.constructor();
    }

    public void proj(ArrayList<GameObject2D> eles) {
        for (GameObject2D ele: eles) {
            if(!ele.equals(this) && ele.parent == null)
                ele.getConstAf().translate(
                        -(getConstAf().getTranslateX() - oldX),
                        -(getConstAf().getTranslateY() - oldY));
        }
        oldX = getConstAf().getTranslateX();
        oldY = getConstAf().getTranslateY();
    }
}
