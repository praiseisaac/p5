package edu.uab.praise24.p5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX CalendarApp
 */
public class CalendarApp extends Application {
    Stage stage;
    @Override
    public void start(Stage stage) throws InterruptedException {
        this.stage = stage;
        AddActivityMenu();
    }

    public static void main(String[] args) {
        launch();
        
        Activity activity = new Activity(Year.EVEN,new Date("May"),new Role("Boss"),"test description");
        System.out.println(activity);
    }

    public void run(){

      //=> View Calendar


      //=> Export Calendar

      //=> Add Role

      //=> Add Activity

      //=> Edit Activity

      //=> Edit Role

  }

  public void MainMenu(Stage stage){

  }

  public void AddActivityMenu(){
    var javaVersion = SystemInfo.javaVersion();
    var javafxVersion = SystemInfo.javafxVersion();
    Button button = new Button("button 1");
    button.setOnAction((ActionEvent t) -> {
        EditActivityMenu();
    });

    var label = new Label("Hello, JavaFX " + javafxVersion + ", Java " + javaVersion + ".");
    var scene = new Scene(new VBox(label,button), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public void EditActivityMenu(){
    var javaVersion = SystemInfo.javaVersion();
    var javafxVersion = SystemInfo.javafxVersion();
    Button button = new Button("button 1");
    button.setOnAction((ActionEvent t) -> {
        AddActivityMenu();
    });
    var label = new Label("Hello, JavaFX " + javafxVersion);
    var scene = new Scene(new HBox(label,button), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public void AddRoleMenu(){

  }

  public void EditMenu(){

  }

}
