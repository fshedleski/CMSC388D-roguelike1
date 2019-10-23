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
        Main.gameObjects.add(this);
    }

    // constructor that indicates a start position for game object
    public GameObject(int _row, int _col) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        row = _row;
        col = _col;
        Main.gameObjects.add(this);
    }

    // add a component to logic and graphics
    // (its normally better to use add graphics and add logic functions)
    public boolean addComponent(Component _c) {
        if(Gcomponents.contains(_c) && Lcomponents.contains(_c)) {
            // TODO maybe update component properties here like isActive and Priority?
            return false;
        } else {
            if(!Gcomponents.contains(_c)) {
                Gcomponents.add(_c);
            } // TODO else maybe update component properties here like isActive and Priority?
            if(!Lcomponents.contains(_c)) {
                Lcomponents.add(_c);
            } // TODO else maybe update component properties here like isActive and Priority?
            return true;
        }
    }

    // add a graphics component that will update in graphics()
    public boolean addGraphicsComponent(Component _class) {
        if(Gcomponents.contains(_class)) {
            // TODO maybe update component properties here like isActive and Priority?
            return false;
        } else {
            Gcomponents.add(_class);
            return true;
        }
    }

    // add a logic component that will update in logic()
    public boolean addLogicComponent(Component _class) {
        if(Lcomponents.contains(_class)) {
            // TODO maybe update component properties here like isActive and Priority?
            return false;
        } else {
            Lcomponents.add(_class);
            return true;
        }
    }

    // remove a component from logic and graphics
    // (its normally better to use remove graphics and remove logic functions)
    public boolean removeComponent(Class<? extends Component> _class) {
        boolean ret = false;
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                Gcomponents.remove(component);
                ret = true;
            }
        }
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                Lcomponents.remove(component);
                ret = true;
            }
        }
        return ret;
    }

    // remove a graphics component
    public boolean removeGraphicsComponent(Class<? extends Component> _class) {
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                Gcomponents.remove(component);
                return true;
            }
        }
        return false;
    }

    // remove a logic component that will update in logic()
    public boolean removeLogicComponent(Class<? extends Component> _class) {
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                Lcomponents.remove(component);
                return true;
            }
        }
        return false;
    }

    // retrieve a component from logic and graphics
    // (its normally better to use get graphics and get logic functions)
    public Component getComponent(Class<? extends Component> _class) {
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                return component;
            }
        }
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                return component;
            }
        }
        return null;
    }

    // retrieve a graphics component
    public Component getGraphicsComponent(Class<? extends Component> _class) {
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                return component;
            }
        }
        return null;
    }

    // retrieve a logic component
    public Component getLogicComponent(Class<? extends Component> _class) {
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                return component;
            }
        }
        return null;
    }

    private int calcMaxComponentPriority() {
        int tempMax = 0;
        for(Component c : Lcomponents) {
            tempMax = Math.max(tempMax, c.Priority);
        }
        for(Component c : Gcomponents) {
            tempMax = Math.max(tempMax, c.Priority);
        }
        return tempMax;
    }

    // logic update
    public void logic() {
        for(int priority = 0; priority <= calcMaxComponentPriority(); priority++) {
            for (Component c : Lcomponents) {
                if(c.active && c.Priority == priority) {
                    c.logic();
                }
            }
        }
    }

    // graphic update
    public void graphics() {
        for(int priority = 0; priority <= calcMaxComponentPriority(); priority++) {
            for (Component c : Gcomponents) {
                if (c.active && c.Priority == priority) {
                    c.graphics();
                }
            }
        }
    }

}
