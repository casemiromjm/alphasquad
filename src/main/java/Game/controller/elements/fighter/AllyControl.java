package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.controller.MainMenuController;
import Game.model.GameModel;
import Game.model.MainMenuModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Fighter;
import Game.state.GameState;
import Game.state.MainMenuState;
import Game.view.GameViewer;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Classe que implementa o controlo de um aliado durante o jogo
public class AllyControl extends GameController implements FighterControl {

    private final Ally ally; // Instância do aliado controlado
    private Fighter target; // Alvo do aliado

    // Construtor que inicializa o controlador com o modelo do jogo e o aliado
    public AllyControl(GameModel gameModel, Ally ally) {
        super(gameModel);
        this.ally = ally;
    }

    /**
     * Método principal que gere as ações do aliado, incluindo movimentação, aplicação
     * de power-ups, seleção de alvos e ataques.
     *
     * @param application Instância da aplicação principal.
     * @param screen O ecrã onde o jogo é exibido.
     * @param viewer O visualizador que atualiza a interface gráfica do jogo.
     */
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();
        GameViewer gameViewer = (GameViewer) viewer;

        // Desenha o estado inicial do jogo
        gameViewer.draw(screen);

        // Move o aliado com base na entrada do utilizador
        move(application, screen);

        // Verifica se o estado atual da aplicação ainda é o jogo
        if (!(application.getState() instanceof GameState)) {
            return;
        }

        // Aplica os power-ups ao aliado
        gameModel.applyPowerUps(ally);

        // Atualiza o ecrã após aplicar os power-ups
        gameViewer.draw(screen);

        // Obtém a lista de inimigos e seleciona um alvo
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);

        // Ataca o alvo selecionado
        fire(target, gameViewer);
    }

    /**
     * Método responsável pela movimentação do aliado.
     *
     * @param application A instância da aplicação principal.
     * @param screen O ecrã onde os elementos do jogo são exibidos.
     */
    @Override
    public void move(Application application, Screen screen) throws IOException {
        boolean moved = false;
        GameModel gameModel = (GameModel) super.getModel();
        Position position = ally.getPosition();

        // Aguarda até que o aliado se mova para uma posição válida
        while (!moved) {
            KeyStroke keyStroke = screen.readInput();

            // Movimentos do aliado com base nas setas do teclado
            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                position = new Position(ally.getPosition().getX(), ally.getPosition().getY() - 1);
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                position = new Position(ally.getPosition().getX(), ally.getPosition().getY() + 1);
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                position = new Position(ally.getPosition().getX() + 1, ally.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                position = new Position(ally.getPosition().getX() - 1, ally.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            }

            // Enter confirma a posição atual
            else if (keyStroke.getKeyType() == KeyType.Enter) {
                position = ally.getPosition();
                moved = true;
            }

            // Escape volta ao menu principal
            else if (keyStroke.getKeyType() == KeyType.Escape) {
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
                return;
            }
        }

        // Atualiza a posição do aliado
        ally.setPosition(position);
    }

    /**
     * Método que permite ao jogador selecionar um alvo da lista de inimigos.
     *
     * @param screen O ecrã onde o jogo é exibido.
     * @param targets A lista de inimigos disponíveis como alvos.
     * @param gameViewer O visualizador para exibir o estado atual.
     * @return O lutador selecionado como alvo.
     */
    @Override
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException {
        int target_index = 0; // Índice inicial do alvo
        Fighter target = targets.get(target_index);
        boolean selected = false;

        // Ciclo que permite selecionar um alvo até ser confirmado com Enter
        while (!selected) {
            // Exibe o estado de combate com o alvo selecionado
            gameViewer.drawFighterCombatPhase(screen, ally, targets.get(target_index));
            KeyStroke keyStroke = screen.readInput();

            // Alterna entre os alvos com as setas esquerda e direita
            switch (keyStroke.getKeyType()) {
                case KeyType.ArrowRight:
                    target_index++;
                    break;
                case KeyType.ArrowLeft:
                    target_index--;
                    break;
                case KeyType.Enter:
                    selected = true;
                    break;
            }

            // Garante que o índice está dentro dos limites da lista
            if (target_index < 0) {
                target_index += targets.size();
            } else if (target_index >= targets.size()) {
                target_index -= targets.size();
            }

            target = targets.get(target_index);
        }
        return target;
    }

    /**
     * Realiza um ataque ao alvo selecionado.
     *
     * @param target O inimigo a ser atacado.
     * @param gameViewer O visualizador para exibir o resultado do ataque.
     */
    @Override
    public void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();

        // Verifica se o ataque foi bem-sucedido
        if (gameModel.hitOrMiss(ally, target)) {
            gameViewer.hitSound(); // Som de acerto
            target.setHitPoints(target.getHitPoints() - gameModel.damageCalculator(ally, target.getPosition())); // Reduz os pontos de vida do alvo
            return;
        }
        gameViewer.missSound(); // Som de falha
    }
}