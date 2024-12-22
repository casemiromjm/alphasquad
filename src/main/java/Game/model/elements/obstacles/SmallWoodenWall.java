package Game.model.elements.obstacles;

import Game.model.elements.Position;

// Classe que representa uma pequena parede de madeira no jogo, derivada da classe Obstacle
public class SmallWoodenWall extends Obstacle {

    /**
     * Construtor que inicializa uma pequena parede de madeira na posição especificada.
     *
     * @param position A posição inicial da parede de madeira na arena.
     */
    public SmallWoodenWall(Position position) {
        super(position, 5, 20); // Chama o construtor da classe base Obstacle com resistência e durabilidade
    }
}
