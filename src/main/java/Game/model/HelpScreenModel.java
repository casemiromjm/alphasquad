package Game.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Classe que representa os dados associados ao ecrã de ajuda no jogo
public class HelpScreenModel extends Model {

    private List<String> text; // Lista de linhas de texto a serem exibidas no ecrã de ajuda

    /**
     * Construtor que inicializa o modelo do ecrã de ajuda.
     * Lê o texto de ajuda de um ficheiro específico.
     *
     * @throws IOException Caso ocorra um erro ao ler o ficheiro de ajuda.
     */
    public HelpScreenModel() throws IOException {
        createHelpText();
    }

    /**
     * Método privado que lê o texto de ajuda de um ficheiro e o armazena.
     *
     * @throws IOException Caso ocorra um erro ao aceder ou ler o ficheiro.
     */
    private void createHelpText() throws IOException {
        text = Files.readAllLines(Paths.get("src/main/resources/help/help_short.txt"));
    }

    /**
     * Obtém o texto de ajuda armazenado.
     *
     * @return Uma lista de strings representando as linhas do texto de ajuda.
     */
    public List<String> getText() {
        return text;
    }
}

