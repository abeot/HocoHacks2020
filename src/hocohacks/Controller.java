package hocohacks;

import hocohacks.datamodel.TodoData;
import hocohacks.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Controller {

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        todoListView.getSelectionModel().selectedItemProperty().addListener((observableValue, todoItem, t1) -> {
            if (t1 != null) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                detailsTextArea.setText(item.getDescription());
                LocalDate deadline = item.getDeadline();
                deadlineLabel.setText(deadline.getMonth().toString().toUpperCase() +
                        ' ' + deadline.getDayOfMonth() +
                        ',' + ' ' + deadline.getYear());
            }
        });

        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
        detailsTextArea.setEditable(false);
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo Item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            // todoListView.getItems().add(newItem);
            todoListView.getSelectionModel().select(newItem);
        }
    }

    @FXML
    public void deleteItem() {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        TodoData.getInstance().getTodoItems().remove(selectedItem);
    }

    @FXML
    public void handleClickListView() {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        detailsTextArea.setText(selectedItem.getDescription());
        LocalDate deadline = selectedItem.getDeadline();
        deadlineLabel.setText(deadline.getMonth().toString().toUpperCase() +
                ' ' + deadline.getDayOfMonth() +
                ',' + ' ' + deadline.getYear());
    }
}
