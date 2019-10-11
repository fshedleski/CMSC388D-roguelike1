package rl1;

import engine.CollisionHandler;
import engine.Component;
import engine.GameObject;
import engine.Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerComponent extends Component {

    public int prevRow, prevCol;
    public Direction dir;

    public PlayerComponent(GameObject _parent) {
        super(_parent);
        prevRow = parent.row;
        prevCol = parent.col;
        dir = Direction.Up;
    }

    @Override
    // player movement logic
    public void logic() {
        if(Main.frameCounter % 2 == 0) { // only update player every other frame
            if (Main.hidHandler.pending.contains(KeyEvent.VK_W)) {
                this.dir = Direction.Up;
                this.move();
            }
            if (Main.hidHandler.pending.contains(KeyEvent.VK_S)) {
                this.dir = Direction.Down;
                this.move();
            }
            if (Main.hidHandler.pending.contains(KeyEvent.VK_A)) {
                this.dir = Direction.Left;
                this.move();
            }
            if (Main.hidHandler.pending.contains(KeyEvent.VK_D)) {
                this.dir = Direction.Right;
                this.move();
            }

            // Check collision state
            parent.getLogicComponent(CollisionHandler.CollisionHandlerComponent.class).logic();
            if(Main.collisionHandler1.containsCollisionWith(parent)) {
                parent.row = prevRow;
                parent.col = prevCol;
            }

            if (Main.hidHandler.pending.contains(KeyEvent.VK_SPACE)) {
                GameObject bullet = new GameObject(parent.row, parent.col);
                BulletComponent bulletComponent = new BulletComponent(bullet, dir);
                bullet.addComponent(bulletComponent);
                bullet.addLogicComponent(Main.collisionHandler1.newCollisionHandlerComponent(bullet));
            }


        }
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(parent.row - 1 < 0) {
                    parent.row = Main.MAXROW - 1;
                } else {
                    parent.row--;
                }
                break;
            case Down:
                if(parent.row + 1 >= Main.MAXROW) {
                    parent.row = 0;
                } else {
                    parent.row++;
                }
                break;
            case Left:
                if(parent.col - 1 < 0) {
                    parent.col = Main.MAXCOL - 1;
                } else {
                    parent.col--;
                }
                break;
            case Right:
                if(parent.col + 1 >= Main.MAXCOL) {
                    parent.col = 0;
                } else {
                    parent.col++;
                }
                break;
        }
    }

    @Override
    // player visualization logic
    public void graphics() {
        Main.myGrid.setColor(parent.row, parent.col, Color.RED);
        if(!(prevCol == parent.col && prevRow == parent.row)) {
            Main.myGrid.setColor(prevRow, prevCol, Color.LIGHT_GRAY);
            prevRow = parent.row;
            prevCol = parent.col;
        }
    }
}
