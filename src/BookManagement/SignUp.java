package BookManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
  @FXML
  TextField tfUsername;
  @FXML
  PasswordField tfPassword;
  @FXML
  PasswordField tfRePassword;
  @FXML
  Button btnSignUp;

  public void signUp(ActionEvent event) throws SQLException, IOException {
    String username = tfUsername.getText();
    String password = tfPassword.getText();
    String rePassword = tfRePassword.getText();
    if(!password.equals(rePassword)) {
      tfPassword.requestFocus();
    } else {
      Controller controller = new Controller();
      Connection connected = controller.getConnection();

      String sql = "INSERT INTO `account`(`username`, `password`) VALUES ('" + username + "', '" + password + "')" ;
      PreparedStatement preparedStatement = connected.prepareStatement(sql);
      preparedStatement.execute();

      Stage stage = (Stage) tfUsername.getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/BookManagement.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);

      //Send username to BookManagement
      BookManagement bookManagement = loader.getController();
      bookManagement.setUsername(username);

      stage.setScene(scene);
      stage.show();
    }
  }
}
