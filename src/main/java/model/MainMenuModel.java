package model;

import java.util.Arrays;
import java.util.List;

public class MainMenuModel extends Model {
    private String title = "Title";
    private List<String> options = Arrays.asList("Play", "Help", "Quit");

    public MainMenuModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
