package Game.controller.elements.fighter;

import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public interface FighterControl {
    public void move(Screen screen) throws IOException;

    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException;

    public void fire(Fighter target);
}
