package rl1;

import engine.CollisionHandler;
import engine.Component;
import engine.GameObject;
import engine.Main;

import java.awt.*;

public class BulletComponent extends Component {

    public int prevRow, prevCol;
    public Direction dir;
    public boolean isAlive;

    public BulletComponent(GameObject _parent, Direction _dir) {
        super(_parent);
        dir = _dir;
        isAlive = true;
        move(); // handles initially placing the bullet 1 square away from the player
        prevRow = parent.row;
        prevCol = parent.col;
        if(Main.obstacleGrid[prevRow][prevCol] != 0) { destroy(); }
        Main.myGrid.setColor(parent.row, parent.col, Color.BLUE);
    }

    @Override
    // player movement logic
    public void logic() {
        if(isAlive) {
            move();
            // Check collision state
            collisionLogic();
        }
        if(isAlive) {
            move();
            collisionLogic();
        }
        if(!isAlive) {
            destroy();
        }
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(parent.row - 1 < 0) {
                    isAlive = false;
                    destroy();
                } else {
                    parent.row--;
                }
                break;
            case Down:
                if(parent.row + 1 >= Main.MAXROW) {
                    isAlive = false;
                    destroy();
                } else {
                    parent.row++;
                }
                break;
            case Left:
                if(parent.col - 1 < 0) {
                    isAlive = false;
                    destroy();
                } else {
                    parent.col--;
                }
                break;
            case Right:
                if(parent.col + 1 >= Main.MAXCOL) {
                    isAlive = false;
                    destroy();
                } else {
                    parent.col++;
                }
                break;
        }
    }

    private void collisionLogic() {
        parent.getLogicComponent(CollisionHandler.CollisionHandlerComponent.class).logic();
        CollisionHandler.CollisionPair pair = Main.collisionHandler1.getCollisionWith(parent);
        if(pair != null) {
            if(pair.containsComponentClass(EnemyComponent.class)) {
                EnemyComponent enemy = ((EnemyComponent)pair.getComponentClass(EnemyComponent.class));
                if(enemy.isAlive) {
                    enemy.isAlive = false;
                    enemy.destroy();
                }
            }
            isAlive = false;
            destroy();
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
        if(isAlive) {
            Main.myGrid.setColor(parent.row, parent.col, Color.BLUE);
            if (!(prevCol == parent.col && prevRow == parent.row)) {
                Main.myGrid.setColor(prevRow, prevCol, Color.LIGHT_GRAY);
                prevRow = parent.row;
                prevCol = parent.col;
            }
        } else {
            Main.myGrid.setColor(parent.row, parent.col, Color.LIGHT_GRAY);
        }
    }
}
