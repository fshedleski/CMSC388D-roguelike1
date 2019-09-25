package engine;

public abstract class Component implements Comparable<Component> {
    public int id; // used for priority
    public GameObject parent;

    protected Component(int _id, GameObject _parent) {
        id = _id;
        parent = _parent;
    }

    // checks rate control and updates object
    public abstract void update ();

    @Override
    public int compareTo(Component c) {
        return this.id - c.id;
    }

}
