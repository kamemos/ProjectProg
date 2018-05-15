package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;

public class Pork extends GrillableFood{
	
	public Pork(){
		super();
		this.timeOfStage1 = 300;
		this.timeOfStage2 = 600;
		this.timeOfStage3 = 900;
		this.timeOfStage4 = 1200;
	}

	public void draw(GraphicsContext gc){
		int CurrentStage = this.getcurrentFace1Stage();
		gc.drawImage(RenderableHolder.getPorkImage()[CurrentStage], this.positionX, this.positionY);
	}
	
}

