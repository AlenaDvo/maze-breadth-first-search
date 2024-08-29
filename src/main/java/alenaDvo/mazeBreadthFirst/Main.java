package alenaDvo.mazeBreadthFirst;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Maze maze = openTxtMaze("src/test/resources/maze.txt");

        Cell start = maze.findStart();
        Cell destination = maze.findDestination();
        maze.goFromStartToDestination(start, destination);
        System.out.println(destination.getWayCoordinates());
        System.out.println(destination.getWayDirections());
    }

    static Maze openTxtMaze(String fileName) throws IOException {
        return new Maze(new File(fileName));
    }
}