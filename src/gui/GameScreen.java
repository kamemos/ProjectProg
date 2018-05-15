package gui;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lib.InputUtility;
import logic.MainLogic;
import main.Main;

public class GameScreen extends GridPane {

	private GamePlayScreen gamePlayScreen;
	private PlayerStatus playerStatus;
	private ButtonPane buttonPane;
	private MainLogic mainLogic;
	private RightPane rightPane;
	private AnimationTimer animationTimer;
	
	public GameScreen(MainLogic mainLogic){
		this.setPrefSize(700, 480);
		this.mainLogic = mainLogic;
		this.gamePlayScreen = new GamePlayScreen(this.mainLogic);
		this.playerStatus = new PlayerStatus(this.mainLogic);
		this.buttonPane = new ButtonPane(this.gamePlayScreen);
		this.rightPane = new RightPane(this.mainLogic);
		GridPane gridPane1 = new GridPane();
		gridPane1.setPrefSize(500, 480);
		gridPane1.add(this.playerStatus, 0, 0);
		gridPane1.add(this.buttonPane, 0, 1);
		gridPane1.add(this.gamePlayScreen, 0, 2);
		GridPane gridPane2 = new GridPane();
		gridPane2.add(this.rightPane, 0, 0);
		this.add(gridPane1, 0, 0);
		this.add(gridPane2, 1, 0);
		this.animationTimer = new AnimationTimer() {
			Long start=0l;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(start==0l)start=now;
				long diff = now-start;
				if(diff>=100000000l){
					
					Main.instance.getGameScreen().paintComponents();
					Main.instance.getMainLogic().update();
	                InputUtility.postUpdate();
					
				}
			}
		};
		addListener();
	}
	
	public AnimationTimer getAnimationTimer() {
		return animationTimer;
	}
	
	public void paintComponents(){
		this.gamePlayScreen.drawGamePlayScreen();
		this.playerStatus.drawPlayerStatusBar();
	}
	
	private void addListener(){
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER){
					mainLogic.setPause();
				}
			}
		});

		
	}
}
