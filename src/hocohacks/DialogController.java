package hocohacks;

import hocohacks.datamodel.TodoData;
import hocohacks.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResults() {
        String task = shortDescriptionField.getText().trim();
        String description = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(task, description, deadline);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
