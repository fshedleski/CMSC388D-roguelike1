package game;

import engine.EngineCore;
import engine.GameObject;

public class Main {

    private static String assetsDirectory = "Assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "Rogue-like 2", assetsDirectory);

    public static void main(String args[]) {
        // make background object
        GameObject background = new GameObject(core);
        BackgroundGComp bgGComp = new BackgroundGComp(background);
        bgGComp.Priority = 0;
        background.addGraphicsComponent(bgGComp);

        // make player
        GameObject player = new GameObject(core, 683, 389);
        PlayerGComponent playerGComp = new PlayerGComponent(player);
        playerGComp.Priority = 2;
        player.addGraphicsComponent(playerGComp);
        PlayerLComponent playerLComp = new PlayerLComponent(player);
        playerLComp.Priority = 2;
        player.addLogicComponent(playerLComp);

        // run
        core.start();
    }
}
