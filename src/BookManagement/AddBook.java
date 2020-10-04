package BookManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBook {
  @FXML
  TextField tfTitle;
  @FXML
  TextField tfPrice;

  public void addBook(ActionEvent event) throws IOException, SQLException {
    Controller controller = new Controller();
    Connection connected = controller.getConnection();

    String title = tfTitle.getText();
    String price = tfPrice.getText();

    String sql = "INSERT INTO `book`(`id`, `title`, `price`) VALUES (null,'" + title + "' , '" + price + "')" ;
    PreparedStatement preparedStatement = connected.prepareStatement(sql);
    preparedStatement.execute();


    controller.moveToBookManagement(event);
  }

  public void moveToBookManagement(ActionEvent event) throws IOException, SQLException {
    Controller controller = new Controller();
    controller.moveToBookManagement(event);
  }
}
