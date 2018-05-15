package gui;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lib.InputUtility;
import logic.GrillableFood;
import logic.IRenderable;
import logic.MainLogic;

public class GamePlayScreen extends Canvas{
    
	private MainLogic mainLogic;
	private GraphicsContext gc;
	private Image BBQGrill;
	
	public GamePlayScreen(MainLogic mainLogic){
		
		super(500,300);
		this.mainLogic = mainLogic;
		this.gc = this.getGraphicsContext2D();
		this.BBQGrill = RenderableHolder.getBBQGrill();
		
		addListener();
		
	}
	
	public void drawGamePlayScreen() {
		///BBQGrill
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 500, 300);
		gc.drawImage(this.BBQGrill, 0, 0);
		///Draw table
		gc.setFill(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(350, 0, 150, 300);
		gc.strokeRect(350, 75, 150, 75);
		gc.strokeRect(350, 150, 150, 300);
		gc.strokeRect(350, 225, 150, 300);
		gc.setFont(new Font("Tahoma",30));
		gc.fillText("Table 1", 360, 10+30);
		gc.fillText("Table 2", 360, 85+30);
		gc.fillText("Table 3", 360, 160+30);
		gc.fillText("Table 4", 360, 235+30);
		///draw current mouse ontable
		if (mainLogic.isOnTable(InputUtility.getMouseX())){
			gc.setGlobalAlpha(0.4);
			gc.setFill(Color.YELLOW);
			gc.fillRect(350, mainLogic.getTable(InputUtility.getMouseY())*75, 150, 73);
			gc.setStroke(Color.BLACK);
			gc.setGlobalAlpha(1);
			gc.strokeRect(350, mainLogic.getTable(InputUtility.getMouseY())*75, 150, 75);
		}
		for (int i = RenderableHolder.getInstance().getEntities().size() - 1 ; i>=0 ;i--){
			if (RenderableHolder.getInstance().getEntities().get(i).isDestroy()){
				RenderableHolder.getInstance().getEntities().remove(i);
			}
			else RenderableHolder.getInstance().getEntities().get(i).draw(gc);
		}
		
	}
	
	private void addListener() {
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if (event.getButton() == MouseButton.PRIMARY)
					InputUtility.setMouseLeftDown(false);
				    mainLogic.dragObject = null;
			}
		});
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				if (event.getButton() == MouseButton.PRIMARY) {
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftLastDown(true);
					
				}

				if (RenderableHolder.getInstance().getEntities() == null) return;
				for (int i = RenderableHolder.getInstance().getEntities().size() - 1 ; i >= 0 ; i--){
					IRenderable object = RenderableHolder.getInstance().getEntities().get(i);
					if (InputUtility.getMouseX() >= ((GrillableFood) object).getPositionX() + 10 &&
						InputUtility.getMouseX() <= ((GrillableFood) object).getPositionX() + 100 &&
						InputUtility.getMouseY() >= ((GrillableFood) object).getPositionY() - 30 &&
						InputUtility.getMouseY() <= ((GrillableFood) object).getPositionY() + 100 
						){
						object.setZ(0);
						RenderableHolder.getInstance().sort();
						object.setZ(2);
						mainLogic.dragObject = (GrillableFood) object;
					}
				}
				
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(false);
			}
		});

		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				InputUtility.setMouseOnScreen(true);
				
			}
		});

		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});

		this.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (InputUtility.isMouseOnScreen()) {
					if (mainLogic.dragObject == null) return;
					mainLogic.dragObject.setPositionX((int) event.getX() -30);
					mainLogic.dragObject.setPositionY  ((int) event.getY() -30);
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}
		});
		
		
		
		
	}
	
}
