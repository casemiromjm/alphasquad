package Game.model.gameModel;

import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.powerUps.PowerUp;

import java.util.List;

// Classe abstrata que define o contrato para criar os componentes de um jogo
public abstract class GameBuilder {

    /**
     * Cria o jogador principal do jogo.
     *
     * @return Um objeto da classe Player representando o jogador.
     */
    protected abstract Player createPlayer();

    /**
     * Cria uma lista de aliados para o jogo.
     *
     * @param gameModel O modelo atual do jogo, utilizado para validar posicionamentos.
     * @return Uma lista de objetos Ally representando os aliados.
     */
    protected abstract List<Ally> createAllies(GameModel gameModel);

    /**
     * Cria uma lista de inimigos para o jogo.
     *
     * @param gameModel O modelo atual do jogo, utilizado para validar posicionamentos.
     * @return Uma lista de objetos Enemy representando os inimigos.
     */
    protected abstract List<Enemy> createEnemies(GameModel gameModel);

    /**
     * Cria uma lista de obstáculos para o jogo.
     *
     * @param gameModel O modelo atual do jogo, utilizado para validar posicionamentos.
     * @return Uma lista de objetos Obstacle representando os obstáculos.
     */
    protected abstract List<Obstacle> createObstacles(GameModel gameModel);

    /**
     * Cria uma lista de Power-Ups para o jogo.
     *
     * @param gameModel O modelo atual do jogo, utilizado para validar posicionamentos.
     * @return Uma lista de objetos PowerUp representando os Power-Ups.
     */
    protected abstract List<PowerUp> createPowerUps(GameModel gameModel);
}
