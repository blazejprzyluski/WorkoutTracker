package GUI.Data.workoutData;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private List<Exercise> exercises;

    public Workout()
    {

        this.exercises = new ArrayList<>();
        this.name = "";
    }

    public void addExcersise(Exercise exercise)
    {
        if(exercise != null)
        {
            exercises.add(exercise);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
