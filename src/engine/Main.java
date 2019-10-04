package engine;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static final String MAP_FILE = "map.txt";

    public static MyGrid myGrid;
    public static int[][] paint;

    public static void main(String Args[]) {
        // build grid
        ArrayList<ArrayList<String>> csvVals = FileIOHelpers.readCSV(MAP_FILE); // read map file
        int SIZE = csvVals.size();
        myGrid = new MyGrid(SIZE);
        paint = new int[SIZE][SIZE*2];

        // paint based on map file
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE*2; c++) {
                paint[r][c] = Integer.parseInt(csvVals.get(r).get(c));
                switch(paint[r][c]) {
                    case -1:
                        myGrid.setColor(r, c, Color.BLUE);
                        break;
                    case 1:
                        myGrid.setColor(r, c, Color.LIGHT_GRAY);
                        break;
                    case 2:
                        myGrid.setColor(r, c, Color.GREEN);
                        break;
                    case 10:
                        myGrid.setColor(r, c, Color.YELLOW);
                        break;
                }
            }
        }



    }
}
