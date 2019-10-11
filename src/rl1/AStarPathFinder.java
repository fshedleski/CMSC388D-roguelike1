package rl1;

import engine.Main;

import java.util.*;

public class AStarPathFinder {

    static class Cell{
        int finalCost = 0;
        int i, j;
        Cell parent;
        int weight = 0;

        Cell(int i, int j){
            this.i = i;
            this.j = j;
        }
        @Override
        public String toString(){
            return "["+this.i+", "+this.j+"]";
        }
    }

    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[Main.MAXROW][Main.MAXCOL];

    static PriorityQueue<Cell> open;

    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;

    public static void setWeight(int i, int j, int w){
        grid[i][j].weight = w;
    }

    public static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }

    public static void setEndCell(int i, int j){
        endI = i;
        endJ = j;
    }

    static int manhattan(Cell _current) {
        return Math.abs(_current.i-endI) + Math.abs(_current.j-endJ);
    }

    static void loadGridCosts() {

    }

    static int cost(Cell current) {
        if(current.parent == null) {
            return current.weight;
        } else {
            return current.weight + cost(current.parent);
        }
    }

    static void checkAndUpdateCost(Cell current, Cell t){
        if(t.weight == -1 || closed[t.i][t.j])return;
        //heurtistic and weight added to parent cost
        int t_final_cost = manhattan(t) + cost(current) + t.weight;

        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.finalCost){
            t.finalCost = t_final_cost;
            t.parent = current;
            if(!inOpen)open.add(t);
        }
    }

    public static LinkedList<Cell> AStarTrack(){
        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Cell current;

        while(true){
            current = open.poll();
            if(current == null || current.weight == -1) {
                return null;
            }
            closed[current.i][current.j]=true;

            if(current.equals(grid[endI][endJ])){
                LinkedList<Cell> ret = new LinkedList<Cell>();
                while(current != null) {
                    ret.addFirst(current);
                    current = current.parent;
                }
                return ret;
            }

            Cell t;
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                checkAndUpdateCost(current, t);
            }

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                checkAndUpdateCost(current, t);
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t);
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t);
            }
        }
    }

    public static LinkedList<Cell> findPath(int[][] _grid, int si, int sj, int ei, int ej){
        int x = _grid.length;
        int y = _grid[0].length;
        grid = new Cell[Main.MAXROW][Main.MAXCOL];
        for(int r = 0; r < x; r++) {
            for(int c = 0; c < y; c++) {
                grid[r][c] = new Cell(r, c);
                grid[r][c].weight = _grid[r][c];
            }
        }
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell)o1;
            Cell c2 = (Cell)o2;

            return c1.finalCost<c2.finalCost?-1:
                    c1.finalCost>c2.finalCost?1:0;
        });

        //Set start position
        setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(ei, ej);

        // Set inital cell cost
        grid[si][sj].finalCost = grid[si][sj].weight;

        // run A*
        return AStarTrack();
    }
}
