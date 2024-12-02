package state;

import view.Viewer;

public abstract class State {
    public Model model;
    private Viewer viewer;
    private Controller controller;
}
