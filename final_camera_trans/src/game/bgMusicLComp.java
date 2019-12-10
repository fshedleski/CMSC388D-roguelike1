package game;

import engine.AudioComponent;
import engine.Component;
import engine.EngineCore;
import engine.GameObject2D;

import java.awt.event.KeyEvent;

public class bgMusicLComp extends Component {
    public bgMusicLComp(GameObject2D _parent) {
        super(_parent);
    }

    @Override
    public void logic() {
        try {
            AudioComponent audioComp = (AudioComponent) parent.getLogicComponent(AudioComponent.class).get(0);
            if (!audioComp.isPlaying()) {
                audioComp.stop();
                audioComp.play();
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
    }
}
