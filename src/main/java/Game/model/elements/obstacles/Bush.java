package Game.model.elements.obstacles;

import Game.model.elements.Position;

// Classe que representa um arbusto no jogo, derivada da classe Obstacle
public class Bush extends Obstacle {

    /**
     * Construtor que inicializa um arbusto na posição especificada.
     *
     * @param position A posição inicial do arbusto na arena.
     */
    public Bush(Position position) {
        super(position, 0, 10); // Chama o construtor da classe base Obstacle com valores de redução de dano e proteção
    }
}