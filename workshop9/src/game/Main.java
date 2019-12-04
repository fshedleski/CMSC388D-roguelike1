package game;

import engine.EngineCore;
import engine.GameObject25D;
import engine.GameObject2D;

// GROUP
// Franklin Shedleski
// Dennis Dao
// McCauley Peters
// Dan Song

public class Main {

    private static String assetsDirectory = "assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "Workshop9", assetsDirectory);

    public static void main(String args[]) {
        // make wall 1
        GameObject25D.scale(0.1, 0.1);
        GameObject25D wall1 = new GameObject25D(core, 1, 1, 1);
        WallLComponent wall1LComponent = new WallLComponent(wall1, 10, 10, 10);
        wall1.addComponent(wall1LComponent);

        // run
        core.start();
    }
}
