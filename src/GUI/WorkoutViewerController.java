package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.time.LocalDate;

public class WorkoutViewerController {
    @FXML
    private ListView workouts;

    private ObservableList<Workout> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        Workout w = new Workout();
        w.setDate(LocalDate.now());
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

        w.addExcersise(e);
        w.addExcersise(ew);

        lista.add(w);
        workouts.setItems(lista);
    }
}
