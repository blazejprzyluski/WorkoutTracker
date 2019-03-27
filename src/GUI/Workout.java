package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private ObservableList<Exercise> exercises;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Workout()
    {
        this.exercises = FXCollections.observableArrayList();
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

    public ObservableList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ObservableList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return this.name + this.date.toString();
    }
}
