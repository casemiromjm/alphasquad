package Game.controller.elements.fighter;

import Game.Application;
import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

// Interface que define os métodos de controlo para lutadores no jogo
public interface FighterControl {
    
    /**
     * Método responsável por movimentar o lutador no jogo.
     * 
     * @param application A instância da aplicação principal.
     * @param screen O ecrã onde os elementos do jogo são apresentados.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     */
    void move(Application application, Screen screen) throws IOException;

    /**
     * Método para selecionar um alvo a partir de uma lista de lutadores.
     * 
     * @param screen O ecrã onde os elementos do jogo são apresentados.
     * @param targets A lista de lutadores disponíveis como alvos.
     * @param gameViewer O visualizador do jogo para mostrar o estado atual.
     * @return O lutador selecionado como alvo.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     */
    Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException;

    /**
     * Método para realizar um ataque ao lutador alvo.
     * 
     * @param target O lutador que será atacado.
     * @param gameViewer O visualizador do jogo para mostrar o estado atualizado após o ataque.
     * @throws UnsupportedAudioFileException Caso o ficheiro de som não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     */
    void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException;
}
