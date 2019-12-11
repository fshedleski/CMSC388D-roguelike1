package engine;

import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioComponent extends Component {
    int lastFrame;
    Clip clip;
    AudioInputStream audioInputStream;
    FloatControl gainControl;
    float range;

    public AudioComponent(GameObject2D _parent, String mp3FileName) {
        super(_parent);

        lastFrame = 0;
        try{
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(EngineCore.assetsCenter.path + mp3FileName).toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            range = (gainControl.getMaximum() - gainControl.getMinimum()) * 0.95f;
        } catch(Exception e) {
            System.out.println("Failed to create audioinputstream");
            e.printStackTrace();
        }
    }

    public void play() {
        //mplayer.play();
        clip.setFramePosition(lastFrame);
        clip.start();
    }

    public void loop() {
        clip.setFramePosition(lastFrame);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        lastFrame = clip.getFramePosition() + 3;
        clip.stop();
    }

    public void stop() {
        lastFrame = 0;
        clip.stop();
    }

    public boolean isPlaying() {
        //return mplayer.getStatus().equals(MediaPlayer.Status.PLAYING);
        return clip.isRunning();
    }

    public void seekMicroSec(long msecs) {
        clip.setMicrosecondPosition(msecs);
    }
    public void seekFrame(int frame) {
        clip.setFramePosition(frame);
    }
    public long getMicroSec() {
        return clip.getMicrosecondPosition();
    }
    public long getFrame() {
        return clip.getFramePosition();
    }

    // takes value 0-1 and sets volume to that percent
    public void setVolume(double v) {
        float gain = (range * (float)v) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    public void mute() {
        setVolume(0);
    }

    public void unmute() {
        setVolume(1);
    }

    @Override
    public void logic() { }

    @Override
    public void graphics(Graphics2D g) { }
}
