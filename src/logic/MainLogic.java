package logic;

import gui.RenderableHolder;
import lib.InputUtility;

public class MainLogic {
	public static int time;
	public static int displayHour,displayMin;
	public static int score;
    private static Customer customer1;
    private static Customer customer2;
    private static Customer customer3;
    private static Customer customer4;
    public GrillableFood dragObject;
    private static boolean pause;
    

	public MainLogic() {
		this.score = 0 ;
		customer1 = new Customer();
		customer2 = new Customer();
		customer3 = new Customer();
		customer4 = new Customer();
		
		this.time = 0;
		this.displayHour = 16 ;
		this.displayMin = this.time/100;
		this.pause = false;
	}
	
	public static boolean isPause() {
		return pause;
	}
	public static void setPause() {
		MainLogic.pause = !pause;
	}
	
	public void timeUpdate(){
		if(this.displayMin == 60){
		    this.displayMin = 0;
		    this.displayHour ++;
		   }
		   
		   if(this.time == 20){
		    this.displayMin++;
		    this.time = 0;
		   }
		   this.time++;
			
	}
    public synchronized static boolean isGameEnd(){
		if (displayHour >= 22){
			return true;
		}
		else return false;
	}
	
	public void update(){
		if (this.isGameEnd()){
			return;
		}
		if (this.pause){
			return;
		}
		this.timeUpdate();
		this.getFood();
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()){
			entity.update();
		}
	}
	
	public static Customer getCustomer1() {
		return customer1;
	}

	public static Customer getCustomer2() {
		return customer2;
	}

	public static Customer getCustomer3() {
		return customer3;
	}

	public static Customer getCustomer4() {
		return customer4;
	}
	
	public boolean isOnTable(double x){
		if (InputUtility.isMouseOnScreen() && x >= 350){
			return true;
		}
		else return false;
	}
	
	public int getTable(double y){
		if (y  <= 75) return 0;
		else if (y > 75 && y <= 150) return 1;
		else if (y > 150 && y <= 225) return 2;
		else if (y > 225 && y <= 300) return 3;
		else return -1;

	}

	public synchronized void setScore(Customer customer,GrillableFood food){
		int burnStage;
		if (food instanceof Fish){
			burnStage = 2;
		}
		else if (food instanceof Pork){
			burnStage = 4;
		}
		else burnStage = 3;
			
		if (customer.wantedFood.getClass() == food.getClass() && food.getcurrentFace1Stage() != burnStage ){
			if (customer.getCurrentFace() != customer.sadFace){
				this.score += 200*customer.getCurrentFace()*food.getcurrentFace1Stage();
			}
			else this.score -= 200;
		}
		else this.score -= 200;
	}
	
	public void getFood() {
		IRenderable entity;
		if (
				!InputUtility.isMouseLeftDown()){
			if (RenderableHolder.getInstance().getEntities().size() != 0){
				entity = RenderableHolder.getInstance().getEntities().get(0);
			}
			else return;
			if (this.isOnTable(((GrillableFood) entity).positionX + 30)){
				int currentTable = this.getTable(((GrillableFood) entity).positionY + 30);
				if (currentTable == 0){
					this.setScore(this.customer1, ((GrillableFood) entity));
					this.customer1.customerGetFood();
					entity.setDestroy();
					return;
				}
				else if(currentTable == 1){
					this.setScore(this.customer2, ((GrillableFood) entity));
					this.customer2.customerGetFood();
					entity.setDestroy();
					return;
				}
				else if(currentTable == 2){
					this.setScore(this.customer3, ((GrillableFood) entity));
					this.customer3.customerGetFood();
					entity.setDestroy();
					return;
				}
				else {
					this.setScore(this.customer4, ((GrillableFood) entity));
					this.customer4.customerGetFood();
					entity.setDestroy();
					return;
				}
			}
			else return;
		}
		else return;
	}
	
	
}
