package game;

import engine.*;

import java.awt.event.KeyEvent;

public class toggleMusicLComp extends Component {
    int delay = 0;
    Keybind kToggle;

    public toggleMusicLComp(GameObject2D _parent) {
        super(_parent);
        kToggle = new Keybind(KeyEvent.VK_P, "Toggle Music");
        EngineCore.kbmenu.addKeybind(kToggle);
    }

    public toggleMusicLComp(GameObject2D _parent, Keybind _kToggle) {
        super(_parent);
        kToggle = _kToggle;
        EngineCore.kbmenu.addKeybind(kToggle);
    }

    @Override
    public void logic() {
        delay--;
        if (EngineCore.inputs.pending.contains(kToggle.bind) && delay <= 0) {
            try {
                AudioComponent audioComp = (AudioComponent) parent.getLogicComponent(AudioComponent.class).get(0);
                if (audioComp.isPlaying()) {
                    audioComp.pause();
                } else {
                    audioComp.play();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            delay = 5;
        }
    }
}
