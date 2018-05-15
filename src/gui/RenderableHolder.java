package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import logic.IRenderable;



public class RenderableHolder {
	
	private static List<IRenderable> entities;
	private static Image[] customerFace;
	private static Image[] foodIconImage;
	private static Image[] porkImage;
	private static Image[] chickenImage;
	private static Image[] fishImage;
	private static Image BBQGrill;
	private static Image theLastSupper;
	private static Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();
	
	
	public RenderableHolder() {
		this.entities = new ArrayList<IRenderable>();
		this.customerFace = new Image[3];
		this.foodIconImage = new Image[3];
		this.fishImage = new Image[3];
		this.chickenImage = new Image[4];
		this.porkImage = new Image[5];
	
		
		this.comparator = (IRenderable ob1,IRenderable ob2) -> {
	    	if (ob1.getZ() >= ob2.getZ()){
	    		return 1;
	    	}
	    	return -1;
	    }; 
	}
	
	static {
		try {
			loadResources();
		}catch(Exception e){
			Alert a = new Alert(AlertType.ERROR);
			a.setHeaderText("ERROR");
			a.setContentText("Image not found.");
			a.showAndWait();
		}
	}
	
	public synchronized void addEntity(IRenderable entity){
		entities.add(entity);
	}
	
	public static void loadResources()  {
		
		customerFace[0] = new Image(ClassLoader.getSystemResource("sadFace.jpg").toString());
		customerFace[1] = new Image(ClassLoader.getSystemResource("normalFace.png").toString());
		customerFace[2] = new Image(ClassLoader.getSystemResource("happyFace.jpg").toString());
		
		foodIconImage[0] = new Image(ClassLoader.getSystemResource("pig.png").toString());
		foodIconImage[1] = new Image(ClassLoader.getSystemResource("chicken.png").toString());
		foodIconImage[2] = new Image(ClassLoader.getSystemResource("fish.png").toString());

		fishImage[0] = new Image(ClassLoader.getSystemResource("fish0.png").toString());
		fishImage[1] = new Image(ClassLoader.getSystemResource("fish1.png").toString());
		fishImage[2] = new Image(ClassLoader.getSystemResource("fish2.png").toString());
		
		chickenImage[0] = new Image(ClassLoader.getSystemResource("chicken0.png").toString());
		chickenImage[1] = new Image(ClassLoader.getSystemResource("chicken1.png").toString());
		chickenImage[2] = new Image(ClassLoader.getSystemResource("chicken2.png").toString());
		chickenImage[3] = new Image(ClassLoader.getSystemResource("chicken3.png").toString());
		
		porkImage[0] = new Image(ClassLoader.getSystemResource("pork0.png").toString());
		porkImage[1] = new Image(ClassLoader.getSystemResource("pork1.png").toString()); 
		porkImage[2] = new Image(ClassLoader.getSystemResource("pork2.png").toString()); 
		porkImage[3] = new Image(ClassLoader.getSystemResource("pork3.png").toString());
		porkImage[4] = new Image(ClassLoader.getSystemResource("pork4.png").toString()); 
		
		BBQGrill = new Image(ClassLoader.getSystemResource("BBQGrill.png").toString());
		theLastSupper = new Image(ClassLoader.getSystemResource("lastsupper.jpg").toString());
	}

	public static Image getTheLastSupper() {
		return theLastSupper;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public static Image[] getCustomerFace() {
		return customerFace;
	}

	public static Image[] getFoodIconImage() {
		return foodIconImage;
	}

	public static Image[] getPorkImage() {
		return porkImage;
	}

	public static Image[] getChickenImage() {
		return chickenImage;
	}

	public static Image[] getFishImage() {
		return fishImage;
	}

	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}

	public static Image getBBQGrill() {
		return BBQGrill;
	}
}
