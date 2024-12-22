package Game.model.elements

import Game.model.elements.fighter.Player
import Game.model.elements.powerUps.ExtraAim
import Game.model.elements.powerUps.ExtraDamage
import Game.model.elements.powerUps.ExtraHealth
import spock.lang.Specification

class TestPowerUps extends Specification {

    def fighter

    def setup(){
        fighter = new Player(new Position(1, 1))
    }

    def "test ExtraAim"(){
        given:
        def extraAim = new ExtraAim(new Position(1,1))
        def baseAim = fighter.getAim()

        when:
        extraAim.apply(fighter)

        then:
        fighter.getAim() > baseAim
    }

    def "test ExtraHealth"(){
        given:
        def extraHealth = new ExtraHealth(new Position(1,1))
        def baseHealth = fighter.getHealth()

        when:
        extraHealth.apply(fighter)

        then:
        fighter.getHealth() > baseHealth
    }

    def "test ExtraDamage"(){
        given:
        def extraDamage = new ExtraDamage(new Position(1,1))
        def baseDamage = fighter.getDamage()

        when:
        extraDamage.apply(fighter)

        then:
        fighter.getDamage() > baseDamage
    }
}
