package engine;

import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject {
    private ArrayList<Component> Gcomponents;
    private ArrayList<Component> Lcomponents;


    public GameObject() {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
    }

    public boolean addGraphicsComponent(Component _c) {
        Gcomponents.add(_c);
        return true;
    }
    public boolean addLogicComponent(Component _c) {
        Gcomponents.add(_c);
        return true;
    }


    // logic update
    public void logic() {
        for (Component c : Lcomponents) {
            c.logic();
        }
    }

    // graphic update
    public void graphics() {
        for(Component c : Gcomponents) {
            c.graphic();
        }
    }

}
