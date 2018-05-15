package logic;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
    
	int getZ();
	void setZ(int z);
	void draw(GraphicsContext gc);
	boolean isDestroy();
	void setDestroy();
	void update();
		
	
}
