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
import javafx.stage.Stage;

/**
 * JavaFX CalendarApp
 */
public class CalendarApp extends Application {
    Calendar calendar;
    Label label;
    ArrayList<TextArea> textArea = new ArrayList<>();
    Stage stage;
    Scene scene;
    ChoiceBox subroles, year;
    int bttnWidth = 150;
    int bttnHeight = 25;
    Year yearValue;
    Role roleValue;
    Date dateValue;
    String descValue;
    Exporter exporter = new Exporter();

    @Override
    public void start(Stage stage) throws InterruptedException {
        this.stage = stage;
        calendar = new Calendar();
        MainMenu();
    }

    public static void main(String[] args) {
        launch();

        //Activity activity = new Activity(Year.EVEN, new Date("May"), new Role("Boss"), "test description");
        //System.out.println(activity);
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
        textArea.clear();
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
        exportCalendar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Activity> activities = calendar.getActivities(2020, "Boss");
                exporter.printCalendar(activities, 2020);
            }
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
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(0, 10, 0, 10));
      roleValue = null;
      yearValue = null;
      // => text input box for role title
      Label yearLabel = new Label("Year: ");
      Label date = new Label("Date: ");
      Label role = new Label("Role: ");
      Label description = new Label("Description: ");
      textArea.add(new TextArea());
      textArea.add(new TextArea());
      textArea.get(0).setMaxHeight(30);
      textArea.get(0).setMaxWidth(300);
      textArea.get(1).setMaxHeight(30);
      textArea.get(1).setMaxWidth(300);
      // => add role button
      Button addActivity = new Button("Add Activity");
      addActivity.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Activity activity = new Activity(yearValue,
                new Date(textArea.get(0).getText()),
                roleValue, textArea.get(1).getText());
            calendar.add(activity);
            AddActivityMenu();
        }
      });

      Year[] yearValues = {Year.EVEN,Year.ODD,Year.EACH};
      ChoiceBox yearMenu = new ChoiceBox(FXCollections.observableArrayList(yearValues));
      yearMenu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          // if the item of the list is changed
          @Override
          public void changed(ObservableValue ov, Number value, Number new_value) {
              yearValue = yearValues[new_value.intValue()];
              System.out.println(yearValue + " selected");

          }
      });

      ChoiceBox roles = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
      roles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          // if the item of the list is changed
          public void changed(ObservableValue ov, Number value, Number new_value) {
              roleValue = Role.getRoles().get(new_value.intValue());
              System.out.println(roleValue + " selected");
          }
      });

      Button home = new Button("Home");
      home.setOnAction((ActionEvent t) -> {
          MainMenu();
      });

      gridPane.add(yearLabel, 0, 0);
      gridPane.add(date, 0, 1);
      gridPane.add(role, 0, 2);
      gridPane.add(description, 0, 3);
      gridPane.add(yearMenu,1,0);
      gridPane.add(textArea.get(0),1,1);
      gridPane.add(roles,1,2);
      gridPane.add(textArea.get(1),1,3);
      gridPane.add(addActivity,1,4,2,1);
      gridPane.add(home,1,5,2,1);


      scene = new Scene(gridPane, 640, 480);
      stage.setScene(scene);
      stage.show();
    }

    public void EditActivityMenu() {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        Button button = new Button("button 1");
        button.setOnAction((ActionEvent t) -> {
            System.out.println(textArea.get(0).getText());
            MainMenu();
        });
        textArea.add(new TextArea());
        textArea.get(0).setMaxHeight(10);
        textArea.get(0).setMaxWidth(300);
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
        gridPane.add(textArea.get(0), 1, 1, 2, 1);
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

        // => drop down menu for existing subroles
        subroles = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        subroles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value) {
                System.out.println(Role.getRoles().get(new_value.intValue()) + " selected");
            }
        });


        subroles.setMaxSize(bttnWidth,bttnHeight);
        // => add role button
        Button addRole = new Button("Add Role");
        addRole.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            Role role = new Role(textArea.get(0).getText());
            System.out.println(textArea.get(0).getText() + " added");
            AddRoleMenu();
          }
        });

        Button home = new Button("Home");
        home.setOnAction((ActionEvent t) -> {
            MainMenu();
        });
        textArea.add(new TextArea());
        textArea.get(0).setMaxHeight(5);
        textArea.get(0).setMaxWidth(300);
        gridPane.add(title, 0, 0);
        gridPane.add(textArea.get(0),1,0);
        gridPane.add(subroles, 1, 1);
        gridPane.add(addRole, 1, 2);
        gridPane.add(home,1,3);



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
