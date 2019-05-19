package com.blazejprzyluski.workout_tracker;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewSetsController {
    @FXML
    private TableView<Exercise> exerciseTableView;

    @FXML
    private TableColumn set1;

    @FXML
    private TableColumn set2;

    @FXML
    private TableColumn set3;

    @FXML
    private TableColumn exerciseName;

    @FXML
    private TextArea commentArea;

    @FXML
    public void initialize()
    {
        commentArea.setEditable(false);
    }

    @FXML
    public void test(Workout w)
    {
        commentArea.setText(w.getComment());

        exerciseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        set1.setCellValueFactory(new PropertyValueFactory<>("set1"));
        set2.setCellValueFactory(new PropertyValueFactory<>("set2"));
        set3.setCellValueFactory(new PropertyValueFactory<>("set3"));

        exerciseTableView.setItems(w.getExercises());
        exerciseTableView.refresh();
    }
}
