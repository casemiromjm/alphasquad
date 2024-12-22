package Game.model;

import java.util.Arrays;
import java.util.List;

// Classe que representa os dados associados ao menu principal do jogo
public class MainMenuModel extends Model {
    private String title = "Alpha Squad"; // Título do menu principal
    private List<String> options = Arrays.asList("Play", "Help", "Quit"); // Lista de opções disponíveis no menu

    /**
     * Construtor padrão que inicializa o modelo do menu principal.
     */
    public MainMenuModel() {
    }

    /**
     * Obtém o título do menu principal.
     *
     * @return O título do menu.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtém a lista de opções do menu principal.
     *
     * @return A lista de opções disponíveis no menu.
     */
    public List<String> getOptions() {
        return options;
    }
}
