package Game.model.elements.obstacles;

import Game.model.elements.Position;

// Classe que representa uma pequena parede de pedra no jogo, derivada da classe Obstacle
public class SmallStoneWall extends Obstacle {

    /**
     * Construtor que inicializa uma pequena parede de pedra na posição especificada.
     *
     * @param position A posição inicial da parede de pedra na arena.
     */
    public SmallStoneWall(Position position) {
        super(position, 10, 30); // Chama o construtor da classe base Obstacle com resistência e durabilidade
    }
}
