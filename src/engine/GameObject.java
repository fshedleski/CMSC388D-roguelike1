package engine;

import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject {
    private ArrayList<Component> Gcomponents;
    private ArrayList<Component> Lcomponents;

    public int row, col;


    public GameObject() {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        row = 0;
        col = 0;
    }

    // constructor that indicates a start position for game object
    public GameObject(int _row, int _col) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        row = _row;
        col = _col;
    }

    // add a graphics component that will update in graphics()
    public boolean addGraphicsComponent(Component _c) {
        Gcomponents.add(_c);
        return true;
    }

    // add a logic component that will update in logic()
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
