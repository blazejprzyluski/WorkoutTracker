package WorkoutController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class CreateExercisesAndCommentController {
    @FXML
    private TextField firstExerciseName;

    @FXML
    private TextField secondExerciseName;

    @FXML
    private TextField thirdExerciseName;

    //First exercise kgs
    @FXML
    private TextField setOneFirstKgs;

    @FXML
    private TextField setTwoFirstKgs;

    @FXML
    private TextField setThreeFirstKgs;

    //First exercise reps
    @FXML
    private TextField setOneFirstReps;

    @FXML
    private TextField setTwoFirstReps;

    @FXML
    private TextField setThreeFirstReps;

    //Second exercise kgs
    @FXML
    private TextField setOneSecondKgs;

    @FXML
    private TextField setTwoSecondKgs;

    @FXML
    private TextField setThreeSecondKgs;

    //Second exercise reps

    @FXML
    private TextField setOneSecondReps;

    @FXML
    private TextField setTwoSecondReps;

    @FXML
    private TextField setThreeSecondReps;

    //Third exercise kgs
    @FXML
    private TextField setOneThirdKgs;

    @FXML
    private TextField setTwoThirdKgs;

    @FXML
    private TextField setThreeThirdKgs;

    //Third exercise reps

    @FXML
    private TextField setOneThirdReps;

    @FXML
    private TextField setTwoThirdReps;

    @FXML
    private TextField setThreeThirdReps;

    @FXML
    private TextArea commentArea;

    private int changeToInt(TextField e)
    {
        if(!e.getText().equals(""))
        {
            return Integer.parseInt(e.getText());
        }
        return 0;
    }

    private Set createSet(TextField kgs, TextField reps)
    {
        if(changeToInt(kgs) >= 0 && changeToInt(reps) >= 0)
        {
            Set set1 = new Set(changeToInt(kgs), changeToInt(reps));
            return set1;
        }
        return null;
    }

    private ObservableList<Set> createFirstSetList()
    {
        ObservableList<Set> sets = FXCollections.observableArrayList();
        Set set1 = createSet(setOneFirstKgs,setOneFirstReps);
        Set set2 =  createSet(setTwoFirstKgs,setTwoFirstReps);
        Set set3 = createSet(setThreeFirstKgs,setThreeFirstReps);
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        return sets;
    }

    private ObservableList<Set> createSecondSetList()
    {
        ObservableList<Set> sets = FXCollections.observableArrayList();
        Set set1 = createSet(setOneSecondKgs,setOneSecondReps);
        Set set2 =  createSet(setTwoSecondKgs,setTwoSecondReps);
        Set set3 = createSet(setThreeSecondKgs,setThreeSecondReps);
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        return sets;
    }

    private ObservableList<Set> createThirdSetList()
    {
        ObservableList<Set> sets = FXCollections.observableArrayList();
        Set set1 = createSet(setOneThirdKgs,setOneThirdReps);
        Set set2 =  createSet(setTwoThirdKgs,setTwoThirdReps);
        Set set3 = createSet(setThreeThirdKgs,setThreeThirdReps);
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        return sets;
    }

    private Exercise createFirstExercise()
    {

        Exercise first = new Exercise("");
        if(!firstExerciseName.getText().isEmpty())
        {
            first = new Exercise(firstExerciseName.getText());
        }

        first.addReps(createFirstSetList());

        return first;
    }

    private Exercise createSecondExercise()
    {
        Exercise first = new Exercise("");
        if(!secondExerciseName.getText().isEmpty())
        {
            first = new Exercise(secondExerciseName.getText());
        }

        first.addReps(createSecondSetList());

        return first;

    }

    private Exercise createThirdExercise()
    {
        Exercise first = new Exercise("");
        if(!thirdExerciseName.getText().isEmpty())
        {
            first = new Exercise(thirdExerciseName.getText());
        }

        first.addReps(createThirdSetList());

        return first;
    }

    @FXML
    public ObservableList<Exercise> addExercises()
    {
        ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        Exercise first = createFirstExercise();
        Exercise second = createSecondExercise();
        Exercise third = createThirdExercise();

        if(!first.getName().isBlank())
        {
            exercises.add(first);
        }
        if(!second.getName().isBlank())
        {
            exercises.add(second);
        }
        if(!third.getName().isBlank())
        {
            exercises.add(third);
        }
        return exercises;
    }

    @FXML
    public String getComment()
    {
        return commentArea.getText();
    }

    @FXML
    public void editComment(Workout w)
    {
        int workout_id = DataManagement.getInstance().getWorkoutId(w.getName(),w.getDate().toString());
        if(!w.getComment().equals(commentArea.getText()))
        {
            w.setComment(commentArea.getText());
            DataManagement.getInstance().addComment(commentArea.getText(),workout_id);
        }
    }


    @FXML
    public void editExercises(Workout w)
    {
        ObservableList<Exercise> ex = w.getExercises();
        ObservableList<Exercise> exToCompare = addExercises();
        int workoutID = DataManagement.getInstance().getWorkoutId(w.getName(),w.getDate().toString());
        int exerciseID = 0;
        int setID = 0;


        editComment(w);
        if(ex.size() > exToCompare.size())
        {
            for(int i = 0; i < exToCompare.size();i++)
            {
                exerciseID = DataManagement.getInstance().getExerciseID(ex.get(i).getName(),workoutID);

                if(!ex.get(i).equals(exToCompare.get(i)))
                {
                    ex.get(i).setName(exToCompare.get(i).getName());
                    DataManagement.getInstance().updateExercise(exToCompare.get(i).getName(),exerciseID);
                }
                ObservableList<Set> sets = ex.get(i).getReps();
                ObservableList<Set> setsToCompare = exToCompare.get(i).getReps();

                if(sets.size() > setsToCompare.size())
                {
                    for(int j = 0; j < setsToCompare.size();j++)
                    {
                        if(setsToCompare.get(j) != null && sets.get(j) != null)
                        {
                            if(!sets.get(j).equals(setsToCompare.get(j)))
                            {
                                setID = DataManagement.getInstance().getSetID(sets.get(j).getKgs(),sets.get(j).getReps(),exerciseID);
                                ex.get(i).addReps(setsToCompare);
                                DataManagement.getInstance().updateSet(setsToCompare.get(j).getKgs(),setsToCompare.get(j).getReps(),setID);
                            }
                        }

                    }
                }
                else
                {
                    ex.get(i).addReps(setsToCompare);
                }

            }
        }
        else
        {
            w.setExercises(exToCompare);
            for(Exercise e : ex)
            {
                exerciseID = DataManagement.getInstance().getExerciseID(e.getName(),workoutID);
                DataManagement.getInstance().deleteSet(exerciseID);
                DataManagement.getInstance().deleteExercise(workoutID);
            }

            for(Exercise exDi : w.getExercises())
            {
                DataManagement.getInstance().createExercise(exDi.getName(),workoutID);
                exerciseID = DataManagement.getInstance().getExerciseID(exDi.getName(),workoutID);
                for(Set s : exDi.getReps())
                {
                    DataManagement.getInstance().createSet(s.getKgs(),s.getReps(),exerciseID);
                }
            }
        }

    }


}
