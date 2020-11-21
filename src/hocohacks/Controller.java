package hocohacks;

import hocohacks.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label deadlineLabel;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail Birthday Card",
                "Buy a birthday card for John",
                LocalDate.of(2016, Month.APRIL, 25));
        todoItems = new ArrayList<>();
        todoItems.add(item1);

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

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
        detailsTextArea.setEditable(false);
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
