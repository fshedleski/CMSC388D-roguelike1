package engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class EngineCore extends Canvas implements Runnable  {

	public int Width,
			   Height,
			   Scale;
	public String Name;
	public JFrame Frame;
	public Boolean runing;
	
	private int SleepTime = 0;
	
	public ArrayList<GameObject2D> elements,tempElements;

	private BufferedImage BackGround;
	private int[] pixles;
	
	public static int FCount = 0;
	public static int LCount = 0;
	
	private String path;

	public static int runSpeed = 60;
	public static AssetsCenter assetsCenter;
	public static HIDHandler inputs;
	public static Camera camera;

	public EngineCore(int Size, double Ratio, int Scale, String Name, String path){
		
		
		//initiation the variables
		this.Height = Size;
		this.Width = (int)(Size*Ratio);
		this.Scale = Scale;
		this.Name = Name;
		
		this.path = path;
		this.Frame = new JFrame(Name);
		
		// Starting the data collection/storage systems
		inputs =new HIDHandler(Frame);
		assetsCenter = new AssetsCenter(this.path);
		elements = new ArrayList<GameObject2D>();
		
		
		// Hard-coding the sky-box (not the best thing to do)
		this.BackGround = new BufferedImage (this.Width,this.Height,BufferedImage.TYPE_INT_RGB);
		this.pixles = ((DataBufferInt)BackGround.getRaster().getDataBuffer()).getData();
		
		// Setting up the canvas 
		setMinimumSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		setMaximumSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		setPreferredSize(new Dimension(this.Width*this.Scale, this.Height * this.Scale));
		
		this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.Frame.setLayout(new BorderLayout());
		
		this.Frame.add(this, BorderLayout.CENTER);
		this.Frame.pack();
		
		this.Frame.setResizable(true);
		this.Frame.setLocationRelativeTo(null);
		this.Frame.setVisible(true);

		// creating the camera
		camera = new Camera(this, this.Width/2.0, this.Height/2.0);
	}
	
	public synchronized void start() {
		this.runing = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		this.runing = false;
		new Thread(this).start();
	}
	
	
	@Override
	public void run() {
		long now = System.nanoTime();
		long lasttime = System.nanoTime();
		long LT = System.nanoTime();
		double nsPL = 1000000000D/ runSpeed;
		
		double delta = 0;
		
		while(this.runing) {
			Frame.requestFocusInWindow();
			tempElements = new ArrayList<GameObject2D>(elements);
			boolean render = true;
			//Time Management variables
			now = System.nanoTime();
			delta += (now - lasttime) / nsPL;
			lasttime = now;
			
			//Logic set to perform only 60 times per second
			if(delta >= 1) {
				LCount++;
				logic();
				delta -=1;
				render = true;
			}

			// apply camera effects to temp elements
			camera.proj(tempElements);

			//Sleep to limit the number of graphic updates (too much would slow the logic too)
			try {
				Thread.sleep(SleepTime);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(render) {
				FCount++;
				graphic();
			}
			
			//Graphics update free to use all available resources. 
			if(System.nanoTime() - LT >= 1000000000) {
				
				LT += 1000000000;
				System.out.println("FPS: " + FCount +" LPS: " + LCount);
				LCount = 0;
				FCount = 0;
			}

			updatePriorities();
			
			//resets
			inputs.UpdatePending();
		}
		
		
	}
	
	public void logic() {
		for(int i = GameObject2D.Min; i <= GameObject2D.Max; i++) {
			for (GameObject2D j : tempElements) {
				j.logic(i);
			}
		}
	}
	
	public void graphic() {
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
			
		Graphics2D G = (Graphics2D) bs.getDrawGraphics();
		G.setBackground(Color.LIGHT_GRAY);
		G.clearRect(0, 0, this.Width*10, this.Height*10);
		//Background color (in most cases you will have an element background, which draws a picture instead)
		//G.setColor(Color.decode("#33FFFF"));
		//G.fillRect(0, 0, Width*Scale, Height*Scale);
		
		//calling the graphic methods of every element
		for(int i = GameObject2D.Min; i <= GameObject2D.Max; i++) {
			for (GameObject2D j : tempElements) {
				j.graphics(i, G);
			}
		}
		
		G.dispose();
		bs.show();
	}

	public void updatePriorities() {
		for (GameObject2D j : tempElements) {
			j.updatePriorities();
		}
	}
	
	public void AddObject(GameObject2D newObject) {
		elements.add(newObject);
	}
	
}
