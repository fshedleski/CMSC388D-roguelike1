package rl1;

import engine.Component;
import engine.GameObject;
import engine.Main;

import java.awt.*;

public class ObstacleComponenet extends Component {

    public ObstacleComponenet(GameObject _parent) {
        super(_parent);
    }

    @Override
    public void logic() {
        // don't need to check collisions here because everything moving should be handling their own collisions
    }

    @Override
    public void graphics() {
        Main.myGrid.setColor(parent.row, parent.col, Color.GREEN);
    }
}
