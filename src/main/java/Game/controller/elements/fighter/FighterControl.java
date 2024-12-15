package Game.controller.elements.fighter;

import Game.Application;
import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public interface FighterControl {
    void move(Application application, Screen screen) throws IOException;

    Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException;

    void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException;
}
