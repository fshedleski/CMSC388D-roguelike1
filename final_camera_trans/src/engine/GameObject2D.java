package engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// base game object that everything inherits from
public class GameObject2D {
    static int Min;
    static int Max;

    private ArrayList<Component> Gcomponents;
    private ArrayList<Component> Lcomponents;
    public EngineCore corePtr;
    private AffineTransform af_trans;
    private AffineTransform af_rotation;
    private AffineTransform af_scale;
    public GameObject2D parent;

    public GameObject2D(EngineCore _core) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af_trans = new AffineTransform();
        af_trans.setToIdentity();
        af_rotation = new AffineTransform();
        af_rotation.setToIdentity();
        af_scale = new AffineTransform();
        af_scale.setToIdentity();
        corePtr = _core;
        corePtr.AddObject(this);
        parent = null;
    }

    public GameObject2D(EngineCore _core, GameObject2D _parent) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af_trans = new AffineTransform();
        af_trans.setToIdentity();
        af_rotation = new AffineTransform();
        af_rotation.setToIdentity();
        af_scale = new AffineTransform();
        af_scale.setToIdentity();
        corePtr = _core;
        corePtr.AddObject(this);
        parent = _parent;
    }

    // constructor that indicates a start position for game object
    public GameObject2D(EngineCore _core, double _x, double _y) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af_trans = new AffineTransform();
        af_trans.setToTranslation(_x, _y);
        af_rotation = new AffineTransform();
        af_rotation.setToIdentity();
        af_scale = new AffineTransform();
        af_scale.setToIdentity();
        corePtr = _core;
        corePtr.AddObject(this);
        parent = null;
    }

    public GameObject2D(EngineCore _core, GameObject2D _parent, double _x, double _y) {
        Gcomponents = new ArrayList<Component>();
        Lcomponents = new ArrayList<Component>();
        af_trans = new AffineTransform();
        af_trans.setToTranslation(_x, _y);
        af_rotation = new AffineTransform();
        af_rotation.setToIdentity();
        af_scale = new AffineTransform();
        af_scale.setToIdentity();
        corePtr = _core;
        corePtr.AddObject(this);
        parent = _parent;
    }

    public AffineTransform getConstAf() {
        if(parent == null) {
            AffineTransform ret = new AffineTransform();
            ret.setToIdentity();
            ret.concatenate(af_trans);
            ret.concatenate(af_rotation);
            ret.concatenate(af_scale);
            return ret;
        } else {
            AffineTransform ret = new AffineTransform(parent.getConstAf());
            ret.concatenate(af_trans);
            return ret;
        }
    }

    public AffineTransform getMutAfTrans() {
        return this.af_trans;
    }
    public void setMutAfTrans(AffineTransform _af) { this.af_trans = _af; }
    public AffineTransform getMutAfRot() {
        return this.af_rotation;
    }
    public void setMutAfRot(AffineTransform _af) { this.af_rotation = _af; }
    public AffineTransform getMutAfScale() {
        return this.af_scale;
    }
    public void setMutAfScale(AffineTransform _af) { this.af_scale = _af; }

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
    public ArrayList<Component> getComponent(Class<? extends Component> _class) {
        ArrayList<Component> ret = new ArrayList<Component>();
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                ret.add(component);
            }
        }
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                ret.add(component);
            }
        }
        return ret;
    }

    // retrieve a graphics component
    public ArrayList<Component> getGraphicsComponent(Class<? extends Component> _class) {
        ArrayList<Component> ret = new ArrayList<Component>();
        for(Component component : Gcomponents) {
            if(component.getClass().equals(_class)) {
                ret.add(component);
            }
        }
        return ret;
    }

    // retrieve a logic component
    public ArrayList<Component> getLogicComponent(Class<? extends Component> _class) {
        ArrayList<Component> ret = new ArrayList<Component>();
        for(Component component : Lcomponents) {
            if(component.getClass().equals(_class)) {
                ret.add(component);
            }
        }
        return ret;
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
