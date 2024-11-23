package model.elements;

//Abstract class for all elements: characters and obstacles
public abstract class Elements {
    private Position position;

    public Elements(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
