package Game.controller;

import Game.Application;
import Game.model.DefeatModel;
import Game.model.MainMenuModel;
import Game.state.MainMenuState;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Controlador responsável por gerir o estado de derrota do jogo
public class DefeatController extends Controller {

    // Construtor que inicializa o controlador com o modelo de derrota
    public DefeatController(DefeatModel defeatModel) {
        super(defeatModel);
    }

    /**
     * Método principal que gere as ações no estado de derrota.
     * Aguarda um período de tempo antes de redirecionar o jogador para o menu principal.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o estado de derrota é exibido.
     * @param viewer O visualizador responsável pela renderização do estado de derrota.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     * @throws UnsupportedAudioFileException Caso o ficheiro de som utilizado não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // Captura o momento atual em milissegundos
        long time = System.currentTimeMillis();

        // Aguarda 5 segundos antes de transitar para o menu principal
        application.waiting(time, 5000);

        // Cria uma nova instância do menu principal
        MainMenuModel mainMenuModel = new MainMenuModel();

        // Define o estado atual da aplicação para o menu principal
        application.setState(new MainMenuState(
            mainMenuModel,
            new MainMenuViewer(mainMenuModel),
            new MainMenuController(mainMenuModel)
        ));
    }
}

