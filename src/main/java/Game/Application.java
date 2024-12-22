package Game;

import Game.controller.MainMenuController;
import Game.model.MainMenuModel;
import Game.state.MainMenuState;
import Game.state.State;
import Game.view.MainMenuViewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

// Classe principal da aplicação que gere o ciclo de vida do jogo
public class Application {
    private State state; // Estado atual da aplicação
    private final ScreenManager screenManager; // Gerente do ecrã do jogo

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     * @throws IOException Caso ocorra um erro ao inicializar o ecrã ou os estados.
     * @throws URISyntaxException Caso o caminho para a fonte seja inválido.
     * @throws FontFormatException Caso o ficheiro da fonte esteja num formato inválido.
     * @throws UnsupportedAudioFileException Caso algum ficheiro de áudio não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        new Application().start(); // Cria e inicia a aplicação
    }

    /**
     * Construtor que inicializa a aplicação, configurando o ecrã e definindo o estado inicial.
     * 
     * @throws IOException Caso ocorra um erro ao inicializar o ecrã.
     * @throws URISyntaxException Caso o caminho para a fonte seja inválido.
     * @throws FontFormatException Caso o ficheiro da fonte esteja num formato inválido.
     */
    public Application() throws IOException, URISyntaxException, FontFormatException {
        screenManager = new ScreenManager(85, 45, "fonts/square.ttf"); // Configura o ecrã
        MainMenuModel mainMenuModel = new MainMenuModel(); // Modelo do menu principal
        state = new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)); // Define o estado inicial como o menu principal
    }

    /**
     * Define um novo estado para a aplicação.
     * 
     * @param state O novo estado a ser definido.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Obtém o estado atual da aplicação.
     * 
     * @return O estado atual.
     */
    public State getState() {
        return state;
    }

    /**
     * Obtém o ecrã configurado pela aplicação.
     * 
     * @return O objeto Screen gerido pelo ScreenManager.
     */
    public Screen getScreen() {
        return screenManager.getScreen();
    }

    /**
     * Inicia o ciclo principal da aplicação, mantendo-a ativa enquanto existir um estado válido.
     * 
     * @throws IOException Caso ocorra um erro durante a execução do estado.
     * @throws UnsupportedAudioFileException Caso algum ficheiro de áudio não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    private void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        while (this.state != null) {
            state.run(this, screenManager.getScreen()); // Executa o estado atual
        }

        screenManager.closeScreen(); // Fecha o ecrã quando o estado é nulo (aplicação termina)
    }

    /**
     * Método utilitário que espera por um período especificado (em milissegundos).
     * 
     * @param time O tempo inicial em milissegundos.
     * @param timer A duração da espera em milissegundos.
     * @return O tempo atual após a espera.
     */
    public long waiting(long time, long timer) {
        while (System.currentTimeMillis() - time < timer); // Loop ativo até que o tempo especificado seja atingido
        return System.currentTimeMillis();
    }
}