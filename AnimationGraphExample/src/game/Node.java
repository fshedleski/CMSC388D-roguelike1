package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Node{
    ArrayList<BufferedImage> images;
    int frame;
    Node(ArrayList<BufferedImage> im, int f){
        images = im;
        frame = f;
    }

    public Node() {

    }
}