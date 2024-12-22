package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.model.gameModel.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.sound.SoundPlayer;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnemyControl extends GameController implements FighterControl {
    private final Enemy enemy; // Referência ao inimigo controlado
    private Fighter target; // Alvo atual do inimigo

    // Construtor que inicializa o controlador com o modelo do jogo e o inimigo
    public EnemyControl(GameModel gameModel, Enemy enemy) {
        super(gameModel);
        this.enemy = enemy;
    }

    /**
     * Método principal para executar as ações do inimigo, incluindo seleção de alvo,
     * movimentação e ataque.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde os elementos do jogo são apresentados.
     * @param viewer O visualizador que gere a interface gráfica do jogo.
     */
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();

        // Cria uma lista com o jogador e os aliados para escolher como alvo
        List<Fighter> alliesAndPlayer = new ArrayList<>();
        alliesAndPlayer.add(gameModel.getPlayer());
        alliesAndPlayer.addAll(gameModel.getAllyList());

        // Seleciona o alvo mais próximo
        target = selectTarget(screen, alliesAndPlayer, (GameViewer) viewer);

        GameViewer gameViewer = (GameViewer) viewer;
        long time = System.currentTimeMillis();
        long timer = 1000;

        // Atualiza o ecrã e executa as ações do inimigo
        gameViewer.draw(screen);
        time = application.waiting(time, timer);
        move(application, screen);
        gameViewer.draw(screen);
        application.waiting(time, timer);
        gameViewer.drawFighterCombatPhase(screen, enemy, target);
        fire(target);
    }

    /**
     * Método responsável por movimentar o inimigo para a posição mais vantajosa.
     *
     * @param application A instância da aplicação principal.
     * @param screen O ecrã onde os elementos do jogo são apresentados.
     */
    public void move(Application application, Screen screen) {
        GameModel gameModel = (GameModel) super.getModel();
        List<Position> adjacentPositions = Arrays.asList(enemy.getUp(), enemy.getDown(), enemy.getLeft(), enemy.getRight());

        Position best = null;
        int aim_penalty = 0;

        // Avalia as posições adjacentes para encontrar a mais vantajosa
        for (Position pos : adjacentPositions) {
            int pos_aim_penalty = gameModel.aimPenaltyCalculator(pos, target.getPosition());
            if (best == null && gameModel.elementCanBePlaced(pos)) {
                best = pos;
                aim_penalty = pos_aim_penalty;
            } else if (pos_aim_penalty < aim_penalty && gameModel.elementCanBePlaced(pos)) {
                best = pos;
                aim_penalty = pos_aim_penalty;
            }
        }

        // Atualiza a posição do inimigo
        enemy.setPosition(best);
    }

    /**
     * Seleciona o alvo mais próximo da lista fornecida.
     *
     * @param screen O ecrã onde os elementos do jogo são apresentados.
     * @param targets A lista de possíveis alvos.
     * @param gameViewer O visualizador para mostrar o estado atual.
     * @return O lutador mais próximo.
     */
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer){
        Fighter closest = targets.getFirst();

        for(Fighter fighter : targets){
            if(Position.getDistance(enemy.getPosition(), fighter.getPosition()) < Position.getDistance(enemy.getPosition(), closest.getPosition()))
                closest = fighter;
            }
        }
        return closest;
    }

    /**
     * Realiza um ataque ao alvo selecionado.
     *
     * @param target O lutador que será atacado.
     */
    public void fire(Fighter target) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();
        SoundPlayer soundPlayer = new SoundPlayer();
        // Verifica se o ataque foi bem-sucedido
        if(gameModel.hitOrMiss(enemy, target)){
            soundPlayer.hitSound();
            target.sufferDamage(gameModel.damageCalculator(enemy, target.getPosition()));
            return;
        }
        soundPlayer.missSound();
    }
}
