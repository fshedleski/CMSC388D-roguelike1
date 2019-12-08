package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject {
    static int Min;
    static int Max;

    private ArrayList<Component> Gcomponents;
    private ArrayList<Component> Lcomponents;
    public EngineCore corePtr;
    public AffineTransform af;

    public GameObject(EngineCore _core) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af = new AffineTransform();
        af.setToIdentity();
        af.setToTranslation(0,0);
        corePtr = _core;
        corePtr.AddObject(this);
    }

    // constructor that indicates a start position for game object
    public GameObject(EngineCore _core, int _x, int _y) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af = new AffineTransform();
        af.setToIdentity();
        af.setToTranslation(_x, _y);
        corePtr = _core;
        corePtr.AddObject(this);
    }

    // add a component to logic and graphics
    // (its normally better to use add graphics and add logic functions)
    public boolean addComponent(Component _c) {
        if(Gcomponents.contains(_c) && Lcomponents.contains(_c)) {
            return false;
        } else {
            if(!Gcomponents.contains(_c)) {
                Gcomponents.add(_c);
                if(_c.Priority < Min) {
                    Min = _c.Priority;
                } else if(_c.Priority > Max) {
                    Max = _c.Priority;
                }
            }
            if(!Lcomponents.contains(_c)) {
                Lcomponents.add(_c);
                if(_c.Priority < Min) {
                    Min = _c.Priority;
                } else if(_c.Priority > Max) {
                    Max = _c.Priority;
                }
            }
            return true;
        }
    }

    // add a graphics component that will update in graphics()
    public boolean addGraphicsComponent(Component _c) {
        if(Gcomponents.contains(_c)) {
            // TODO maybe update component properties here like isActive and Priority?
            return false;
        } else {
            Gcomponents.add(_c);
            if(_c.Priority < Min) {
                Min = _c.Priority;
            } else if(_c.Priority > Max) {
                Max = _c.Priority;
            }
            return true;
        }
    }

    // add a logic component that will update in logic()
    public boolean addLogicComponent(Component _c) {
        if(Lcomponents.contains(_c)) {
            // TODO maybe update component properties here like isActive and Priority?
            return false;
        } else {
            Lcomponents.add(_c);
            if(_c.Priority < Min) {
                Min = _c.Priority;
            } else if(_c.Priority > Max) {
                Max = _c.Priority;
            }
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
    public void logic(int priority) {
        for (Component c : Lcomponents) {
            if(c.active && c.Priority == priority) {
                c.logic();
            }
        }
    }

    // graphic update
    public void graphics(int priority, Graphics2D g) {
        for (Component c : Gcomponents) {
            if (c.active && c.Priority == priority) {
                c.graphics(g);
            }
        }
    }

}
