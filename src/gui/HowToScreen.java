package gui;

import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import main.Main;

public class HowToScreen extends BorderPane{
	 private TextArea text;
	 
     public HowToScreen() {
       	 this.setPrefSize(800, 400);
       	 this.read();
       	 Button btn = new Button ("Back to Menu");
       	 btn.setPrefSize(800, 100);;
       	 this.setBottom(btn);
       	 btn.setOnAction((event) -> {
       		 Main.instance.toggleSceneToMain();
       	 });
        }
        
        public void read() {
        	String string = "How to play\n1) push the button to grill meat.\n2) drag the grilled meat to the table.\n3) scores depend on customer's face and stage of meat(well done or not).\n\n**Beware that your score can be minus.**\n^\n^\nRealistic\n4) enjoy the last night of your restaurant.\n\nPS.Please don't left the restaurant before it's closed at 10.00 PM.";
            text = new TextArea(string);
            text.setEditable(false);
            text.setPrefSize(800, 300);
            this.setCenter(text);       
    }
}
