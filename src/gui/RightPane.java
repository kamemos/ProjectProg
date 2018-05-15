package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import logic.MainLogic;
import thread.RunnableCustomer;
import thread.ThreadHolder;

public class RightPane extends GridPane{

	private MainLogic mainLogic;
	
	public RightPane(MainLogic mainLogic){
		this.setPrefSize(200, 480);
		this.mainLogic = mainLogic;
		
		
		Canvas a = new Canvas(200,120);
		Canvas b = new Canvas(200,120);
		Canvas c = new Canvas(200,120);
		Canvas d = new Canvas(200,120);
		
		this.add(a, 0 , 0);
		this.add(b, 0 , 1);
		this.add(c, 0 , 2);
		this.add(d, 0 , 3);
		
		///create RunnableCustomer and add to thread
		Thread customer1 = new Thread(new RunnableCustomer
				(mainLogic.getCustomer1(),this.mainLogic,a.getGraphicsContext2D()));
		Thread customer2 = new Thread(new RunnableCustomer
				(mainLogic.getCustomer2(),this.mainLogic,b.getGraphicsContext2D()));
		Thread customer3 = new Thread(new RunnableCustomer
				(mainLogic.getCustomer3(),this.mainLogic,c.getGraphicsContext2D()));
		Thread customer4 = new Thread(new RunnableCustomer
				(mainLogic.getCustomer4(),this.mainLogic,d.getGraphicsContext2D()));
		
		ThreadHolder.instance.getThreads().add(customer1);
		ThreadHolder.instance.getThreads().add(customer2);
		ThreadHolder.instance.getThreads().add(customer3);
		ThreadHolder.instance.getThreads().add(customer4);
		
	}
}
