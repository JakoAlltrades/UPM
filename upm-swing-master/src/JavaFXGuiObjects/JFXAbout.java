package JavaFXGuiObjects;

import com._17od.upm.gui.JavaFX.AboutDialog;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JFXAbout extends JFXDialog {

	public JFXAbout(Stage frame, String title, boolean modal) {
		super(frame, title, modal);
		// TODO Auto-generated constructor stub
		String version = AboutDialog.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "<version unknown>";
        }
        
        Scene gp = new Scene(frame.);
        //set the pane
	}

	public void render() {
		// TODO Auto-generated method stub
		
	}

}
