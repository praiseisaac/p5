package edu.uab.praise24.p5;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * JavaFX CalendarApp
 */
public class CalendarApp extends Application {

    static Calendar calendar;
    Label label;
    ArrayList<TextField> textArea = new ArrayList<>();
    Stage stage;
    Scene scene;
    ChoiceBox subroles, year,roleVals1;
    int bttnWidth = 150;
    int bttnHeight = 25;
    Year yearValue;
    Role roleValue;
    String dateValue;
    String descValue;
    Exporter exporter = new Exporter();
    ArrayList<Role> subRoles = new ArrayList<>();
    int roleIndex, roleIndex0;
    Label alert = new Label("");
    File file;

    @Override
    public void start(Stage stage) throws InterruptedException {
        this.stage = stage;
        alert.setMinWidth(100);
        MainMenu();
    }

    public static void main(String[] args) throws FileNotFoundException {
        calendar = Loader.load();
        launch();
        System.out.println("Calendar Roles");
        calendar.updateRoles();
        for (int i = 0; i < calendar.getRoles().size();i++){
            System.out.println(calendar.getRoles().get(i));
            for (Role rolel : calendar.getRoles().get(i).getSubRoles()){
                System.out.println(rolel + " " + rolel.getIndex());
            }
        }
        System.out.println("All Roles");
        for (int i = 0; i < Role.getRoles().size();i++){
            System.out.println(Role.getRoles().get(i));
            for (Role rolel : Role.getRoles().get(i).getSubRoles()){
                System.out.println(Role.getRoles().get(i)+ " s " + rolel + " " + rolel.getIndex());
            }
        }
        Loader.save(calendar);
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
        calendar.updateRoles();
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

        Button editRole = new Button("Edit Role");
        editRole.setOnAction((ActionEvent t) -> {
            EditRoleMenu();
        });
        editRole.setMaxSize(bttnWidth, bttnHeight);

        Button viewCalendar = new Button("View Calendar");
        viewCalendar.setOnAction((ActionEvent t) -> {
            PrintCalendarMenu();
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
        gridPane.add(addRole, 0, 1);
        gridPane.add(editRole, 0, 2);
        gridPane.add(viewCalendar, 0, 3);
        gridPane.add(exportCalendar, 0, 4);

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void AddActivityMenu() {
        calendar.updateRoles();
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
                if (yearValue == null || roleValue == null) {
                    error = true;
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No Date/Role value selected!");
                    alert.showAndWait();
                }
                if (Date.isValidDate(textArea.get(0).getText()) && error == false) {
                    Activity activity = new Activity(yearValue,
                            new Date(textArea.get(0).getText()),
                            roleValue, descBox.getText());
                    calendar.add(activity);
                    textArea.get(0).setText("");
                    descBox.setText("");
                    AddActivityMenu();
                } else if (error == false) {
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
        Year[] yearValues = {Year.EVEN, Year.ODD, Year.EACH};
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
        gridPane.add(yearMenu, 1, 0);
        gridPane.add(textArea.get(0), 1, 1);
        gridPane.add(rolesMenu, 1, 2);
        gridPane.add(descBox, 1, 3);
        gridPane.add(addActivity, 1, 4, 2, 1);
        gridPane.add(home, 1, 5, 2, 1);
        gridPane.add(addRole, 2, 2);

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void AddRoleMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        System.out.println("add role");
        roleValue = null;
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // text input box for role title
        Label title = new Label("Title: ");
        textArea.add(new TextField());
        textArea.get(0).setMaxSize(300, 10);

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
                if (!textArea.get(0).getText().equals("")) {
                    if (roleValue == null) {
                        roleValue = new Role(textArea.get(0).getText());
                    }
                    if (!Role.getRoles().get(roleValue.getIndex()).isParentRole(Role.getRoles().get(roleIndex))) {
                            Role.getRoles().get(roleValue.getIndex()).addSubRole(
                                    Role.getRoles().get(roleIndex).toString());
                            alert.setText(Role.getRoles().get(roleIndex) + " added");
                            AddRoleMenu();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Cannot add sub role due to heirachy!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid role title!");
                    alert.showAndWait();
                }
            }
        });
        subroles.setMaxSize(bttnWidth, bttnHeight);

        // add role button
        Button addRole = new Button("Add Role");
        addRole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!textArea.get(0).getText().equals("")) {
                    if (roleValue == null) {
                        roleValue = new Role(textArea.get(0).getText());
                    }
                    calendar.updateRoles();
                    alert.setText(textArea.get(0).getText() + " created");
                    textArea.get(0).setText("");
                    AddRoleMenu();
                }
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
        gridPane.add(textArea.get(0), 1, 0, 2, 1);
        gridPane.add(sRole, 0, 1);
        gridPane.add(subroles, 1, 1);
        gridPane.add(addSubRole, 2, 1);
        gridPane.add(addRole, 1, 2);
        gridPane.add(home, 0, 3);
        gridPane.add(alert, 1, 3);
        gridPane.add(addActivity, 2, 3);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void EditRoleMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        roleIndex0 = -1;
        System.out.println("add role");
        roleValue = null;
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        // text input box for role title
        Label title = new Label("Title: ");
        textArea.add(new TextField());
        textArea.get(0).setMaxSize(300, 10);

        // drop down containing a list of existing roles
        roleVals1 = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        roleVals1.setMaxWidth(150);
        roleVals1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleIndex = new_value.intValue();
            }
        });

        // drop down containing a list of existing roles
        subroles = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        subroles.setMaxWidth(150);
        subroles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleIndex0 = new_value.intValue();
            }
        });

        // add subrole button confirms the subrole is added
        Label sRole = new Label("Sub Role: ");
        Button addSubRole = new Button("Add Sub Role");
        addSubRole.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (roleIndex != -1 && roleIndex0 != -1) {
                    if (roleValue == null) {
                        roleValue = Role.getRoles().get(roleIndex);
                    }
                    System.out.println(Role.getIndex(
                            textArea.get(0).getText())+ " " + roleIndex +" "+ roleIndex0);
                    //if (Role.getRoles().get(roleIndex).isHigher(Role.getRoles().get(roleIndex0))) {
                        if (roleIndex != -1) {
                            Role.getRoles().get(roleValue.getIndex()).addSubRole(
                                    Role.getRoles().get(roleIndex0).toString());
                            alert.setText(Role.getRoles().get(roleIndex0) + " added");
                            EditRoleMenu();
                        }
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid role title!");
                    alert.showAndWait();
                }
            }
        });
        subroles.setMaxSize(bttnWidth, bttnHeight);

        // add role button
        Button addRole = new Button("Done");
        addRole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (roleIndex != -1) {
                    if (roleValue == null) {
                        roleValue = Role.getRoles().get(roleIndex);
                    }
                    calendar.updateRoles();
                    alert.setText(textArea.get(0).getText() + " created");
                    textArea.get(0).setText("");
                    AddRoleMenu();
                }
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
        gridPane.add(roleVals1, 1, 0, 2, 1);
        gridPane.add(sRole, 0, 1);
        gridPane.add(subroles, 1, 1);
        gridPane.add(addSubRole, 2, 1);
        gridPane.add(addRole, 1, 2);
        gridPane.add(home, 0, 3);
        gridPane.add(alert, 1, 3);
        gridPane.add(addActivity, 2, 3);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void PrintCalendarMenu() {
        calendar.updateRoles();
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

        gridPane.add(home, 0, 0);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("view calendar");
    }

    public void ExportCalendarMenu() {
        calendar.updateRoles();
        textArea.clear();
        Button byRole = new Button("By Role");
        byRole.setOnAction((ActionEvent t) -> {
            ExportByRoleMenu();
        });
        byRole.setMaxSize(bttnWidth, bttnHeight);

        Button byYear = new Button("By Year");
        byYear.setOnAction((ActionEvent t) -> {
            ExportByYearMenu();
        });
        byYear.setMaxSize(bttnWidth, bttnHeight);

        Button byMonth = new Button("By Month");
        byMonth.setOnAction((ActionEvent t) -> {
            ExportByMonthMenu();
        });
        byMonth.setMaxSize(bttnWidth, bttnHeight);

        // home button for going back to the home screen
        Button back = new Button("Back");
        back.setOnAction((ActionEvent t) -> {
            MainMenu();
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.add(byRole, 0, 0);
        gridPane.add(byYear, 0, 1);
        gridPane.add(byMonth, 0, 2);
        gridPane.add(back, 0, 3);

        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void ExportByRoleMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        alert.setText("");
        textArea.clear();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        Label filename = new Label("Filename:");
        textArea.add(new TextField()); // filename
        Label roleTitle = new Label("Role:");

        Button browse = new Button("Browse");
        browse.setOnAction((ActionEvent t) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                file = fileChooser.showSaveDialog(stage);
                textArea.get(0).setText(file.toString());
                gridPane.add(textArea.get(0), 1, 1);
                scene = new Scene(gridPane, 640, 480);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                // skip
            }
        });

        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            try {
                ArrayList<Activity> activities = calendar.getActivities(
                        Role.getRoles().get(roleIndex));
                exporter.print(activities);
            } catch (IndexOutOfBoundsException e) {

            }
        });

        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                if (roleIndex != -1) {
                    ArrayList<Activity> activities = calendar.getActivities(
                            Role.getRoles().get(roleIndex));
                    if (file != null) {
                        exporter.Export(activities, file);
                    }
                }
            } catch (NullPointerException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No activities exist/No role selected!");
                alert.showAndWait();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            //exporter.Export(textArea.get(0));
        });

        ChoiceBox roleMenu = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        roleMenu.setMaxWidth(150);
        roleMenu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleIndex = new_value.intValue();
            }
        });

        // home button for going back to the home screen
        Button back = new Button("Back");
        back.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });
        System.out.println("edit role");

        gridPane.add(filename, 0, 1);
        gridPane.add(roleTitle, 0, 0);
        gridPane.add(textArea.get(0), 1, 1);
        gridPane.add(browse, 2, 1);
        gridPane.add(roleMenu, 1, 0);
        gridPane.add(export, 1, 2);
        gridPane.add(printCal, 2, 2);
        gridPane.add(back, 1, 3, 2, 1);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("export calendar");
        // input year
        // select role
        //
    }

    public void ExportByYearMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        alert.setText("");
        textArea.clear();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        Label yearTitle = new Label("Year:");
        textArea.add(new TextField()); // year
        Label filename = new Label("Filename:");
        textArea.add(new TextField()); // filename

        Button browse = new Button("Browse");
        browse.setOnAction((ActionEvent t) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                file = fileChooser.showSaveDialog(stage);
                textArea.get(1).setText(file.toString());
                gridPane.add(textArea.get(1), 1, 1);
                scene = new Scene(gridPane, 640, 480);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                // skip
            }
        });

        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            ArrayList<Activity> activities = calendar.getActivities(
                    Integer.valueOf(textArea.get(0).getText()));
            exporter.print(activities, Integer.valueOf(textArea.get(0).getText()));
        });

        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                ArrayList<Activity> activities = calendar.getActivities(
                        Integer.valueOf(textArea.get(0).getText()));
                if (file != null) {
                    exporter.Export(activities, file, Integer.valueOf(textArea.get(0).getText()));
                }
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No activities exist!");
                alert.showAndWait();
            }

            //exporter.Export(textArea.get(0));
        });

        // home button for going back to the home screen
        Button back = new Button("Back");
        back.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });

        gridPane.add(yearTitle, 0, 0);
        gridPane.add(filename, 0, 1);
        gridPane.add(textArea.get(0), 1, 0);
        gridPane.add(textArea.get(1), 1, 1);
        gridPane.add(browse, 2, 1);
        gridPane.add(export, 1, 2);
        gridPane.add(printCal, 2, 2);
        gridPane.add(back, 1, 3, 2, 1);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("export calendar");
        // input year
        // select role
        //
    }

    public void ExportByMonthMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        alert.setText("");
        textArea.clear();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        Label filename = new Label("Filename:");
        textArea.add(new TextField()); // filename

        Button browse = new Button("Browse");
        browse.setOnAction((ActionEvent t) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                file = fileChooser.showSaveDialog(stage);
                textArea.get(1).setText(file.toString());
                gridPane.add(textArea.get(1), 1, 1);
                scene = new Scene(gridPane, 640, 480);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                // skip
            }
        });

        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            if (dateValue != null) {
                ArrayList<Activity> activities = calendar.getActivities(dateValue);
                exporter.print(activities);
            }

        });

        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                ArrayList<Activity> activities = calendar.getActivities(
                        dateValue);
                if (file != null) {
                    exporter.Export(activities, file);
                }

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No activities exist!");
                alert.showAndWait();
            }

            //exporter.Export(textArea.get(0));
        });
        ChoiceBox dates = new ChoiceBox(FXCollections.observableArrayList(Month.values()));
        dates.setMaxWidth(150);
        dates.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value) {
                dateValue = Month.values()[new_value.intValue()].toString();
                System.out.println(dateValue + " selected");
            }
        });

        // home button for going back to the home screen
        Button home = new Button("Home");
        home.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });
        System.out.println("edit role");

        gridPane.add(filename, 0, 1);
        gridPane.add(textArea.get(0), 1, 1);
        gridPane.add(browse, 2, 1);
        gridPane.add(new Label("Date"), 0, 0);
        gridPane.add(dates, 1, 0);
        gridPane.add(export, 1, 2);
        gridPane.add(printCal, 2, 2);
        gridPane.add(home, 1, 3, 2, 1);

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
