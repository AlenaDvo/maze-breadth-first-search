package alenaDvo.mazeBreadthFirst;

import java.util.stream.Stream;

public enum Direction {
    LEFT(0, -1),
    RIGHT(0, 1),
    UP(-1, 0),
    DOWN(1, 0);

    //UP(0, -1),
//    DOWN(0, 1),
//    LEFT(1, 0),
//    RIGHT(-1, 0);

    Direction(int changeInRows, int changeInColumns) {
        this.changeInRows = changeInRows;
        this.changeInColumns = changeInColumns;
    }

    private final int changeInRows;
    private final int changeInColumns;

    public int getChangeInRows() {
        return changeInRows;
    }

    public int getChangeInColumns() { return changeInColumns; }

    public static Direction getDirection(int rowDifference, int columnDifference) {
            for (Direction direction : Direction.values()) {
                if (direction.getChangeInRows() == rowDifference && direction.getChangeInColumns() == columnDifference) {
                    return direction;
                }
            }
        throw new IllegalArgumentException("Invalid coordinates: (" + rowDifference + ", " + columnDifference + ")");
    }

    public static Stream<Direction> stream() {
        return Stream.of(Direction.values());
    }
}
