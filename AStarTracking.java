package project2;

import java.util.*;

public class AStarTracking {
    static int size = 5;
    
    static class Cell{
        int finalCost = 0;
        int i, j;
        Cell parent; 
        int weight = 0;
        
        Cell(int i, int j){
            this.i = i;
            this.j = j; 
        }
        @Override
        public String toString(){
            return "["+this.i+", "+this.j+"]";
        }
    }
    
    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[size][size];
    
    static PriorityQueue<Cell> open;
     
    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;
            
    public static void setWeight(int i, int j, int w){
        grid[i][j].weight = w;
    }
    
    public static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }
    
    public static void setEndCell(int i, int j){
        endI = i;
        endJ = j; 
    }
    
    static int manhattan(Cell current) {
    	return Math.abs(current.i-endI) + Math.abs(current.j-endJ);
    }
    
    static void loadGridCosts() {
    	
    }
    
    static void checkAndUpdateCost(Cell current, Cell t, int cost){
        if(t.weight == -1 || closed[t.i][t.j])return;
        //heurtistic and weight added to parent cost
        int t_final_cost = manhattan(t) + cost + t.weight;
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.finalCost){
            t.finalCost = t_final_cost;
            t.parent = current;
            if(!inOpen)open.add(t);
        }
    }
    
    public static void AStarTrack(){ 
        //add the start location to open list.
        open.add(grid[startI][startJ]);
        
        Cell current;
        
        while(true){ 
            current = open.poll();
            if(current.weight == -1)break;
            closed[current.i][current.j]=true; 

            if(current.equals(grid[endI][endJ])){
                return; 
            } 

            Cell t;  
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                checkAndUpdateCost(current, t, current.finalCost); 
            } 

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                checkAndUpdateCost(current, t, current.finalCost+grid[current.i][current.j-1].weight); 
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t, current.finalCost+grid[current.i][current.j+1].weight); 
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t, current.finalCost+grid[current.i+1][current.j].weight); 
            }
        } 
    }


public static void test(int tCase, int x, int y, int si, int sj, int ei, int ej){
    System.out.println("\n\nTest Case #"+tCase);
     //Reset
    grid = new Cell[x][y];
    closed = new boolean[x][y];
    open = new PriorityQueue<>((Object o1, Object o2) -> {
         Cell c1 = (Cell)o1;
         Cell c2 = (Cell)o2;

         return c1.finalCost<c2.finalCost?-1:
                 c1.finalCost>c2.finalCost?1:0;
     });
    //Set start position
    setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part
    
    //Set End Location
    setEndCell(ei, ej); 
    
    
    /*
      Set blocked cells. Simply set the cell values to null
      for blocked cells.
    */
    for(int i = 0; i < x; i++) {
    	for(int j = 0; j < y; j++) {
    		grid[i][j] = new Cell(i, j);
    		grid[i][j].weight = (int)(Math.random() * 5 + 1);
    	}

//      System.out.println();
    }
    
    grid[si][sj].finalCost = 0;
    
    //Display initial map
    System.out.println("Grid: ");
     for(int i=0;i<x;++i){
         for(int j=0;j<y;++j){
            if(i==si&&j==sj)System.out.print("SO  "); //Source
            else if(i==ei && j==ej)System.out.print("DE  ");  //Destination
            else if(grid[i][j]!=null)System.out.printf("%-3d ", grid[i][j].weight);
            else System.out.print("BL  "); 
         }
         System.out.println();
     } 
     System.out.println();
    
    AStarTrack(); 
    System.out.println("\nScores for cells: ");
    for(int i=0;i<x;++i){
        for(int j=0;j<x;++j){
            if(grid[i][j]!=null)System.out.printf("%-3d ", grid[i][j].finalCost);
            else System.out.print("BL  ");
        }
        System.out.println();
    }
    System.out.println();
     
    if(closed[endI][endJ]){
        //Trace back the path 
         System.out.println("Path: ");
         Cell current = grid[endI][endJ];
         System.out.print(current);
         while(current.parent!=null){
             System.out.print(" -> "+current.parent);
             current = current.parent;
         } 
         System.out.println();
    }else System.out.println("No possible path");
}

public static void main(String[] args) throws Exception{   
 test(1, 3, 3, 0, 0, 1, 2); 
 test(2, 3, 3, 0, 0, 2, 1);   
}
}