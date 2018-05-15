package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class Chicken extends GrillableFood {
	public Chicken(){
		super();
		this.timeOfStage1 = 360;
		this.timeOfStage2 = 720;
		this.timeOfStage3 = 1080;
		this.timeOfStage4 = Integer.MAX_VALUE;
		
	}
	
	public void draw(GraphicsContext gc) {
		int CurrentStage = this.getcurrentFace1Stage();
		gc.drawImage(RenderableHolder.getChickenImage()[CurrentStage], this.positionX, this.positionY);
	}
	
}
