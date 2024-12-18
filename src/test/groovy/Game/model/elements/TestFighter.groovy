package Game.model.elements


import Game.model.elements.fighter.Player
import spock.lang.Specification

class TestFighter extends Specification {
    //Since fighter is an abstract class, the class player, which inherits its functions will be used in its place
    def fighter
    def baseHealth

    def setup(){
        fighter = new Player(new Position(1,1))
        baseHealth = fighter.getHealth()
    }

    def "test sufferDamage"(){
        when:
        fighter.sufferDamage(dam)

        then:
        fighter.getHealth() == baseHealth - dam

        where:
        dam << [0, 1, 5, 10, 20, 21, 25, 30]
    }

    def "test isDead"(){
        when:
        fighter.sufferDamage(dam)

        then:
        fighter.isDead() == (baseHealth - dam <= 0)

        where:
        dam << [0, 1, 5, 10, 20, 21, 25, 30]
    }

    def "test increaseHealth"(){

        when:
        fighter.increaseHealth(bonus)

        then:
        fighter.getHealth() == baseHealth + bonus

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25]
    }

    def "test increaseAim"(){
        given:
        def baseAim = fighter.getAim()

        when:
        fighter.increaseAim(bonus)

        then:
        fighter.getAim() == baseAim + bonus

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25]
    }

    def "test increaseDamage"(){
        given:
        def baseDamage = fighter.getDamage()

        when:
        fighter.increaseDamage(bonus)

        then:
        fighter.getDamage() == baseDamage + bonus

        where:
        bonus << [0, 1, 5, 10, 20, 21, 25]
    }

    def "test getUp"(){
        expect:
        fighter.getUp().equals(new Position(fighter.getPosition().getX(), fighter.getPosition().getY() - 1))
    }

    def "test getDown"(){
        expect:
        fighter.getDown().equals(new Position(fighter.getPosition().getX(), fighter.getPosition().getY() + 1))
    }

    def "test getRight"(){
        expect:
        fighter.getRight().equals(new Position(fighter.getPosition().getX() + 1, fighter.getPosition().getY()))
    }

    def "test getLeft"(){
        expect:
        fighter.getLeft().equals(new Position(fighter.getPosition().getX() - 1, fighter.getPosition().getY()))
    }
}
