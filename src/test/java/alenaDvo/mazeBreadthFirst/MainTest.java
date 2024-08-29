package alenaDvo.mazeBreadthFirst;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class MainTest {

    @Test
    void openTxtMaze() throws IOException {
        Maze maze = Main.openTxtMaze("src/test/resources/maze.txt");
        System.out.println(maze);
    }
}