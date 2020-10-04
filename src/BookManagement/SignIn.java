package BookManagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class SignIn {
  @FXML
  TextField tfUsername;
  @FXML
  PasswordField tfPassword;
  @FXML
  Button btnLogin;
  @FXML
  Hyperlink linkSignup;

  public void signIn(ActionEvent event) throws SQLException, IOException {
    Controller controller = new Controller();
    String username = tfUsername.getText();
    String password = tfPassword.getText();
    if(controller.checkLogin(username, password)) {
      Stage stage = (Stage) tfUsername.getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/BookManagement.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);

      //Send username to BookManagement
      BookManagement bookManagement = loader.getController();
      bookManagement.setUsername(username);

      stage.setScene(scene);
      stage.show();
    };
  }

  public void gotoSignUp(ActionEvent event) throws IOException {
    Stage stage = (Stage) tfUsername.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/SignUp.fxml"));
    Parent parent = loader.load();

    Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

}
