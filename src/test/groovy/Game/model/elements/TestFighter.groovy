package Game.model.elements;

import Game.model.elements.fighter.Player;
import spock.lang.Specification;

// Classe de testes para a classe abstrata Fighter utilizando Spock Framework.
// Como Fighter é uma classe abstrata, a classe Player, que herda suas funcionalidades, é usada nos testes.
class TestFighter extends Specification {
    def fighter // Instância de Fighter (Player será usada)
    def baseHealth // Valor inicial da saúde do Fighter

    /**
     * Configuração inicial do teste.
     * Cria uma instância de Player e define a saúde inicial.
     */
    def setup() {
        fighter = new Player(new Position(1, 1))
        baseHealth = fighter.getHealth()
    }

    /**
     * Testa o método sufferDamage para verificar se a saúde diminui corretamente após sofrer dano.
     */
    def "test sufferDamage"() {
        when:
        fighter.sufferDamage(damage)

        then:
        fighter.getHealth() == baseHealth - damage // Verifica se a saúde é calculada corretamente

        where:
        damage << [0, 1, 5, 10, 20, 21, 25, 30] // Vários valores de dano para validar
    }

    /**
     * Testa o método isDead para verificar se o Fighter é considerado morto corretamente.
     */
    def "test isDead"() {
        when:
        fighter.sufferDamage(damage)

        then:
        fighter.isDead() == (baseHealth - damage <= 0) // Verifica se o estado de "morto" está correto

        where:
        damage << [0, 1, 5, 10, 20, 21, 25, 30] // Vários valores de dano para testar a morte
    }

    /**
     * Testa o método increaseHealth para verificar se a saúde aumenta corretamente.
     */
    def "test increaseHealth"() {
        when:
        fighter.increaseHealth(bonus)

        then:
        fighter.getHealth() == baseHealth + bonus // Verifica se a saúde é incrementada corretamente

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25] // Vários valores de bónus de saúde
    }

    /**
     * Testa o método increaseAim para verificar se a precisão aumenta corretamente.
     */
    def "test increaseAim"() {
        given:
        def baseAim = fighter.getAim() // Precisão inicial

        when:
        fighter.increaseAim(bonus)

        then:
        fighter.getAim() == baseAim + bonus // Verifica se a precisão foi incrementada corretamente

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25] // Vários valores de bónus de precisão
    }

    /**
     * Testa o método increaseDamage para verificar se o dano aumenta corretamente.
     */
    def "test increaseDamage"() {
        given:
        def baseDamage = fighter.getDamage() // Dano inicial

        when:
        fighter.increaseDamage(bonus)

        then:
        fighter.getDamage() == baseDamage + bonus // Verifica se o dano foi incrementado corretamente

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25] // Vários valores de bónus de dano
    }

    /**
     * Testa o método getUp para verificar se a posição acima é calculada corretamente.
     */
    def "test getUp"() {
        expect:
        fighter.getUp().equals(new Position(fighter.getPosition().getX(), fighter.getPosition().getY() - 1)) // Verifica a posição acima
    }

    /**
     * Testa o método getDown para verificar se a posição abaixo é calculada corretamente.
     */
    def "test getDown"() {
        expect:
        fighter.getDown().equals(new Position(fighter.getPosition().getX(), fighter.getPosition().getY() + 1)) // Verifica a posição abaixo
    }

    /**
     * Testa o método getRight para verificar se a posição à direita é calculada corretamente.
     */
    def "test getRight"() {
        expect:
        fighter.getRight().equals(new Position(fighter.getPosition().getX() + 1, fighter.getPosition().getY())) // Verifica a posição à direita
    }

    /**
     * Testa o método getLeft para verificar se a posição à esquerda é calculada corretamente.
     */
    def "test getLeft"() {
        expect:
        fighter.getLeft().equals(new Position(fighter.getPosition().getX() - 1, fighter.getPosition().getY())) // Verifica a posição à esquerda
    }
}

