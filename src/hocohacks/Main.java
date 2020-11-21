package hocohacks;

import hocohacks.datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        try {
            TodoData.getInstance().storeTodoItems();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            TodoData.getInstance().loadToDoItems();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
