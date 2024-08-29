package alenaDvo.mazeBreadthFirst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MazeTest {

    Maze maze;

    @BeforeEach
    void before() throws IOException {
        maze = Main.openTxtMaze("src/test/resources/maze.txt");
    }

    @Test
    void getCell() {
        Coordinates coordinates = new Coordinates(0, 1);
        assertThat(maze.getCell(coordinates)).isSameAs(maze.getCell(coordinates));
        assertThat(maze.getCell(coordinates).isStart()).isTrue();
    }

    @Test
    void areCoordinatesInBounds() {
        assertThat(maze.areCoordinatesInBounds(new Coordinates(0, 1))).isTrue(); // are in bounds
        assertThat(maze.areCoordinatesInBounds(new Coordinates(-100, 0))).isFalse(); // are not in bounds
        assertThat(maze.areCoordinatesInBounds(new Coordinates(0, -100))).isFalse(); // are not in bounds
    }

    @Test
    void findStart() {
        assertThat(maze.findStart().getCoordinates()).satisfies(c -> {
            assertThat(c.row()).isEqualTo(0);
            assertThat(c.column()).isEqualTo(1);
        });
        assertThat(maze.findStart().getDistanceFromStart()).isEqualTo(0);
    }

    @Test
    void findDestination() {
        assertThat(maze.findDestination().getCoordinates()).satisfies(c -> {
            assertThat(c.row()).isEqualTo(1);
            assertThat(c.column()).isEqualTo(3);
        });
    }
}