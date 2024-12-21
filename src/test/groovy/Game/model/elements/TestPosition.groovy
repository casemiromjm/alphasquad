package Game.model.elements


import static java.lang.Math.sqrt

import spock.lang.Specification

class TestPosition extends Specification {

    def "test getX"(){
        given:
        Position position = new Position(1,2)

        when:
        int x = position.getX()

        then:
        x == 1
    }

    def "test getY"(){
        given:
        Position position = new Position(1,2)

        when:
        int y = position.getY()

        then:
        y == 2
    }

    def "test setX"(){
        given:
        Position position = new Position(1,2)

        when:
        position.setX(3)
        int x = position.getX()

        then:
        x == 3
    }

    def "test setY"(){
        given:
        Position position = new Position(1,2)

        when:
        position.setY(30)
        int y = position.getY()

        then:
        y == 30
    }

    def "test equals"(){
        given:
        Position position = new Position(1, 2)
        Position position1 = new Position(1,2)
        Position position2 = new Position(3,4)

        expect:
        position.equals(position)
        position.equals(position1)
        !position.equals(position2)
    }

    def "test getDistance"(Position a, Position b, double dist){
        expect:
        Position.getDistance(a, b) == dist

        where:
        a                   | b                 | dist
        new Position(0,0)   | new Position(0,0)   | 0
        new Position(1, 0)  | new Position(2, 0)  | 1
        new Position(0,1)   | new Position(0, 2)  | 1
        new Position(1, 1)  | new Position(2,2)   | sqrt(2)
        new Position(10, 20)| new Position(5, 10) | sqrt(125)
    }

}
