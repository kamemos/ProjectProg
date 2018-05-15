package lib;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;

public class AudioUtility {
	private static final String bgm = "bgm.mp3";
	private static final String grill = "sound_grill.m4a";
	private static final String scoring = "scoring.mp3";
	private static AudioClip sound_bgm;
	private static AudioClip sound_grill;
	private static AudioClip sound_scoring;
	static {
		try {
			loadResources();
		}catch(Exception e){
			Alert a = new Alert(AlertType.ERROR);
			a.setHeaderText("ERROR");
			a.setContentText("Audio not found.");
			a.showAndWait();
		}
	}
	
	public static void loadResources(){
		sound_bgm = new AudioClip(ClassLoader.getSystemResource(bgm).toString());
		sound_grill = new AudioClip(ClassLoader.getSystemResource(grill).toString());
		sound_scoring = new AudioClip(ClassLoader.getSystemResource(scoring).toString());
	}

	public static void playSound(String Identifier){
		if(Identifier.equals("bgm")){
			sound_bgm.play();
		}if(Identifier.equals("grill")){
			sound_grill.play();
		}if(Identifier.equals("scoring")){
			sound_scoring.play();
		}
	}

	public static AudioClip getSound_bgm() {
		return sound_bgm;
	}

	public static void setSound_bgm(AudioClip sound_bgm) {
		AudioUtility.sound_bgm = sound_bgm;
	}
}
