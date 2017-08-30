import javax.swing.event.DocumentEvent.EventType;

import javafx.event.ActionEvent;
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
	Button cancel;
	CheckBox useEscapeCharacters;
	CheckBox numberBox;
	CheckBox upperCaseBox;
	CheckBox lowerCaseBox;
	CheckBox allSymbolBox;
	Label label;
	TextField tf;
	public PasswordOptions(ProgressSample ps)
	{
		Group iAm = new Group();
		scene = new Scene(iAm, 250, 250);
		window = new Stage();
		window.setScene(scene);
		window.setTitle("Generate Password Options");
		apply = new Button("Apply password rules");
		cancel = new Button("Cancel");
		useEscapeCharacters = new CheckBox("Use Escape Characters");
		numberBox = new CheckBox("Use numbers");
		lowerCaseBox = new CheckBox("Use lowercase");
		upperCaseBox = new CheckBox("Use uppercase");
		allSymbolBox = new CheckBox("Use all symbols");
		useEscapeCharacters.setSelected(ps.getUseEscapeCharacters());
		lowerCaseBox.setSelected(ps.getUseLowerCharacters());
		upperCaseBox.setSelected(ps.getUseUpperCharacters());
		numberBox.setSelected(ps.getUseNumberCharacters());
		allSymbolBox.setSelected(ps.getUseSymbolCharacters());
		allSymbolBox.setOnAction(new EventHandler() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				if(allSymbolBox.isSelected())
				{
					numberBox.setSelected(true);
					upperCaseBox.setSelected(true);
					lowerCaseBox.setSelected(true);
					useEscapeCharacters.setSelected(true);
				}
			}
		
		});
		label = new Label("Password length");
		tf = new TextField();
		tf.setText(ps.getpLength() + "");
		apply.setOnAction(new EventHandler() {

			@Override
			public void handle(Event e) {
				// TODO Auto-generated method stub
				int passlength = Integer.parseInt(tf.getText());
				boolean useEscape = useEscapeCharacters.isSelected();
				boolean useLower = lowerCaseBox.isSelected();
				boolean useUpper = upperCaseBox.isSelected();
				boolean useNumber = numberBox.isSelected();
				boolean useSymbol = allSymbolBox.isSelected();
				ps.setpLength(passlength);
				ps.setUseEscapeCharacters(useEscape);
				ps.setUseLowerCharacters(useLower);
				ps.setUseNumberCharacters(useNumber);
				ps.setUseSymbolCharacters(useSymbol);
				ps.setUseUpperCharacters(useUpper);
				window.close();
			}
	    	   
	       });
		
		cancel.setOnAction(new EventHandler() {

			
			public void handle(Event event) {
				// TODO Auto-generated method stub
				window.close();
			}
		
		});
		VBox vb = new VBox();
		HBox hb = new HBox(label, tf);
		HBox buttons = new HBox(apply,cancel);
		buttons.setSpacing(5);
		hb.setSpacing(5);
		vb.setSpacing(10);
		vb.getChildren().addAll(hb);
		vb.getChildren().add(useEscapeCharacters);
		vb.getChildren().add(upperCaseBox);
		vb.getChildren().add(lowerCaseBox);
		vb.getChildren().add(numberBox);
		vb.getChildren().add(allSymbolBox);
		vb.getChildren().addAll(buttons);
		scene.setRoot(vb);
		window.show();
	}
}
