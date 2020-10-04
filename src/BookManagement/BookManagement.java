package BookManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookManagement implements Initializable {
  @FXML
  TextField tfSearch;
  @FXML
  Label txtAccount;
  @FXML
  Button btnAddBook;
  @FXML
  Hyperlink linkLogout;
  @FXML
  TableColumn colID;
  @FXML
  TableColumn colTitle;
  @FXML
  TableColumn colPrice;
  @FXML
  TableView tbBook;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      loadTable();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void loadTable() throws SQLException {
    Controller controller = new Controller();
    Connection connected = controller.getConnection();
    String sql = "SELECT * FROM book";
    PreparedStatement statement = connected.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery(sql);

    colID.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
    colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
    colPrice.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));

    tbBook.setItems(getAllBook(resultSet));
  }

  public void moveToAddBook(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/AddBook.fxml"));
    Parent parent = loader.load();
    Scene scene = new Scene(parent);

    stage.setScene(scene);
    stage.show();
  }

  public void moveToEditBook(ActionEvent event) throws IOException {
    try {
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/EditBook.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);

      //Send info to EditBook
      EditBook editBook = loader.getController();
      Book selected = (Book) tbBook.getSelectionModel().getSelectedItem();
      editBook.setValue(selected);

      stage.setScene(scene);
      stage.show();
    } catch (Exception exception) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Select column");
      alert.setContentText("Please select column you want edit");
      alert.showAndWait();

    }
  }

  public void searching(ActionEvent event) throws SQLException {
    String finding = tfSearch.getText();
    System.out.println(finding);
    if (finding.isEmpty()) loadTable();
    else {
    Controller controller = new Controller();
    Connection connected = controller.getConnection();

    String sql = "SELECT * FROM book WHERE id = '" + finding + "'" ;
    PreparedStatement statement = connected.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery(sql);

    colID.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
    colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
    colPrice.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));

    tbBook.setItems(getAllBook(resultSet));
    }
  }

  public void moveToDelete(ActionEvent event) throws SQLException {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Delete Book");
    alert.setHeaderText("Do you want to delete?");

    ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
    ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

    Optional <ButtonType> result = alert.showAndWait();

    if(result.get() == buttonTypeYes) {
      Controller controller = new Controller();
      Connection connected = controller.getConnection();
      Book selected = (Book) tbBook.getSelectionModel().getSelectedItem();

      String sql = "DELETE FROM book WHERE id = '" + selected.getId() + "'" ;
      PreparedStatement preparedStatement = connected.prepareStatement(sql);
      preparedStatement.execute();
      loadTable();
    }  else {
      loadTable();
    }
  }

  public void signOut(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Design/SignIn.fxml"));
    Parent parent = loader.load();
    Scene scene = new Scene(parent);

    stage.setScene(scene);
    stage.show();
  }

  public void setUsername(String str) {
    txtAccount.setText(str);
  }

  private ObservableList<Book> getAllBook(ResultSet resultSet) throws SQLException {
    ObservableList<Book> books = FXCollections.observableArrayList();

    while (resultSet.next()) {
      String id = resultSet.getString(1);
      String title = resultSet.getString(2);
      String price = resultSet.getString(3);
      books.add(new Book(id, title, price));
    }
    return books;
  }
}
