package alenaDvo.mazeBreadthFirst;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The default equals and hashCode implementations are sufficient since the maze guarantees to always return the same
 * cell instance for same coordinates.
 * Only Maze is creating new Cell instances.
 */
public class Cell {
    private final Coordinates coordinates;

    private final Type type;

    private final Maze maze;

    public enum Type {
        START,
        DEST,
        FREE,
        WALL;
    }

    private static final int UNKNOWN = -1;

    private int distanceFromStart = UNKNOWN;

    Cell(Coordinates coordinates, Type type, Maze maze) {
        this.coordinates = coordinates;
        this.type = type;
        this.maze = maze;
    }

    public Type getType() {
        return type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public boolean isDestination() {
        return type == Type.DEST;
    }

    public boolean isStart() {
        return type == Type.START;
    }

    public boolean isPassable() {
        return type != Type.WALL && distanceFromStart == UNKNOWN;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public Cells getAllNeighbours() {
        Set<Cell> setOfNeighbours = Direction.stream()
                .map(d -> new Coordinates(coordinates.row() + d.getChangeInRows(), coordinates.column() + d.getChangeInColumns()))
                .filter(maze::areCoordinatesInBounds)
                .map(maze::getCell)
                .collect(Collectors.toSet());
        return new Cells(setOfNeighbours);
    }

    public Cells getAllPassableNeigbours() {
        Set<Cell> setOfFreeNeighbours = getAllNeighbours().stream()
                .filter(Cell::isPassable)
                .collect(Collectors.toSet());
        return new Cells(setOfFreeNeighbours);
    }

    public List<Coordinates> getWayCoordinates() {
        if (getDistanceFromStart() == 0) {
            List<Coordinates> way = new LinkedList<>();
            way.add(getCoordinates());
            return way;
        } else {
            for (Cell cell : getAllNeighbours()) {
                if (cell.getDistanceFromStart() == getDistanceFromStart() - 1) {
                    List<Coordinates> way = cell.getWayCoordinates();
                    way.add(getCoordinates());
                    return way;
                }
            }
            throw new IllegalStateException("No way found, maze without solution.");
        }
    }

    public List<Direction> getWayDirections() {
        List<Coordinates> wayCoordinates = getWayCoordinates();
        List<Direction> way = new LinkedList<>();
        int changeInRow;
        int changeInColumn;

        for (int i = 1; i < wayCoordinates.size(); i++) {
            changeInRow = wayCoordinates.get(i).row() - wayCoordinates.get(i - 1).row();
            changeInColumn = wayCoordinates.get(i).column() - wayCoordinates.get(i - 1).column();
            way.add(Direction.getDirection(changeInRow, changeInColumn));
        }
        return way;
    }
}