package model.elements.characters

import model.elements.Position
import spock.lang.Specification

class TestPlayer extends Specification{

    def "Test Player(Position position) constructor"(){
        given:
        def player = new Player(new Position(1, 2))

        when:
        boolean result = player.getPosition().equals(new Position(1, 2))

        then:
        result == true
        player.getLevel() == 1
    }

    def "Test Player(Position position, int level) constructor"(){
        given:
        def player = new Player(new Position(1, 2), 4)

        when:
        boolean result = player.getPosition().equals(new Position(1, 2))

        then:
        result == true
        player.getLevel() == 4
    }

    def "Test level increase"(){
        given:
        def player = new Player(new Position(1, 2), 4)

        when:
        player.increaseLevel()

        then:
        player.getLevel() == 5
    }
}
