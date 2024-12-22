package Game.state;

import Game.Application;
import Game.controller.Controller;
import Game.model.Model;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Classe abstrata que representa um estado genérico do jogo
public abstract class State {
    private final Model model; // Modelo associado ao estado
    private final Viewer viewer; // Visualizador responsável pela renderização do estado
    private final Controller controller; // Controlador responsável pelas interações do estado

    /**
     * Construtor que inicializa o estado com o modelo, o visualizador e o controlador associados.
     * 
     * @param model O modelo que contém os dados e lógica específica do estado.
     * @param viewer O visualizador que renderiza o estado no ecrã.
     * @param controller O controlador que gere as interações no estado.
     */
    // Comentário interno: Método factory poderá ser aplicado no futuro para evitar a criação independente de controladores e visualizadores.
    public State(Model model, Viewer viewer, Controller controller) {
        this.model = model;
        this.viewer = viewer;
        this.controller = controller;
    }

    /**
     * Método para obter o visualizador associado ao estado.
     * 
     * @return O visualizador do estado.
     */
    public Viewer getViewer() {
        return viewer;
    }

    /**
     * Método para obter o controlador associado ao estado.
     * 
     * @return O controlador do estado.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Método para obter o modelo associado ao estado.
     * 
     * @return O modelo do estado.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Método principal que executa o ciclo de execução do estado.
     * Este método desenha o estado no ecrã e delega as interações ao controlador.
     * 
     * @param application A instância principal da aplicação.
     * @param screen O ecrã onde o estado será exibido.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     * @throws UnsupportedAudioFileException Caso o ficheiro de áudio não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    public void run(Application application, Screen screen) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        viewer.draw(screen); // Desenha o estado atual no ecrã
        controller.run(application, screen, viewer); // Executa as interações no estado
    }
}