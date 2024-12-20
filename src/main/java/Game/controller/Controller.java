package Game.controller;

import Game.Application;
import Game.model.Model;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Classe abstrata que serve como base para todos os controladores no jogo
public abstract class Controller {
    private final Model model; // Modelo associado ao controlador

    /**
     * Construtor que inicializa o controlador com o modelo especificado.
     *
     * @param model O modelo associado ao controlador.
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Método para obter o modelo associado ao controlador.
     *
     * @return O modelo atual do controlador.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Método abstrato que deve ser implementado por subclasses.
     * Gere as ações principais do controlador.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o estado atual é exibido.
     * @param viewer O visualizador responsável pela renderização do estado atual.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     * @throws UnsupportedAudioFileException Caso o ficheiro de som utilizado não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    public abstract void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}
