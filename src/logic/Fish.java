package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class Fish extends GrillableFood {
	
	public Fish(){
		super();
		this.timeOfStage1 = 200;
		this.timeOfStage2 = 400;
		this.timeOfStage3 = Integer.MAX_VALUE;
		this.timeOfStage4 = Integer.MAX_VALUE+1;
		
	}

	public void draw(GraphicsContext gc){
		int CurrentStage = this.getcurrentFace1Stage();
		gc.drawImage(RenderableHolder.getFishImage()[CurrentStage], this.positionX, this.positionY);
	}
	

	
}