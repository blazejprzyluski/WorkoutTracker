package GUI;

import Data.dayData.Day;
import Data.dayData.Monday;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TableView<Day> daysTable;

    @FXML
    private TableColumn mondayColumn;

    @FXML
    private TableColumn tuesdayColumn;

    @FXML
    private TableColumn wendesdayColumn;

    @FXML
    private TableColumn thursdayColumn;

    @FXML
    private TableColumn fridayColumn;

    @FXML
    private TableColumn saturdayColumn;

    @FXML
    private TableColumn sundayColumn;

    private List<Day> daysOfTheWeek = new ArrayList<>();


    public void initialize()
    {
        Monday monday = new Monday();

        daysTable.getItems().add(monday);
        daysTable.refresh();
    }

    @FXML
    public void contextMenuStub()
    {
        System.out.println("I was clicked!");
    }

    @FXML
    public void addWorkoutToDay()
    {

        //Workout workoutToAdd = createWorkout();
        //daysTable.getSelectionModel().getSelectedItem().addWorkout(workoutToAdd);

    }

}
