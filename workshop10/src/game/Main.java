package game;

import engine.EngineCore;
import engine.GameObject25D;

// GROUP
// Franklin Shedleski
// Dennis Dao
// McCauley Peters
// Dan Song

public class Main {
    private static String assetsDirectory = "assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "Workshop10", assetsDirectory);

    public static void main(String args[]) {
        // make wall 1
        GameObject25D.scale(1, 1);
        GameObject25D cube1 = new GameObject25D(core, 100, 100, 1);
        CubeLineComponent cube1Component = new CubeLineComponent(cube1);
        cube1.addComponent(cube1Component);

        // run
        core.start();
    }
}
