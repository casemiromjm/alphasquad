package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.controller.MainMenuController;
import Game.model.GameModel;
import Game.model.MainMenuModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
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

public class PlayerControl extends GameController implements FighterControl {
    private final Player player;

    // Construtor que inicializa o controlador com o modelo do jogo e o jogador
    public PlayerControl(GameModel gameModel, Player player) {
        super(gameModel);
        this.player = player;
    }

    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();
        GameViewer gameViewer = (GameViewer) viewer;

        // Desenha o estado atual do jogo no ecrã
        gameViewer.draw(screen);

        // Move o jogador baseado na entrada do utilizador
        move(application, screen);

        // Verifica se o estado atual da aplicação ainda é o jogo
        if (!(application.getState() instanceof GameState)) {
            return;
        }

        // Aplica os power-ups ao jogador
        gameModel.applyPowerUps(player);

        // Redesenha o estado do jogo após a aplicação dos power-ups
        gameViewer.draw(screen);

        // Obtém a lista de inimigos e seleciona um alvo
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);

        // Realiza o ataque ao inimigo selecionado
        fire(target, gameViewer);
    }

    @Override
    public void move(Application application, Screen screen) throws IOException {
        boolean moved = false;
        GameModel gameModel = (GameModel) super.getModel();
        Position position = player.getPosition();

        // Aguarda até que o jogador realize uma ação válida
        while (!moved) {
            KeyStroke keyStroke = screen.readInput();

            // Movimentos do jogador com base nas setas do teclado
            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                position = new Position(player.getPosition().getX(), player.getPosition().getY() - 1);
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                position = new Position(player.getPosition().getX(), player.getPosition().getY() + 1);
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                position = new Position(player.getPosition().getX() + 1, player.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                position = new Position(player.getPosition().getX() - 1, player.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            } 
            // Permanecer na posição atual ao pressionar Enter
            else if (keyStroke.getKeyType() == KeyType.Enter) {
                position = player.getPosition();
                moved = true;
            } 
            // Volta ao menu principal ao pressionar Escape
            else if (keyStroke.getKeyType() == KeyType.Escape) {
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
                return;
            }
        }

        // Atualiza a posição do jogador após o movimento
        player.setPosition(position);
    }

    @Override
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException {
        int target_index = 0;
        Fighter target = targets.get(target_index);
        boolean selected = false;

        // Permite ao jogador selecionar um inimigo como alvo
        while (!selected) {
            // Mostra a fase de combate com o inimigo selecionado
            gameViewer.drawFighterCombatPhase(screen, player, targets.get(target_index));
            KeyStroke keyStroke = screen.readInput();

            // Alterna entre os inimigos com as setas direita e esquerda
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

            // Garante que o índice do alvo está dentro dos limites da lista
            if (target_index < 0) {
                target_index += targets.size();
            } else if (target_index >= targets.size()) {
                target_index %= targets.size();
            }

            target = targets.get(target_index);
        }
        return target;
    }

    // Realiza o ataque ao inimigo selecionado
    @Override
    public void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();

        // Determina se o ataque acertou ou falhou
        if (gameModel.hitOrMiss(player, target)) {
            gameViewer.hitSound(); // Toca som de acerto
            target.setHitPoints(target.getHitPoints() - gameModel.damageCalculator(player, target.getPosition())); // Reduz os pontos de vida do alvo
            return;
        }
        gameViewer.missSound(); // Toca som de falha
    }
}

