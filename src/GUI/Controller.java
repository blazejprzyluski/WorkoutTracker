package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private ListView<Exercise> workoutListView;

    @FXML
    private TableView<Series> seriesTable;

    @FXML
    private TableColumn setColumn;

    @FXML
    private TableColumn repsColumn;

    @FXML
    private TableColumn kgsColumn;

    @FXML
    private TextArea commentArea;


    public void initialize()
    {

        Exercise e = new Exercise("martwy");
        Exercise ew = new Exercise("pull ups");
        ObservableList<Series> series = FXCollections.observableArrayList();
        ObservableList<Series> series1 = FXCollections.observableArrayList();

        series.add(new Series(50,7,1));
        series.add(new Series(60,9,2));
        series.add(new Series(70,8,3));

        e.setSeries(series);

        series1.add(new Series(60,8,1));
        series1.add(new Series(50,8,2));
        series1.add(new Series(70,8,3));
        ew.setSeries(series1);

        ObservableList<Exercise> workouts = FXCollections.observableArrayList(e,ew);

        setColumn.setCellValueFactory(
                new PropertyValueFactory<Series,Integer>("setNumber")
        );
        repsColumn.setCellValueFactory(
                new PropertyValueFactory<Series,Integer>("reps")
        );
        kgsColumn.setCellValueFactory(
                new PropertyValueFactory<Series,Integer>("kgs")
        );



        workoutListView.setItems(workouts);
        workoutListView.getSelectionModel().selectFirst();
        commentArea.setText(workoutListView.getSelectionModel().getSelectedItem().getComment());
        seriesTable.setItems(workoutListView.getSelectionModel().getSelectedItem().getSeries());
        workoutListView.refresh();


    }

    @FXML
    public void setCommentAreaText()
    {
        String s = workoutListView.getSelectionModel().getSelectedItem().getComment();
        seriesTable.setItems(workoutListView.getSelectionModel().getSelectedItem().getSeries());
        commentArea.setText(s);
    }

}
