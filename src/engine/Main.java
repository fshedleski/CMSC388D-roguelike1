package engine;

import rl1.EnemyComponent;
import rl1.ObstacleComponenet;
import rl1.PlayerComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final int SIZE = 50;
    public static final int MAXROW = SIZE;
    public static final int MAXCOL = 2 * SIZE;

    public static MyGrid myGrid;
    public static HIDHandler hidHandler;
    public static CollisionHandler collisionHandler1;
    public static ArrayList<GameObject> gameObjects;
    public static int[][] obstacleGrid;

    public static int frameCounter;

    public static void main(String Args[]) {
        // build grid
        myGrid = new MyGrid(SIZE);
        hidHandler = new HIDHandler();
        myGrid.addKeyListener(hidHandler);
        obstacleGrid = new int[MAXROW][MAXCOL];
        for(int r = 0; r < MAXROW; r++) {
            for(int c = 0; c < MAXCOL; c++) {
                obstacleGrid[r][c] = 0;
            }
        }

        // build game objects
        collisionHandler1 = new CollisionHandler();
        gameObjects = new ArrayList<GameObject>();
        GameObject player = new GameObject(MAXROW-1, 0);
        GameObject enemy1 = new GameObject(0, MAXCOL-1);

        // Generate Obstacles
        ArrayList<int[]> pairs = new ArrayList<>(MAXROW*MAXCOL-2);
        for(int r = 0; r < MAXROW; r++) {
            for(int c = 0; c < MAXCOL; c++) {
                if((r == MAXROW-1 && c == 0) ||
                        (r == 0 && c == MAXCOL-1)) {
                    continue;
                }
                int tmp[] = {r, c};
                pairs.add(tmp);
            }
        }
        Collections.shuffle(pairs);
        for(int i = 0; i < (int)(MAXROW*MAXCOL*0.1); i++) {
            GameObject obstacle = new GameObject(pairs.get(i)[0], pairs.get(i)[1]);
            obstacle.addGraphicsComponent(new ObstacleComponenet(obstacle));
            obstacle.addLogicComponent(collisionHandler1.newCollisionHandlerComponent(obstacle));
            obstacleGrid[pairs.get(i)[0]][pairs.get(i)[1]] = -1;
        }

        //Test Component add/remove/get here --------------------
        GameObject test   = new GameObject(0,0);
        test.addGraphicsComponent(new PlayerComponent(test));
        test.getGraphicsComponent(PlayerComponent.class);
        test.removeGraphicsComponent(PlayerComponent.class);
        //-------------------------------------------------------

        // Attach player components
        player.addComponent(new PlayerComponent(player));
        player.addLogicComponent(collisionHandler1.newCollisionHandlerComponent(player));
        //Add Component here-------------------------------------




        //-------------------------------------------------------

        // Attach enemy components
        enemy1.addComponent(new EnemyComponent(enemy1));
        enemy1.addLogicComponent(collisionHandler1.newCollisionHandlerComponent(enemy1));



        // main loop
        frameCounter = 0;
        while(true) {
            // update frame counter
            frameCounter = (frameCounter + 1) % 2;

            // dupe game objects so that update state is consistent regardless of update order
            ArrayList<GameObject> newGameObjects = new ArrayList(gameObjects);

            // do logic updates
            for(GameObject gameObject : newGameObjects) {
                gameObject.logic();
            }

            // do graphic updates
            for(GameObject gameObject : newGameObjects) {
                gameObject.graphics();
            }

            // update the pending/pressed key lists like sina reccommended
            hidHandler.UpdatePending();

            // clear the collision lists to prepare for next frame
            collisionHandler1.clearCollisionPairs();

            try {
                TimeUnit.MILLISECONDS.sleep(40); // this is honestly an arbitrary number that just makes the frame rate feel right
            } catch (InterruptedException ie) {}
        }
    }
}
