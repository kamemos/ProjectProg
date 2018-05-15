package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import lib.InputUtility;
import lib.Utility;

public class GrillableFood implements IRenderable {
	public int timeOfStage1;
	public int timeOfStage2;
	public int timeOfStage3;
	public int timeOfStage4;
	public int currentTimeFace;
	protected boolean isDestroy;
	protected int z = 2;
	protected double positionX;
	protected double positionY;
	
	public GrillableFood(){
		this.currentTimeFace = 0;
		this.isDestroy = false ;
		this.positionX = Utility.random(100, 250);
		this.positionY = Utility.random(50, 200);
		
	}




	public int getcurrentFace1Stage(){
		if(this.currentTimeFace < timeOfStage1){
			return 0;
		}else if(this.currentTimeFace >= timeOfStage1 && currentTimeFace < timeOfStage2){
			return 1;
		}else if(this.currentTimeFace >= timeOfStage2 && this.currentTimeFace < timeOfStage3){
			return 2;
		}else if(this.currentTimeFace >= timeOfStage3 && this.currentTimeFace < timeOfStage4){
			return 3;
		}else{
			return 4;
		}
	}
	

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return this.z;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return this.isDestroy;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	@Override
	public void setZ(int z) {
		// TODO Auto-generated method stub
		this.z = z;
	}

	@Override
	public void setDestroy() {
		// TODO Auto-generated method stub
		this.isDestroy = true;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (this.equals(RenderableHolder.getInstance().getEntities().get(0)) && InputUtility.isMouseLeftDown()){
			return;
		}
		else this.currentTimeFace += 1;
	}
	
}
