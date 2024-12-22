package Game.model.elements;

// Classe abstrata que representa um elemento genérico no jogo, como personagens e obstáculos
public abstract class Element {
    private Position position; // Posição do elemento na arena

    /**
     * Construtor que inicializa um elemento com a posição especificada.
     *
     * @param position A posição inicial do elemento na arena.
     */
    public Element(Position position) {
        this.position = position;
    }

    /**
     * Obtém a posição atual do elemento.
     *
     * @return A posição do elemento.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Define uma nova posição para o elemento.
     *
     * @param position A nova posição a ser atribuída ao elemento.
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}

