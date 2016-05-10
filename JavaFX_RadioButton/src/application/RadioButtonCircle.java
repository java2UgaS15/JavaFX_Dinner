package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RadioButtonCircle extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create a RadioButton with caption "red",
		//you can also use the following two statements to
		//substitute for RadioButton rbRed = new RadioButton("red");
		//They have the same effect.
		//
		//RadioButton rbRed = new RadioButton();
		//rbRed.setText("red");
		RadioButton rbRed = new RadioButton("red");
		//Associate Color.RED with rbRed.
		rbRed.setUserData(Color.RED);
		
		RadioButton rbOrange = new RadioButton("orange");
		
		//Since we have no constant Color.ORANGE defined,
		//we need to create the color by using 
		//new Color(double red, double green, double blue, double opacity);
		//where red is a double number to represent the red component, 
		//which is at most 1 (obtained by 255/255).
		//In the rgb (red-green-blue) mode, 
		//each red, green, blue component is 8-bit binary number 
		//(remember 24-bit color?),
		//which accounts for a maximum of 255 in decimal number.
		//Normally people will use some integer <= 255 to represent
		//the part of red, green, blue component.
		//
		//google "orange rgb code" get 255, 165, 0,
		//since in JavaFX we use double for each red-green-blue component,
		//divide those integers by 255.
		//The last parameter is opacity, a double ranging from 0 to 1,
		//where 0 is totally transparency, 1 is for no transparency.
		rbOrange.setUserData(new Color(1, 165./255, 0, 1));
		
		RadioButton rbBlue = new RadioButton("blue");
		rbBlue.setUserData(Color.BLUE);
		
		RadioButton rbPink = new RadioButton("pink");
		rbPink.setUserData(new Color(1, 0, 1, 1));
		
		//Set rbRed, rbOrange, rbBlue, rbGreen to be in a toggle group.
		//So that only one item can be selected.
		//If we do not put them in a toggle group,
		//then we can select more than one button a time,
		//which is not the case of radio button.
		final ToggleGroup group = new ToggleGroup();
		rbRed.setToggleGroup(group);
		rbOrange.setToggleGroup(group);
		rbBlue.setToggleGroup(group);
		rbPink.setToggleGroup(group);
		
		//Draw a rectangle object r.
		//Since r needs to be used inside action listener,
		//by rule of action listener, that object needs to
		//be a constant -- hence the keyword final before Rectangle
		//(another option is to set it as data member).
		//Reason: all declarations and initializations of non-data-member-objects
		//are run once and exactly once,
		//but action listener may be called any time an event happens,
		//had those non-data-member objects been able to changed,
		//then action listener cannot trace their current values.
		//So a rule of thumb is variables/objects used inside action listener
		//must be declared as final (constant) unless they are data members.
	    final Rectangle r = new Rectangle();
	    //r.setX(60);
	    //r.setY(50);
	    r.setWidth(260);
	    r.setHeight(100);
	    r.setArcWidth(20);
	    r.setArcHeight(20);
	    
	    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
			        Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					r.setFill((Paint) group.getSelectedToggle().getUserData());
					   //group.getSelectedToggle() find out which toggle (button)
					   //in the toggle group is selected,
					   //then retrieve its user data, which we associate with a color,
					   //since group.getSelectedToggle().getUserData() returns Object type,
					   //we need to forcibly to set it as Paint type,
					   //which is needed for the setFill method of Rectangle object r.
				}
			}
		});
	    
		GridPane root = new GridPane();
		
		//GridPane object root is set in the center of the stage.
		root.setAlignment(Pos.CENTER);
		//Since root is a GridPane object, we can talk about gaps between columns and rows.
		//sgetHgap method of a GridPane object root set its horizontal space between columns
		//to be 10 pixels.
		root.setHgap(10);
		//Set the vertical space between rows of the grid pane object to be 20 pixels.
		root.setVgap(20);
		//Put rectangle object r to grid pane r at column 0 and row 0,
		//and spans through 4 columns and 4 rows. 
		//(The first column starts from 0, the first row starts from 0).
		root.add(r, 0, 0, 4, 4);
		//Put radio button object rbRed to the first column (column index 0)
		//and fifth row (row index 4).
		root.add(rbRed, 0, 4);
		root.add(rbOrange, 1, 4);
		root.add(rbBlue, 2, 4);
		root.add(rbPink, 3, 4);
		
		//Create a scene with grid pane root as its root node,
		//the scene is 300 pixels wide and 200 pixels high.
		Scene scene = new Scene(root, 300, 200);
		//primaryStage set scene to be its scene (like a stage in theater with scene setting).
		//primaryStage is like a window frame.
		primaryStage.setScene(scene);
		
		//Set the title of primaryStage.
		primaryStage.setTitle("Change Color of Rectangle");
		//Display primaryStage.
		primaryStage.show();	
	}
}
