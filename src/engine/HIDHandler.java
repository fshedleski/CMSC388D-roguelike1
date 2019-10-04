package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Human-Interface-Device (HID) Handler
// currently only implements keyboard listening, in the future will implement mouse listening
public class HIDHandler implements KeyListener {
    public ArrayList<Integer> pressed;

    public HIDHandler() {
        pressed = new ArrayList<Integer>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // dont use
    }

    // activated on key-down
    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add((e.getKeyCode()));
    }

    // activated on key-up
    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove((Integer)e.getKeyCode());
    }
}
