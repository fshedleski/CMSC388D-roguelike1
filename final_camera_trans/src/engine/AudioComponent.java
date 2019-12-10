package engine;

//import javafx.scene.media.*;
//import javafx.util.Duration;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioComponent extends Component {
    //Media media;
    //MediaPlayer mplayer;
    int lastFrame;
    Clip clip;
    AudioInputStream audioInputStream;

    public AudioComponent(GameObject2D _parent, String mp3FileName) {
        super(_parent);

        //media = new Media(new File(EngineCore.assetsCenter.path + mp3FileName).toURI().toString());
        //mplayer = new MediaPlayer(media);

        lastFrame = 0;
        try{
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(EngineCore.assetsCenter.path + mp3FileName).toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
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

    public void pause() {
        //mplayer.pause();
        lastFrame = clip.getFramePosition() + 3;
        clip.stop();
    }

    public void stop() {
        //mplayer.stop();
        lastFrame = 0;
        clip.stop();
    }

    public boolean isPlaying() {
        //return mplayer.getStatus().equals(MediaPlayer.Status.PLAYING);
        return clip.isRunning();
    }

    public void seek(double secs) {
        //if(secs > media.getDuration().toSeconds()) {
        //    secs = media.getDuration().toSeconds();
        //} else if(secs < 0) {
        //    secs = 0;
        //}
        //mplayer.seek(new Duration(1000*secs));
        clip.setMicrosecondPosition((long)(secs*1000));
    }

    // takes int 0 - 100
    //public void setVolume(double val) {
        //if(val > 100) {
        //    val = 100;
        //} else if(val < 0) {
        //    val = 0;
        //}
        //mplayer.setVolume(val/100.0);
    //}

    //public MediaPlayer getMplayer() {
    //    return mplayer;
    //}

    @Override
    public void logic() { }

    @Override
    public void graphics(Graphics2D g) { }
}
