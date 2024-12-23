package Game.model.elements;

import Game.model.elements.fighter.Player;
import Game.model.elements.powerUps.ExtraAim;
import Game.model.elements.powerUps.ExtraDamage;
import Game.model.elements.powerUps.ExtraHealth;
import spock.lang.Specification;

// Classe de testes para os power-ups (ExtraAim, ExtraHealth, ExtraDamage) utilizando Spock Framework
class TestPowerUps extends Specification {

    def fighter // Instância de Fighter usada nos testes (Player será utilizado)

    /**
     * Configuração inicial dos testes.
     * Cria um jogador (Player) na posição (1, 1) para ser o alvo dos power-ups.
     */
    def setup() {
        fighter = new Player(new Position(1, 1))
    }

    /**
     * Testa o comportamento do power-up ExtraAim.
     * Verifica se o atributo de precisão (aim) do jogador aumenta após aplicar o power-up.
     */
    def "test ExtraAim"() {
        given:
        def extraAim = new ExtraAim(new Position(1, 1)) // Criação do power-up ExtraAim
        def baseAim = fighter.getAim() // Valor inicial de precisão do jogador

        when:
        extraAim.apply(fighter) // Aplica o power-up no jogador

        then:
        fighter.getAim() > baseAim // Verifica se a precisão aumentou
    }

    /**
     * Testa o comportamento do power-up ExtraHealth.
     * Verifica se o atributo de saúde (health) do jogador aumenta após aplicar o power-up.
     */
    def "test ExtraHealth"() {
        given:
        def extraHealth = new ExtraHealth(new Position(1, 1)) // Criação do power-up ExtraHealth
        def baseHealth = fighter.getHealth() // Valor inicial de saúde do jogador

        when:
        extraHealth.apply(fighter) // Aplica o power-up no jogador

        then:
        fighter.getHealth() > baseHealth // Verifica se a saúde aumentou
    }

    /**
     * Testa o comportamento do power-up ExtraDamage.
     * Verifica se o atributo de dano (damage) do jogador aumenta após aplicar o power-up.
     */
    def "test ExtraDamage"() {
        given:
        def extraDamage = new ExtraDamage(new Position(1, 1)) // Criação do power-up ExtraDamage
        def baseDamage = fighter.getDamage() // Valor inicial de dano do jogador

        when:
        extraDamage.apply(fighter) // Aplica o power-up no jogador

        then:
        fighter.getDamage() > baseDamage // Verifica se o dano aumentou
    }
}

