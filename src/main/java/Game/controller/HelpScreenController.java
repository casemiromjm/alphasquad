package Game.controller;

import Game.Application;
import Game.model.HelpScreenModel;
import Game.model.MainMenuModel;
import Game.state.MainMenuState;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

// Controlador responsável pela interação no ecrã de ajuda
public class HelpScreenController extends Game.controller.Controller {
    private HelpScreenModel helpScreenModel; // Modelo do ecrã de ajuda
    private Screen screen; // Referência ao ecrã onde o jogo é exibido

    // Construtor que inicializa o controlador com o modelo do ecrã de ajuda
    public HelpScreenController(HelpScreenModel helpScreenModel) {
        super(helpScreenModel);
    }

    /**
     * Método que gere as interações no ecrã de ajuda.
     * Aguarda a entrada do utilizador para determinar quando voltar ao menu principal.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o ecrã de ajuda é exibido.
     * @param viewer O visualizador responsável pela renderização do ecrã de ajuda.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     */
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        while (true) {
            // Lê a entrada do utilizador
            KeyStroke key = screen.readInput();

            // Verifica se o utilizador pressionou 'q' para sair do ecrã de ajuda
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                // Cria uma nova instância do menu principal e define o estado da aplicação para ele
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(
                    mainMenuModel,
                    new MainMenuViewer(mainMenuModel),
                    new MainMenuController(mainMenuModel)
                ));
                break; // Sai do ciclo ao voltar ao menu principal
            }
        }
    }
}
