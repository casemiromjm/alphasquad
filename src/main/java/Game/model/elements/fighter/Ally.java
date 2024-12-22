package Game.model.elements.fighter;

import Game.model.elements.Position;

// Classe que representa um aliado no jogo, derivada da classe Fighter
public class Ally extends Fighter {

    /**
     * Construtor que inicializa um aliado na posição especificada.
     *
     * @param position A posição inicial do aliado na arena.
     */
    public Ally(Position position) {
        super(position); // Chama o construtor da classe base Fighter
    }
}