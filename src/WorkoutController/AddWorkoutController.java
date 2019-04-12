package WorkoutController;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class AddWorkoutController {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    public Workout addWorkout()
    {
        Workout newWorkout = new Workout(nameField.getText(), datePicker.getValue());
        return newWorkout;
    }

    @FXML
    public void editWorkout(Workout w)
    {
        if(!nameField.getText().equals(w.getName()))
        {
            w.setName(nameField.getText());
        }
        if(!datePicker.getValue().equals(w.getDate()))
        {
            w.setDate(datePicker.getValue());
        }
    }
}
