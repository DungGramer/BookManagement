package BookManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
  private String url = "jdbc:mysql://localhost:3306/library?useLegacyDatetimeCode=true&serverTimezone=UTC";
  private String username = "root";
  private String password = "";
  public Connection connection;

  public Controller() throws SQLException {
    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Cannot connect to Database");
      alert.setContentText("Please open database and try again!");
      alert.showAndWait();
    }
  }

  public boolean checkLogin(String username, String password) throws SQLException {
    String sql = "SELECT * FROM `account` WHERE `username` = '" + username +"' AND `password` = '" + password + "'";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery(sql);
    if(resultSet.next()) {
      return true;
    } return false;
  }

  public void moveToBookManagement(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/BookManagement.fxml"));
    Parent parent = loader.load();
    Scene scene = new Scene(parent);

    stage.setScene(scene);
    stage.show();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Connection getConnection() {
    return connection;
  }

}
