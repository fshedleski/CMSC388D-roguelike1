package game_pack;

import engine.Component;
import engine.GameObject;
import engine.Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerComponent extends Component {

    public int row, col;
    public Direction dir;

    public PlayerComponent(GameObject _parent) {
        super(_parent);
        row = parent.row;
        col = parent.col;
        dir = Direction.Left;
    }

    @Override
    // player movement logic
    public void logic() {
        if(Main.hidHandler.pending.contains(KeyEvent.VK_W)) {
            this.dir = Direction.Up;
            this.move();
        }
        if(Main.hidHandler.pending.contains(KeyEvent.VK_S)) {
            this.dir = Direction.Down;
            this.move();
        }
        if(Main.hidHandler.pending.contains(KeyEvent.VK_A)) {
            this.dir = Direction.Left;
            this.move();
        }
        if(Main.hidHandler.pending.contains(KeyEvent.VK_D)) {
            this.dir = Direction.Right;
            this.move();
        }
        if(Main.hidHandler.pending.contains(KeyEvent.VK_SPACE)) {
            // TODO make bullet here
        }

        // TODO check collision somewhere
    }

    private void move() {
        move(this.dir);
    }
    private void move(Direction _dir) {
        switch(_dir) {
            case Up:
                if(this.row - 1 < 0) {
                    this.row = Main.MAXROW - 1;
                } else {
                    this.row--;
                }
                break;
            case Down:
                if(this.row + 1 >= Main.MAXROW) {
                    this.row = 0;
                } else {
                    this.row++;
                }
                break;
            case Left:
                if(this.col - 1 < 0) {
                    this.col = Main.MAXCOL - 1;
                } else {
                    this.col--;
                }
                break;
            case Right:
                if(this.col + 1 >= Main.MAXCOL) {
                    this.col = 0;
                } else {
                    this.col++;
                }
                break;
        }
    }

    @Override
    // player visualization logic
    public void graphic() {

    }
}
