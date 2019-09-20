package rl_pack;

import java.awt.Color;
import java.util.*;


public class Painted_Grid extends Component{
	static int size = 10;
	
	public static int[][] paint = new int [size][size];
	
	static MyGrid g = new MyGrid(size);
	public static final String FILENAME = "map.txt";
	

	protected Painted_Grid(int _id) {
		super(_id);
		// TODO Auto-generated constructor stub
		
		ArrayList<ArrayList<String>> csvVals = FileIOHelpers.readCSV(FILENAME);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
	                paint[i][j] = Integer.parseInt(csvVals.get(i).get(j));
			}
		}
		
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(paint[i][j] == 1) {
					g.setColor(i, j, Color.LIGHT_GRAY);
				}else if(paint[i][j] == 2) {
					g.setColor(i, j, Color.GREEN);
				}else if(paint[i][j] == 10) {
					g.setColor(i, j, Color.YELLOW);
				}else if(paint[i][j] == -1) {
					g.setColor(i, j, Color.BLUE);
				}
			}
		}
	}

	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
