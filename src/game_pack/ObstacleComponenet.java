package game_pack;

import engine.Component;
import engine.GameObject;

public class ObstacleComponenet extends Component {
    int row, col;

    public ObstacleComponenet(GameObject _parent) {
        super(_parent);
        row = parent.row;
        col = parent.col;
    }

    @Override
    public void logic() {
        // TODO check collision system
    }

    @Override
    public void graphics() {

    }
}
