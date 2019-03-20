package Data.workoutData;

import Data.workoutData.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<Exercise> exercises;
    private LocalDate date;

    public Workout()
    {
        this.exercises = new ArrayList<>();
    }

    public void addExcersise(Exercise exercise)
    {
        if(exercise != null)
        {
            exercises.add(exercise);
        }
    }
}
