package Game.model;

// Classe que representa os dados associados ao estado de vitória no jogo
public class VictoryModel extends Model {
    private final String victoryMsg = "You have successfully completed your mission. Great job."; // Mensagem de vitória

    /**
     * Construtor padrão que inicializa o modelo de vitória.
     */
    public VictoryModel() {
    }

    /**
     * Obtém a mensagem de vitória.
     *
     * @return A mensagem de vitória.
     */
    public String getVictoryMsg() {
        return victoryMsg;
    }
}

