package JavaFXGuiObjects;

import javafx.stage.Stage;

public abstract class JFXDialog {

	protected Stage frame;
	protected String title;
	protected boolean modal;
	
	public JFXDialog(Stage frame, String title, boolean modal) {
		this.frame = frame;
		this.title = title;
		this.modal = modal;
	}
	
	public JFXDialog(Stage frame, boolean modal) {
		this.frame = frame;
		this.modal = modal;
	}
	
	public abstract void render();
}
