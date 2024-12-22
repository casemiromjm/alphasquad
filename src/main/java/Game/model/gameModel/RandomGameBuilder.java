package Game.model.gameModel;

import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.*;
import Game.model.elements.powerUps.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Classe responsável por construir um jogo com elementos posicionados aleatoriamente
public class RandomGameBuilder extends GameBuilder {

    private final int width; // Largura da arena
    private final int height; // Altura da arena
    private final int arenaStartPoint; // Ponto inicial da arena
    private final Random rand; // Gerador de números aleatórios

    /**
     * Construtor que inicializa o construtor de jogos aleatórios.
     *
     * @param width Largura da arena.
     * @param height Altura da arena.
     * @param arenaStartPoint Ponto inicial da arena.
     * @param rand Objeto Random para gerar posições aleatórias.
     */
    public RandomGameBuilder(int width, int height, int arenaStartPoint, Random rand) {
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
        this.rand = rand;
    }

    /**
     * Gera uma posição aleatória dentro de um intervalo especificado.
     *
     * @param xOrigin Limite inferior para a coordenada X.
     * @param xLimit Limite superior para a coordenada X.
     * @param yOrigin Limite inferior para a coordenada Y.
     * @param yLimit Limite superior para a coordenada Y.
     * @return Uma posição aleatória.
     */
    private Position generatePosition(int xOrigin, int xLimit, int yOrigin, int yLimit) {
        int x = rand.nextInt(xOrigin, xLimit);
        int y = rand.nextInt(yOrigin, yLimit);
        return new Position(x, y);
    }

    @Override
    protected Player createPlayer() {
        return new Player(new Position(width / 2, height - 2)); // Posiciona o jogador no centro inferior
    }

    @Override
    protected List<Ally> createAllies(GameModel gameModel) {
        List<Ally> allyList = new ArrayList<>();
        while (allyList.size() < 2) {
            int x = rand.nextInt(width / 2 - 3, width / 2 + 4);
            int y = height - 2;
            if (gameModel.elementCanBePlaced(new Position(x, y))) {
                allyList.add(new Ally(new Position(x, y)));
            }
        }
        return allyList;
    }

    @Override
    protected List<Enemy> createEnemies(GameModel gameModel) {
        List<Enemy> enemyList = new ArrayList<>();
        while (enemyList.size() < gameModel.getLevel() * 3) {
            Position position = generatePosition(arenaStartPoint, width, 0, height / 4);
            if (gameModel.elementCanBePlaced(position)) {
                enemyList.add(new Enemy(position));
            }
        }
        return enemyList;
    }

    @Override
    protected List<Obstacle> createObstacles(GameModel gameModel) {
        List<Obstacle> obstacleList = new ArrayList<>();
        
        // Adiciona paredes de pedra
        for (int i = 0; i < 2; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);
            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new SmallStoneWall(position));
                i++;
            }
        }

        // Adiciona paredes de madeira
        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);
            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new SmallWoodenWall(position));
                i++;
            }
        }

        // Adiciona arbustos
        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);
            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new Bush(position));
                i++;
            }
        }

        return obstacleList;
    }

    @Override
    protected List<PowerUp> createPowerUps(GameModel gameModel) {
        List<PowerUp> powerUpList = new ArrayList<>();
        List<Position> powerup_pos = new ArrayList<>();

        // Adiciona um Power-Up ExtraHealth
        while (true) {
            Position position = generatePosition(arenaStartPoint, width, 4 * height / 5, 4 * height);
            if (gameModel.elementCanBePlaced(position)) {
                powerUpList.add(new ExtraHealth(position));
                powerup_pos.add(position);
                break;
            }
        }

        // Adiciona um Power-Up ExtraAim
        while (true) {
            Position position = generatePosition(arenaStartPoint, width, 4 * height / 5, 4 * height);
            if (gameModel.elementCanBePlaced(position) && !powerup_pos.contains(position)) {
                powerUpList.add(new ExtraAim(position));
                break;
            }
        }

        // Adiciona um Power-Up ExtraDamage
        while (true) {
            Position position = generatePosition(arenaStartPoint, width, 4 * height / 5, 4 * height);
            if (gameModel.elementCanBePlaced(position) && !powerup_pos.contains(position)) {
                powerUpList.add(new ExtraDamage(position));
                break;
            }
        }

        return powerUpList;
    }
}
