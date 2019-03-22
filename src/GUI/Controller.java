package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TableView<Day> daysTable;

    @FXML
    private TableView<Tuesday> tuesdayTable;

    @FXML
    private TableColumn testColumn;

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
        Tuesday tuesday = new Tuesday();
        Wednesday wednesday = new Wednesday();
        Thursday thursday = new Thursday();
        Friday friday = new Friday();
        Saturday saturday = new Saturday();
        Sunday sunday = new Sunday();

        ObservableList<Day> daysList = FXCollections.observableArrayList(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        //test tableview
        ObservableList<Tuesday> tuesdays = FXCollections.observableArrayList(tuesday);



        mondayColumn.setCellValueFactory(
                new PropertyValueFactory<Monday,String>("workoutData")
        );

        tuesdayColumn.setCellValueFactory(
                new PropertyValueFactory<Tuesday,String>("workoutData")
        );

        wendesdayColumn.setCellValueFactory(
                new PropertyValueFactory<Wednesday,String>("workoutData")
        );

        thursdayColumn.setCellValueFactory(
                new PropertyValueFactory<Thursday,String>("workoutData")
        );

        fridayColumn.setCellValueFactory(
                new PropertyValueFactory<Friday,String>("workoutData")
        );

        saturdayColumn.setCellValueFactory(
                new PropertyValueFactory<Saturday,String>("workoutData")
        );

        sundayColumn.setCellValueFactory(
                new PropertyValueFactory<Sunday,String>("workoutData")
        );


        testColumn.setCellValueFactory(
                new PropertyValueFactory<Tuesday,String>("workoutData")
        );


        //test
        tuesday.setWorkoutData("chuj");
        wednesday.setWorkoutData("cipa");
        thursday.setWorkoutData("anal");

        daysTable.setItems(daysList);
        tuesdayTable.setItems(tuesdays);

        //Allows selection of just one cell
        daysTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        daysTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    @FXML
    public void addWorkoutToDay()
    {

        System.out.println(daysTable.getSelectionModel().getSelectedItem().getDate().toString());

    }

}
