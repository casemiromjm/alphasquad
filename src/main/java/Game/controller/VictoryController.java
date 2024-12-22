package Game.controller;

import Game.Application;
import Game.model.MainMenuModel;
import Game.model.VictoryModel;
import Game.state.MainMenuState;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Controlador responsável por gerir o estado de vitória do jogo
public class VictoryController extends Controller {

    // Construtor que inicializa o controlador com o modelo de vitória
    public VictoryController(VictoryModel victoryModel) {
        super(victoryModel);
    }

    /**
     * Método que gere as ações a realizar no estado de vitória.
     * Aguarda um tempo definido antes de retornar ao menu principal.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde os elementos do jogo são exibidos.
     * @param viewer O visualizador responsável por renderizar os elementos do estado atual.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     * @throws UnsupportedAudioFileException Caso o ficheiro de som utilizado não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        long time = System.currentTimeMillis();

        // Aguarda 5 segundos antes de transitar para o menu principal
        application.waiting(time, 5000);

        // Cria uma nova instância do modelo do menu principal
        MainMenuModel mainMenuModel = new MainMenuModel();

        // Define o estado atual da aplicação para o menu principal
        application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
    }
}

