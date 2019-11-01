package edu.uab.praise24.p5;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX CalendarApp
 */
public class CalendarApp extends Application {

    Label label;
    TextArea textArea;
    Stage stage;
    Scene scene;
    int bttnWidth = 150;
    int bttnHeight = 25;

    @Override
    public void start(Stage stage) throws InterruptedException {
        this.stage = stage;
        MainMenu();
    }

    public static void main(String[] args) {
        launch();

        Activity activity = new Activity(Year.EVEN, new Date("May"), new Role("Boss"), "test description");
        System.out.println(activity);
    }

    public void run() {

        //=> View Calendar
        //=> Export Calendar
        //=> Add Role
        //=> Add Activity
        //=> Edit Activity
        //=> Edit Role
    }

    public void MainMenu() {

        Button addActivity = new Button("Add Activity");
        addActivity.setOnAction((ActionEvent t) -> {
            AddActivityMenu();
        });
        addActivity.setMaxSize(bttnWidth, bttnHeight);

        Button addRole = new Button("Add Role");
        addRole.setOnAction((ActionEvent t) -> {
            AddRoleMenu();
        });
        addRole.setMaxSize(bttnWidth, bttnHeight);

        Button editActivity = new Button("Edit Activity");
        editActivity.setOnAction((ActionEvent t) -> {
            EditActivityMenu();
        });
        editActivity.setMaxSize(bttnWidth, bttnHeight);

        Button editRole = new Button("Edit Role");
        editRole.setOnAction((ActionEvent t) -> {
            EditRoleMenu();
        });
        editRole.setMaxSize(bttnWidth, bttnHeight);

        Button viewCalendar = new Button("View Calendar");
        viewCalendar.setOnAction((ActionEvent t) -> {
            ViewCalendarMenu();
        });
        viewCalendar.setMaxSize(bttnWidth, bttnHeight);

        Button exportCalendar = new Button("Export Calendar");
        exportCalendar.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });
        exportCalendar.setMaxSize(bttnWidth, bttnHeight);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.add(addActivity, 0, 0);
        gridPane.add(editActivity, 0, 1);
        gridPane.add(addRole, 0, 2);
        gridPane.add(editRole, 0, 3);
        gridPane.add(viewCalendar, 0, 4);
        gridPane.add(exportCalendar, 0, 5);

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void AddActivityMenu() {
        /*var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        Button button = new Button("button 1");
        button.setOnAction((ActionEvent t) -> {
            EditActivityMenu();
        });

        label = new Label("Hello, JFx version " + javafxVersion + ", Java " + javaVersion + ".");
        scene = new Scene(new VBox(label, button), 640, 480);
        stage.setScene(scene);
        stage.show();*/
        System.out.println("add activity");
    }

    public void EditActivityMenu() {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        Button button = new Button("button 1");
        button.setOnAction((ActionEvent t) -> {
            System.out.println(textArea.getText());
            MainMenu();
        });
        textArea = new TextArea();
        textArea.setMaxHeight(10);
        textArea.setMaxWidth(300);
        ArrayList<String> items = new ArrayList<>();
        items.add("Really");
        items.add("Do not");

        ChoiceBox c = new ChoiceBox(FXCollections.observableArrayList(items));
        label = new Label("Hello, java fx" + javafxVersion + ", on Java " + javaVersion + ".");
        label.setLayoutY(400);
        label.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.add(label, 1, 0, 3, 1);
        gridPane.add(textArea, 1, 1, 2, 1);
        gridPane.add(button, 0, 1);

        c.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value) {

                // set the text for the label to the selected item
                System.out.println(items.get(new_value.intValue()) + " selected");
            }
        });

        gridPane.add(c, 0, 2);

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("edit activity");
    }

    public void AddRoleMenu() {
        System.out.println("add role");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // => text input box for role title
        Label title = new Label("Title: ");
        textArea = new TextArea();
        textArea.setMaxHeight(5);
        textArea.setMaxWidth(300);
        // => drop down menu for existing subroles
        ChoiceBox subroles = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));

        subroles.setMaxSize(bttnWidth,bttnHeight);
        // => add role button
        Button addRole = new Button("Add Role");
        addRole.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            System.out.println(textArea.getText());
          }
        });

        gridPane.add(title, 0, 0);
        gridPane.add(textArea,1,0);
        gridPane.add(subroles, 1, 1);
        gridPane.add(addRole, 1, 2);

        subroles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value) {

                // set the text for the label to the selected item
                System.out.println(Role.getRoles().get(new_value.intValue()) + " selected");
            }
        });

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void EditRoleMenu() {
        System.out.println("edit role");
    }

    public void ViewCalendarMenu() {
        System.out.println("view calendar");
    }

    public void ExportCalendarMenu() {
        System.out.println("export calendar");
    }

}
