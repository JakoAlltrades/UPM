import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

int pLength = 4;
boolean useEscapeCharacters = false;
boolean useNumberCharacters = true;
boolean useUpperCharacters = true;
boolean useLowerCharacters = true;
boolean useSymbolCharacters = false;
private VBox vb;

public VBox getVb() {
	return vb;
}

public void setVb(VBox vb) {
	this.vb = vb;
}

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

public boolean getUseNumberCharacters() {
	return useNumberCharacters;
}

public void setUseNumberCharacters(boolean useNumberCharacters) {
	this.useNumberCharacters= useNumberCharacters;
}

public boolean getUseLowerCharacters() {
	return useLowerCharacters;
}

public void setUseLowerCharacters(boolean useLowerCharacters) {
	this.useLowerCharacters = useLowerCharacters;
}

public boolean getUseUpperCharacters() {
	return useUpperCharacters;
}

public void setUseUpperCharacters(boolean useUpperCharacters) {
	this.useUpperCharacters= useUpperCharacters;
}

public boolean getUseSymbolCharacters() {
	return useSymbolCharacters;
}

public void setUseSymbolCharacters(boolean useSymbolCharacters) {
	this.useSymbolCharacters= useSymbolCharacters;
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
       Scene scene = new Scene(root, 300, 100);
       stage.setScene(scene);
       stage.setTitle("Progress Controls");
       ProgressBar passBar = new ProgressBar();
       passBar.setProgress(securityValue);
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
			PasswordOptions po = new PasswordOptions(ps, IsDark());
			//stage.close();
		}
    	   
       });
       
       Button generatePassword = new Button("Generate Password");
       generatePassword.setOnAction(new EventHandler(){

		@Override
		public void handle(Event e) {
			// TODO Auto-generated method stub
			String temp;
			do {
				temp = (GeneratePassword(pLength));
			}while(!CheckPassStrong(temp));
			password.setText(temp);
			System.out.println(password.getText());
			passBar.setProgress(securityValue);
		} 
       });
       vb = new VBox();
       if(IsDark())
       {
    	  vb.setStyle("-fx-background: black;"); 
       }
       HBox pass = new HBox(new Label("Password: "), password);
       HBox buttons = new HBox(genOptions, generatePassword);
       vb.setSpacing(5);
       //vb.getChildren().addAll(hbs);
       vb.getChildren().addAll(pass);
       vb.getChildren().add(passBar);
       vb.getChildren().addAll(buttons);
       scene.setRoot(vb);
       stage.show();
   }
       
   private String GeneratePassword(int PassLength) {
		SecureRandom random = new SecureRandom();
		StringBuffer passwordBuffer = new StringBuffer();
		boolean usedUpper = false;
		boolean usedNumber = false;
		boolean usedSymbol = false;
		if (useSymbolCharacters || (useUpperCharacters && useLowerCharacters && useNumberCharacters && useEscapeCharacters)) {

			for (int i = 0; i < PassLength; i++) {
				passwordBuffer.append(EXTRA_ALLOWED_CHARS[random.nextInt(EXTRA_ALLOWED_CHARS.length)]);
			}
		}
		else if(useUpperCharacters && useLowerCharacters && useNumberCharacters && !useEscapeCharacters) {
			for (int i = 0; i < PassLength; i++) {
				passwordBuffer.append(ALLOWED_CHARS[random.nextInt(ALLOWED_CHARS.length)]);
			}
		} 
		else if(useUpperCharacters && useLowerCharacters && !useNumberCharacters && useEscapeCharacters) {
			for (int i = 0; i < PassLength; i++) {
				int r = random.nextInt(3);
				switch(r)
				{
					case 0:	passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
						break;
					case 1: passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(LOWERCASE_CHARS.length)]);
						break;
					case 2: passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
						break;
				}
			}
		}
		else if(useUpperCharacters && !useLowerCharacters && useNumberCharacters && useEscapeCharacters) {
			for (int i = 0; i < PassLength; i++) {
				int r = random.nextInt(3);
				switch(r)
				{
					case 0:	passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
						break;
					case 1: passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
						break;
					case 2: passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
						break;
				}
			}
		}
		else if(!useUpperCharacters && useLowerCharacters && useNumberCharacters && useEscapeCharacters) {
			for (int i = 0; i < PassLength; i++) {
				int r = random.nextInt(3);
				switch(r)
				{
					case 0:	passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(LOWERCASE_CHARS.length)]);
						break;
					case 1: passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
						break;
					case 2: passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
						break;
				}
			}
		}
		else if(useUpperCharacters && useLowerCharacters && !useNumberCharacters && !useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(LOWERCASE_CHARS.length)]);
				}
			}
		}
		else if(!useUpperCharacters && !useLowerCharacters && useNumberCharacters && useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
				}
			}
		}else if(useUpperCharacters && !useLowerCharacters && !useNumberCharacters && useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
				}
			}
		}
		else if(useUpperCharacters && !useLowerCharacters && useNumberCharacters && !useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
				}
			}
		}
		else if(!useUpperCharacters && useLowerCharacters && useNumberCharacters && !useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(LOWERCASE_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
				}
			}
		}
		else if(!useUpperCharacters && useLowerCharacters && !useNumberCharacters && useEscapeCharacters)
		{
			for(int i = 0; i < PassLength; i++)
			{
				if(random.nextBoolean())
				{
					passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(LOWERCASE_CHARS.length)]);
				}
				else
				{
					passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
				}
			}
		}
		else if(useUpperCharacters && !useLowerCharacters && !useNumberCharacters && !useEscapeCharacters)
		{
			passwordBuffer.append(UPPERCASE_CHARS[random.nextInt(UPPERCASE_CHARS.length)]);
		}
		else if(!useUpperCharacters && useLowerCharacters && !useNumberCharacters && !useEscapeCharacters)
		{
			passwordBuffer.append(LOWERCASE_CHARS[random.nextInt(ALLOWED_CHARS.length)]);
		}
		else if(!useUpperCharacters && !useLowerCharacters && useNumberCharacters && !useEscapeCharacters)
		{
			passwordBuffer.append(NUMBER_CHARS[random.nextInt(NUMBER_CHARS.length)]);
		}
		else if(!useUpperCharacters && !useLowerCharacters && !useNumberCharacters && useEscapeCharacters)
		{
			passwordBuffer.append(PUNCTUATION_CHARS[random.nextInt(PUNCTUATION_CHARS.length)]);
		}
		else
		{
			System.out.println("ERROR: There are no allowed characters will generate one with all");
			for (int i = 0; i < PassLength; i++) {
				passwordBuffer.append(EXTRA_ALLOWED_CHARS[random.nextInt(EXTRA_ALLOWED_CHARS.length)]);
			}
		}
		return passwordBuffer.toString();
	} // End GeneratePassword()
   
   public static void main(String[] args) {
       launch(args);
   }
   
   private boolean CheckPassStrong(String Pass) {
	   boolean passed = false;
		if ((useEscapeCharacters && useLowerCharacters && useUpperCharacters && useNumberCharacters) || useSymbolCharacters) {
			if ((InclUpperCase(Pass)) && (InclLowerCase(Pass)) && (InclNumber(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		} else if (!useEscapeCharacters && useLowerCharacters && useUpperCharacters && useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclLowerCase(Pass)) && (InclNumber(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		}
		else if(useEscapeCharacters && !useLowerCharacters && useUpperCharacters && useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclNumber(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		}
		else if(useEscapeCharacters && useLowerCharacters && !useUpperCharacters && useNumberCharacters){
			if ((InclLowerCase(Pass)) && (InclNumber(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		}
		else if(useEscapeCharacters && useLowerCharacters && useUpperCharacters && !useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclLowerCase(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed =  true;
			} 
		}
		
		else if (!useEscapeCharacters && !useLowerCharacters && useUpperCharacters && useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclNumber(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		}
		else if(useEscapeCharacters && useLowerCharacters && !useUpperCharacters && !useNumberCharacters){
			if ((InclLowerCase(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed =  true;
			} 
		}
		else if (!useEscapeCharacters && useLowerCharacters && !useUpperCharacters && useNumberCharacters){
			if ((InclLowerCase(Pass)) && (InclNumber(Pass))) {
				securityValue = 1f;
				passed = true;
			} 
		}
		else if(useEscapeCharacters && !useLowerCharacters && useUpperCharacters && !useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed =  true;
			} 
		}
		else if(useEscapeCharacters && !useLowerCharacters && !useUpperCharacters && useNumberCharacters){
			if ((InclNumber(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed =  true;
			} 
		}
		else if(useEscapeCharacters && !useLowerCharacters && useUpperCharacters && !useNumberCharacters){
			if ((InclUpperCase(Pass)) && (InclEscape(Pass))) {
				securityValue = 1f;
				passed =  true;
			} 
		}
		else if(!useEscapeCharacters && useLowerCharacters && useUpperCharacters && !useNumberCharacters)
		{
			if ((InclLowerCase(Pass)) && (InclUpperCase(Pass))) {
				securityValue = 1f;
				passed =  true;
			}
		}
		else if(useEscapeCharacters && !useLowerCharacters && !useUpperCharacters && !useNumberCharacters)
		{
			if(InclEscape(Pass))
			{
				securityValue = 1f;
				passed =  true;
			}
		}
		else if(!useEscapeCharacters && useLowerCharacters && !useUpperCharacters && !useNumberCharacters)
		{
			if(InclLowerCase(Pass))
			{
				securityValue = 1f;
				passed =  true;
			}
		}
		else if(!useEscapeCharacters && !useLowerCharacters && useUpperCharacters && !useNumberCharacters)
		{
			if(InclUpperCase(Pass))
			{
				securityValue = 1f;
				passed =  true;
			}
		}
		else if(!useEscapeCharacters && !useLowerCharacters && !useUpperCharacters && useNumberCharacters)
		{
			if(InclNumber(Pass))
			{
				securityValue = 1f;
				passed =  true;
			}
		}
		return passed;
	} // End CheckPassStrong()

	/**
	 * This method returns true if the generated password contains at least one
	 * Upper Case character. If not, then the method returns false.
	 *
	 * @param GeneratedPass
	 * @return true or false, depending on existence of one upper case letter.
	 */
	private static boolean InclUpperCase(String GeneratedPass) {
		char[] PassWordArray = GeneratedPass.toCharArray();
		boolean find = false;
		outerloop: for (int i = 0; i < PassWordArray.length; i++) {
			for (int j = 0; j < UPPERCASE_CHARS.length; j++) {
				if (PassWordArray[i] == UPPERCASE_CHARS[j]) {
					find = true;
					break outerloop;
				}
			}
		}
		if (find) {
			return true;
		} else {
			return false;
		}
	} // End InclUpperCase()

	/**
	 * This method returns true if the generated password contains at least one
	 * Lower Case character. If not, then the method returns false.
	 *
	 * @param GeneratedPass
	 * @return true or false, depending on existence of one lower case letter.
	 */
	private static boolean InclLowerCase(String GeneratedPass) {
		char[] PassWordArray = GeneratedPass.toCharArray();
		boolean find = false;
		outerloop: for (int i = 0; i < PassWordArray.length; i++) {
			for (int j = 0; j < LOWERCASE_CHARS.length; j++) {
				if (PassWordArray[i] == LOWERCASE_CHARS[j]) {
					find = true;
					break outerloop;
				}
			}
		}
		if (find) {
			return true;
		} else {
			return false;
		}
	} // End InclLowerCase()

	/**
	 * This method returns true if the generated password contains at least one
	 * Number. If not, then the method returns false.
	 *
	 * @param GeneratedPass
	 * @return true or false, depending on existence of one number.
	 */
	private static boolean InclNumber(String GeneratedPass) {
		char[] PassWordArray = GeneratedPass.toCharArray();
		boolean find = false;
		outerloop: for (int i = 0; i < PassWordArray.length; i++) {
			for (int j = 0; j < NUMBER_CHARS.length; j++) {
				if (PassWordArray[i] == NUMBER_CHARS[j]) {
					find = true;
					break outerloop;
				}
			}
		}
		if (find) {
			return true;
		} else {
			return false;
		}
	} // End InclNumber()

	/**
	 * This method returns true if the generated password contains at least one
	 * Escape character. If not, then the method returns false.
	 *
	 * @param GeneratedPass
	 * @return true or false, depending on existence of one escape character.
	 */
	private static boolean InclEscape(String GeneratedPass) {
		char[] PassWordArray = GeneratedPass.toCharArray();
		boolean find = false;
		outerloop: for (int i = 0; i < PassWordArray.length; i++) {
			for (int j = 0; j < PUNCTUATION_CHARS.length; j++) {
				if (PassWordArray[i] == PUNCTUATION_CHARS[j]) {
					find = true;
					break outerloop;
				}
			}
		}
		if (find) {
			return true;
		} else {
			return false;
		}
	} // End InclEscape()
   private boolean IsDark(){
	   try {
		   String path = System.getProperty("user.dir") + "/styleFile.txt"; 
		   File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
			String string = stringBuffer.toString();
			 if(string.equals("Dark"))
			   {
				   return true;
			   }
			 else
			 {
				 return false;    
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
	return false;
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