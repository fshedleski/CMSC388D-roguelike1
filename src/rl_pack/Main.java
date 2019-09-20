package rl_pack;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int SIZE = 50;
    public static final String FILENAME = "map.txt";

    public static MyGrid gui = new MyGrid(SIZE);
    public static HIDHandler hidHandler = new HIDHandler();
    public static GameObject gameObject = new GameObject();
    public static int[][] map;

    public static void main(String Args[]) {
        gui.addKeyListener(hidHandler);
        //gameObject.addComponenet(new Player());
        ArrayList<ArrayList<String>> csvVals = FileIOHelpers.readCSV(FILENAME);

        // main loop, put update funcs in here
        while(true) {
            gameObject.update();
            try { TimeUnit.MILLISECONDS.sleep(1); } catch (InterruptedException e) {}
        }
    }
}
