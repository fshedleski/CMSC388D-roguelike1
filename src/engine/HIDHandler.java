package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Human-Interface-Device (HID) Handler
// currently only implements keyboard listening, in the future will implement mouse listening
public class HIDHandler implements KeyListener {
    private ArrayList<Integer> pressed;

    public HIDHandler() {
        pressed = new ArrayList<Integer>();
    }

    public boolean hasKeyBeenPressed() {
        return !pressed.isEmpty();
    }

    public Integer getPressedKey() {
        if(!pressed.isEmpty()) {
            return pressed.remove(0);
        } else {
            return null;
        }
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
        pressed.remove((Object)e.getKeyCode());
    }
}
