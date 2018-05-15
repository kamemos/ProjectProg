package gui;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import lib.AudioUtility;
import lib.ShortAudioException;
import main.Main;

public class MainScreen extends StackPane {
	
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimationTimer animationTimer;
	
	public MainScreen(){
		super();
		this.setPrefSize(900, 400);
		this.canvas = new Canvas(900,400);
	    canvas.setFocusTraversable(true);
		this.gc = canvas.getGraphicsContext2D();
		////animationtimer
		this.animationTimer = new AnimationTimer() {
			   double alpha = 0.0;
			   boolean increase = true;

			   public void handle(long currentNanoTime) {
			    double t = 50;
			    if (increase)
			     alpha += 1.0 / t;
			    else
			     alpha -= 1.0 / t;
			    if (alpha > 1.0) {
			     alpha = 2 - alpha;
			     increase = false;
			    } else if (alpha < 0.0) {
			     alpha = Math.abs(alpha);
			     increase = true;
			    }
			    ///BackGround
			    gc.setGlobalAlpha(1);
			    gc.drawImage(RenderableHolder.getTheLastSupper(),0, 0);
				gc.setFill(Color.WHITE);
				gc.setStroke(Color.WHITE);
				Font font = Font.font("Times New Roman", FontWeight.BOLD, 40);
				gc.setFont(font);
				gc.setLineWidth(2);
				FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
				double font_width = fontLoader.computeStringWidth("Yakiniku THE LAST DINNER", gc.getFont());
				double font_hieght = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
				gc.fillText("Yakiniku THE LAST DINNER", (900-font_width)/2, 75);
				//////Blink TEXT
				gc.setGlobalAlpha(alpha);
			    gc.setFill(Color.CYAN);
			    double font_width2 = fontLoader.computeStringWidth("Press Enter to start", gc.getFont());
			    double font_hieght2 = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
			    double font_width3 = fontLoader.computeStringWidth("Press B to select BGM", gc.getFont());
			    double font_hieght3 = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
			    double font_width4 = fontLoader.computeStringWidth("Press H to read How to play", gc.getFont());
			    double font_hieght4 = fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
			    gc.fillText("Press Enter to start", (900-font_width2)/2, 380-(3*font_hieght3)-font_hieght2-font_hieght4);
			    gc.fillText("Press H to read How to play", (900-font_width4)/2, 380-(2*font_hieght3)-font_hieght2);
			    gc.fillText("Press B to select BGM", (900-font_width3)/2, 380-font_hieght3);

			   }
			  };
        this.getChildren().add(canvas);
        
        addListerner();
		
		
	}

	public AnimationTimer getAnimationTimer() {
		return animationTimer;
	}
	
	private void addListerner(){
		this.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER){
				Main.instance.toggleSceneToGame();
			}
			
			if (event.getCode() == KeyCode.B){
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
				try {
					File file = fileChooser.showOpenDialog(null);
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
					AudioFormat format = audioInputStream.getFormat();
					long frames = audioInputStream.getFrameLength();
					double durationInSeconds = (frames+0.0) / format.getFrameRate();  
			    	 // open the sound file as a Java input stream
			        String stringFile = file.toString();
			        InputStream in = new FileInputStream(stringFile);

			        // create an audiostream from the inputstream
			        AudioClip audioStream = new AudioClip(Paths.get(stringFile).toUri().toString());

			        // play the audio clip with the audioplayer class
			        AudioUtility.setSound_bgm(audioStream);
				    if ( durationInSeconds <= 20){
				    	throw new ShortAudioException();
				    }
				    
				    

				} catch (ShortAudioException  e) {
					// TODO Auto-generated catch block
					Alert a = new Alert(AlertType.ERROR);
					a.setHeaderText("ERROR");
					a.setContentText("Audio has to be longer than 20 seconds.");
					a.showAndWait();
				}catch(UnsupportedAudioFileException |IOException e){
					Alert a = new Alert(AlertType.ERROR);
					a.setHeaderText("ERROR");
					a.setContentText("File is not supported.");
					a.showAndWait();
				}
				
				
			}
			if(event.getCode() == KeyCode.H){
				   Main.instance.toggleToHowToScreen();
			   }
		});
	}
	
}

