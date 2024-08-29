package alenaDvo.mazeBreadthFirst;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Maze implements Iterable<Cell> {

    private final Map<Coordinates, Cell> map;
    private final int maxRows;
    private final int maxColumns;

    public Maze(File input) throws IOException {
        try (InputStream in = new FileInputStream(input);
             Reader reader = new InputStreamReader(in);
             BufferedReader buffer = new BufferedReader(reader)) {
            String line = buffer.readLine();

            maxColumns = line.length();

            int row = 0;
            map = new HashMap<>();
            while (line != null) {
                for (int column = 0; column < maxColumns; column++) {
                    Coordinates coordinates = new Coordinates(row, column);
                    Cell cell = new Cell(coordinates, toType(line.charAt(column)), this);

                    map.put(coordinates, cell);
                }
                row++;
                line = buffer.readLine();
            }
            maxRows = row;
        }
    }

    private Cell.Type toType(char c) throws IOException {
        return switch (c) {
            case 'S' -> Cell.Type.START;
            case 'X' -> Cell.Type.DEST;
            case '+' -> Cell.Type.FREE;
            case '*' -> Cell.Type.WALL;
            default -> throw new IOException("Unexpected character: " + c);
        };
    }

    @Override
    public Iterator<Cell> iterator() {
        return map.values().iterator();
    }

    @Override
    public String toString() {
        String mazeToString = "";
        for (int y = 0; y < maxColumns; y++) {
            for (int x = 0; x < maxRows; x++) {
                mazeToString = mazeToString + this.map.get(new Coordinates(x, y)).getType() + " ";
            }
            mazeToString = mazeToString + "\n";
        }
        return mazeToString;
    }

    public Cell getCell(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean areCoordinatesInBounds(Coordinates coordinates) {
        return coordinates.row() >= 0 && coordinates.row() < getMaxRows() && coordinates.column() >= 0 && coordinates.column() < getMaxColumns();
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public Cell findStart() {
        for (Cell cell : this) {
            if (cell.isStart()) {
                cell.setDistanceFromStart(0);
                return cell;
            }
        }
        throw new IllegalStateException("No start found, invalid maze.");
    }

    public Cell findDestination() {
        for (Cell cell : this) {
            if (cell.isDestination()) {
                return cell;
            }
        }
        throw new IllegalStateException("No destination found, invalid maze.");
    }

    public void goFromStartToDestination(Cell start, Cell destination) {
        int distance = 0;
        Cells passableNeighbours = new Cells(Set.of(start));
        passableNeighbours.setDistanceFromStart(distance);


        while (!passableNeighbours.contains(destination)) {
            passableNeighbours = passableNeighbours.getAllPassableNeighbours();
            passableNeighbours.setDistanceFromStart(++distance);
        }
    }
}