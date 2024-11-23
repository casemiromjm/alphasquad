package view;

import com.googlecode.lanterna.screen.Screen;
import model.HelpScreenModel;

public class HelpScreenView {

    // members
    HelpScreenModel helpScreenModel;
    Screen screen;

    // Default Constructor
    public HelpScreenView(HelpScreenModel helpScreenModel, Screen screen) {
        this.helpScreenModel = helpScreenModel;
        this.screen = screen;
    }

    public void draw(){

    }
}
