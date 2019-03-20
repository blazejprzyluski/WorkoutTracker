package GUI;

import Data.Day;
import Data.workoutData.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
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
        Day nextDay = new Day();
        nextDay.setDate(LocalDate.now());
        daysOfTheWeek.add(nextDay);
        for(int i = 0; i < 6; i++)
        {
            Day nDay = new Day();
            nDay.setDate(LocalDate.now().plusDays(i));
            daysOfTheWeek.add(nDay);
        }
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
