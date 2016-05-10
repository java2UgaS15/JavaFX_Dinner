package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Dinner extends Application {
	//There are two types of Application classes. 
	//Select the one with javafx.application.Application.
	//Then the system will automatically ask you to override unimplemented method of Application:
	//public void start(Stage primaryStage)

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//1. Download and open pizza.jpeg in a picture view program, then right click and copy.
		//2. Right click src directory of your project, right click, choose new file, 
		//   enter file name pizza.jpeg in the text field popped,
		//   the system will warn this is an empty file.
		//   Proceed and then paste the content to the empty file (choose "yes to all").
		Image image = new Image(getClass().getResourceAsStream("../pizza.jpeg"));
				//new Image("file:/Users/user/Downloads/pizza.jpeg");
		        //The above local file link works, but awkward.
		        //You can also use http location, but then you need 
		        //to have Internet connection.
		Label label = new Label("Pizza tonight?", new ImageView(image));
		label.setContentDisplay(ContentDisplay.RIGHT);
		    //content will be placed to the right of the label
		label.setTooltip(new Tooltip("pizza picture"));
		    //put the cursor a little longer to the picture and you will see the tool tip hint.
		
		StackPane root = new StackPane();
		root.getChildren().add(label);
		
		Scene scene = new Scene(root, 380, 300);
		
		primaryStage.setTitle("What's for dinner?");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
}
