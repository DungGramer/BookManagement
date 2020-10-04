package BookManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditBook {
  @FXML
  TextField tfID;
  @FXML
  TextField tfTitle;
  @FXML
  TextField tfPrice;

  public void editBook(ActionEvent event) throws SQLException, IOException {
    Controller controller = new Controller();
    Connection connected = controller.getConnection();

    String id = tfID.getText();
    String title = tfTitle.getText();
    String price = tfPrice.getText();

    String sql = "UPDATE book SET `title` = '" + title + "', `price` = '" + price + "' WHERE id = '" + id + "'";
    PreparedStatement preparedStatement = connected.prepareStatement(sql);
    preparedStatement.execute();

    controller.moveToBookManagement(event);
  }

  public void moveToBookManagement(ActionEvent event) throws IOException, SQLException {
    Controller controller = new Controller();
    controller.moveToBookManagement(event);
  }

  public void setValue(Book book) {
    tfID.setText(book.getId());
    tfTitle.setText(book.getTitle());
    tfPrice.setText(book.getPrice());
  }
}
