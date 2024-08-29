package alenaDvo.mazeBreadthFirst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CellTest {

    Maze maze;
    Cell destination;
    Cell start;
    Cell free;

    @BeforeEach
    void before() throws IOException {
        maze = Main.openTxtMaze("src/test/resources/maze.txt");
        destination = maze.findDestination();
        start = maze.findStart();
        free = maze.getCell(new Coordinates(1, 0));
    }

    @Test
    void isDestination() {
        assertThat(destination.isDestination()).isTrue();
        assertThat(start.isDestination()).isFalse();
    }

    @Test
    void isStart() {
        assertThat(start.isStart()).isTrue();
        assertThat(destination.isStart()).isFalse();
    }

    @Test
    void isFree() {
        assertThat(free.isPassable()).isTrue();
        assertThat(start.isPassable()).isFalse();
    }

    @Test
    void getAllNeighbours() {
        Cell first = maze.getCell(new Coordinates(0, 0));
        Cell second = maze.getCell(new Coordinates(1, 1));
        Cell third = maze.getCell(new Coordinates(0, 2));

        assertThat(start.getAllNeighbours()).contains(first, second, third);
    }

    @Test
    void getAllFreeNeighbours() {
        Cell first = maze.getCell(new Coordinates(0, 0));

        assertThat(start.getAllPassableNeigbours()).contains(first);
    }

    @Test
    void getWay() {
        maze.goFromStartToDestination(start, destination);
        assertThat(destination.getWayCoordinates()).containsExactly(
                new Coordinates(0, 0),
                new Coordinates(1, 0),
                new Coordinates(2, 0),
                new Coordinates(3, 0),
                new Coordinates(3, 1),
                new Coordinates(3, 2),
                new Coordinates(3, 3),
                new Coordinates(2, 3),
                new Coordinates(1, 3));
    }
}