package rl1;

import engine.CollisionHandler;
import engine.Component;
import engine.GameObject;
import engine.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class EnemyComponent extends Component {

    public int prevRow, prevCol;
    public int destRow, destCol;
    public boolean isAlive;
    public LinkedList<AStarPathFinder.Cell> path;

    public EnemyComponent(GameObject _parent) {
        super(_parent);
        prevRow = parent.row;
        prevCol = parent.col;
        isAlive = true;
        genRandomDest();
    }

    @Override
    // player movement logic
    public void logic() {
        if(Main.frameCounter % 2 == 0) { // only update enemy every other frame
            // check for 'h' and move is needed
            boolean hold = false;
            if (Main.hidHandler.pending.contains(KeyEvent.VK_H)) {
                hold = true;
            }
            if(!hold) {
                move();
            }

            // Check collision state
            parent.getLogicComponent(CollisionHandler.CollisionHandlerComponent.class).logic();
            CollisionHandler.CollisionPair pair = Main.collisionHandler1.getCollisionWith(parent);
            if(pair != null) {
                if(pair.containsComponentClass(BulletComponent.class)) {
                    isAlive = false;
                    destroy();
                } else if(pair.containsComponentClass(PlayerComponent.class)) {
                    // TODO handle collision with player
                }
            }

            // gen new destination if needed
            if(parent.row == destRow && parent.col == destCol) {
                genRandomDest();
            }
        }
    }

    private void move() {
        parent.row = path.getFirst().i;
        parent.col = path.getFirst().j;
        path.remove(0);
    }

    private void genRandomDest() {
        path = null;
        while(path == null) {
            destRow = (int) (Math.random() * Main.MAXROW + 1);
            destCol = (int) (Math.random() * Main.MAXCOL + 1);
            path = AStarPathFinder.findPath(Main.obstacleGrid, parent.row, parent.col, destRow, destCol);
        }
    }

    public void destroy() {
        Main.gameObjects.remove(parent);
        Main.collisionHandler1.removeComponentHandler(parent);
        Main.myGrid.setColor(parent.row, parent.col, Color.LIGHT_GRAY);
        if (!(prevCol == parent.col && prevRow == parent.row)) {
            Main.myGrid.setColor(prevRow, prevCol, Color.LIGHT_GRAY);
            prevRow = parent.row;
            prevCol = parent.col;
        }
    }

    @Override
    // player visualization logic
    public void graphics() {
        Main.myGrid.setColor(parent.row, parent.col, Color.GRAY);
        if(!(prevCol == parent.col && prevRow == parent.row)) {
            Main.myGrid.setColor(prevRow, prevCol, Color.LIGHT_GRAY);
            prevRow = parent.row;
            prevCol = parent.col;
        }
    }
}
