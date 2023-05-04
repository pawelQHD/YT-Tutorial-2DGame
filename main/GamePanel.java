package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int  ORIGINAL_TILE_SIZE = 16; // 16x16 tile
	final int SCALE = 4;
	
	final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
	final int MAX_SCREEN_COLUMN = 16;
	final int MAX_SCREEN_ROW = 12;
	final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
	final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;	
	
	// Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel(){
	
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread(){
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run(){
		
		while(gameThread != null){
			
			System.out.println("The game loop is running");
			
			// UPDATE: update information such as character position
			update();
		
			// DRAW: draw the sreen with the updated information
			repaint();
		}
		
	}
	public void update(){
		
		if(keyH.upPressed == true){
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true){
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true){
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true){
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
		g2.dispose();
	}
}