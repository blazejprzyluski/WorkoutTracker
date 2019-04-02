package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainPane;

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

    @FXML
    private ObservableList<Exercise> workouts;

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

        workouts = FXCollections.observableArrayList(e);

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

    @FXML
    public void addExcersise()
    {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Add new Excersise");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addSeries.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e)
        {
            System.out.println("Couldn't load the dialog");
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            AddSeriesController controller = fxmlLoader.getController();
            Exercise e = controller.addExcersise();
            workouts.add(e);
            for(Exercise es : workouts)
            {
                System.out.println(es.getName());
            }
            workoutListView.setItems(workouts);
            workoutListView.refresh();
        }
    }

}
