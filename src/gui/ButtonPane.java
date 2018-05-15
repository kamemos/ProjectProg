package gui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lib.AudioUtility;
import logic.Chicken;
import logic.Fish;
import logic.MainLogic;
import logic.Pork;

public class ButtonPane extends GridPane{
	
	public ButtonPane(GamePlayScreen gamePlayScreen){
		
		this.setPrefSize(500,100);
	
		
		Button pig = new Button("",new ImageView(RenderableHolder.getFoodIconImage()[0]));
		pig.setPrefSize(166.5, 100);
		
		Button chicken = new Button("",new ImageView(RenderableHolder.getFoodIconImage()[1]));
		chicken.setPrefSize(166.5, 100);
		
		Button fish = new Button("",new ImageView(RenderableHolder.getFoodIconImage()[2]));
		fish.setPrefSize(166.5, 100);
		
		this.add(pig, 0, 0);
		this.add(chicken, 1, 0);
		this.add(fish, 2, 0);
		
		pig.setOnAction((event) -> {
			if (MainLogic.isPause()){
				return;
			}
			if (RenderableHolder.getInstance().getEntities().size() <= 10){
				Pork a = new Pork();
				a.setZ(2);
				RenderableHolder.getInstance().addEntity(a);
				RenderableHolder.getInstance().sort();
				a.setZ(1);
				AudioUtility.playSound("grill");
			}
		});
		
		chicken.setOnAction((event) -> {
			if (MainLogic.isPause()){
				return;
			}
			if (RenderableHolder.getInstance().getEntities().size() <= 10){
				Chicken a = new Chicken();
				a.setZ(1);
				RenderableHolder.getInstance().addEntity(a);
				RenderableHolder.getInstance().sort();
				a.setZ(2);
				AudioUtility.playSound("grill");
			}
		});
		
		fish.setOnAction((event) -> {
			if (MainLogic.isPause()){
				return;
			}
			if (RenderableHolder.getInstance().getEntities().size() <= 10){
				Fish a = new Fish();
				a.setZ(1);
				RenderableHolder.getInstance().addEntity(a);
				RenderableHolder.getInstance().sort();
				a.setZ(2);
				AudioUtility.playSound("grill");
			}
		});

	}
}
