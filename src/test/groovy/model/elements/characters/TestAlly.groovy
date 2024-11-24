package model.elements.characters

import model.elements.Position
import spock.lang.Specification

class TestAlly extends Specification{

    def "Test Ally(Position position) constructor"(){
        given:
        def ally = new Ally(new Position(1, 3))

        when:
        boolean result = ally.getPosition().equals(new Position(1, 3))

        then:
        result == true
        ally.getLevel() == 1
    }

    def "Test Ally(Position position, int level) constructor"(){
        given:
        def ally = new Ally(new Position(1, 3), 4)

        when:
        boolean result = ally.getPosition().equals(new Position(1, 3))

        then:
        ally.getPosition().equals(new Position(1, 3)) == true
        ally.getLevel() == 4
    }

    def "Test level increase"(){
        given:
        def ally = new Ally(new Position(1, 3), 4)

        when:
        ally.increaseLevel()

        then:
        ally.getLevel() == 5
    }
}
