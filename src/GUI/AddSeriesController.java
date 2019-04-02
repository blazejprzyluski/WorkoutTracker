package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddSeriesController {

    @FXML
    private TextField excersiseName;

    @FXML
    private TextField firstSetNumber;

    @FXML
    private TextField secondSetNumber;

    @FXML
    private TextField thirdSetNumber;

    @FXML
    private TextField fourthSetNumber;

    @FXML
    private TextField fifthSetNumber;

    @FXML
    private TextField firstSet;

    @FXML
    private TextField secondSet;

    @FXML
    private TextField thirdSet;

    @FXML
    private TextField fourthSet;

    @FXML
    private TextField fifthSet;

    @FXML
    private TextField firstKgs;

    @FXML
    private TextField secondKgs;

    @FXML
    private TextField thirdKgs;

    @FXML
    private TextField fourthKgs;

    @FXML
    private TextField fifthKgs;

    @FXML
    private TextArea commentArea;

    @FXML
    public Exercise addExcersise()
    {
        Exercise exerciseToAdd = new Exercise(excersiseName.getText());
        if(!firstKgs.getText().equals("") && !firstSet.getText().equals("") && !firstSetNumber.getText().equals(""))
        {
            addSet(exerciseToAdd,firstKgs.getText(),firstSet.getText(),firstSetNumber.getText());
        }
        if(!secondKgs.getText().equals("") && !secondSet.getText().equals("") && !secondSetNumber.getText().equals(""))
        {
            addSet(exerciseToAdd,secondKgs.getText(),secondSet.getText(),secondSetNumber.getText());
        }
        if(!thirdKgs.getText().equals("") && !thirdSet.getText().equals("") && !thirdSetNumber.getText().equals(""))
        {
            addSet(exerciseToAdd,thirdKgs.getText(),thirdSet.getText(),thirdSetNumber.getText());
        }
        if(!fourthKgs.getText().equals("") && !fourthSet.getText().equals("") && !fourthSetNumber.getText().equals(""))
        {
            addSet(exerciseToAdd,fourthKgs.getText(),fourthSet.getText(),fourthSetNumber.getText());
        }
        if(!fifthKgs.getText().equals("") && !fifthSet.getText().equals("") && !fifthSetNumber.getText().equals(""))
        {
            addSet(exerciseToAdd,fifthKgs.getText(),fifthSet.getText(),fifthSetNumber.getText());
        }
        if(commentArea.getText().isBlank())
        {
            exerciseToAdd.setComment("");
        }
        else
        {
            exerciseToAdd.setComment(commentArea.getText());
        }


        return exerciseToAdd;
    }

    private void addSet(Exercise exercise, String kgs,String reps,String setNumber)
    {
        exercise.addSeries(Integer.parseInt(kgs),Integer.parseInt(reps),Integer.parseInt(setNumber));
    }

}
