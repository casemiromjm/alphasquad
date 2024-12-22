package Game.model;

// Classe que representa os dados associados ao estado de derrota no jogo
public class DefeatModel extends Model {
    private final String defeatMsg = "You have been killed. May your soul rest in peace."; // Mensagem de derrota

    /**
     * Construtor padrão que inicializa o modelo de derrota.
     */
    public DefeatModel() {
    }

    /**
     * Obtém a mensagem de derrota.
     *
     * @return A mensagem de derrota.
     */
    public String getDefeatMsg() {
        return defeatMsg;
    }
}
