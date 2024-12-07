package model.elements.characters

import model.elements.Position
import spock.lang.Specification

class TestEnemy extends Specification{

    def "Test Enemy(Position position) constructor"(){
        given:
        def enemy = new Enemy(new Position(1, 2))

        when:
        boolean result = enemy.getPosition().equals(new Position(1, 2))

        then:
        result == true
        enemy.getLevel() == 1
    }

    def "Test Enemy(Position position, int level) constructor"(){
        given:
        def enemy = new Enemy(new Position(1, 2), 4)

        when:
        boolean result = enemy.getPosition().equals(new Position(1, 2))

        then:
        result == true
        enemy.getLevel() == 4
    }

    def "Test level increase"(){
        given:
        def enemy = new Enemy(new Position(1, 2), 4)

        when:
        enemy.increaseLevel()

        then:
        enemy.getLevel() == 5
    }
}
