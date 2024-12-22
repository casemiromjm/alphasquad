package Game.model.elements.fighter;

import Game.model.elements.Element;
import Game.model.elements.Position;

// Classe abstrata que representa os lutadores do jogo, incluindo inimigos, aliados e o jogador
public abstract class Fighter extends Element {
    int health; // Pontos de vida do lutador
    int aim; // Precisão do lutador (chance de acertar um ataque)
    int damage; // Dano base do lutador

    /**
     * Construtor que inicializa um lutador com posição inicial e atributos padrão.
     *
     * @param position A posição inicial do lutador na arena.
     */
    public Fighter(Position position) {
        super(position);
        health = 20; // Valor inicial de saúde
        aim = 70; // Precisão inicial
        damage = 10; // Dano inicial
    }

    /**
     * Verifica se o lutador está morto.
     *
     * @return `true` se a saúde do lutador for menor ou igual a 0, caso contrário `false`.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Obtém a saúde atual do lutador.
     *
     * @return Pontos de vida do lutador.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Aumenta a saúde do lutador com um valor de bónus.
     *
     * @param bonus O valor de saúde a ser adicionado.
     */
    public void increaseHealth(int bonus) {
        health += bonus;
    }

    /**
     * Reduz a saúde do lutador com base no dano sofrido.
     *
     * @param damage O valor do dano sofrido.
     */
    public void sufferDamage(int damage) {
        health -= damage;
    }

    /**
     * Obtém a precisão atual do lutador.
     *
     * @return Valor da precisão.
     */
    public int getAim() {
        return aim;
    }

    /**
     * Aumenta a precisão do lutador com um valor de bónus.
     *
     * @param bonus O valor de precisão a ser adicionado.
     */
    public void increaseAim(int bonus) {
        aim += bonus;
    }

    /**
     * Obtém o dano base atual do lutador.
     *
     * @return Valor do dano base.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Aumenta o dano base do lutador com um valor de bónus.
     *
     * @param bonus O valor do dano a ser adicionado.
     */
    public void increaseDamage(int bonus) {
        damage += bonus;
    }

    /**
     * Calcula a posição acima do lutador.
     *
     * @return A posição acima da posição atual do lutador.
     */
    public Position getUp() {
        int new_x = super.getPosition().getX();
        int new_y = super.getPosition().getY() - 1;
        return new Position(new_x, new_y);
    }

    /**
     * Calcula a posição abaixo do lutador.
     *
     * @return A posição abaixo da posição atual do lutador.
     */
    public Position getDown() {
        int new_x = super.getPosition().getX();
        int new_y = super.getPosition().getY() + 1;
        return new Position(new_x, new_y);
    }

    /**
     * Calcula a posição à esquerda do lutador.
     *
     * @return A posição à esquerda da posição atual do lutador.
     */
    public Position getLeft() {
        int new_x = super.getPosition().getX() - 1;
        int new_y = super.getPosition().getY();
        return new Position(new_x, new_y);
    }

    /**
     * Calcula a posição à direita do lutador.
     *
     * @return A posição à direita da posição atual do lutador.
     */
    public Position getRight() {
        int new_x = super.getPosition().getX() + 1;
        int new_y = super.getPosition().getY();
        return new Position(new_x, new_y);
    }
}
