package alenaDvo.mazeBreadthFirst;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Cells implements Iterable<Cell> {
    private Set<Cell> cells;

    public Cells(Set<Cell> cells) {
        this.cells = cells;
    }

    public Cells() {
        cells = new HashSet<>();
    }

    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }

    public Cells getAllPassableNeighbours() {
        Cells passableNeighbours = new Cells();
        for (Cell cell : this) {
            passableNeighbours.add(cell.getAllPassableNeigbours());
        }
        return passableNeighbours;
    }

    public void add(Cells newCells) {
        cells.addAll(newCells.cells);
    }

    public void setDistanceFromStart(int distanceFromStart) {
        for (Cell cell : this) {
            cell.setDistanceFromStart(distanceFromStart);
        }
    }

    @Override
    public Iterator<Cell> iterator() {
        return cells.stream().iterator();
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public String toString() {
        return "Cells{" +
                "cells=" + cells +
                '}';
    }

    public Stream<Cell> stream() {
        return cells.stream();
    }
}