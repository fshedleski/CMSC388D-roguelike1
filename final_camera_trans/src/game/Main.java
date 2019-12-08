package game;

import engine.*;

// GROUP
// Franklin Shedleski
// Dennis Dao
// McCauley Peters
// Dan Song

// curent features:
// camera translation
// game object hierarchy

public class Main {
    private static String assetsDirectory = "assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "final", assetsDirectory);

    public static void main(String args[]) {
        // make background object
        GameObject2D background = new GameObject2D(core);
        BackgroundGComp bgGComp = new BackgroundGComp(background);
        bgGComp.Priority = 0;
        background.addGraphicsComponent(bgGComp);

        // make player body
        GameObject2D player = new GameObject2D(core, 400, 400);
        //player.addLogicComponent(new engine.MoveComponent(player, 1880, 1040, 4));
        //player.addLogicComponent(new RotateComponent(player, 18, 4, 0.25));
        player.addGraphicsComponent(new PlayerBodyGComponent(player));

        // make head as a seperate object with player as parent
        GameObject2D playerHead = new GameObject2D(core, player, -10, -40);
        playerHead.addGraphicsComponent(new PlayerHeadGComponent(playerHead));


        // run
        core.start();
    }
}
