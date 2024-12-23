package Game.model.elements;

import static java.lang.Math.sqrt;

import spock.lang.Specification;

// Classe de testes para a classe Position utilizando Spock Framework
class TestPosition extends Specification {

    /**
     * Testa o método getX para verificar se retorna corretamente o valor da coordenada X.
     */
    def "test getX"() {
        given:
        Position position = new Position(1, 2) // Cria uma posição com X=1 e Y=2

        when:
        int x = position.getX() // Obtém a coordenada X

        then:
        x == 1 // Verifica se o valor retornado é 1
    }

    /**
     * Testa o método getY para verificar se retorna corretamente o valor da coordenada Y.
     */
    def "test getY"() {
        given:
        Position position = new Position(1, 2)

        when:
        int y = position.getY() // Obtém a coordenada Y

        then:
        y == 2 // Verifica se o valor retornado é 2
    }

    /**
     * Testa o método setX para verificar se atualiza corretamente a coordenada X.
     */
    def "test setX"() {
        given:
        Position position = new Position(1, 2)

        when:
        position.setX(3) // Atualiza a coordenada X para 3
        int x = position.getX()

        then:
        x == 3 // Verifica se o valor foi atualizado corretamente
    }

    /**
     * Testa o método setY para verificar se atualiza corretamente a coordenada Y.
     */
    def "test setY"() {
        given:
        Position position = new Position(1, 2)

        when:
        position.setY(30) // Atualiza a coordenada Y para 30
        int y = position.getY()

        then:
        y == 30 // Verifica se o valor foi atualizado corretamente
    }

    /**
     * Testa o método equals para verificar se compara corretamente objetos Position.
     */
    def "test equals"() {
        given:
        Position position = new Position(1, 2)
        Position position1 = new Position(1, 2)
        Position position2 = new Position(3, 4)

        expect:
        position.equals(position) // Verifica se um objeto é igual a ele mesmo
        position.equals(position1) // Verifica se dois objetos com os mesmos valores são iguais
        !position.equals(position2) // Verifica se objetos com valores diferentes não são iguais
    }

    /**
     * Testa o método estático getDistance para calcular corretamente a distância entre duas posições.
     */
    def "test getDistance"(Position a, Position b, double dist) {
        expect:
        Position.getDistance(a, b) == dist // Verifica se a distância calculada corresponde à esperada

        where:
        // Casos de teste com diferentes pares de posições e suas distâncias esperadas
        a                   | b                 | dist
        new Position(0, 0)  | new Position(0, 0)  | 0
        new Position(1, 0)  | new Position(2, 0)  | 1
        new Position(0, 1)  | new Position(0, 2)  | 1
        new Position(1, 1)  | new Position(2, 2)  | sqrt(2)
        new Position(10, 20)| new Position(5, 10) | sqrt(125)
    }
}
