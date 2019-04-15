package WorkoutController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
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

    private ObservableList<Workout> list;

    @FXML
    public void initialize()
    {
        DataManagement.getInstance().open();
        list = FXCollections.observableArrayList();
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
           deleteWorkout(workoutToDelete);
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
            w.setExercises(e);
            w.setComment(controller.getComment());
        }
    }

    @FXML
    public void handleEdit()
    {
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
            AddWorkoutController controller = fxmlLoader.getController();
            controller.editWorkout(listView.getSelectionModel().getSelectedItem());
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
}
