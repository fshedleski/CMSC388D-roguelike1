package engine;

import java.awt.*;

public abstract class Component {

    public GameObject parent = null;
    public boolean active = true;
    public int Priority = 0;

    public Component(GameObject _parent) {
        parent = _parent;
    }

    // logic update
    public void logic() {}

    // graphic update
    public void graphics(Graphics2D g) {}

}
