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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX CalendarApp
 */
public class CalendarApp extends Application {
    Calendar calendar;
    Label label;
    ArrayList<TextField> textArea = new ArrayList<>();
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
    ArrayList<Role> subRoles = new ArrayList<>();
    int roleIndex;
    Label alert = new Label("");

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
      alert.setText("");
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(0, 10, 0, 10));
      roleValue = null;
      yearValue = null;

      // text box for date and description
      textArea.add(new TextField());
      TextArea descBox = new TextArea();
      Label date = new Label("Date: ");
      textArea.get(0).setMaxWidth(150);
      Label description = new Label("Description: ");
      descBox.setMaxHeight(30);
      descBox.setMaxWidth(300);

      // => add role button
      Button addActivity = new Button("Add Activity");
      addActivity.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          boolean error = false;
          if (yearValue == null || roleValue == null){
            error = true;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No Date/Role value selected!");
            alert.showAndWait();
          }
          if (Date.isValidDate(textArea.get(0).getText()) && error == false){
            Activity activity = new Activity(yearValue,
            new Date(textArea.get(0).getText()),
            roleValue, textArea.get(1).getText());
            calendar.add(activity);
            textArea.get(0).setText("");
            descBox.setText("");
            AddActivityMenu();
          } else if ( error == false ){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid date!");
            alert.showAndWait();
          }

        }
      });

      // drop down menu for the year
      Label yearLabel = new Label("Year: ");
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

      // drop down menu of existing roles
      Label role = new Label("Role: ");
      ChoiceBox rolesMenu = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
      rolesMenu.setMaxWidth(150);
      rolesMenu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          // if the item of the list is changed
          public void changed(ObservableValue ov, Number value, Number new_value) {
              roleValue = Role.getRoles().get(new_value.intValue());
              System.out.println(roleValue + " selected");
          }
      });

      // allows the user to create new role from activity menu
      Button addRole = new Button("New Role");
      addRole.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          AddRoleMenu();
        }
      });

      // returns the user to the main menu
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
      gridPane.add(rolesMenu,1,2);
      gridPane.add(descBox,1,3);
      gridPane.add(addActivity,1,4,2,1);
      gridPane.add(home,1,5,2,1);
      gridPane.add(addRole,2,2);


      scene = new Scene(gridPane, 640, 480);
      stage.setScene(scene);
      stage.show();
    }

    public void EditActivityMenu() {
      alert.setText("");
      // creatinig the gird pane
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(0, 10, 0, 10));

      // home button for going back to the home screen
      Button home = new Button("Home");
      home.setOnAction((ActionEvent t) -> {
          MainMenu();
      });
      System.out.println("edit role");


      gridPane.add(home,0,0);

      // shows the scene
      scene = new Scene(gridPane, 640, 480);
      stage.setScene(scene);
      stage.show();
        System.out.println("edit activity");
    }

    public void AddRoleMenu() {
        System.out.println("add role");
        alert.setText("");
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));


        // text input box for role title
        Label title = new Label("Title: ");
        textArea.add(new TextField());
        textArea.get(0).setMaxSize(300,10);

        // drop down containing a list of existing roles
        subroles = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        subroles.setMaxWidth(150);
        subroles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleIndex = new_value.intValue();
            }
        });

        // add subrole button confirms the subrole is added
        Label sRole = new Label("Sub Role: ");
        Button addSubRole = new Button("Add Sub Role");
        addSubRole.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            subRoles.add(Role.getRoles().get(roleIndex));
            alert.setText(Role.getRoles().get(roleIndex) + " added");
            AddRoleMenu();
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
              }
            AddRoleMenu();
          }
        });
        subroles.setMaxSize(bttnWidth,bttnHeight);

        // add role button
        Button addRole = new Button("Add Role");
        addRole.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            Role role = new Role(textArea.get(0).getText());
            for (Role srole: subRoles){
              role.addSubRole(srole);
            }
            subRoles.clear();
            System.out.println(textArea.get(0).getText() + " added");
            textArea.get(0).setText("");
            AddRoleMenu();
          }
        });

        // goes into the activity menu
        Button addActivity = new Button("Activity Menu");
        addActivity.setOnAction((ActionEvent t) -> {
            AddActivityMenu();
        });

        // home button for going back to the home screen
        Button home = new Button("Home");
        home.setOnAction((ActionEvent t) -> {
            MainMenu();
        });

        // assigning components to the gird pane
        gridPane.add(title, 0, 0);
        gridPane.add(textArea.get(0),1,0,2,1);
        gridPane.add(sRole,0,1);
        gridPane.add(subroles, 1, 1);
        gridPane.add(addSubRole,2,1);
        gridPane.add(addRole, 1, 2);
        gridPane.add(home,0,3);
        gridPane.add(alert,1,3);
        gridPane.add(addActivity,2,3);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void EditRoleMenu() {
        alert.setText("");
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // home button for going back to the home screen
        Button home = new Button("Home");
        home.setOnAction((ActionEvent t) -> {
            MainMenu();
        });
        System.out.println("edit role");


        gridPane.add(home,0,0);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void ViewCalendarMenu() {
        // home button for going back to the home screen
        alert.setText("");
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // home button for going back to the home screen
        Button home = new Button("Home");
        home.setOnAction((ActionEvent t) -> {
            MainMenu();
        });
        System.out.println("edit role");


        gridPane.add(home,0,0);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("view calendar");
    }

    public void ExportCalendarMenu() {
      alert.setText("");
      // creatinig the gird pane
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);
      gridPane.setPadding(new Insets(0, 10, 0, 10));

      // home button for going back to the home screen
      Button home = new Button("Home");
      home.setOnAction((ActionEvent t) -> {
          MainMenu();
      });
      System.out.println("edit role");


      gridPane.add(home,0,0);

      // shows the scene
      scene = new Scene(gridPane, 640, 480);
      stage.setScene(scene);
      stage.show();
        System.out.println("export calendar");
        // input year
        // select role
        //
    }

}
