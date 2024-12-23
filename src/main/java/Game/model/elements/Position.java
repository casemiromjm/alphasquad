package Game.model.elements;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// Classe que representa uma posição 2D no jogo
public class Position {
    int x; // Coordenada X
    int y; // Coordenada Y

    /**
     * Construtor que inicializa uma posição com as coordenadas especificadas.
     *
     * @param x A coordenada X da posição.
     * @param y A coordenada Y da posição.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtém a coordenada X da posição.
     *
     * @return O valor da coordenada X.
     */
    public int getX() {
        return x;
    }

    /**
     * Define a coordenada X da posição.
     *
     * @param x O novo valor para a coordenada X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtém a coordenada Y da posição.
     *
     * @return O valor da coordenada Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Define a coordenada Y da posição.
     *
     * @param y O novo valor para a coordenada Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Verifica se dois objetos Position são iguais, com base nas coordenadas X e Y.
     *
     * @param o O objeto a ser comparado.
     * @return `true` se as coordenadas X e Y forem iguais, caso contrário `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Gera um código hash para a posição com base nas coordenadas X e Y.
     *
     * @return O código hash gerado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Calcula a distância entre duas posições no plano 2D.
     *
     * @param p1 A primeira posição.
     * @param p2 A segunda posição.
     * @return A distância euclidiana entre as duas posições.
     */
    public static double getDistance(Position p1, Position p2) {
        return sqrt(pow(p1.getX() - p2.getX(), 2) + pow(p1.getY() - p2.getY(), 2));
    }
}
