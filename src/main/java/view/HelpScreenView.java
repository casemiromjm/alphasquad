package view;

import com.googlecode.lanterna.screen.Screen;
import model.HelpScreenModel;

public class HelpScreenView {
    HelpScreenModel helpScreenModel;
    Screen screen;

    public HelpScreenView(HelpScreenModel helpScreenModel, Screen screen) {
        this.helpScreenModel = helpScreenModel;
        this.screen = screen;
    }

    public void draw(){

    }
}
