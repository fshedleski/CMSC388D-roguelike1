package game;

import engine.EngineCore;
import engine.GameObject;

public class Main {

    private static String assetsDirectory = "Assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "Rogue-like 2", assetsDirectory);

    public static void main(String args[]) {
        // make background object
        GameObject background = new GameObject();
        BackgroundGComp bgGComp = new BackgroundGComp(background);
        background.addGraphicsComponent(bgGComp);
        //core.AddObject(background);

        // make player
        GameObject player = new GameObject();
        PlayerComponent playerComp = new PlayerComponent(player);
        player.addComponent(playerComp);
        core.AddObject(player);

        // run
        core.start();
    }
}
