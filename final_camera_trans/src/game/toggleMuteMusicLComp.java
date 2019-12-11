package game;

import engine.*;

import java.awt.event.KeyEvent;

public class toggleMuteMusicLComp extends Component {
    int delay = 0;
    Keybind kToggle;
    boolean isMuted;
    long muteTime;
    long muteSystemTime;


    public toggleMuteMusicLComp(GameObject2D _parent) {
        super(_parent);
        kToggle = new Keybind(KeyEvent.VK_P, "Toggle Music");
        EngineCore.kbmenu.addKeybind(kToggle);
        isMuted = false;
    }

    public toggleMuteMusicLComp(GameObject2D _parent, Keybind _kToggle) {
        super(_parent);
        kToggle = _kToggle;
        EngineCore.kbmenu.addKeybind(kToggle);
        isMuted = false;
    }

    @Override
    public void logic() {
        try {
            AudioComponent audioComp = (AudioComponent) parent.getLogicComponent(AudioComponent.class).get(0);
            if (!audioComp.isPlaying() && !isMuted) {
                audioComp.stop();
                audioComp.play();
            }
            if (EngineCore.inputs.pending.contains(kToggle.bind) && delay <= 0) {
                delay = 8;
                if (isMuted) {
                    //audioComp.unmute();
                    audioComp.play();
                    //System.out.println(muteTime + (System.currentTimeMillis()-muteSystemTime)*1000);
                    //audioComp.seekMicroSec(muteTime + (System.currentTimeMillis()-muteSystemTime)*1000);
                    isMuted = false;
                } else {
                    //audioComp.mute();
                    audioComp.pause();
                    //muteTime = audioComp.getMicroSec();
                    //muteSystemTime = System.currentTimeMillis();
                    //audioComp.stop();
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
