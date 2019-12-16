package game;

import engine.*;

import java.awt.event.KeyEvent;

public class keyStartMusicLComp extends Component {
    int delay = 0;
    Keybind kToggle;
    boolean isMuted;
    long muteTime;
    long muteSystemTime;


    public keyStartMusicLComp(GameObject2D _parent) {
        super(_parent);
        kToggle = new Keybind(KeyEvent.VK_P, "Toggle Music");
        EngineCore.kbmenu.addKeybind(kToggle);
        isMuted = true;
    }

    public keyStartMusicLComp(GameObject2D _parent, Keybind _kToggle) {
        super(_parent);
        kToggle = _kToggle;
        EngineCore.kbmenu.addKeybind(kToggle);
        isMuted = true;
    }

    @Override
    public void logic() {
        try {
            AudioComponent audioComp = (AudioComponent) parent.getLogicComponent(AudioComponent.class).get(0);
            if (EngineCore.inputs.pending.contains(kToggle.bind) && delay <= 0) {
                delay = 8;
                if (isMuted) {
                    audioComp.play();
                    isMuted = false;
                } else {
                    audioComp.pause();
                    isMuted = true;
                }
            } else if(delay > 0) {
                delay--;
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

    }
}
