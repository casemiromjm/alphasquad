package Game.model;

public class DefeatModel extends Model {
    private final String defeatMsg = "You have been killed. May your soul rest in peace.";

    public DefeatModel() {
    }

    public String getDefeatMsg() {
        return defeatMsg;
    }
}
