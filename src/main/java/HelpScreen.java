import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class HelpScreen {
    private Screen screen;

    public HelpScreen(Screen screen) {
        this.screen = screen;
    }

    public void run() throws IOException {
        draw();

        while(true){
            KeyStroke key = screen.readInput();

            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.clear();
                break;
            }
        }
    }
}
