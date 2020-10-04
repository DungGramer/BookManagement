package BookManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Design/SignIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Book Management");
        Image icon = new Image("BookManagement/Design/styles/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
