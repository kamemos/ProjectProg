package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.MainLogic;

public class PlayerStatus extends Canvas{

	private MainLogic mainLogic;
	private GraphicsContext gc;
	
	public PlayerStatus(MainLogic mainLogic){
		super(500,80);
		this.mainLogic = mainLogic;
		this.gc = this.getGraphicsContext2D();
	}
	
	public void drawPlayerStatusBar(){
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 500, 80);
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Tahoma",36));
		gc.fillText("PM" + mainLogic.displayHour + ":" + String.format("%02d", mainLogic.displayMin), 20, 50);
		gc.fillText("Score : " + mainLogic.score, 250, 50);
		
	}
	
}
