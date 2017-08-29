import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordOptions {
	Stage window;
	Scene scene;
	Button apply;
	CheckBox useEscapeCharacters;
	CheckBox numberBox;
	CheckBox upperCaseBox;
	CheckBox lowerCaseBox;
	CheckBox allSymbol;
	Label label;
	TextField tf;
	public PasswordOptions(ProgressSample ps)
	{
		Group iAm = new Group();
		scene = new Scene(iAm, 250, 100);
		window = new Stage();
		window.setScene(scene);
		window.setTitle("Generate Password Options");
		apply = new Button("Apply password rules");
		useEscapeCharacters = new CheckBox("Use Escape Characters");
		useEscapeCharacters.setSelected(ps.getUseEscapeCharacters());
		label = new Label("Password length");
		tf = new TextField();
		tf.setText(ps.getpLength() + "");
		apply.setOnAction(new EventHandler() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				int passlength = Integer.parseInt(tf.getText());
				boolean fuckJava = useEscapeCharacters.isSelected();
				ps.setpLength(passlength);
				ps.setUseEscapeCharacters(fuckJava);
				window.close();
			}
	    	   
	       });
		VBox vb = new VBox();
		HBox hb = new HBox(label, tf);
		hb.setSpacing(5);
		vb.setSpacing(10);
		vb.getChildren().addAll(hb);
		vb.getChildren().add(useEscapeCharacters);
		vb.getChildren().add(apply);
		scene.setRoot(vb);
		window.show();
	}
}
