package Game.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HelpScreenModel extends Model {

    private List<String> text;

    public HelpScreenModel() throws IOException {
        createHelpText();
    }

    private void createHelpText() throws IOException {
        text = Files.readAllLines(Paths.get("src/main/resources/help/help_short.txt"));
    }

    public List<String> getText() {
        return text;
    }
}
