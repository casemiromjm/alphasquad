package Game.view;

import Game.model.MainMenuModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

// Classe responsável por renderizar o menu principal no ecrã
public class MainMenuViewer extends Viewer {

    private final MainMenuModel mainMenuModel; // Modelo associado ao menu principal

    /**
     * Construtor que inicializa o visualizador do menu principal com o modelo fornecido.
     *
     * @param mainMenuModel O modelo que contém os dados do menu principal.
     */
    public MainMenuViewer(MainMenuModel mainMenuModel) {
        super(mainMenuModel);
        this.mainMenuModel = mainMenuModel;
    }

    /**
     * Método que desenha o menu principal no ecrã.
     * Inclui o título do jogo, as opções do menu e uma nota explicativa.
     *
     * @param screen O ecrã onde os elementos do menu serão desenhados.
     * @throws IOException Caso ocorra um erro ao desenhar no ecrã.
     */
    @Override
    public void draw(Screen screen) throws IOException {
        // Limpa o ecrã antes de desenhar
        screen.clear();

        // Configura o estilo do texto para o título do jogo
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED);

        // Desenha o título do jogo centrado na parte superior do ecrã
        textGraphics.putString(
            new TerminalPosition(
                (screen.getTerminalSize().getColumns() / 2) - (mainMenuModel.getTitle().length() / 2), 
                3
            ), 
            mainMenuModel.getTitle()
        );

        // Remove os modificadores antes de desenhar as opções
        textGraphics.disableModifiers(SGR.BOLD, SGR.BORDERED);

        // Calcula a posição inicial das opções no centro vertical do ecrã
        int options_row = screen.getTerminalSize().getRows() / 2 - 2;

        // Obtém as opções do menu a partir do modelo
        List<String> options = mainMenuModel.getOptions();

        // Desenha cada opção no centro do ecrã, destacando a primeira letra
        for (String opt : options) {
            textGraphics.enableModifiers(SGR.BOLD); // Destaca a primeira letra
            textGraphics.putString(
                new TerminalPosition(
                    (screen.getTerminalSize().getColumns() / 2) - (opt.length() / 2), 
                    options_row
                ), 
                opt.substring(0, 1)
            );

            textGraphics.disableModifiers(SGR.BOLD); // Remove o destaque do resto da palavra
            textGraphics.putString(
                new TerminalPosition(
                    (screen.getTerminalSize().getColumns() / 2) - (opt.length() / 2) + 1, 
                    options_row
                ), 
                opt.substring(1)
            );

            options_row += 2; // Incrementa a linha para a próxima opção
        }

        // Desenha a nota explicativa na parte inferior do ecrã
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, screen.getTerminalSize().getRows() - 2), "Note: ");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(
            new TerminalPosition(7, screen.getTerminalSize().getRows() - 2),
            "Press the key corresponding to the bolded letter of the option."
        );

        // Atualiza o ecrã para exibir os elementos desenhados
        screen.refresh();
    }
}

