package com.ldts.elements


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

}
