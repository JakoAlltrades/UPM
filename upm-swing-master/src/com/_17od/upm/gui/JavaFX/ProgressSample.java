import java.util.Arrays;
import java.util.List;

import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressSample extends Application {

final Float[] values = new Float[] {-.1f, 0f, 0.6f, 1.0f};
final Label [] labels = new Label[values.length];
final ProgressBar[] pbs = new ProgressBar[values.length];
final ProgressIndicator[] pins = new ProgressIndicator[values.length];
final HBox hbs [] = new HBox [values.length];
int pLength = 4;
boolean useEscapeCharacters = false;
boolean useNumberCharacters = true;
boolean useUpperCharacters = true;
boolean useLowerCharacters = true;
boolean useSymbolCharacters = true;

ProgressSample ps;
private float securityValue = 0f;
private static final long serialVersionUID = 1L;
private static final Character[] ALLOWED_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
		'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9' };
public int getpLength() {
	return pLength;
}

public void setpLength(int pLength) {
	this.pLength = pLength;
}

public boolean getUseEscapeCharacters() {
	return useEscapeCharacters;
}

public void setUseEscapeCharacters(boolean useEscapeCharacters) {
	this.useEscapeCharacters = useEscapeCharacters;
}

// Extended CharArray list which include also 24 Escape characters for
// stronger password generation
private static final Character[] EXTRA_ALLOWED_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
		'3', '4', '5', '6', '7', '8', '9', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ',', ')', '_', '-',
		'+', '=', '|', '/', '<', '>', '.', '?', ';', ':' };

/*
 * We will use the (4)four above CharArray lists(UPPERCASE_CHARS,
 * LOWERCASE_CHARS, NUMBER_CHARS, ESCAPE_CHARS) to ensure that the generated
 * passwords will be more complex. If the user has selected to include
 * escape characters to generated passwords and the length of the passwords
 * is 4 or above, then we will use some methods in order to generate
 * passwords that will have at least 1 lower case + 1 upper case + 1 number
 * + 1 escape character. On the other hand, if the user has not selected to
 * include escape characters to generated passwords and the length of the
 * passwords is at least 3, then we will use methods in order to generate
 * passwords that will have at least 1 lower case + 1 upper case + 1 number.
 * *
 *float values based on the passage above:
 *1 lower = .25f,
 *1 upper = .25f,
 *1 number = .25f,
 *1 spCase = .25f
 *or if gen is set to 3 then
 *1 lower = .33f
 *1 upper = .33f
 *1 number = .33f
 *for user entered maybe do something similar
 *1 lower case = .2f
 *1 upper case = .2f,
 *1 number = .2f,
 *1 spCase = .2f,
 *length of 5 = .2f
 */
private static final Character[] UPPERCASE_CHARS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'

};

private static final Character[] LOWERCASE_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'

};

private static final Character[] NUMBER_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

private static final Character[] PUNCTUATION_CHARS = { '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ',', ')', '_',
		'-', '+', '=', '|', '/', '<', '>', '.', '?', ';', ':' };
PasswordField password = new PasswordField();
   @Override
   public void start(Stage stage) {
       Group root = new Group();
       Scene scene = new Scene(root, 300, 250);
       stage.setScene(scene);
       stage.setTitle("Progress Controls");

       for (int i = 0; i < values.length; i++) {
           final Label label = labels[i] = new Label();
           label.setText("progress:" + values[i]);

           final ProgressBar pb = pbs[i] = new ProgressBar();
           pb.setProgress(values[i]);

           final ProgressIndicator pin = pins[i] = new ProgressIndicator();
           pin.setProgress(values[i]);
           final HBox hb = hbs[i] = new HBox();
           hb.setSpacing(5);
           hb.setAlignment(Pos.CENTER);
           hb.getChildren().addAll(label, pb, pin);
       }
       ProgressBar passBar = new ProgressBar();
       password.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			// TODO Auto-generated method stub
			String enteredPass = password.getText();
			securityValue = CheckPasswordSecurity(enteredPass);
			passBar.setProgress(securityValue);
		}
    	   
       });
       Button genOptions = new Button("Password Generation Options");
       ps = this;
       genOptions.setOnAction(new EventHandler() {

		@Override
		public void handle(Event e) {
			// TODO Auto-generated method stub
			PasswordOptions po = new PasswordOptions(ps);
			//stage.close();
		}
    	   
       });
       final VBox vb = new VBox();
       vb.setSpacing(5);
       //vb.getChildren().addAll(hbs);
       vb.getChildren().add(password);
       vb.getChildren().add(passBar);
       vb.getChildren().add(genOptions);
       scene.setRoot(vb);
       stage.show();
   }
       
   public static void main(String[] args) {
       launch(args);
   }
   
   private float CheckPasswordSecurity(String entered) {
	   float value = 0f;
	   boolean lowercaseFound = false;
	   boolean uppercaseFound = false;
	   boolean numberFound = false;
	   boolean spcaseFound = false;
	   boolean lengthCheck = false;
	   List<Character> lowercase = Arrays.asList(LOWERCASE_CHARS);
	   List<Character> uppercase = Arrays.asList(UPPERCASE_CHARS);
	   List<Character> numbers = Arrays.asList(NUMBER_CHARS);
	   List<Character> spcase = Arrays.asList(PUNCTUATION_CHARS);
	   //this is just a base system which will be used the other settings and page will lead into this check based on include escapeCharsBoolean and the length of the gen password
	   
	   for(int j = 0; j < entered.length(); j++)
	   {
		   if(useEscapeCharacters) {
		   if(lowercase.contains(entered.charAt(j)) && !lowercaseFound)
		   {
			  if(pLength == 4)
			  {
				  value += .20f;
			  }
			  else if(pLength == 5)
			  {
				  value += 1f/6f;
			  }
			  else if(pLength >= 6)
			  {
				  value += 1f/7f;
			  }
			   lowercaseFound = true;
		   }
		   if(uppercase.contains(entered.charAt(j)) && !uppercaseFound)
		   {
			   if(pLength == 4)
				  {
					  value += .20f;
				  }
				  else if(pLength == 5)
				  {
					  value += 1f/6f;
				  }
				  else if(pLength >= 6)
				  {
					  value += 1f/7f;
				  }
			   uppercaseFound = true;
		   }
		   if(numbers.contains(entered.charAt(j)) && !numberFound)
		   {
			   if(pLength == 4)
				  {
					  value += .20f;
				  }
				  else if(pLength == 5)
				  {
					  value += 1f/6f;
				  }
				  else if(pLength >= 6)
				  {
					  value += 1f/7f;
				  }
			   numberFound = true;
		   }
		   if(spcase.contains(entered.charAt(j)) && !spcaseFound)
		   {
			   if(pLength == 4)
				  {
					  value += .20f;
				  }
				  else if(pLength == 5)
				  {
					  value += 1f/6f;
				  }
				  else if(pLength >= 6)
				  {
					  value += 1f/7f;
				  }
			   spcaseFound = true;
		   }
		   if(entered.length() == pLength && !lengthCheck)
		   {
			   if(pLength == 4)
				  {
					  value += .20f;
				  }
				  else if(pLength == 5)
				  {
					  value += 1f/6f;
				  }
				  else if(pLength >= 6)
				  {
					  value += 1f/7f;
				  }
			   lengthCheck = true;
		   	}
		   }
		   else 
		   {
			   if(lowercase.contains(entered.charAt(j)) && !lowercaseFound)
			   {
				  if(pLength == 4)
				  {
					  value += .25f;
				  }
				  else if(pLength == 5)
				  {
					  value += .20f;
				  }
				  else if(pLength >= 6)
				  {
					  value += 1f/6f;
				  }
				   lowercaseFound = true;
			   }
			   if(uppercase.contains(entered.charAt(j)) && !uppercaseFound)
			   {
				   if(pLength == 4)
					  {
						  value += .25f;
					  }
				   
					  else if(pLength == 5)
					  {
						  value += .20f;
					  }
					  else if(pLength >= 6)
					  {
						  value += 1f/6f;
					  }
				   uppercaseFound = true;
			   }
			   if(numbers.contains(entered.charAt(j)) && !numberFound)
			   {
				   if(pLength == 4)
					  {
						  value += .25f;
					  }
					  else if(pLength == 5)
					  {
						  value += .20f;
					  }
					  else if(pLength >= 6)
					  {
						  value += 1/6f;
					  }
				   numberFound = true;
			   }
			   if(entered.length() >= pLength && !lengthCheck)
			   {
				   if(pLength == 4)
					  {
						  value += .25f;
					  }
					  else if(pLength == 5)
					  {
						  value += .4f;
					  }
					  else if(pLength >= 6)
					  {
						  value += 1f/6f;
					  }
				   lengthCheck = true;
			   	}
		   }
	   }
	   return value;
   }
}