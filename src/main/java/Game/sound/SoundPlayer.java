package Game.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    public SoundPlayer() {
    }

    public void hitSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        File soundFile = new File("src/main/resources/sounds/hit.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-12.0f);

        clip.start();
        clip.drain();
        clip.close();
        audioInputStream.close();
    }

    public void missSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File soundFile = new File("src/main/resources/sounds/miss.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-12.0f);

        clip.start();
        clip.drain();
        clip.close();
        audioInputStream.close();
    }
}
