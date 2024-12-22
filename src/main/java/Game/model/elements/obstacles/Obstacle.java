package Game.model.elements.obstacles;

import Game.model.elements.Element;
import Game.model.elements.Position;

// Classe abstrata que representa um obstáculo no jogo, derivada da classe Element
public abstract class Obstacle extends Element {
    private final int damageReduction; // Redução de dano proporcionada pelo obstáculo
    private final int protection; // Nível de proteção fornecido pelo obstáculo

    /**
     * Construtor que inicializa um obstáculo na posição especificada com os valores de redução de dano e proteção.
     *
     * @param position A posição do obstáculo na arena.
     * @param damageReduction O valor da redução de dano proporcionada pelo obstáculo.
     * @param protection O nível de proteção oferecido pelo obstáculo.
     */
    public Obstacle(Position position, int damageReduction, int protection) {
        super(position);
        this.damageReduction = damageReduction;
        this.protection = protection;
    }

    /**
     * Obtém o valor de redução de dano do obstáculo.
     *
     * @return O valor da redução de dano.
     */
    public int getDamageReduction() {
        return damageReduction;
    }

    /**
     * Obtém o nível de proteção fornecido pelo obstáculo.
     *
     * @return O valor do nível de proteção.
     */
    public int getProtection() {
        return protection;
    }
}

