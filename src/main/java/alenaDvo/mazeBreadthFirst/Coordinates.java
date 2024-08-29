package alenaDvo.mazeBreadthFirst;

public record Coordinates(int row, int column) {

    @Override
    public String toString() {
        return "Coordinates: " +
                "row = " + row +
                ", column = " + column +
                " ";
    }
}