/**
 * File: Calendar.java
 * Author: Praise Daramola praise24@uab.edu
 * Assignment:  P5-praise24 - EE333 Fall 2019
 * Vers: 2.0.0 11/08/2019 P.D - GUI redesign
 * Vers: 1.3.0 11/06/2019 P.D - Role hierarchy verification
 * Vers: 1.2.0 11/05/2019 P.D - debugging
 * Vers: 1.1.0 11/04/2019 P.D - Edit Role menu Implemented
 * Vers: 1.0.0 11/03/2019 P.D - GUI menus implementation
 * Vers: 0.1.0 11/01/2019 P.D - code partially functional
 *
 */
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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This app is used to keep track of activities assigned to different roles This
 * application gives the user the ability to create new roles and activities
 */
public class CalendarApp extends Application {

    static Calendar calendar;
    Label label;
    ArrayList<TextField> textArea = new ArrayList<>();
    Stage stage;
    Scene scene;
    ChoiceBox subroles, year, roleVals1;
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

    /**
     * starts the application
     *
     * @param stage
     * @throws InterruptedException
     */
    @Override
    public void start(Stage stage) throws InterruptedException {
        this.stage = stage;
        alert.setMinWidth(100);
        MainMenu();
    }

    /**
     * Main method for running the application
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Role all = new Role("all");
        calendar = Loader.load(); // loads the history
        launch();
        calendar.updateRoles(); // updates the calendar
        Loader.save(calendar); // saves the history
    }

    /**
     * This is the main menu of the app. This menu contains four buttons that
     * redirects to the other menus in the application
     */
    public void MainMenu() {
        calendar.updateRoles();
        textArea.clear();

        // redirects to the add activity menu
        Button addActivity = new Button("Add Activity");
        addActivity.setOnAction((ActionEvent t) -> {
            AddActivityMenu();
        });
        addActivity.setMaxSize(bttnWidth, bttnHeight);

        // redirects to the add role menu
        Button addRole = new Button("Add Role");
        addRole.setOnAction((ActionEvent t) -> {
            AddRoleMenu();
        });
        addRole.setMaxSize(bttnWidth, bttnHeight);

        // redirects to the edit role menu
        Button editRole = new Button("Edit Role");
        editRole.setOnAction((ActionEvent t) -> {
            EditRoleMenu();
        });
        editRole.setMaxSize(bttnWidth, bttnHeight);

        // redirects to the export calendar menu
        Button exportCalendar = new Button("Export Calendar");
        exportCalendar.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });
        exportCalendar.setMaxSize(bttnWidth, bttnHeight);

        //preparing the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.add(addActivity, 0, 0);
        gridPane.add(addRole, 0, 1);
        gridPane.add(editRole, 0, 2);
        gridPane.add(exportCalendar, 0, 3);

        // preparing and displaying the stage
        stage.getIcons().add(new Image("file:\\\\\\"
                + System.getProperty("user.dir") + "\\History\\index.png"));
        stage.setTitle("Calendar App v1.0");
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This menu is used to create an activity.
     */
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

        // add role button
        Button addActivity = new Button("Add Activity");
        addActivity.setOnAction((ActionEvent event) -> {
            boolean error = false; // used to verify if all inputs are available

            // verifies if there is a year or date input
            if (yearValue == null || roleValue == null) {
                error = true;
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText(null);
                alert1.setContentText("No Date/Role value selected!");
                alert1.showAndWait();
            }

            // checks if the date is valid
            if (Date.isValidDate(textArea.get(0).getText()) && error == false) {
                Activity activity = new Activity(yearValue,
                        new Date(textArea.get(0).getText()),
                        roleValue, descBox.getText());
                calendar.add(activity);
                textArea.get(0).setText("");
                descBox.setText("");
                AddActivityMenu();
            } else if (error == false) {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Information Dialog");
                alert2.setHeaderText(null);
                alert2.setContentText("Invalid date!");
                alert2.showAndWait();
            }
        });

        // drop down menu for the year
        Label yearLabel = new Label("Year: ");
        Year[] yearValues = {Year.EVEN, Year.ODD, Year.EACH};
        ChoiceBox yearMenu = new ChoiceBox(FXCollections.observableArrayList(yearValues));
        yearMenu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                yearValue = yearValues[new_value.intValue()];

            }
        });

        // drop down menu of existing roles
        Label role = new Label("Role: ");
        ChoiceBox rolesMenu = new ChoiceBox(
                FXCollections.observableArrayList(Role.getRoles()));
        rolesMenu.setMaxWidth(150);
        rolesMenu.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleValue = Role.getRoles().get(new_value.intValue());
            }
        });

        // allows the user to create new role from activity menu
        Button addRole = new Button("New Role");
        addRole.setOnAction((ActionEvent event) -> {
            AddRoleMenu();
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

    /**
     * used to create a new role
     */
    public void AddRoleMenu() {
        calendar.updateRoles();
        roleIndex = -1;
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
        subroles.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
            @Override
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

                // ensures there is a role name input before proceeding
                if (!(textArea.get(0).getText().equals("") && textArea.get(0).getText().equals("all"))) {
                    if (roleValue == null) {
                        roleValue = new Role(textArea.get(0).getText());
                    }
                    /**
                     *  checks to verify that the role does not exist and the 
                     *  role is not lower than its sub role
                    */
                    if (!(roleValue.matches(Role.getRoles().get(roleIndex))
                            && Role.getRoles().get(roleIndex).isSubrole(roleValue)
                            && Role.getRoles().get(roleIndex).toString().equals("all"))) {
                        Role.getRoles().get(roleValue.getIndex()).addSubRole(
                                Role.getRoles().get(roleIndex).toString());
                        alert.setText(Role.getRoles().get(roleIndex) + " added");
                        AddRoleMenu();
                    } 
                    /*
                     *  prevents the user from adding a subrole to the all pseudo
                     *  role
                     */ 
                    else if (Role.getRoles().get(roleIndex).toString().equals("all")) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("The 'all' role cannot be a sub role!");
                        alert.showAndWait();
                    } else if (Role.getRoles().get(roleIndex).isSubrole(roleValue)) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Cannot add sub role due to hierarchy!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Role cannot be added due to itself");
                        alert.showAndWait();
                    }
                } else if (textArea.get(0).getText().equals("all")) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The 'all' title already exists!");
                    alert.showAndWait();
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

    /**
     * Used to edit an existing role
     */
    public void EditRoleMenu() {
        calendar.updateRoles();
        roleIndex = -1;
        roleIndex0 = -1;
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
        roleVals1.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
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
                // ensures there is a role name input before proceeding
                if (roleIndex > 0) {
                    if (roleValue == null) {
                        roleValue = Role.getRoles().get(roleIndex);
                    }
                    // checks to verify that the role does not exist and the 
                    // role is not lower than its sub role
                    if (!(roleValue.matches(Role.getRoles().get(roleIndex0))
                            && roleValue.isSubrole(Role.getRoles().get(roleIndex0)))
                            && !Role.getRoles().get(roleIndex0).toString().equals("all")) {
                        Role.getRoles().get(roleValue.getIndex()).addSubRole(
                                Role.getRoles().get(roleIndex0).toString());
                        alert.setText(Role.getRoles().get(roleIndex0) + " added");
                        calendar.updateRoles();
                    } else if (Role.getRoles().get(roleIndex0).toString().equals("all")) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("The 'all' role cannot be a sub role!");
                        alert.showAndWait();
                    }else if (roleValue.isSubrole(Role.getRoles().get(roleIndex0))) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Cannot add sub role due to hierarchy!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Role cannot be added due to itself");
                        alert.showAndWait();
                    }
                } else if (roleIndex == 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The 'all' role cannot be modified!");
                    alert.showAndWait();
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

    /**
     * Menu used to export the calendar
     */
    public void ExportCalendarMenu() {
        calendar.updateRoles();
        textArea.clear();

        /**
         * used to export the calendar by role. Redirects to the export by role
         * menu
         */
        Button byRole = new Button("By Role");
        byRole.setOnAction((ActionEvent t) -> {
            ExportByRoleMenu();
        });
        byRole.setMaxSize(bttnWidth, bttnHeight);

        /**
         * used to export the calendar by year. Redirects to the export by year
         * menu
         */
        Button byYear = new Button("By Year");
        byYear.setOnAction((ActionEvent t) -> {
            ExportByYearMenu();
        });
        byYear.setMaxSize(bttnWidth, bttnHeight);

        /**
         * used to export the calendar by month. Redirects to the export by
         * month menu
         */
        Button byMonth = new Button("By Month");
        byMonth.setOnAction((ActionEvent t) -> {
            ExportByMonthMenu();
        });
        byMonth.setMaxSize(bttnWidth, bttnHeight);

        // Back button redirects to the main menu
        Button back = new Button("Back");
        back.setOnAction((ActionEvent t) -> {
            MainMenu();
        });

        // setting up the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.add(byRole, 0, 0);
        gridPane.add(byYear, 0, 1);
        gridPane.add(byMonth, 0, 2);
        gridPane.add(back, 0, 3);

        // displays the new stage
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This menu is used to select options for exporting by role
     */
    public void ExportByRoleMenu() {
        calendar.updateRoles();
        dateValue = "";
        roleIndex = -1;
        alert.setText("");
        textArea.clear();
        // creatinig the gird pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        Label filename = new Label("Filename:");
        textArea.add(new TextField()); // filename
        Label roleTitle = new Label("Role:");

        // browsse button for selecting the export file and directory
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

        ChoiceBox roleDates = new ChoiceBox(FXCollections.observableArrayList(Month.values()));
        roleDates.setMaxWidth(150);
        roleDates.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                dateValue = Month.values()[new_value.intValue()].toString();
            }
        });
        TextField roleYear = new TextField();

        // button for printing the calendar to the terminal
        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            try {
                if (roleIndex != -1) {
                    if (roleYear.getText().equals("") && dateValue.equals("")) {
                        ArrayList<Activity> activities = calendar.getActivities(
                                Role.getRoles().get(roleIndex));
                        exporter.print(activities, Role.getRoles().get(roleIndex));
                    } else if (!roleYear.getText().equals("") && dateValue.equals("")) {
                        // get calendar by year and role
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(
                                Integer.valueOf(roleYear.getText()),
                                Role.getRoles().get(roleIndex));
                        exporter.print(activities,
                                Role.getRoles().get(roleIndex),
                                Integer.valueOf(roleYear.getText()));
                    } else if (roleYear.getText().equals("") && !dateValue.equals("")) {
                        // get calendar by year and role
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(dateValue,
                                Role.getRoles().get(roleIndex));
                        exporter.print(activities,
                                Role.getRoles().get(roleIndex),
                                dateValue);
                    } else {
                        // get calendar by role, year and date
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(
                                Integer.valueOf(roleYear.getText()),
                                dateValue,
                                Role.getRoles().get(roleIndex));
                        exporter.print(activities,
                                Role.getRoles().get(roleIndex),
                                Integer.valueOf(roleYear.getText()),
                                dateValue);
                    }
                } else {
                    Alert alertbox = new Alert(AlertType.INFORMATION);
                    alertbox.setTitle("Information Dialog");
                    alertbox.setHeaderText(null);
                    alertbox.setContentText("No role selected!");
                    alertbox.showAndWait();
                }
            } catch (IndexOutOfBoundsException e) {

            }
        });

        // button for exporting to selected file
        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                // ensures a role is selected from the drop-down
                if (roleIndex != -1) {

                    if (roleYear.getText().equals("") && dateValue.equals("")) {
                        ArrayList<Activity> activities = calendar.getActivities(
                                Role.getRoles().get(roleIndex));
                        if (file != null) {
                            exporter.Export(activities, file, Role.getRoles().get(roleIndex));
                        }
                    } else if (!roleYear.getText().equals("") && dateValue.equals("")) {
                        // get calendar by year and role
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(
                                Integer.valueOf(roleYear.getText()),
                                Role.getRoles().get(roleIndex));
                        if (file != null) {
                            exporter.Export(activities, file,
                                    Role.getRoles().get(roleIndex),
                                    Integer.valueOf(roleYear.getText()));
                        }
                    } else if (roleYear.getText().equals("") && !dateValue.equals("")) {
                        // get calendar by year and role
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(dateValue,
                                Role.getRoles().get(roleIndex));
                        if (file != null) {
                            exporter.Export(activities, file,
                                    Role.getRoles().get(roleIndex),
                                    dateValue);
                        }
                    } else {
                        // get calendar by role, year and date
                        System.out.println(roleYear.getText());
                        ArrayList<Activity> activities = calendar.getActivities(
                                Integer.valueOf(roleYear.getText()),
                                dateValue,
                                Role.getRoles().get(roleIndex));
                        if (file != null) {
                            exporter.Export(activities,
                                    file,
                                    Role.getRoles().get(roleIndex),
                                    Integer.valueOf(roleYear.getText()),
                                    dateValue);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Alert alertbox = new Alert(AlertType.INFORMATION);
                alertbox.setTitle("Information Dialog");
                alertbox.setHeaderText(null);
                alertbox.setContentText("No activities exist/No role selected!");
                alertbox.showAndWait();
            } catch (FileNotFoundException ex) {
                Alert alertbox = new Alert(AlertType.INFORMATION);
                alertbox.setTitle("Information Dialog");
                alertbox.setHeaderText(null);
                alertbox.setContentText("No file selected!");
                alertbox.showAndWait();
            }
        });

        // drop-down menu of existing roles
        ChoiceBox roleMenu = new ChoiceBox(FXCollections.observableArrayList(Role.getRoles()));
        roleMenu.setMaxWidth(150);
        roleMenu.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                roleIndex = new_value.intValue();
            }
        });

        // home button for going back to the home screen
        Button back = new Button("Back");
        back.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });

        // setting up the grid pane
        gridPane.add(roleTitle, 0, 0);
        gridPane.add(roleMenu, 1, 0);
        gridPane.add(new Label("Date:"), 0, 1);
        gridPane.add(roleDates, 1, 1);
        gridPane.add(new Label("Year:"), 0, 2);
        gridPane.add(roleYear, 1, 2);
        gridPane.add(filename, 0, 3);
        gridPane.add(textArea.get(0), 1, 3);
        gridPane.add(browse, 2, 3);
        gridPane.add(export, 1, 4);
        gridPane.add(printCal, 2, 4);
        gridPane.add(back, 1, 5, 2, 1);

        // shows the scene
        scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This menu is used to select options for exporting by year
     */
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

        // browsse button for selecting the export file and directory
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

        // button for printing the calendar to the terminal
        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            ArrayList<Activity> activities = calendar.getActivities(
                    Integer.valueOf(textArea.get(0).getText()));
            exporter.print(activities, Integer.valueOf(textArea.get(0).getText()));
        });

        // button for exporting to selected file
        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                ArrayList<Activity> activities = calendar.getActivities(
                        Integer.valueOf(textArea.get(0).getText()));
                if (file != null) {
                    exporter.Export(activities, file, Integer.valueOf(textArea.get(0).getText()));
                }
            } catch (NumberFormatException e) {
                Alert alertbox = new Alert(AlertType.INFORMATION);
                alertbox.setTitle("Information Dialog");
                alertbox.setHeaderText(null);
                alertbox.setContentText("No activities exist!");
                alertbox.showAndWait();
            } catch (FileNotFoundException e) {
                Alert alertbox = new Alert(AlertType.INFORMATION);
                alertbox.setTitle("Information Dialog");
                alertbox.setHeaderText(null);
                alertbox.setContentText("File not found!");
                alertbox.showAndWait();
            }
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
    }

    /**
     * This menu is used to select options for exporting by month
     */
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

        // browsse button for selecting the export file and directory
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

        // button for printing the calendar to the terminal
        Button printCal = new Button("Print");
        printCal.setOnAction((ActionEvent t) -> {
            if (dateValue != null) {
                ArrayList<Activity> activities = calendar.getActivities(dateValue);
                exporter.print(activities);
            }

        });

        // button for exporting to selected file
        Button export = new Button("Export to File");
        export.setOnAction((ActionEvent t) -> {
            try {
                ArrayList<Activity> activities = calendar.getActivities(
                        dateValue);
                if (file != null) {
                    exporter.Export(activities, file);
                }

            } catch (FileNotFoundException e) {
                Alert alertbox = new Alert(AlertType.INFORMATION);
                alertbox.setTitle("Information Dialog");
                alertbox.setHeaderText(null);
                alertbox.setContentText("No activities exist!");
                alertbox.showAndWait();
            }
        });
        ChoiceBox dates = new ChoiceBox(FXCollections.observableArrayList(Month.values()));
        dates.setMaxWidth(150);
        dates.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                dateValue = Month.values()[new_value.intValue()].toString();
            }
        });

        // home button for going back to the home screen
        Button home = new Button("Back");
        home.setOnAction((ActionEvent t) -> {
            ExportCalendarMenu();
        });

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
    }
}
