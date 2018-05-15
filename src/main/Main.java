
package main;

import gui.GameScreen;
import gui.HowToScreen;
import gui.MainScreen;
import gui.RenderableHolder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.AudioUtility;
import logic.MainLogic;
import thread.ThreadHolder;

public class Main extends Application{

	public static Main instance;
	private MainLogic mainLogic;
	private GameScreen gameScreen;
	private HowToScreen howToScreen;
	private Scene gameScene;
	private MainScreen mainScreen;
	private Stage primaryStage;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.howToScreen = new HowToScreen();
		this.primaryStage = primaryStage;
		this.mainLogic = new MainLogic();
		this.gameScreen = new GameScreen(this.mainLogic);
		this.mainScreen = new MainScreen();
		this.gameScene = new Scene(this.mainScreen);
		primaryStage.setScene(this.gameScene);
		primaryStage.show();
		this.mainScreen.getAnimationTimer().start();
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			   @Override
			   public void handle(WindowEvent event) {
				   Alert a = new Alert(AlertType.INFORMATION);
				   a.setHeaderText("Pls dont do this to me");
				   a.setContentText("Pls dont do this to me");
				   a.showAndWait();
				   System.exit(0);
			   }
			  });
	}
	
	public void toggleSceneToGame(){
		this.mainScreen.getAnimationTimer().stop();
		ThreadHolder.instance.getThreads().clear();
		RenderableHolder.getInstance().getEntities().clear();
		this.mainLogic = new MainLogic();
		this.gameScreen = new GameScreen(this.mainLogic);
		
		new Thread(() -> {
			
			while (!this.mainLogic.isGameEnd()){
				if (AudioUtility.getSound_bgm().isPlaying()){
					
				}
				else AudioUtility.playSound("bgm");
			}
			AudioUtility.getSound_bgm().stop();
		}).start();
		new Thread(() -> {
			while (!this.mainLogic.isGameEnd()){
				
			}
			Platform.runLater(() -> {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText("GAME END");
				a.setContentText("Your score is " + this.mainLogic.score);
				a.showAndWait();
				Main.instance.toggleSceneToMain();
			});
		}).start();
		
		this.gameScene.setRoot(this.gameScreen);
		this.primaryStage.sizeToScene();

		for (Thread i : ThreadHolder.instance.getThreads()){
			i.start();
		}
		this.gameScreen.getAnimationTimer().start();
	}
	public void toggleSceneToMain(){
		this.gameScreen.getAnimationTimer().stop();
		this.gameScene.setRoot(this.mainScreen);
		this.primaryStage.sizeToScene();
		this.mainScreen.getAnimationTimer().start();
	}
	
	public void toggleToHowToScreen(){
		this.gameScene.setRoot(this.howToScreen);
		this.primaryStage.sizeToScene();
	}
	
	public MainLogic getMainLogic() {
		return mainLogic;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}
}
