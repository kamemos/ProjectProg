package lib;

public class ShortAudioException extends Exception{
	public ShortAudioException(){
		System.out.println("Audio has to be longer.");
		AudioUtility.loadResources();
	}
}
