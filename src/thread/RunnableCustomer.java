package thread;

import gui.RenderableHolder;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Chicken;
import logic.Customer;
import logic.GrillableFood;
import logic.MainLogic;
import logic.Pork;

public class RunnableCustomer implements Runnable{
    
	private Customer customer;
	private MainLogic mainLogic;
	private GraphicsContext gc;
	
	public RunnableCustomer(Customer customer , MainLogic mainLogic ,GraphicsContext gc){
		this.customer = customer ;
		this.mainLogic = mainLogic ;
		this.gc = gc;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!this.mainLogic.isGameEnd()){
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!mainLogic.isPause()){
				customer.update();
			}
			int foodIcon;
			Image currentFace = RenderableHolder.getCustomerFace()[customer.getCurrentFace()];
			GrillableFood wantedFood = customer.getWantedFood();
			if (wantedFood instanceof Pork){
				foodIcon = 0;
			}
			else if (wantedFood instanceof Chicken){
				foodIcon = 1;
			}
			else foodIcon = 2;
			Image wantedFoodIcon = RenderableHolder.getFoodIconImage()[foodIcon];
			Platform.runLater(() -> {
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, 200, 120);
				gc.drawImage(currentFace , 100 , 0);
				gc.drawImage(wantedFoodIcon , 0, 0);
				if (customer.faceTick*200/(customer.timeToChangeFace*2) <= 100){
					gc.setFill(Color.GREEN);
				}
				else gc.setFill(Color.RED);
				gc.setLineWidth(2);
				gc.fillRect(0, 90 , 200 , 30);
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 90 , customer.faceTick*200/(customer.timeToChangeFace*2) , 30);
				gc.strokeRect(0, 90, 200, 30);
			});
			
		}
		
	}
}
