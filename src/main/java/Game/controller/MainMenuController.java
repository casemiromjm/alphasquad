package Game.controller;

import Game.Application;
import Game.model.gameModel.GameModel;
import Game.model.HelpScreenModel;
import Game.model.MainMenuModel;
import Game.model.gameModel.RandomGameBuilder;
import Game.state.GameState;
import Game.state.HelpState;
import Game.view.GameViewer;
import Game.view.HelpScreenViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Random;

// Controlador responsável por gerir o menu principal do jogo
public class MainMenuController extends Game.controller.Controller {

    // Construtor que inicializa o controlador com o modelo do menu principal
    public MainMenuController(MainMenuModel mainMenuModel) {
        super(mainMenuModel);
    }

    /**
     * Método que gere as interações no menu principal.
     * Processa a entrada do utilizador para determinar a próxima ação, como iniciar o jogo,
     * abrir a ajuda, ou sair da aplicação.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o menu principal é exibido.
     * @param viewer O visualizador que apresenta o menu principal.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     */
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        // Lê a entrada do utilizador
        KeyStroke key = screen.readInput();

        // Fecha a aplicação se o utilizador pressionar 'q'
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            application.setState(null);

        // Abre o ecrã de ajuda se o utilizador pressionar 'h'
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
            HelpScreenModel helpScreenModel = new HelpScreenModel();
            application.setState(new HelpState(
                helpScreenModel,
                new HelpScreenViewer(helpScreenModel),
                new HelpScreenController(helpScreenModel)
            ));

        // Inicia o jogo se o utilizador pressionar 'p'
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
            int width = screen.getTerminalSize().getColumns();
            int height = screen.getTerminalSize().getRows();
            GameModel gameModel = new GameModel(width, height, 17, new RandomGameBuilder(width, height, 17, new Random()));
            application.setState(new GameState(gameModel, new GameViewer(gameModel), new GameController(gameModel)));

        // Fecha a aplicação se for recebida a EOF (End of File)
        } else if (key.getKeyType() == KeyType.EOF) {
            application.setState(null);
        }
    }
}

