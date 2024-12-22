package Game.model.elements.fighter;

import Game.model.elements.Position;

// Classe que representa um inimigo no jogo, derivada da classe Fighter
public class Enemy extends Fighter {

    /**
     * Construtor que inicializa um inimigo na posição especificada.
     *
     * @param position A posição inicial do inimigo na arena.
     */
    public Enemy(Position position) {
        super(position); // Chama o construtor da classe base Fighter
    }
}
