package engine;

import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject {
    private ArrayList<Component> components;

    public GameObject() {
        components = new ArrayList<Component>();
    }

    public boolean addComponent(Component _c) {
        if(containsComponent(_c.id)) { return false; }
        components.add(_c);
        return true;
    }

    public boolean containsComponent(Component _c) {
        return components.contains(_c);
    }
    public boolean containsComponent(int _id) {
        for(Component c : components) {
            if(c.id == _id) {
                return true;
            }
        }
        return false;
    }

    public Component getComponent(int _id) {
        for(Component c : components) {
            if(c.id == _id) {
                return c;
            }
        }
        return null;
    }

    public boolean removeComponent(Component _c) {
        if(containsComponent(_c)) { return false; }
        components.remove(_c);
        return true;
    }
    public boolean removeComponent(int _id) {
        for(Component c : components) {
            if(c.id == _id) {
                return components.remove(c);
            }
        }
        return false;
    }

    // logic update
    public void logic() {
        for (Component c : components) {
            c.logic();
        }
    }

    // graphic update
    public void graphic() {
        for(Component c : components) {
            c.graphic();
        }
    }

}
