package model.elements


import spock.lang.Specification

class TestPosition extends Specification{

    def "test getX"(){
        given:
        Position position = new Position(1,2)

        when:
        int x = position.getX();

        then:
        x == 1;
    }

    def "test getY"(){
        given:
        Position position = new Position(1,2)

        when:
        int y = position.getY();

        then:
        y == 2;
    }

    def "test setX"(){
        given:
        Position position = new Position(1,2)

        when:
        position.setX(3)
        int x = position.getX();

        then:
        x == 3;
    }

    def "test setY"(){
        given:
        Position position = new Position(1,2)

        when:
        position.setY(30)
        int y = position.getY();

        then:
        y == 30;
    }

    def "test equals"(){
        given:
        Position position = new Position(1, 2)
        Position position1 = new Position(1,2)
        Position position2 = new Position(3,4)

        when:
        boolean result = position.equals(position)
        boolean result1 = position.equals(position1)
        boolean result2 = position.equals(position2)

        then:
        result == true
        result1 == true
        result2 == false
    }

}
