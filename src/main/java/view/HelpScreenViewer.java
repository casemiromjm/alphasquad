package view;

import com.googlecode.lanterna.screen.Screen;
import model.HelpScreenModel;

public class HelpScreenViewer extends Viewer {
    // members
    HelpScreenModel helpScreenModel;
    Screen screen;

    // Default Constructor
    public HelpScreenViewer(HelpScreenModel helpScreenModel, Screen screen) {
        super(screen);
        this.helpScreenModel = helpScreenModel;
    }

    public void draw(){

    }
}
