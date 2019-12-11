package game;

import engine.*;

import java.awt.event.KeyEvent;

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
    private static String bgmusicpath = "1minutejam_bass.wav";
    private static String togglemusicpath = "1minutejam_treble.wav";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "final", assetsDirectory);

    public static void main(String args[]) {
        // make background object
        GameObject2D background = new GameObject2D(core);
        BackgroundGComp bgGComp = new BackgroundGComp(background);
        bgGComp.Priority = 0;
        background.addGraphicsComponent(bgGComp);

        // make player body
        GameObject2D player = new GameObject2D(core, 400, 400);
        player.addLogicComponent(new engine.MoveComponent(player, 1880, 1040, 4,
                new Keybind(KeyEvent.VK_W, "Player Move Up"),
                new Keybind(KeyEvent.VK_S, "Player Move Down"),
                new Keybind(KeyEvent.VK_A, "Player Move Left"),
                new Keybind(KeyEvent.VK_D, "Player Move Right")));
        player.addLogicComponent(new RotateComponent(player, 18, 4, 0.25,
                new Keybind(KeyEvent.VK_Q, "Player Rotate CW"),
                new Keybind(KeyEvent.VK_E, "Player Rotate CCW")));
        player.addGraphicsComponent(new PlayerBodyGComponent(player));

        // make head as a seperate object with player as parent
        GameObject2D playerHead = new GameObject2D(core, player, -10, -40);
        playerHead.addGraphicsComponent(new PlayerHeadGComponent(playerHead));

        // background music
        GameObject2D bgmusic = new GameObject2D(core);
        bgmusic.addLogicComponent(new AudioComponent(bgmusic, bgmusicpath));
        bgmusic.addLogicComponent(new bgMusicLComp(bgmusic));

        // toggle music radio
        GameObject2D radio = new GameObject2D(core);
        radio.addLogicComponent(new AudioComponent(radio, togglemusicpath));
        radio.addLogicComponent(new toggleMuteMusicLComp(radio));

        // run
        core.start();
    }
}
