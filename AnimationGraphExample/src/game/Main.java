package game;

import engine.EngineCore;
import engine.GameObject;
import engine.ResourceNotFound;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

// GROUP
// Franklin Shedleski
// Dennis Dao
// McCauley Peters -- this is my part of the project
// Dan Song

public class Main {

    private static String assetsDirectory = "Assets\\";

    private static EngineCore core = new EngineCore(768, 1366.0/768, 1, "Rogue-like 2", assetsDirectory);

    public static void main(String args[]) throws ResourceNotFound {
        // make background object
        GameObject background = new GameObject(core);
        BackgroundGComp bgGComp = new BackgroundGComp(background);
        bgGComp.Priority = 0;
        background.addGraphicsComponent(bgGComp);

        // make player
        GameObject player = new GameObject(core, 683, 389);
        AnimationComp playerGComp = new AnimationComp(player);
        String assetsPlayerSheet = "boiS.png";
        ArrayList<BufferedImage> pImages;
        ArrayList<Node> playerNodesToAdd = new ArrayList<>();
        //stationary
        pImages = new ArrayList<BufferedImage>(EngineCore.assetsCenter.getImageList(assetsPlayerSheet).subList(8,9));
        playerNodesToAdd.add(new Node(pImages, 10));
        //up
        pImages = new ArrayList<BufferedImage>(EngineCore.assetsCenter.getImageList(assetsPlayerSheet).subList(8,17));
        playerNodesToAdd.add(new Node(pImages, 10));
        //down
        pImages = new ArrayList<BufferedImage>(EngineCore.assetsCenter.getImageList(assetsPlayerSheet).subList(8,17));
        playerNodesToAdd.add(new Node(pImages, 10));
        //right
        pImages = new ArrayList<BufferedImage>(EngineCore.assetsCenter.getImageList(assetsPlayerSheet).subList(18,27));
        playerNodesToAdd.add(new Node(pImages, 10));
        //left
        pImages = new ArrayList<BufferedImage>(EngineCore.assetsCenter.getImageList(assetsPlayerSheet).subList(28,37));
        playerNodesToAdd.add(new Node(pImages, 10));
        playerGComp.addNodes(playerNodesToAdd);

        //stat to up
        playerGComp.addDirEdge(0, 1, 87);
        //stat to down
        playerGComp.addDirEdge(0, 2, 83);
        //stat to right
        playerGComp.addDirEdge(0, 3, 68);
        //stat to left
        playerGComp.addDirEdge(0, 4, 65);
        //up to right
        playerGComp.addDirEdge(1, 3, 68);
        //up to left
        playerGComp.addDirEdge(1, 4, 65);
        //up to down
        playerGComp.addDirEdge(1,2,83);
        //down to left
        playerGComp.addDirEdge(2, 4, 65);
        //down to right
        playerGComp.addDirEdge(2, 3, 68);
        //down to up
        playerGComp.addDirEdge(2,1,87);
        //left to right
        playerGComp.addDirEdge(4, 3, 68);
        //left to down
        playerGComp.addDirEdge(4,2,83);
        //left to up
        playerGComp.addDirEdge(4,1,87);
        //right to left
        playerGComp.addDirEdge(3,4,65);
        //right to down
        playerGComp.addDirEdge(3,2,83);
        //right to up
        playerGComp.addDirEdge(3,1,87);
        playerGComp.whenNotMoving(true);
        playerGComp.setTranslate(10.0D,42.0D);
        playerGComp.Priority = 2;

        PlayerLComponent playerLComp = new PlayerLComponent(player);
        playerLComp.Priority = 2;

        GunGComponent gunGComp = new GunGComponent(player);
        gunGComp.Priority = 3;

        GunLComponent gunLComp = new GunLComponent(player);
        gunLComp.Priority = 3;

        player.addLogicComponent(playerLComp);
        player.addLogicComponent(gunLComp);
        player.addGraphicsComponent(playerGComp);
        player.addGraphicsComponent(gunGComp);

        // run
        core.start();
    }
}
