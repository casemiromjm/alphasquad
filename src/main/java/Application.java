import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    private State state;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        ScreenManager screenManager = new ScreenManager();
        screenManager.run();
    }

    public void setState(State state){
        this.state = state;
    }
}
