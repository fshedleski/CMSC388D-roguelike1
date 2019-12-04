package game;

import engine.Component;
import engine.GameObject25D;
import engine.GameObject2D;
import engine.Vector;

import java.util.Random;

public class WallLComponent extends Component {
    private Vector cubeDims;
    private Random rng;

    public WallLComponent(GameObject2D _parent, double _x, double _y, double _z) {
        super(_parent);
        rng = new Random();
        for(int x = 0; x < _x; x++) {
            for(int y = 0; y < _y; y++) {
                for(int z = 0; z < _z; z++) {
                    //if(rng.nextBoolean())
                        makeCube(x, y, z);
                }
            }
        }
    }

    private void makeCube(double posX, double posY, double posZ) {
        GameObject25D cube = new GameObject25D(parent.corePtr, posX, posY, posZ);
        CubeComponent cubeComponent = new CubeComponent(cube);
        this.parent.addComponent(cubeComponent);
    }
}
