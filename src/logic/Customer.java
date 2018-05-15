package logic;

import lib.AudioUtility;
import lib.Utility;

public class Customer {
	public int currentFace;
	public int faceTick;
	public GrillableFood wantedFood;
	protected static final int happyFace = 2;
	protected static final int normalFace = 1;
	protected static final int sadFace = 0;
	public int timeToChangeFace;
	
	public Customer(){
		this.wantedFood = Utility.randomFood();
		this.faceTick = 0;
		this.currentFace = happyFace;
		this.timeToChangeFace = Utility.random(100,400);
	}
	
	public int getCurrentFace() {
		if (this.faceTick >= 0 && this.faceTick <= this.timeToChangeFace){
			return this.happyFace;
		}
		else if(this.faceTick > this.timeToChangeFace && this.faceTick <= this.timeToChangeFace*2){
			return this.normalFace;
		}
		
		else return this.sadFace;
		
	}

	public synchronized void update() {
		// TODO Auto-generated method stub
		this.faceTick += 1;
		
	}
	
	public GrillableFood getWantedFood() {
		return this.wantedFood;
	}
	
	public void customerGetFood(){
		this.wantedFood = Utility.randomFood();
		this.faceTick = 0;
		this.timeToChangeFace = Utility.random(100,400);
		AudioUtility.playSound("scoring");
	}
}