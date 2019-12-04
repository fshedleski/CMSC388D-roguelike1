package engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Human-Interface-Device (HID) Handler
// currently only implements keyboard listening, in the future will implement mouse listening
public class HIDHandler implements KeyListener {
    private ArrayList<Integer> pressed;
    public ArrayList<Integer> pending;

    public HIDHandler(JFrame frame) {
        pressed = new ArrayList<Integer>();
        pending = new ArrayList<Integer>();
        frame.addKeyListener(this);
    }

    // call every other frame to clear pending of non pressed keys.
    // this delayed removal ensures that key presses dont get missed
    // by things with differing update rates
    public void UpdatePending() {
        ArrayList<Integer> temp = new ArrayList<Integer>(pending);

        for(Integer keyCode : pending) {
            if (!pressed.contains(keyCode)) {
                temp.remove(keyCode);
            }
        }

        pending = temp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // dont use
    }

    // activated on key-down
    @Override
    public void keyPressed(KeyEvent e) {
        // add to pending and pressed
        if (!pending.contains(e.getKeyCode())) {
            pending.add(e.getKeyCode());
        }

        if (!pressed.contains(e.getKeyCode())) {
            pressed.add(e.getKeyCode());
        }
    }

    // activated on key-up
    @Override
    public void keyReleased(KeyEvent e) {
        // remove from only pressed
        if (pressed.contains(e.getKeyCode())) {
            pressed.remove(new Integer(e.getKeyCode()));
        }
    }
}
