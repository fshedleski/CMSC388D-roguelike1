package game;

import engine.CollisionHandler;
import engine.Component;
import engine.EngineCore;
import engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class AnimationComp extends Component {
    public class Edge{
        boolean e;
        int change;
        Edge(boolean ed, int c){
            e = ed;
            change = c;
        }
    }


    private ArrayList<Node> nodes;
    private Edge adjMatrix[][];
    private int numVertices;
    private Node current;
    private int Nodeind = 0;
    int changer = 0;
    private ArrayList<Integer> changes = new ArrayList<Integer>();
    AffineTransform afBody;
    private int animateCounter = 0;
    Direction dir;
    private boolean moving = false;

    private double translateX = 0;
    private double translateY = 0;

    public AnimationComp(GameObject _parent) {
        super(_parent);

        afBody = new AffineTransform();
        afBody.setToIdentity();
        afBody.scale(1, 1);
        dir = Direction.Down;
    }

    public void addNodes(ArrayList<Node> nodearr){
        this.numVertices = nodearr.size();
        current = nodearr.get(Nodeind);
        nodes = nodearr;
        adjMatrix = new Edge[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjMatrix[i][j] = new Edge(false, 0);
            }
        }
    }

    public void addUnDirEdge(int i, int j, int c) {
        adjMatrix[i][j] = new Edge(true, c);
        adjMatrix[j][i] = new Edge(true, c);
        changes.add(c);
    }

    public void addDirEdge(int i, int j, int c){
        adjMatrix[i][j] = new Edge(true, c);
        changes.add(c);
    }

    //if boool = true then when no input, snaps back to nodes[0] otherwise it doesnt
    public void whenNotMoving(boolean bool){
        moving = bool;
    }

    public void removeUnDirEdge(int i, int j) {
        adjMatrix[i][j] = new Edge(false, 0);
        adjMatrix[j][i] = new Edge(false, 0);
    }

    public void removeDirEdge(int i, int j) {
        adjMatrix[i][j] = new Edge(false, 0);
    }

    public boolean isEdge(int i, int j) {
        return adjMatrix[i][j].e;
    }

    public void setTranslate(double x, double y){
        translateX = x;
        translateY = y;
    }

    @Override
    // player visualization logic
    public void graphics(Graphics2D g) {
        boolean isMoving = false;
        for(int a = 0; a < changes.size() - 1; a ++){
            if(EngineCore.inputs.pending.contains(changes.get(a))){
                changer = changes.get(a);
                isMoving = true;
            }
        }

        for(int i = 0; i<numVertices;i++){
            //updates current node to match after a key press
            if(adjMatrix[Nodeind][i].change == changer){
                Nodeind = i;
                current = nodes.get(Nodeind);
            }
        }

        if(moving && !isMoving){
            current = nodes.get(0);
            animateCounter = 0;
        }

        if (animateCounter <= current.images.size() && isMoving && EngineCore.FCount % current.frame == 0) {
            animateCounter = (animateCounter + 1) % current.images.size();
        }


        Image im;
        im = current.images.get(animateCounter);
        this.afBody.setToTranslation(this.parent.af.getTranslateX() + translateX, this.parent.af.getTranslateY() + translateY);
        g.drawImage(im, this.afBody, (ImageObserver)null);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ": ");
            for (int j = 0; j < numVertices; j++) {
                s.append((adjMatrix[i][j].e?1:0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
