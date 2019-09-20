package rl_pack;

public abstract class Component implements Comparable<Component> {
    public int id; // used for priority

    protected Component(int _id) {
        id = _id;
    }

    // checks rate control and updates object
    public abstract void update ();

    @Override
    public int compareTo(Component c) {
        return this.id - c.id;
    }

}
