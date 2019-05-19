package com.blazejprzyluski.workout_tracker;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MainWindowController {


    @FXML
    private ListView<Workout> listView;

    @FXML
    private BorderPane mainPane;

    @FXML
    private MenuItem editSeries;

    @FXML
    private MenuItem addSeries;

    private ObservableList<Workout> list;

    @FXML
    public void initialize()
    {
        DataManagement.getInstance().open();
        list = DataManagement.getInstance().createWorkouts();
        listView.setItems(list);
        listView.getSelectionModel().selectFirst();
    }

    private void deleteWorkout(Workout w)
    {
        list.remove(w);
        listView.setItems(list);
    }

    //Deleting Workout
    @FXML
    public void handleDelete()
    {
        Workout workoutToDelete = listView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete workout");
        alert.setHeaderText("Delete item: " + workoutToDelete.nameDate());
        alert.setContentText("Are you sure? Press ok to confirm, or cancel to back out");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            int workout_id = DataManagement.getInstance().getWorkoutId(workoutToDelete.getName(),workoutToDelete.getDate().toString());
            deleteWorkout(workoutToDelete);
           DataManagement.getInstance().deleteWorkout(workout_id);
           for(Exercise ex : workoutToDelete.getExercises())
           {
               int exerciseID = DataManagement.getInstance().getExerciseID(ex.getName(),workout_id);
               DataManagement.getInstance().deleteSet(exerciseID);
           }
           DataManagement.getInstance().deleteExercise(workout_id);
        }
    }

    @FXML
    public void deleteWithKey(KeyEvent e)
    {
        if(e.getCode().equals(KeyCode.DELETE))
        {
            handleDelete();
        }
    }

    private void addWorkoutToList(Workout w)
    {
        list.add(w);
        listView.setItems(list);
    }

    //Adding Workout
    @FXML
    public void handleAdd()
    {
        Workout workoutToAdd = new Workout();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Add new Workout");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWorkout.fxml"));
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
            AddWorkoutController controller = fxmlLoader.getController();
            workoutToAdd = controller.addWorkout();
            try {
                DataManagement.getInstance().addWorkoutToDatabase(workoutToAdd.getName(),workoutToAdd.getDate().toString());
            }catch (SQLException e)
            {
                System.out.println("Error while adding to database " + e.getMessage());
            }
            addWorkoutToList(workoutToAdd);
        }
    }

    @FXML
    public void showExercisesDialog()
    {
        Workout w = listView.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Add Series");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createExercisesAndComment.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e)
        {
            System.out.println("Couldn't load the dialog");
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            CreateExercisesAndCommentController controller = fxmlLoader.getController();
            ObservableList<Exercise> e = controller.addExercises();
            int workout_id = DataManagement.getInstance().getWorkoutId(w.getName(),w.getDate().toString());
            for(Exercise ex : e)
            {
                if(!ex.getName().isEmpty())
                {
                    DataManagement.getInstance().createExercise(ex.getName(),workout_id);
                }
                for(Set set : ex.getReps())
                {
                    if(set != null)
                    {
                        DataManagement.getInstance().createSet(set.getKgs(),set.getReps(),DataManagement.getInstance().getExerciseID(ex.getName(), workout_id));
                    }
                }
            }
            w.setExercises(e);
            w.setComment(controller.getComment());
            DataManagement.getInstance().addComment(w.getComment(),workout_id);
            listView.refresh();
        }
    }

    @FXML
    public void handleWorkoutEdit()
    {
        Workout workoutToEdit = listView.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Edit workout");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWorkout.fxml"));
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
            int workout_id = DataManagement.getInstance().getWorkoutId(workoutToEdit.getName(),workoutToEdit.getDate().toString());
            AddWorkoutController controller = fxmlLoader.getController();
            controller.editWorkout(workoutToEdit);
            DataManagement.getInstance().updateWorkout(workoutToEdit.getName(),workoutToEdit.getDate().toString(),workout_id);
            listView.refresh();
        }
    }

    @FXML
    public void handleView(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            Workout workoutToView = listView.getSelectionModel().getSelectedItem();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mainPane.getScene().getWindow());
            dialog.setTitle(workoutToView.getName() + " " + workoutToView.getDate().toString());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("viewSets.fxml"));
            try{
                dialog.getDialogPane().setContent(fxmlLoader.load());
            }catch(IOException ex)
            {
                System.out.println("Couldn't load the dialog");
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

            ViewSetsController controller = fxmlLoader.getController();
            controller.test(workoutToView);

            Optional<ButtonType> result = dialog.showAndWait();

        }
    }

    @FXML
    public void handleContextMenu()
    {
        Workout w = listView.getSelectionModel().getSelectedItem();
        if(!w.getExercises().isEmpty())
        {
            addSeries.setDisable(true);
        }
        else
        {
            addSeries.setDisable(false);
        }
        if(w.getExercises().isEmpty())
        {
            editSeries.setDisable(true);
        }
        else
        {
            editSeries.setDisable(false);
        }
    }

    @FXML
    public void handleSeriesEdit()
    {
        Workout w = listView.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Edit Series");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("createExercisesAndComment.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch(IOException e)
        {
            System.out.println("Couldn't load the dialog");
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        CreateExercisesAndCommentController controller = fxmlLoader.getController();
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {

            controller.editExercises(w);
            listView.refresh();
        }
    }
}
