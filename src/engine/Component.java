package engine;

public abstract class Component implements Comparable<Component> {
    public int id; // used for priority
    public GameObject parent;

    protected Component(int _id, GameObject _parent) {
        id = _id;
        parent = _parent;
    }

    // logic update
    public abstract void logic();

    // graphic update
    public abstract void graphic();

    @Override
    public int compareTo(Component c) {
        return this.id - c.id;
    }

}
