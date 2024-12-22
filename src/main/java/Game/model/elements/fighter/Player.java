package Game.model.elements.fighter;

import Game.model.elements.Position;

// Classe que representa o jogador no jogo, derivada da classe Fighter
public class Player extends Fighter {

    /**
     * Construtor que inicializa o jogador na posição especificada.
     *
     * @param position A posição inicial do jogador na arena.
     */
    public Player(Position position) {
        super(position); // Chama o construtor da classe base Fighter
    }
}
