package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private GridPane mainGridPane;



    private List<Day> daysOfTheWeek = new ArrayList<>();


    public void initialize()
    {
        Monday monday = new Monday();
        Tuesday tuesday = new Tuesday();
        Wednesday wednesday = new Wednesday();
        Thursday thursday = new Thursday();
        Friday friday = new Friday();
        Saturday saturday = new Saturday();
        Sunday sunday = new Sunday();

        ObservableList<Day> daysList = FXCollections.observableArrayList(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        //test tableview



    }

    @FXML
    public void addWorkoutToMonday()
    {

    }

}
