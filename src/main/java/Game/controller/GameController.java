package Game.controller;

import Game.Application;
import Game.controller.elements.fighter.AllyControl;
import Game.controller.elements.fighter.EnemyControl;
import Game.controller.elements.fighter.PlayerControl;
import Game.model.DefeatModel;
import Game.model.GameModel;
import Game.model.VictoryModel;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.state.DefeatState;
import Game.state.GameState;
import Game.state.VictoryState;
import Game.view.DefeatViewer;
import Game.view.VictoryViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Controlador principal do jogo, responsável por gerir o ciclo de jogabilidade
public class GameController extends Game.controller.Controller {

    // Construtor que inicializa o controlador com o modelo do jogo
    public GameController(GameModel gameModel) {
        super(gameModel);
    }

    /**
     * Método principal que gere o ciclo de jogabilidade.
     * Inclui verificações de vitória, derrota, e transições de estado do jogo.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o jogo é exibido.
     * @param viewer O visualizador responsável por renderizar o estado atual do jogo.
     * @throws IOException Caso ocorra um erro de entrada/saída.
     * @throws UnsupportedAudioFileException Caso o ficheiro de som não seja suportado.
     * @throws LineUnavailableException Caso a linha de áudio não esteja disponível.
     */
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();

        // Verifica se o jogador atingiu o nível máximo e ativa o estado de vitória
        if (gameModel.getLevel() > 3) {
            VictoryModel victoryModel = new VictoryModel();
            application.setState(new VictoryState(
                victoryModel,
                new VictoryViewer(victoryModel),
                new VictoryController(victoryModel)
            ));
            return;
        }

        // Executa a jogada do jogador
        PlayerControl playerControl = new PlayerControl(gameModel, gameModel.getPlayer());
        playerControl.run(application, screen, viewer);

        // Verifica se o estado do jogo mudou após a jogada do jogador
        if (!(application.getState() instanceof GameState)) {
            return;
        }

        // Remove lutadores mortos e verifica se o nível foi concluído
        gameModel.cleanDeath();
        if (gameModel.checkNewLevel()) {
            return;
        }

        // Executa as jogadas dos aliados
        for (Ally ally : gameModel.getAllyList()) {
            AllyControl allyControl = new AllyControl(gameModel, ally);
            allyControl.run(application, screen, viewer);

            // Verifica se o estado do jogo mudou após a jogada de um aliado
            if (!(application.getState() instanceof GameState)) {
                return;
            }

            // Remove lutadores mortos e verifica se o nível foi concluído
            gameModel.cleanDeath();
            if (gameModel.checkNewLevel()) {
                return;
            }
        }

        // Executa as jogadas dos inimigos
        for (Enemy enemy : gameModel.getEnemyList()) {
            EnemyControl enemyControl = new EnemyControl(gameModel, enemy);
            enemyControl.run(application, screen, viewer);

            // Remove lutadores mortos após a jogada de um inimigo
            gameModel.cleanDeath();

            // Verifica se o jogador morreu e ativa o estado de derrota
            if (gameModel.getPlayer().isDead()) {
                DefeatModel defeatModel = new DefeatModel();
                application.setState(new DefeatState(
                    defeatModel,
                    new DefeatViewer(defeatModel),
                    new DefeatController(defeatModel)
                ));
                return;
            }
        }
    }
}
